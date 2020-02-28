package com.plateform.contorller.basic;

import com.github.pagehelper.PageInfo;
import com.plateform.entity.*;
import com.plateform.service.implement.*;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MemberBasicController {

    //这个控制器负责加盟商会做的事情
    //比如：查询当前加盟商所拥有的身份信息，能力信息，进行修改，增加，删除等
    //还可以查询进度状态

    private MemberServiceImpl memberService;
    private HresServiceImpl hresService;
    private SresServiceImpl sresService;
    private TechServiceImpl techService;
    private TempMemberServiceImpl tempMemberService;
    private TempHresServiceImpl tempHresService;
    private TempSresServiceImpl tempSresService;
    private TempTechServiceImpl tempTechService;
    private TransServiceImpl transService;


    @Autowired
    public void setMemberService(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    @Autowired
    public void setHresService(HresServiceImpl hresService) {
        this.hresService = hresService;
    }
    
    @Autowired
    public void setSresService(SresServiceImpl sresService) {
        this.sresService = sresService;
    }

    @Autowired
    public void setTechService(TechServiceImpl techService) {
        this.techService = techService;
    }
    
    @Autowired
    public void setTempMemberService(TempMemberServiceImpl tempMemberService) {
        this.tempMemberService = tempMemberService;
    }

    @Autowired
    public void setTempHresService(TempHresServiceImpl tempHresService) {
        this.tempHresService = tempHresService;
    }

    @Autowired
    public void setTempSresService(TempSresServiceImpl tempSresService) {
        this.tempSresService = tempSresService;
    }

    @Autowired
    public void setTempTechService(TempTechServiceImpl tempTechService) {
        this.tempTechService = tempTechService;
    }

    @Autowired
    public void setTransService(TransServiceImpl transService) {
        this.transService = transService;
    }

    //-------------------------------------------------------------------------------
    //---------------------------加盟商实体信息接口-----------------------------------
    //-------------------------------------------------------------------------------




    //1、加盟商查看自己的实体信息
    @GetMapping("/member/info")
    public @ResponseBody
    ModelMap getMemberInfo(@RequestHeader("token") String token) {
        ModelMap returnData;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        List<Member> listResult = memberService.selectByMemberNo(memberId);

        if (listResult.isEmpty()) {
            return ModelMapUtil.init(1,null,"该用户信息不存在！");
        }
        returnData = ModelMapUtil.init(0,null,"查询成功！");
        returnData.addAttribute("data",listResult);
        return returnData;
    }

    //2、加盟商修改实体信息
    @PutMapping("/member/info")
    public @ResponseBody
    ModelMap putMemberInfo(@RequestHeader("token") String token,@RequestBody Member frontData) {
        ModelMap returnData;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        List<Member> listResult = memberService.selectByMemberNo(memberId);
        if (listResult.isEmpty()) {
            return ModelMapUtil.init(1,null,"该用户信息不存在！");
        }
        //输入数据格式标准化
        frontData.setId(listResult.get(0).getId());
        frontData.setMemberNo(memberId);
        //添加到临时数据库
        if (tempMemberService.selectByMemberNo(memberId).isEmpty()) {
            tempMemberService.insert(frontData);
        } else {
            tempMemberService.updateByPrimaryKey(frontData);
        }
        //添加到管理员事务表中
        List<Trans> transResult = transService.selectByTransObjid(memberId);
        if (!transResult.isEmpty() && transResult.get(0).getTransStatus().equals("审理中")) {
            return ModelMapUtil.init(0,null,"提交成功！等待审核中");
        } else {
            Trans newTrans = new Trans("资料修改",memberId,memberId,"审理中");
            transService.insert(newTrans);
            return ModelMapUtil.init(0,null,"提交成功！等待审核中");
        }
    }


    //-------------------------------------------------------------------------------
    //---------------------------加盟商硬资源能力接口-----------------------------------
    //-------------------------------------------------------------------------------
    
    
    //3、加盟商查看自己的硬资源信息
    @GetMapping("/member/hres")
    public @ResponseBody
    ModelMap getMemberHres(
            @RequestHeader("token") String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        //单个查询：传入了id参数，按id查询
        if (null != id) {
            Hres result = hresService.selectByPrimaryKey(id);
            if (null == result) {
                //当查询结果为空时
                return ModelMapUtil.init(1,null,"所查询的硬资源信息不存在！");
            } else {
                //当查询结果不为空时
                if (result.getMemberNo().equals(memberId)) {
                    //查询成功
                    data = ModelMapUtil.init(0,null,"查询成功！");
                    data.addAttribute("data",result);
                    return data;
                } else {
                    //该信息不处于当前操作用户
                    return ModelMapUtil.init(1,null,"所查询的硬资源信息不可见！");
                }
            }
        }

        //全部查询：未传入参数，按当前操作用户id查询
        if (page) {
            List<Hres> result = hresService.selectByMemberNo(memberId,pageNum,pageSize);
            PageInfo pageInfo = PageInfo.of(result);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",pageInfo);
            return data;
        } else {
            List<Hres> result = hresService.selectByMemberNo(memberId);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",result);
            return data;
        }
    }



    //4、加盟商增加自己的硬资源信息
    @PostMapping("/member/hres")
    public @ResponseBody ModelMap postMemberHres(@RequestHeader String token , @RequestBody Hres hres) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        //数据有效性验证
        hres.setId(null);
        hres.setMemberNo(memberId);
        hres.setJoinTime(new Date());
        //能力编号编码
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "C"+formatter.format(new Date());
        hres.setAbilityNo(encode);
        //写入数据库
        tempHresService.insert(hres);
        //向管理员事务表中添加事务
        Trans newTrans = new Trans("能力修改-添加",memberId,encode,"审理中");
        transService.insert(newTrans);
        return ModelMapUtil.init(0,null,"硬资源能力添加已申请！");
    }

    //5、加盟商修改自己的硬资源信息
    @PutMapping("/member/hres")
    public @ResponseBody ModelMap putMemberHres(@RequestHeader String token , @RequestBody Hres hres) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        Hres result = hresService.selectByPrimaryKey(hres.getId());
        //当查询结果为空时
        if (null == result) return ModelMapUtil.init(1,null,"所要修改的硬资源信息不存在！");
        //当查询结果不为空时
        //该信息不处于当前操作用户
        if (!result.getMemberNo().equals(memberId)) return ModelMapUtil.init(1,null,"所要修改的硬资源信息不可见！");
        //在临时表中修改项目
        hres.setAbilityNo(result.getAbilityNo());
        hres.setJoinTime(result.getJoinTime());
        hres.setMemberNo(memberId);
        hres.setId(null);
        List<Hres> result2 = tempHresService.selectByAbilityNo(result.getAbilityNo());
        if (result2.isEmpty()) {
            tempHresService.insert(hres);
        } else {
            hres.setId(result2.get(0).getId());
            tempHresService.updateByPrimaryKey(hres);
        }
        //向管理员事务表中添加修改申请
        List<Trans> transResult = transService.selectByTransObjid(result.getAbilityNo());
        if (!transResult.isEmpty() && transResult.get(0).getTransStatus().equals("审理中")) {
            if (transResult.get(0).getTransType().contains("删除")) {
                return ModelMapUtil.init(0,null,"请等待与该项相关联的操作审理完成！");
            } else {
                return ModelMapUtil.init(0,null,"提交成功！等待审核中");
            }
        } else {
            Trans newTrans = new Trans("能力修改-更改",memberId,result.getAbilityNo(),"审理中");
            transService.insert(newTrans);
            return ModelMapUtil.init(0,null,"提交成功！等待审核中");
        }
    }

    //6、加盟商删除自己的硬资源信息
    @DeleteMapping("/member/hres")
    public @ResponseBody ModelMap deleteMemberHres(@RequestHeader String token , @RequestParam Integer id) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        Hres result = hresService.selectByPrimaryKey(id);
        //当查询结果为空时
        if (null == result) return ModelMapUtil.init(1,null,"所要删除的硬资源信息不存在！");
        //当查询结果不为空时
        //该信息不处于当前操作用户
        if (!result.getMemberNo().equals(memberId)) return ModelMapUtil.init(1,null,"所要删除的硬资源信息不可见！");
        //向管理员事务表中添加删除申请
        List<Trans> transResult = transService.selectByTransObjid(result.getAbilityNo());
        if (!transResult.isEmpty() && transResult.get(0).getTransStatus().equals("审理中")) {
            return ModelMapUtil.init(0,null,"请等待与该项相关联的操作审理完成！");
        } else {
            Trans newTrans = new Trans("能力修改-删除",memberId,result.getAbilityNo(),"审理中");
            transService.insert(newTrans);
            return ModelMapUtil.init(0,null,"提交成功！等待审核中");
        }
    }


    //-------------------------------------------------------------------------------
    //---------------------------加盟商软资源能力接口-----------------------------------
    //-------------------------------------------------------------------------------


    //7、加盟商查看自己的软资源信息
    @GetMapping("/member/sres")
    public @ResponseBody
    ModelMap getMemberSres(
            @RequestHeader("token") String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        //单个查询：传入了id参数，按id查询
        if (null != id) {
            Sres result = sresService.selectByPrimaryKey(id);
            if (null == result) {
                //当查询结果为空时
                return ModelMapUtil.init(1,null,"所查询的软资源信息不存在！");
            } else {
                //当查询结果不为空时
                if (result.getMemberNo().equals(memberId)) {
                    //查询成功
                    data = ModelMapUtil.init(0,null,"查询成功！");
                    data.addAttribute("data",result);
                    return data;
                } else {
                    //该信息不处于当前操作用户
                    return ModelMapUtil.init(1,null,"所查询的软资源信息不可见！");
                }
            }
        }

        //全部查询：未传入参数，按当前操作用户id查询
        if (page) {
            List<Sres> result = sresService.selectByMemberNo(memberId,pageNum,pageSize);
            PageInfo pageInfo = PageInfo.of(result);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",pageInfo);
            return data;
        } else {
            List<Sres> result = sresService.selectByMemberNo(memberId);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",result);
            return data;
        }
    }



    //8、加盟商增加自己的软资源信息
    @PostMapping("/member/sres")
    public @ResponseBody ModelMap postMemberSres(@RequestHeader String token , @RequestBody Sres sres) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        //数据有效性验证
        sres.setId(null);
        sres.setMemberNo(memberId);
        sres.setJoinTime(new Date());
        //能力编号编码
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "D"+formatter.format(new Date());
        sres.setAbilityNo(encode);
        //写入数据库
        tempSresService.insert(sres);
        //向管理员事务表中添加事务
        Trans newTrans = new Trans("能力修改-添加",memberId,encode,"审理中");
        transService.insert(newTrans);
        return ModelMapUtil.init(0,null,"软资源能力添加已申请！");
    }

    //9、加盟商修改自己的软资源信息
    @PutMapping("/member/sres")
    public @ResponseBody ModelMap putMemberSres(@RequestHeader String token , @RequestBody Sres sres) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        Sres result = sresService.selectByPrimaryKey(sres.getId());
        //当查询结果为空时
        if (null == result) return ModelMapUtil.init(1,null,"所要修改的软资源信息不存在！");
        //当查询结果不为空时
        //该信息不处于当前操作用户
        if (!result.getMemberNo().equals(memberId)) return ModelMapUtil.init(1,null,"所要修改的软资源信息不可见！");
        //在临时表中修改项目
        sres.setAbilityNo(result.getAbilityNo());
        sres.setJoinTime(result.getJoinTime());
        sres.setMemberNo(memberId);
        sres.setId(null);
        List<Sres> result2 = tempSresService.selectByAbilityNo(result.getAbilityNo());
        if (result2.isEmpty()) {
            tempSresService.insert(sres);
        } else {
            sres.setId(result2.get(0).getId());
            tempSresService.updateByPrimaryKey(sres);
        }
        //向管理员事务表中添加修改申请
        List<Trans> transResult = transService.selectByTransObjid(result.getAbilityNo());
        if (!transResult.isEmpty() && transResult.get(0).getTransStatus().equals("审理中")) {
            if (transResult.get(0).getTransType().contains("删除")) {
                return ModelMapUtil.init(0,null,"请等待与该项相关联的操作审理完成！");
            } else {
                return ModelMapUtil.init(0,null,"提交成功！等待审核中");
            }
        } else {
            Trans newTrans = new Trans("能力修改-更改",memberId,result.getAbilityNo(),"审理中");
            transService.insert(newTrans);
            return ModelMapUtil.init(0,null,"提交成功！等待审核中");
        }
    }

    //10、加盟商删除自己的软资源信息
    @DeleteMapping("/member/sres")
    public @ResponseBody ModelMap deleteMemberSres(@RequestHeader String token , @RequestParam Integer id) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        Sres result = sresService.selectByPrimaryKey(id);
        //当查询结果为空时
        if (null == result) return ModelMapUtil.init(1,null,"所要删除的软资源信息不存在！");
        //当查询结果不为空时
        //该信息不处于当前操作用户
        if (!result.getMemberNo().equals(memberId)) return ModelMapUtil.init(1,null,"所要删除的软资源信息不可见！");
        //向管理员事务表中添加删除申请
        List<Trans> transResult = transService.selectByTransObjid(result.getAbilityNo());
        if (!transResult.isEmpty() && transResult.get(0).getTransStatus().equals("审理中")) {
            return ModelMapUtil.init(0,null,"请等待与该项相关联的操作审理完成！");
        } else {
            Trans newTrans = new Trans("能力修改-删除",memberId,result.getAbilityNo(),"审理中");
            transService.insert(newTrans);
            return ModelMapUtil.init(0,null,"提交成功！等待审核中");
        }
    }

    //-------------------------------------------------------------------------------
    //---------------------------加盟商新技术能力接口-----------------------------------
    //-------------------------------------------------------------------------------


    //11、加盟商查看自己的新技术信息
    @GetMapping("/member/tech")
    public @ResponseBody
    ModelMap getMemberTech(
            @RequestHeader("token") String token,
            @RequestParam(required = false) Integer id,            
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        //单个查询：传入了id参数，按id查询
        if (null != id) {
            Tech result = techService.selectByPrimaryKey(id);
            if (null == result) {
                //当查询结果为空时
                return ModelMapUtil.init(1,null,"所查询的新技术信息不存在！");
            } else {
                //当查询结果不为空时
                if (result.getMemberNo().equals(memberId)) {
                    //查询成功
                    data = ModelMapUtil.init(0,null,"查询成功！");
                    data.addAttribute("data",result);
                    return data;
                } else {
                    //该信息不处于当前操作用户
                    return ModelMapUtil.init(1,null,"所查询的新技术信息不可见！");
                }
            }
        }

        //全部查询：未传入参数，按当前操作用户id查询
        if (page) {
            List<Tech> result = techService.selectByMemberNo(memberId,pageNum,pageSize);
            PageInfo pageInfo = PageInfo.of(result);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",pageInfo);
            return data;
        } else {
            List<Tech> result = techService.selectByMemberNo(memberId);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",result);
            return data;
        }
    }



    //12、加盟商增加自己的新技术信息
    @PostMapping("/member/tech")
    public @ResponseBody ModelMap postMemberTech(@RequestHeader String token , @RequestBody Tech tech) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        //数据有效性验证
        tech.setId(null);
        tech.setMemberNo(memberId);
        tech.setSubmitTime(new Date());
        //能力编号编码
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "E"+formatter.format(new Date());
        tech.setAbilityNo(encode);
        //写入数据库
        tempTechService.insert(tech);
        //向管理员事务表中添加事务
        Trans newTrans = new Trans("能力修改-添加",memberId,encode,"审理中");
        transService.insert(newTrans);
        return ModelMapUtil.init(0,null,"新技术能力添加已申请！");
    }

    //13、加盟商修改自己的新技术信息
    @PutMapping("/member/tech")
    public @ResponseBody ModelMap putMemberTech(@RequestHeader String token , @RequestBody Tech tech) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        Tech result = techService.selectByPrimaryKey(tech.getId());
        //当查询结果为空时
        if (null == result) return ModelMapUtil.init(1,null,"所要修改的新技术信息不存在！");
        //当查询结果不为空时
        //该信息不处于当前操作用户
        if (!result.getMemberNo().equals(memberId)) return ModelMapUtil.init(1,null,"所要修改的新技术信息不可见！");
        //在临时表中修改项目
        tech.setAbilityNo(result.getAbilityNo());
        tech.setSubmitTime(result.getSubmitTime());
        tech.setMemberNo(memberId);
        tech.setId(null);
        List<Tech> result2 = tempTechService.selectByAbilityNo(result.getAbilityNo());
        if (result2.isEmpty()) {
            tempTechService.insert(tech);
        } else {
            tech.setId(result2.get(0).getId());
            tempTechService.updateByPrimaryKey(tech);
        }
        //向管理员事务表中添加修改申请
        List<Trans> transResult = transService.selectByTransObjid(result.getAbilityNo());
        if (!transResult.isEmpty() && transResult.get(0).getTransStatus().equals("审理中")) {
            if (transResult.get(0).getTransType().contains("删除")) {
                return ModelMapUtil.init(0,null,"请等待与该项相关联的操作审理完成！");
            } else {
                return ModelMapUtil.init(0,null,"提交成功！等待审核中");
            }
        } else {
            Trans newTrans = new Trans("能力修改-更改",memberId,result.getAbilityNo(),"审理中");
            transService.insert(newTrans);
            return ModelMapUtil.init(0,null,"提交成功！等待审核中");
        }
    }

    //14、加盟商删除自己的新技术信息
    @DeleteMapping("/member/tech")
    public @ResponseBody ModelMap deleteMemberTech(@RequestHeader String token , @RequestParam Integer id) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        Tech result = techService.selectByPrimaryKey(id);
        //当查询结果为空时
        if (null == result) return ModelMapUtil.init(1,null,"所要删除的新技术信息不存在！");
        //当查询结果不为空时
        //该信息不处于当前操作用户
        if (!result.getMemberNo().equals(memberId)) return ModelMapUtil.init(1,null,"所要删除的新技术信息不可见！");
        //向管理员事务表中添加删除申请
        List<Trans> transResult = transService.selectByTransObjid(result.getAbilityNo());
        if (!transResult.isEmpty() && transResult.get(0).getTransStatus().equals("审理中")) {
            return ModelMapUtil.init(0,null,"请等待与该项相关联的操作审理完成！");
        } else {
            Trans newTrans = new Trans("能力修改-删除",memberId,result.getAbilityNo(),"审理中");
            transService.insert(newTrans);
            return ModelMapUtil.init(0,null,"提交成功！等待审核中");
        }
    }


    //-------------------------------------------------------------------------------
    //---------------------------加盟商事务查询接口-----------------------------------
    //-------------------------------------------------------------------------------
    
    
    //15、加盟商查询事务进度
    @GetMapping("/member/progress")
    public @ResponseBody
    ModelMap getMemberProgress(
            @RequestHeader("token") String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        //单个查询：传入了id参数，按id查询
        if (null != id) {
            Trans result = transService.selectByPrimaryKey(id);
            if (null == result) {
                //当查询结果为空时
                return ModelMapUtil.init(1,null,"所查询的事务信息不存在！");
            } else {
                //当查询结果不为空时
                if (result.getApplicantNo().equals(memberId)) {
                    //查询成功
                    data = ModelMapUtil.init(0,null,"查询成功！");
                    data.addAttribute("data",result);
                    return data;
                } else {
                    //该信息不处于当前操作用户
                    return ModelMapUtil.init(1,null,"所查询的事务信息不可见！");
                }
            }
        }

        //全部查询：未传入参数，按当前操作用户id查询
        if (page) {
            List<Trans> result = transService.selectByApplicantNo(memberId,pageNum,pageSize);
            PageInfo pageInfo = PageInfo.of(result);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",pageInfo);
            return data;
        } else {
            List<Trans> result = transService.selectByApplicantNo(memberId);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",result);
            return data;
        }
    }

    //16、加盟商撤销事务
    @DeleteMapping("/member/progress")
    public @ResponseBody
    ModelMap deleteMemberProgress(@RequestHeader("token") String token,@RequestParam Integer id) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberId = (String) permissionValidationMap.get("userId");
        Trans result = transService.selectByPrimaryKey(id);
        if (null == result) {
            return ModelMapUtil.init(1,null,"找不到该事务！");
        }

        //当该事物不属于该加盟商申请
        if (!result.getApplicantNo().equals(memberId)) {
            return ModelMapUtil.init(1,null,"该事务不可见！");
        }

        //事务状态设置为已撤销
        result.setTransStatus("已撤销");
        transService.updateByPrimaryKey(result);
        return ModelMapUtil.init(0,null,"撤销成功！");
    }
    
}
