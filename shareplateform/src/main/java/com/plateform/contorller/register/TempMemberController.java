package com.plateform.contorller.register;

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
public class TempMemberController {

    //这个控制器负责临时加盟商会做的事情
    //比如：查询当前加盟商所拥有的身份信息，能力信息，进行修改，增加，删除等

    private TempMemberServiceImpl tempMemberService;
    private TempHresServiceImpl tempHresService;
    private TempSresServiceImpl tempSresService;
    private TempTechServiceImpl tempTechService;
    private TransServiceImpl transService;


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

    //1、临时加盟商查看实体信息
    @GetMapping("/member/temp/info")
    public @ResponseBody
    ModelMap getTempMemberInfo(@RequestHeader("token") String token) {
        ModelMap returnData;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        if (((String) permissionValidationMap.get("userRole")).equals("Member")) return ModelMapUtil.init(1,null,"您的用户状态发生变化！");
        //查询
        String memberId = (String) permissionValidationMap.get("userId");
        List<Member> listResult = tempMemberService.selectByMemberNo(memberId);
        if (listResult.isEmpty()) return ModelMapUtil.init(1,null,"该用户信息不存在！");
        returnData = ModelMapUtil.init(0,null,"查询成功！");
        //返回列表数据
        returnData.addAttribute("dataType","Member");
        returnData.addAttribute("data",listResult);
        return returnData;
    }

    //2、临时加盟商修改实体信息
    @PutMapping("/member/temp/info")
    public @ResponseBody
    ModelMap putTempMemberInfo(@RequestHeader("token") String token,@RequestBody Member frontData) {
        ModelMap returnData;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        if (((String) permissionValidationMap.get("userRole")).equals("Member")) return ModelMapUtil.init(1,null,"您的用户状态发生变化！");
        //查询接口
        String memberNo = (String) permissionValidationMap.get("userId");
        List listResult = tempMemberService.selectByMemberNo(memberNo);
        if (listResult.isEmpty()) return ModelMapUtil.init(1,null,"该用户信息不存在！");
        Member result = (Member) listResult.get(0);
        //输入数据格式标准化
        frontData.setId(result.getId());
        frontData.setMemberNo(memberNo);
        //更改数据库
        tempMemberService.updateByPrimaryKey(frontData);
        returnData = ModelMapUtil.init(0,null,"更改成功！");
        return returnData;
    }

    //-------------------------------------------------------------------------------
    //---------------------------加盟商硬资源临时能力接口-------------------------------
    //-------------------------------------------------------------------------------
    
