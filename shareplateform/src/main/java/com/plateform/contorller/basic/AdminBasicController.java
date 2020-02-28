package com.plateform.contorller.basic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plateform.entity.*;
import com.plateform.service.implement.*;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AdminBasicController {


    private HresServiceImpl hresServiceImpl;
    private SresServiceImpl sresServiceImpl;
    private TechServiceImpl techServiceImpl;
    private MemberServiceImpl memberServiceImpl;
    private UserServiceImpl userServiceImpl;
    private TransServiceImpl transServiceImpl;
    private TempMemberServiceImpl tempMemberServiceImpl;
    private TempHresServiceImpl tempHresServiceImpl;
    private TempSresServiceImpl tempSresServiceImpl;
    private TempTechServiceImpl tempTechServiceImpl;
    private LoginServiceImpl loginServiceImpl;
    private TempMemberPictureImpl tempMemberPictureImpl;
    private MemberPictureImpl memberPictureImpl;


    @Autowired
    public void setHresServiceImpl(HresServiceImpl hresServiceImpl) {
        this.hresServiceImpl = hresServiceImpl;
    }

    @Autowired
    public void setSresServiceImpl(SresServiceImpl sresServiceImpl) {
        this.sresServiceImpl = sresServiceImpl;
    }

    @Autowired
    public void setTechServiceImpl(TechServiceImpl techServiceImpl) {
        this.techServiceImpl = techServiceImpl;
    }

    @Autowired
    public void setMemberServiceImpl(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }


    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Autowired
    public void setTransServiceImpl(TransServiceImpl transServiceImpl) {
        this.transServiceImpl = transServiceImpl;
    }

    @Autowired
    public void setTempMemberServiceImpl(TempMemberServiceImpl tempMemberServiceImpl) {
        this.tempMemberServiceImpl = tempMemberServiceImpl;
    }

    @Autowired
    public void setTempHresServiceImpl(TempHresServiceImpl tempHresServiceImpl) {
        this.tempHresServiceImpl = tempHresServiceImpl;
    }

    @Autowired
    public void setTempSresServiceImpl(TempSresServiceImpl tempSresServiceImpl) {
        this.tempSresServiceImpl = tempSresServiceImpl;
    }

    @Autowired
    public void setTempTechServiceImpl(TempTechServiceImpl tempTechServiceImpl) {
        this.tempTechServiceImpl = tempTechServiceImpl;
    }

    @Autowired
    public void setLoginServiceImpl(LoginServiceImpl loginServiceImpl) {
        this.loginServiceImpl = loginServiceImpl;
    }

    //图片
    @Autowired
    public void setTempSavePicture(TempMemberPictureImpl tempMemberPictureImpl) {
        this.tempMemberPictureImpl = tempMemberPictureImpl;
    }

    @Autowired
    public void setTempSavePicture(MemberPictureImpl memberPictureImpl) {
        this.memberPictureImpl = memberPictureImpl;
    }


    //-------------------------------------------------------------------------------
    //---------------------------管理员数据库接口-----------------------------------
    //-------------------------------------------------------------------------------

    //1、管理员查看加盟商的实体信息
    @GetMapping(value = "/manage/dao/member")
    public @ResponseBody
    ModelMap getManageDaoMember(
            @RequestHeader String token,
            @RequestParam(required = false) String memberNo,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //单个查询，查询与memberNo所对应的加盟商信息
        //没传入参数时查询所有
        if (null != memberNo && 0 != memberNo.length()) {
            List result = memberServiceImpl.selectByMemberNo(memberNo);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有找到查询的用户");
            ModelMap data = ModelMapUtil.init(0, null, "查询成功");
            data.addAttribute("data", result);
            return data;
        } else {
            if (page) {
                List<Member> result = memberServiceImpl.selectAll(pageNum,pageSize);
                PageInfo pageInfo = PageInfo.of(result);
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",pageInfo);
                return data;
            } else {
                List<Member> result = memberServiceImpl.selectAll();
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",result);
                return data;
            }
        }
    }


    //2、管理员查看某加盟商正式表中的所有信息
    @GetMapping("/selectMemberAllInformation")
    @CrossOrigin
    public @ResponseBody ModelMap selectMemberAllInformation(@RequestParam String memberNo, @RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap= TokenUtil.validatePermission(token,new String[]{"Admin"});
        if(permissionValidationMap instanceof ModelMap) return (ModelMap)permissionValidationMap;

        //根据加盟商编号查询与该加盟商相关的所有信息
        List<Member> member = memberServiceImpl.selectByMemberNo(memberNo);
        List<Hres> hres = hresServiceImpl.selectByMemberNo(memberNo);
        List<Sres> sres = sresServiceImpl.selectByMemberNo(memberNo);
        List<Tech> tech = techServiceImpl.selectByMemberNo(memberNo);

        ModelMap modelMap  = ModelMapUtil.init(0,null,"查询成功");

        modelMap.addAttribute("member",member);
        modelMap.addAttribute("hres",hres);
        modelMap.addAttribute("sres",sres);
        modelMap.addAttribute("tech",tech);
        return modelMap;
    }

    //3、管理員查询一个加盟商临时表中的所有信息
    @GetMapping("/selectMemberAllTempInformation")
    public @ResponseBody ModelMap selectMemberAllTempInformation(@RequestParam String memberNo,@RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap= TokenUtil.validatePermission(token,new String[]{"Admin"});
        if(permissionValidationMap instanceof ModelMap) return (ModelMap)permissionValidationMap;

        //根据加盟商编号从临时表中查询与该加盟商相关的所有信息
        List<Member> member = tempMemberServiceImpl.selectByMemberNo(memberNo);
        List<Hres> hres = tempHresServiceImpl.selectByMemberNo(memberNo);
        List<Sres> sres = tempSresServiceImpl.selectByMemberNo(memberNo);
        List<Tech> tech = tempTechServiceImpl.selectByMemberNo(memberNo);

        ModelMap modelMap  = ModelMapUtil.init(0,null,"查询成功");

        modelMap.addAttribute("member",member);
        modelMap.addAttribute("hres",hres);
        modelMap.addAttribute("sres",sres);
        modelMap.addAttribute("tech",tech);

        return modelMap;
    }

    //管理员查看所有待加盟的加盟商的实体信息
    @GetMapping("/selectAllTempMemberInformation")
    public @ResponseBody ModelMap selectAllTempMemberInformation
    (@RequestHeader String token, @RequestParam int pageNum) {

        //通过token验证身份
        Map permissionValidationMap= TokenUtil.validatePermission(token,new String[]{"Admin"});
        if(permissionValidationMap instanceof ModelMap) return (ModelMap)permissionValidationMap;


        //根据加盟商编号查询与该加盟商相关的所有信息
        PageHelper.startPage(pageNum,10);
        List<Member> member = tempMemberServiceImpl.selectAll();

        if(member.isEmpty()) {
            return ModelMapUtil.init(0,null,"无新申请加盟商");
        }
        ModelMap modelMap  = ModelMapUtil.init(0,null,"查询成功");
        //返回总页数
        PageInfo page = new PageInfo(member);
        modelMap.addAttribute("totalPages", page.getPages());

        //返回会员信息
        modelMap.addAttribute("data",member);

        return modelMap;
    }
   /* private TransServiceImpl transService;
    @Autowired
    public void setTempMemberService(TempMemberServiceImpl tempMemberService) {
        this.tempMemberService = tempMemberService;
    }
    @Autowired
    public void setTransService(TransServiceImpl transService) {
        this.transService = transService;
    }
    //加盟商未通过时，管理员将事务表中的状态设为已拒绝
    @PostMapping(value = "/refuse")
    public @ResponseBody
    ModelMap putTempMemberInfo(@RequestHeader("token") String token,@RequestParam Integer transId){
        ModelMap returnData;
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        Trans result = transService.selectByPrimaryKey(transId);
        result.setTransStatus("已拒绝");
        transService.updateByPrimaryKey(result);
        return ModelMapUtil.init(0,null,"已拒绝！");

    }*/

   //管理员查看用户的实体信息
    @GetMapping("/manage/dao/user")
    public @ResponseBody ModelMap getManageDaoUser(
            @RequestHeader String token,
            @RequestParam(required = false) String userNo,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize

    ){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //单个查询，查询与userNo所对应的加盟商信息
        //没传入参数时查询所有
        if (null != userNo && 0 != userNo.length()) {
            List result = userServiceImpl.selectByUserNo(userNo);
            //当查询数据为空时
            if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有找到查询的用户");
            ModelMap data = ModelMapUtil.init(0, null, "查询成功");
            data.addAttribute("data", result);
            return data;
        } else {
            if (page) {
                List<User> result = userServiceImpl.selectAll(pageNum,pageSize);
                PageInfo pageInfo = PageInfo.of(result);
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",pageInfo);
                return data;
            } else {
                List<User> result = userServiceImpl.selectAll();
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",result);
                return data;
            }
        }
    }



    //-------------------------------------------------------------------------------
    //---------------------------管理员事务管理接口-----------------------------------
    //-------------------------------------------------------------------------------

    //1、管理员查看所有事务
    @GetMapping(value = "/manage/trans")
    public @ResponseBody
    ModelMap getManageTrans(
            @RequestHeader String token,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false,defaultValue = "true") Boolean page,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //单个查询，查询与主键id所对应的事务信息
        //没传入参数时查询所有
        if (null != id) {
            Trans oneResult = transServiceImpl.selectByPrimaryKey(id);
            //当查询数据为空时
            if (null == oneResult) return ModelMapUtil.init(1, null, "没有找到查询的事务");
            ModelMap data = ModelMapUtil.init(0, null, "查询成功");
            List<Trans> list = new ArrayList<>();
            list.add(oneResult);
            data.addAttribute("data",  list);
            return data;
        } else {
            if (page) {
                List<Trans> result = transServiceImpl.selectAll(pageNum,pageSize);
                PageInfo pageInfo = PageInfo.of(result);
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",pageInfo);
                return data;
            } else {
                List<Trans> result = transServiceImpl.selectAll();
                //当查询数据为空时
                if (result.isEmpty()) return ModelMapUtil.init(1, null, "没有数据");
                ModelMap data = ModelMapUtil.init(0,null,"查询成功！");
                data.addAttribute("data",result);
                return data;
            }
        }
    }

    //2、管理员根据事务类型查看相关信息
    @GetMapping(value = "/manage/trans/detail")
    public @ResponseBody
    ModelMap getManageTransDetail(@RequestHeader String token,@RequestParam() Integer id) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //单个查询，查询与主键id所对应的事务信息
        Trans oneResult = transServiceImpl.selectByPrimaryKey(id);
        //当查询数据为空时
        if (null == oneResult) return ModelMapUtil.init(1, null, "没有找到查询的事务");
        //根据当前的事务查找不同的表，返回相关的事务
        String objid = oneResult.getTransObjid();
        String transType = oneResult.getTransType();
        String dataType;
        List oldResult,newResult;
        ModelMap modelMap = ModelMapUtil.init(0,null,"查询成功");
        modelMap.addAttribute("operateType",transType);
        switch (transType) {
            //对于临时加盟商加盟申请的事务，返回该加盟商的一系列信息
            case "加盟申请":
            case "加盟申请重新提交":
                //此时事务表objid对应于加盟商的id
                List<Member> member = tempMemberServiceImpl.selectByMemberNo(objid);
                List<Hres> hres = tempHresServiceImpl.selectByMemberNo(objid);
                List<Sres> sres = tempSresServiceImpl.selectByMemberNo(objid);
                List<Tech> tech = tempTechServiceImpl.selectByMemberNo(objid);
                ModelMap memberJoinData = new ModelMap();
                memberJoinData.addAttribute("member",member);
                memberJoinData.addAttribute("hres",hres);
                memberJoinData.addAttribute("sres",sres);
                memberJoinData.addAttribute("tech",tech);
                modelMap.addAttribute("dataType","mixed");
                modelMap.addAttribute("data",memberJoinData);
                return modelMap;
            //对于加盟商修改自己的资料
            case "资料修改":
                //此时事务表objid对应于临时加盟商的id
                //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                oldResult = memberServiceImpl.selectByMemberNo(objid);
                if (oldResult.isEmpty()) return ModelMapUtil.initError("不存在旧信息！");
                //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                newResult = tempMemberServiceImpl.selectByMemberNo(objid);
                if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                dataType = "member";
                ModelMap memberChangeData = new ModelMap();
                memberChangeData.addAttribute("old",oldResult);
                memberChangeData.addAttribute("new",newResult);
                modelMap.addAttribute("dataType",dataType);
                modelMap.addAttribute("data",memberChangeData);
                return modelMap;
            //对于加盟商修改能力，返回要修改的能力信息以及以前的能力
            case "能力修改-添加":
            case "能力修改-更改":
            case "能力修改-删除":
                //此时事务表objid对应于能力的id
                //首先根据能力编号来判断是什么种类的能力
                switch (objid.substring(0,1)) {
                    case "C":
                        //当能力是硬资源时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = hresServiceImpl.selectByAbilityNo(objid);
                        if (!"能力修改-添加".equals(transType) && oldResult.isEmpty())
                            return ModelMapUtil.initError("不存在旧信息！");
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempHresServiceImpl.selectByAbilityNo(objid);
                        if (!"能力修改-删除".equals(transType) && newResult.isEmpty())
                            return ModelMapUtil.initError("不存在新信息！");
                        dataType = "hres";
                        ModelMap hresChangeData = new ModelMap();
                        hresChangeData.addAttribute("old",oldResult);
                        hresChangeData.addAttribute("new",newResult);
                        modelMap.addAttribute("dataType",dataType);
                        modelMap.addAttribute("data",hresChangeData);
                        return modelMap;
                    case "D":
                        //当能力是软资源时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = sresServiceImpl.selectByAbilityNo(objid);
                        if (!"能力修改-添加".equals(transType) && oldResult.isEmpty())
                            return ModelMapUtil.initError("不存在旧信息！");
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempSresServiceImpl.selectByAbilityNo(objid);
                        if (!"能力修改-删除".equals(transType) && newResult.isEmpty())
                            return ModelMapUtil.initError("不存在新信息！");
                        dataType = "sres";
                        ModelMap sresChangeData = new ModelMap();
                        sresChangeData.addAttribute("old",oldResult);
                        sresChangeData.addAttribute("new",newResult);
                        modelMap.addAttribute("dataType",dataType);
                        modelMap.addAttribute("data",sresChangeData);
                        return modelMap;
                    case "E":
                        //当能力是新技术时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = techServiceImpl.selectByAbilityNo(objid);
                        if (!"能力修改-添加".equals(transType) && oldResult.isEmpty())
                            return ModelMapUtil.initError("不存在旧信息！");
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempTechServiceImpl.selectByAbilityNo(objid);
                        if (!"能力修改-删除".equals(transType) && newResult.isEmpty())
                            return ModelMapUtil.initError("不存在新信息！");
                        dataType = "tech";
                        ModelMap techChangeData = new ModelMap();
                        techChangeData.addAttribute("old",oldResult);
                        techChangeData.addAttribute("new",newResult);
                        modelMap.addAttribute("dataType",dataType);
                        modelMap.addAttribute("data",techChangeData);
                        return modelMap;
                    default:
                        return ModelMapUtil.initError("编号异常");
                }

            default:
                return ModelMapUtil.init(1,null,"该功能还尚未实现");
        }
    }

    ////2.1 管理员对加盟商的实体信息修改的同意与拒绝接口
    //@PostMapping(value = "/manage/member/accept")
    //public @ResponseBody
    //ModelMap getManageMemberAccept(@RequestHeader String token,@RequestParam Integer transId) {
    //    //通过token验证身份
    //    Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Admin"});
    //    if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
    //
    //    //单个查询，查询与主键id所对应的事务信息
    //    Trans trans = transServiceImpl.selectByPrimaryKey(transId);
    //    //当查询数据为空时
    //    if (null == trans) return ModelMapUtil.initError("没有找到查询的事务");
    //    //当该项目并没有在审理中这个状态时
    //    if (!"审理中".equals(trans.getTransStatus())) return ModelMapUtil.initError("事务状态异常");
    //    //查找对应的加盟会员临时表信息，并将其填入实体表中，删除该临时表条目
    //    String memberId = trans.getTransObjid();
    //    List<Member> tempMemberData = tempMemberServiceImpl.selectByMemberNo(memberId);
    //    if (null == tempMemberData) return ModelMapUtil.init(1, null, "没有找到新的实体信息");
    //    List<Member> memberData = memberServiceImpl.selectByMemberNo(memberId);
    //    if (null == memberData) return ModelMapUtil.init(1, null, "没有找到旧的实体信息");
    //    Member member = tempMemberData.get(0);
    //    Integer tempDataid = member.getId();
    //    member.setId(memberData.get(0).getId());
    //    memberServiceImpl.updateByPrimaryKey(member);
    //    tempMemberServiceImpl.deleteByPrimaryKey(tempDataid);
    //    //修改事务表，并返回成功
    //    trans.setTransStatus("已通过");
    //    transServiceImpl.updateByPrimaryKey(trans);
    //    return ModelMapUtil.init(0, null, "已通过");
    //}
    //
    //@PostMapping(value = "/manage/member/refuse")
    //public @ResponseBody
    //ModelMap getManageMemberRefuse(@RequestHeader String token,@RequestParam Integer transId,@RequestParam String refuseReason) {
    //    //通过token验证身份
    //    Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Admin"});
    //    if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
    //
    //    //单个查询，查询与主键id所对应的事务信息
    //    Trans trans = transServiceImpl.selectByPrimaryKey(transId);
    //    //当查询数据为空时
    //    if (null == trans) return ModelMapUtil.initError("没有找到查询的事务");
    //    //当该项目并没有在审理中这个状态时
    //    if (!"审理中".equals(trans.getTransStatus())) return ModelMapUtil.initError("事务状态异常");
    //    //查找对应的加盟会员临时表信息，删除该临时表条目
    //    String memberId = trans.getTransObjid();
    //    List<Member> tempMemberData = tempMemberServiceImpl.selectByMemberNo(memberId);
    //    if (null == tempMemberData) return ModelMapUtil.init(1, null, "没有找到新的实体信息");
    //    tempMemberServiceImpl.deleteByPrimaryKey(tempMemberData.get(0).getId());
    //    //修改事务表，并返回成功
    //    trans.setTransStatus("已拒绝");
    //    trans.setRemark("refuseReason");
    //    transServiceImpl.updateByPrimaryKey(trans);
    //    return ModelMapUtil.init(0, null, "已拒绝");
    //}

    //2.2、管理员对加盟商的能力信息修改的同意与拒绝接口
    @PostMapping(value = "/manage/trans/accept")
    public @ResponseBody
    ModelMap postManageTransAccept(@RequestHeader String token,@RequestParam Integer id) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //单个查询，查询与主键id所对应的事务信息
        Trans trans = transServiceImpl.selectByPrimaryKey(id);
        //当查询数据为空时
        if (null == trans) return ModelMapUtil.init(1, null, "没有找到查询的事务");
        //当该项目并没有在审理中这个状态时
        if (!"审理中".equals(trans.getTransStatus())) return ModelMapUtil.initError("事务状态异常");
        //根据当前的事务查找不同的表，返回相关的事务
        String objid = trans.getTransObjid();
        String transType = trans.getTransType();
        String dataType;
        List oldResult,newResult;
        Integer tempDataid;
        ModelMap modelMap = ModelMapUtil.init(0,null,"查询成功");
        switch (transType) {
            //对于临时加盟商加盟申请的事务，将加盟商的各项临时表信息加入到正式表中，并删除临时表信息
            case "加盟申请":
            case "加盟申请重新提交":
                //修改加盟商类型为正式会员Member
                List<Login> login = loginServiceImpl.selectByLoginuser(objid);
                if (login.isEmpty())  return ModelMapUtil.initError("不存在该用户的登录信息");
                Login newLogin = login.get(0);
                newLogin.setRole("Member");
                loginServiceImpl.updateByPrimaryKey(newLogin);
                //将加盟商临时表中的信息全部更新到正式表中
                Object temp;
                List<Member> tempMembers = tempMemberServiceImpl.selectByMemberNo(objid);
                while (!tempMembers.isEmpty()) {
                    temp = tempMembers.get(0);
                    tempDataid = ((Member) temp).getId();
                    //当正式表中已经出现该项目时，改为更新
                    newResult = memberServiceImpl.selectByMemberNo(((Member) temp).getMemberNo());
                    if (newResult.isEmpty()) {
                        ((Member) temp).setId(null);
                        memberServiceImpl.insert(((Member) temp));
                    } else {
                        ((Member) temp).setId(((Member) newResult.get(0)).getId());
                        memberServiceImpl.updateByPrimaryKey(((Member) temp));
                    }
                    //删除临时表中的信息
                    tempMemberServiceImpl.deleteByPrimaryKey(tempDataid);
                    //循环条件设置
                    tempMembers = tempMemberServiceImpl.selectByMemberNo(objid);
                }
                List<Hres> tempHress = tempHresServiceImpl.selectByMemberNo(objid);
                while (!tempHress.isEmpty()) {
                    temp = tempHress.get(0);
                    tempDataid = ((Hres) temp).getId();
                    //当正式表中已经出现该项目时，改为更新
                    newResult = hresServiceImpl.selectByAbilityNo(((Hres) temp).getAbilityNo());
                    if (newResult.isEmpty()) {
                        ((Hres) temp).setId(null);
                        hresServiceImpl.insert(((Hres) temp));
                    } else {
                        ((Hres) temp).setId(((Hres) newResult.get(0)).getId());
                        hresServiceImpl.updateByPrimaryKey(((Hres) temp));
                    }
                    //删除临时表中的信息
                    tempHresServiceImpl.deleteByPrimaryKey(tempDataid);
                    //循环条件设置
                    tempHress = tempHresServiceImpl.selectByMemberNo(objid);
                }
                List<Sres> tempSress = tempSresServiceImpl.selectByMemberNo(objid);
                while (!tempSress.isEmpty()) {
                    temp = tempSress.get(0);
                    tempDataid = ((Sres) temp).getId();
                    //当正式表中已经出现该项目时，改为更新
                    newResult = sresServiceImpl.selectByAbilityNo(((Sres) temp).getAbilityNo());
                    if (newResult.isEmpty()) {
                        ((Sres) temp).setId(null);
                        sresServiceImpl.insert(((Sres) temp));
                    } else {
                        ((Sres) temp).setId(((Sres) newResult.get(0)).getId());
                        sresServiceImpl.updateByPrimaryKey(((Sres) temp));
                    }
                    //删除临时表中的信息
                    tempSresServiceImpl.deleteByPrimaryKey(tempDataid);
                    //循环条件设置
                    tempSress = tempSresServiceImpl.selectByMemberNo(objid);
                }
                List<Tech> tempTechs = tempTechServiceImpl.selectByMemberNo(objid);
                while (!tempTechs.isEmpty()) {
                    temp = tempTechs.get(0);
                    tempDataid = ((Tech) temp).getId();
                    //当正式表中已经出现该项目时，改为更新
                    newResult = techServiceImpl.selectByAbilityNo(((Tech) temp).getAbilityNo());
                    if (newResult.isEmpty()) {
                        ((Tech) temp).setId(null);
                        techServiceImpl.insert(((Tech) temp));
                    } else {
                        ((Tech) temp).setId(((Tech) newResult.get(0)).getId());
                        techServiceImpl.updateByPrimaryKey(((Tech) temp));
                    }
                    //删除临时表中的信息
                    tempTechServiceImpl.deleteByPrimaryKey(tempDataid);
                    //循环条件设置
                    tempTechs = tempTechServiceImpl.selectByMemberNo(objid);
                }
                //修改事务表，并返回成功
                trans.setHandleTime(new Date());
                trans.setHandlerId((String) permissionValidationMap.get("userId"));
                trans.setTransStatus("已通过");
                transServiceImpl.updateByPrimaryKey(trans);
                return ModelMapUtil.init(0, null, "已通过");
            //对于加盟商修改自己的资料
            case "资料修改":
                //此时事务表objid对应于加盟商的id
                //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                oldResult = memberServiceImpl.selectByMemberNo(objid);
                if (oldResult.isEmpty()) return ModelMapUtil.initError("不存在旧信息！");
                //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                newResult = tempMemberServiceImpl.selectByMemberNo(objid);
                if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                //将新数据插入数据库
                Member member = (Member) newResult.get(0);
                tempDataid = member.getId();
                member.setId(((Member) oldResult.get(0)).getId());
                memberServiceImpl.updateByPrimaryKey(member);
                //删除临时表中的信息
                tempMemberServiceImpl.deleteByPrimaryKey(tempDataid);
                //修改事务表，并返回成功
                trans.setHandleTime(new Date());
                trans.setHandlerId((String) permissionValidationMap.get("userId"));
                trans.setTransStatus("已通过");
                transServiceImpl.updateByPrimaryKey(trans);
                return ModelMapUtil.init(0, null, "已通过");
            //对于加盟商修改能力，返回要修改的能力信息以及以前的能力
            case "能力修改-添加":
                //此时事务表objid对应于能力的id
                //首先根据能力编号来判断是什么种类的能力
                switch (objid.substring(0,1)) {
                    case "C":
                        //当能力是硬资源时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempHresServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //将新数据插入数据库
                        Hres hres = (Hres) newResult.get(0);
                        tempDataid = hres.getId();
                        hres.setId(null);
                        hresServiceImpl.insert(hres);
                        //删除临时表中的信息
                        tempHresServiceImpl.deleteByPrimaryKey(tempDataid);
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    case "D":
                        //当能力是软资源时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempSresServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //将新数据插入数据库
                        Sres sres = (Sres) newResult.get(0);
                        tempDataid = sres.getId();
                        sres.setId(null);
                        sresServiceImpl.insert(sres);
                        //删除临时表中的信息
                        tempSresServiceImpl.deleteByPrimaryKey(tempDataid);
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    case "E":
                        //当能力是新技术时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempTechServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //将新数据插入数据库
                        Tech tech = (Tech) newResult.get(0);
                        tempDataid = tech.getId();
                        tech.setId(null);
                        techServiceImpl.insert(tech);
                        //删除临时表中的信息
                        tempTechServiceImpl.deleteByPrimaryKey(tempDataid);
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    default:
                        return ModelMapUtil.initError("编号异常");
                }
            case "能力修改-更改":
                //此时事务表objid对应于能力的id
                //首先根据能力编号来判断是什么种类的能力
                switch (objid.substring(0,1)) {
                    case "C":
                        //当能力是硬资源时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = hresServiceImpl.selectByAbilityNo(objid);
                        if (oldResult.isEmpty()) return ModelMapUtil.initError("不存在旧信息！");
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempHresServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //将新数据插入数据库
                        Hres hres = (Hres) newResult.get(0);
                        tempDataid = hres.getId();
                        hres.setId(((Hres) oldResult.get(0)).getId());
                        hresServiceImpl.updateByPrimaryKey(hres);
                        //删除临时表中的信息
                        tempHresServiceImpl.deleteByPrimaryKey(tempDataid);
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    case "D":
                        //当能力是软资源时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = sresServiceImpl.selectByAbilityNo(objid);
                        if (oldResult.isEmpty()) return ModelMapUtil.initError("不存在旧信息！");
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempSresServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //将新数据插入数据库
                        Sres sres = (Sres) newResult.get(0);
                        tempDataid = sres.getId();
                        sres.setId(((Sres) oldResult.get(0)).getId());
                        sresServiceImpl.updateByPrimaryKey(sres);
                        //删除临时表中的信息
                        tempSresServiceImpl.deleteByPrimaryKey(tempDataid);
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    case "E":
                        //当能力是新技术时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = techServiceImpl.selectByAbilityNo(objid);
                        if (oldResult.isEmpty()) return ModelMapUtil.initError("不存在旧信息！");
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempTechServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //将新数据插入数据库
                        Tech tech = (Tech) newResult.get(0);
                        tempDataid = tech.getId();
                        tech.setId(((Tech) oldResult.get(0)).getId());
                        techServiceImpl.updateByPrimaryKey(tech);
                        //删除临时表中的信息
                        tempTechServiceImpl.deleteByPrimaryKey(tempDataid);
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    default:
                        return ModelMapUtil.initError("编号异常");
                }
            case "能力修改-删除":
                //此时事务表objid对应于能力的id
                //首先根据能力编号来判断是什么种类的能力
                switch (objid.substring(0,1)) {
                    case "C":
                        //当能力是硬资源时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = hresServiceImpl.selectByAbilityNo(objid);
                        if (oldResult.isEmpty()) return ModelMapUtil.initError("不存在旧信息！");
                        //删除正式表中的能力
                        hresServiceImpl.deleteByPrimaryKey(((Hres) oldResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    case "D":
                        //当能力是软资源时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = sresServiceImpl.selectByAbilityNo(objid);
                        if (oldResult.isEmpty()) return ModelMapUtil.initError("不存在旧信息！");
                        //删除正式表中的能力
                        sresServiceImpl.deleteByPrimaryKey(((Sres) oldResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    case "E":
                        //当能力是新技术时
                        //检索旧信息，当除了新增状态之外不存在旧信息时返回错误
                        oldResult = techServiceImpl.selectByAbilityNo(objid);
                        if (oldResult.isEmpty()) return ModelMapUtil.initError("不存在旧信息！");
                        //删除正式表中的能力
                        techServiceImpl.deleteByPrimaryKey(((Tech) oldResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已通过");
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已通过");
                    default:
                        return ModelMapUtil.initError("编号异常");
                }
            default:
                return ModelMapUtil.init(1,null,"该功能还尚未实现");
        }
    }

    @PostMapping(value = "/manage/trans/refuse")
    public @ResponseBody
    ModelMap postManageTransRefuse(@RequestHeader String token,@RequestParam Integer id,@RequestParam String refuseReason) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //单个查询，查询与主键id所对应的事务信息
        Trans trans = transServiceImpl.selectByPrimaryKey(id);
        //当查询数据为空时
        if (null == trans) return ModelMapUtil.init(1, null, "没有找到查询的事务");
        //当该项目并没有在审理中这个状态时
        if (!"审理中".equals(trans.getTransStatus())) return ModelMapUtil.initError("事务状态异常");
        //根据当前的事务查找不同的表，返回相关的事务
        String objid = trans.getTransObjid();
        String transType = trans.getTransType();
        List newResult;
        switch (transType) {
            //对于临时加盟商加盟申请的事务，返回该加盟商的一系列信息
            case "加盟申请":
            case "加盟申请重新提交":
                //此时事务表objid对应于加盟商的id
                //修改事务表，并返回成功
                trans.setHandleTime(new Date());
                trans.setHandlerId((String) permissionValidationMap.get("userId"));
                trans.setTransStatus("已拒绝");
                trans.setRemark(refuseReason);
                transServiceImpl.updateByPrimaryKey(trans);
                return ModelMapUtil.init(0, null, "已拒绝");
            //对于加盟商修改自己的资料
            case "资料修改":
                //此时事务表objid对应于加盟商的id
                //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                newResult = tempMemberServiceImpl.selectByMemberNo(objid);
                if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                //删除临时表中的信息
                tempMemberServiceImpl.deleteByPrimaryKey(((Member) newResult.get(0)).getId());
                //修改事务表，并返回成功
                trans.setHandleTime(new Date());
                trans.setHandlerId((String) permissionValidationMap.get("userId"));
                trans.setTransStatus("已拒绝");
                trans.setRemark(refuseReason);
                transServiceImpl.updateByPrimaryKey(trans);
                return ModelMapUtil.init(0, null, "已拒绝");
            //对于加盟商修改能力，返回要修改的能力信息以及以前的能力
            case "能力修改-添加":
                //此时事务表objid对应于能力的id
                //首先根据能力编号来判断是什么种类的能力
                switch (objid.substring(0,1)) {
                    case "C":
                        //当能力是硬资源时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempHresServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //删除临时表中的信息
                        tempHresServiceImpl.deleteByPrimaryKey(((Hres) newResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    case "D":
                        //当能力是软资源时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempSresServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //删除临时表中的信息
                        tempSresServiceImpl.deleteByPrimaryKey(((Sres) newResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    case "E":
                        //当能力是新技术时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempTechServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //删除临时表中的信息
                        tempTechServiceImpl.deleteByPrimaryKey(((Tech) newResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    default:
                        return ModelMapUtil.initError("编号异常");
                }
            case "能力修改-更改":
                //此时事务表objid对应于能力的id
                //首先根据能力编号来判断是什么种类的能力
                switch (objid.substring(0,1)) {
                    case "C":
                        //当能力是硬资源时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempHresServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //删除临时表中的信息
                        tempHresServiceImpl.deleteByPrimaryKey(((Hres) newResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    case "D":
                        //当能力是软资源时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempSresServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //删除临时表中的信息
                        tempSresServiceImpl.deleteByPrimaryKey(((Sres) newResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    case "E":
                        //当能力是新技术时
                        //检索新信息，当除了删除状态之外不存在旧信息时返回错误
                        newResult = tempTechServiceImpl.selectByAbilityNo(objid);
                        if (newResult.isEmpty()) return ModelMapUtil.initError("不存在新信息！");
                        //删除临时表中的信息
                        tempTechServiceImpl.deleteByPrimaryKey(((Tech) newResult.get(0)).getId());
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    default:
                        return ModelMapUtil.initError("编号异常");
                }
            case "能力修改-删除":
                //此时事务表objid对应于能力的id
                //首先根据能力编号来判断是什么种类的能力
                switch (objid.substring(0,1)) {
                    case "C":
                        //当能力是硬资源时
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    case "D":
                        //当能力是软资源时
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    case "E":
                        //当能力是新技术时
                        //修改事务表，并返回成功
                        trans.setHandleTime(new Date());
                        trans.setHandlerId((String) permissionValidationMap.get("userId"));
                        trans.setTransStatus("已拒绝");
                        trans.setRemark(refuseReason);
                        transServiceImpl.updateByPrimaryKey(trans);
                        return ModelMapUtil.init(0, null, "已拒绝");
                    default:
                        return ModelMapUtil.initError("编号异常");
                }
            default:
                return ModelMapUtil.init(1,null,"该功能还尚未实现");
        }
    }



    //2.3、管理员拒绝加盟商
    //将加盟商的信息移动到临时表中，设置加盟商角色为临时加盟商，返回事务为加盟申请，被拒绝装台
    @DeleteMapping(value = "/manage/member/refuse")
    public @ResponseBody
    ModelMap getManageMemberRefuse(@RequestHeader String token,@RequestParam String memberNo,@RequestParam String refuseReason) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token, new String[]{"Admin"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //将加盟商的信息移动到临时表中
        Integer tempId;
        //实体信息
        List<Member> member = memberServiceImpl.selectByMemberNo(memberNo);
        if (member.isEmpty()) return ModelMapUtil.initError("没有找到实体信息");
        List<Member> tempmember = tempMemberServiceImpl.selectByMemberNo(memberNo);
        while (!tempmember.isEmpty()) {
            tempMemberServiceImpl.deleteByPrimaryKey(tempmember.get(0).getId());
            tempmember = tempMemberServiceImpl.selectByMemberNo(memberNo);
        }
        Member tranmember = member.get(0);
        tempId = tranmember.getId();
        tranmember.setId(null);
        tempMemberServiceImpl.insert(tranmember);
        memberServiceImpl.deleteByPrimaryKey(tempId);
        //硬资源信息
        List<Hres> hres = hresServiceImpl.selectByMemberNo(memberNo);
        List<Hres> temphres = tempHresServiceImpl.selectByMemberNo(memberNo);
        while (!temphres.isEmpty()) {
            tempHresServiceImpl.deleteByPrimaryKey(temphres.get(0).getId());
            temphres = tempHresServiceImpl.selectByMemberNo(memberNo);
        }
        while (!hres.isEmpty()) {
            Hres tranhres = hres.get(0);
            tempId = tranhres.getId();
            tranhres.setId(null);
            tempHresServiceImpl.insert(tranhres);
            hresServiceImpl.deleteByPrimaryKey(tempId);
            hres = hresServiceImpl.selectByMemberNo(memberNo);
        }
        //软资源信息
        List<Sres> sres = sresServiceImpl.selectByMemberNo(memberNo);
        List<Sres> tempsres = tempSresServiceImpl.selectByMemberNo(memberNo);
        while (!tempsres.isEmpty()) {
            tempSresServiceImpl.deleteByPrimaryKey(tempsres.get(0).getId());
            tempsres = tempSresServiceImpl.selectByMemberNo(memberNo);
        }
        while (!sres.isEmpty()) {
            Sres transres = sres.get(0);
            tempId = transres.getId();
            transres.setId(null);
            tempSresServiceImpl.insert(transres);
            sresServiceImpl.deleteByPrimaryKey(tempId);
            sres = sresServiceImpl.selectByMemberNo(memberNo);
        }
        //新技术信息
        List<Tech> tech = techServiceImpl.selectByMemberNo(memberNo);
        List<Tech> temptech = tempTechServiceImpl.selectByMemberNo(memberNo);
        while (!temptech.isEmpty()) {
            tempTechServiceImpl.deleteByPrimaryKey(temptech.get(0).getId());
            temptech = tempTechServiceImpl.selectByMemberNo(memberNo);
        }
        while (!tech.isEmpty()) {
            Tech trantech = tech.get(0);
            tempId = trantech.getId();
            trantech.setId(null);
            tempTechServiceImpl.insert(trantech);
            techServiceImpl.deleteByPrimaryKey(tempId);
            tech = techServiceImpl.selectByMemberNo(memberNo);
        }
        //图片
        List<MemberPicture> picture = memberPictureImpl.selectByMemberNo(memberNo);
        List<MemberPicture> temppicture = tempMemberPictureImpl.selectByMemberNo(memberNo);
        while (!temppicture.isEmpty()) {
            tempMemberPictureImpl.deleteInfor(temppicture.get(0));
            temppicture = tempMemberPictureImpl.selectByMemberNo(memberNo);
        }
        while (!picture.isEmpty()) {
            MemberPicture tranpicture = picture.get(0);
            tranpicture.setId(null);
            tempMemberPictureImpl.savePicture(tranpicture);
            memberPictureImpl.deleteInfor(tranpicture);
            picture = memberPictureImpl.selectByMemberNo(memberNo);
        }

        //将加盟商角色设置为临时加盟商，并向事务表中添加事务
        List<Login> logins = loginServiceImpl.selectByLoginuser(memberNo);
        if (logins.isEmpty()) return ModelMapUtil.initError("未找到该加盟商的登录信息");
        Login login = logins.get(0);
        login.setRole("tempMember");
        loginServiceImpl.updateByPrimaryKey(login);
        Trans newTrans = new Trans();
        newTrans.setTransType("加盟商降级");
        newTrans.setTransObjid(memberNo);
        newTrans.setRemark(refuseReason);
        newTrans.setApplyTime(new Date());
        newTrans.setHandleTime(new Date());
        newTrans.setTransStatus("已执行");
        newTrans.setHandlerId((String) permissionValidationMap.get("userId"));
        newTrans.setApplicantNo((String) permissionValidationMap.get("userId"));
        transServiceImpl.insert(newTrans);
        return ModelMapUtil.init(0,null,"已执行");
    }
}