    //3、加盟商查看自己的临时硬资源信息
    @GetMapping("/member/temp/hres")
    public @ResponseBody
    ModelMap getTempMemberHres(
            @RequestHeader("token") String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        //单个查询：传入了id参数，按id查询
        if (null != id) {
            Hres result = tempHresService.selectByPrimaryKey(id);
            //错误提示
            if (null == result) return ModelMapUtil.initError("所查询的硬资源信息不存在！");
            if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.initError("所查询的硬资源信息不可见！");
            //查询成功
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("dataType","Hres");
            data.addAttribute("data",result);
            return data;
        }
        //全部查询：未传入参数，按当前操作用户id查询
        if (page) {
            List<Hres> result = tempHresService.selectByMemberNo(memberNo,pageNum,pageSize);
            PageInfo pageInfo = PageInfo.of(result);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",pageInfo);
            return data;
        } else {
            List<Hres> result = tempHresService.selectByMemberNo(memberNo);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",result);
            return data;
        }
    }

    
    //4、加盟商增加自己的临时硬资源信息
    @PostMapping("/member/temp/hres")
    public @ResponseBody ModelMap postTempMemberHres(@RequestHeader String token , @RequestBody Hres hres) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        //数据有效性验证
        hres.setId(null);
        hres.setMemberNo(memberNo);
        hres.setJoinTime(new Date());
        //能力编号编码
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "C"+formatter.format(new Date());
        hres.setAbilityNo(encode);
        //写入数据库
        tempHresService.insert(hres);
        return ModelMapUtil.init(0,null,"已添加！");
    }

    //5、加盟商修改自己的临时硬资源信息
    @PutMapping("/member/temp/hres")
    public @ResponseBody ModelMap putTempMemberHres(@RequestHeader String token , @RequestBody Hres hres) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        Hres result = tempHresService.selectByPrimaryKey(hres.getId());
        //错误信息
        if (null == result) return ModelMapUtil.initError("所要修改的硬资源信息不存在！");
        if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.initError("所要修改的硬资源信息不可见！");
        //在临时表中修改项目
        hres.setAbilityNo(result.getAbilityNo());
        hres.setJoinTime(result.getJoinTime());
        hres.setMemberNo(memberNo);
        tempHresService.updateByPrimaryKey(hres);
        return ModelMapUtil.init(0,null,"已修改！");
    }

    //6、加盟商删除自己的硬资源信息
    @DeleteMapping("/member/temp/hres")
    public @ResponseBody ModelMap deleteTempMemberHres(@RequestHeader String token , @RequestParam Integer id) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        Hres result = tempHresService.selectByPrimaryKey(id);
        //错误信息
        if (null == result) return ModelMapUtil.init(1,null,"所要删除的信息不存在！");
        if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.init(1,null,"所要删除的信息不可见！");
        //更新数据库
        tempHresService.deleteByPrimaryKey(id);
        return ModelMapUtil.init(0,null,"已删除！");
    }

    //-------------------------------------------------------------------------------
    //---------------------------加盟商软资源临时能力接口-------------------------------
    //-------------------------------------------------------------------------------

    //3、加盟商查看自己的临时软资源信息
    @GetMapping("/member/temp/sres")
    public @ResponseBody
    ModelMap getTempMemberSres(
            @RequestHeader("token") String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        //单个查询：传入了id参数，按id查询
        if (null != id) {
            Sres result = tempSresService.selectByPrimaryKey(id);
            //错误提示
            if (null == result) return ModelMapUtil.initError("所查询的软资源信息不存在！");
            if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.initError("所查询的软资源信息不可见！");
            //查询成功
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("dataType","Sres");
            data.addAttribute("data",result);
            return data;
        }
        //全部查询：未传入参数，按当前操作用户id查询
        if (page) {
            List<Sres> result = tempSresService.selectByMemberNo(memberNo,pageNum,pageSize);
            PageInfo pageInfo = PageInfo.of(result);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",pageInfo);
            return data;
        } else {
            List<Sres> result = tempSresService.selectByMemberNo(memberNo);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",result);
            return data;
        }
    }


    //4、加盟商增加自己的临时软资源信息
    @PostMapping("/member/temp/sres")
    public @ResponseBody ModelMap postTempMemberSres(@RequestHeader String token , @RequestBody Sres sres) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        //数据有效性验证
        sres.setId(null);
        sres.setMemberNo(memberNo);
        sres.setJoinTime(new Date());
        //能力编号编码
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "C"+formatter.format(new Date());
        sres.setAbilityNo(encode);
        //写入数据库
        tempSresService.insert(sres);
        return ModelMapUtil.init(0,null,"已添加！");
    }

    //5、加盟商修改自己的临时软资源信息
    @PutMapping("/member/temp/sres")
    public @ResponseBody ModelMap putTempMemberSres(@RequestHeader String token , @RequestBody Sres sres) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        Sres result = tempSresService.selectByPrimaryKey(sres.getId());
        //错误信息
        if (null == result) return ModelMapUtil.initError("所要修改的软资源信息不存在！");
        if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.initError("所要修改的软资源信息不可见！");
        //在临时表中修改项目
        sres.setAbilityNo(result.getAbilityNo());
        sres.setJoinTime(result.getJoinTime());
        sres.setMemberNo(memberNo);
        tempSresService.updateByPrimaryKey(sres);
        return ModelMapUtil.init(0,null,"已修改！");
    }

    //6、加盟商删除自己的软资源信息
    @DeleteMapping("/member/temp/sres")
    public @ResponseBody ModelMap deleteTempMemberSres(@RequestHeader String token , @RequestParam Integer id) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        Sres result = tempSresService.selectByPrimaryKey(id);
        //错误信息
        if (null == result) return ModelMapUtil.init(1,null,"所要删除的信息不存在！");
        if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.init(1,null,"所要删除的信息不可见！");
        //更新数据库
        tempSresService.deleteByPrimaryKey(id);
        return ModelMapUtil.init(0,null,"已删除！");
    }

    //-------------------------------------------------------------------------------
    //---------------------------加盟商新技术临时能力接口-------------------------------
    //-------------------------------------------------------------------------------

    //3、加盟商查看自己的临时新技术信息
    @GetMapping("/member/temp/tech")
    public @ResponseBody
    ModelMap getTempMemberTech(
            @RequestHeader("token") String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        ModelMap data;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        //单个查询：传入了id参数，按id查询
        if (null != id) {
            Tech result = tempTechService.selectByPrimaryKey(id);
            //错误提示
            if (null == result) return ModelMapUtil.initError("所查询的新技术信息不存在！");
            if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.initError("所查询的新技术信息不可见！");
            //查询成功
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("dataType","Tech");
            data.addAttribute("data",result);
            return data;
        }
        //全部查询：未传入参数，按当前操作用户id查询
        if (page) {
            List<Tech> result = tempTechService.selectByMemberNo(memberNo,pageNum,pageSize);
            PageInfo pageInfo = PageInfo.of(result);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",pageInfo);
            return data;
        } else {
            List<Tech> result = tempTechService.selectByMemberNo(memberNo);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
            data = ModelMapUtil.init(0,null,"查询成功！");
            data.addAttribute("data",result);
            return data;
        }
    }


    //4、加盟商增加自己的临时新技术信息
    @PostMapping("/member/temp/tech")
    public @ResponseBody ModelMap postTempMemberTech(@RequestHeader String token , @RequestBody Tech tech) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        //数据有效性验证
        tech.setId(null);
        tech.setMemberNo(memberNo);
        tech.setSubmitTime(new Date());
        //能力编号编码
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "C"+formatter.format(new Date());
        tech.setAbilityNo(encode);
        //写入数据库
        tempTechService.insert(tech);
        return ModelMapUtil.init(0,null,"已添加！");
    }

    //5、加盟商修改自己的临时新技术信息
    @PutMapping("/member/temp/tech")
    public @ResponseBody ModelMap putTempMemberTech(@RequestHeader String token , @RequestBody Tech tech) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        Tech result = tempTechService.selectByPrimaryKey(tech.getId());
        //错误信息
        if (null == result) return ModelMapUtil.initError("所要修改的新技术信息不存在！");
        if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.initError("所要修改的新技术信息不可见！");
        //在临时表中修改项目
        tech.setAbilityNo(result.getAbilityNo());
        tech.setSubmitTime(result.getSubmitTime());
        tech.setMemberNo(memberNo);
        tempTechService.updateByPrimaryKey(tech);
        return ModelMapUtil.init(0,null,"已修改！");
    }

    //6、加盟商删除自己的新技术信息
    @DeleteMapping("/member/temp/tech")
    public @ResponseBody ModelMap deleteTempMemberTech(@RequestHeader String token , @RequestParam Integer id) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String memberNo = (String) permissionValidationMap.get("userId");
        Tech result = tempTechService.selectByPrimaryKey(id);
        //错误信息
        if (null == result) return ModelMapUtil.init(1,null,"所要删除的信息不存在！");
        if (!result.getMemberNo().equals(memberNo)) return ModelMapUtil.init(1,null,"所要删除的信息不可见！");
        //更新数据库
        tempTechService.deleteByPrimaryKey(id);
        return ModelMapUtil.init(0,null,"已删除！");
    }

    //-------------------------------------------------------------------------------
    //---------------------------临时加盟商查询加盟申请接口-----------------------------
    //-------------------------------------------------------------------------------
    
    
    //5、查询申请状态
    @GetMapping("/member/temp/progress")
    public @ResponseBody
    ModelMap getRegisterProgress(@RequestHeader("token") String token) {
        ModelMap returnData;

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        if (permissionValidationMap.get("userRole").equals("Member")) return ModelMapUtil.init(1,null,"您的用户状态发生变化！");

        String memberId = (String) permissionValidationMap.get("userId");
        List<Trans> listResult = transService.selectByTransObjid(memberId);
        if (listResult.isEmpty()) {
            return ModelMapUtil.init(1,null,"申请状态缺失，请联系管理员！");
        }

        //返回事务数据
        returnData = ModelMapUtil.init(0,null,"查询成功！");
        returnData.addAttribute("data",listResult);
        return returnData;
    }

    //6、重新提交申请
    @PostMapping("/member/temp/reapply")
    public @ResponseBody
    ModelMap reapply(@RequestHeader("token") String token) {

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember","Member"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        if (permissionValidationMap.get("userRole").equals("Member")) return ModelMapUtil.init(1,null,"您的用户状态发生变化！");

        String memberId = (String) permissionValidationMap.get("userId");
        List<Trans> listResult = transService.selectByTransObjid(memberId);
        if (listResult.isEmpty()) {
            return ModelMapUtil.init(1,null,"第一次申请状态异常，请联系管理员！");
        }

        //上次提交并不为已拒绝状态时，返回错误
        Trans result = listResult.get(0);
        if (result.getTransStatus().equals("审理中")) {
            return ModelMapUtil.init(1,null,"请等待管理员审核完成！");
        }

        //重新提交申请
        Trans newTrans = new Trans("加盟申请重新提交",memberId,(String) permissionValidationMap.get("userId"),"审理中");
        transService.insert(newTrans);
        return ModelMapUtil.init(0,null,"提交成功！");
    }



}
