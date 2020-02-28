package com.plateform.contorller.register;

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
@RequestMapping("/member")
public class MemberRegisterController {

    private LoginServiceImpl loginService;
    private TempMemberServiceImpl tempMemberService;
    private TempHresServiceImpl tempHresService;
    private TempSresServiceImpl tempSresService;
    private TempTechServiceImpl tempTechService;
    private TransServiceImpl transService;

    @Autowired
    public void setLoginService(LoginServiceImpl loginService) {
        this.loginService = loginService;
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
    //---------------------------stage0注册接口---------------------------------------
    //-------------------------------------------------------------------------------

    @PostMapping("/register/stage0")
    public @ResponseBody ModelMap register0(@RequestBody Login login) {

        //数据有效性验证



        //数据库重复性检查
        List<Login> result=loginService.selectByName(login.getName());
        //当数据库中存在该用户名时，返回-1，意为用户名已存在
        if (!result.isEmpty()) return ModelMapUtil.init(1,null,"注册失败，已存在用户名！");

        //用户登录角色设置为stage1
        login.setRole("tempMember");
        login.setLoginuser("");
        loginService.insert(login);

        //注册成功反馈
        ModelMap data = ModelMapUtil.init(0,null,"注册成功！");
        data.addAttribute("token", TokenUtil.createJWT("",login.getName(),"register", 8 * 3600000L));
        return data;
    }

    //-------------------------------------------------------------------------------
    //---------------------------stage1注册接口---------------------------------------
    //-------------------------------------------------------------------------------

    @PostMapping("/register/stage1")
    public @ResponseBody ModelMap register1(@RequestBody Member member, @RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //验证用户注册阶段
        String userName = (String) permissionValidationMap.get("userName");
        /*int stage = Member.getRegisterStage(userName);
        if (1 != stage) {
            ModelMap data = ModelMapUtil.init(1,null,"访问错误，您处于注册阶段的第"+stage+"阶段");
            data.addAttribute("stage",stage);
            return data;
        }*/

        //数据有效性验证
        member.setId(null);
        member.setJoinTime(new Date());


        //信息编码，返回编号
        Login result = loginService.selectByName(userName).get(0);
        String encode = result.getId().toString();
        result.setLoginuser(encode);
        loginService.updateByPrimaryKey(result);
        member.setMemberNo(encode);

        //插入数据库
        tempMemberService.insert(member);
        //临时加盟商已经完成三个阶段的注册信息填写，向管理员事务表中添加事务
        Trans newTrans = new Trans("加盟申请",encode,encode,"审理中");
        transService.insert(newTrans);
        ModelMap data = ModelMapUtil.init(0,null,"新增临时加盟商信息成功！");
        data.addAttribute("token", TokenUtil.createJWT(result.getLoginuser(),result.getName(),"login", 8 * 3600000L));
        return data;
    }

    //-------------------------------------------------------------------------------
    //---------------------------stage2注册接口---------------------------------------
    //-------------------------------------------------------------------------------

    //硬资源添加接口
    /*@PostMapping("/register/stage2/hres")*/
    /*public @ResponseBody ModelMap register2hres(@RequestBody Hres hres, @RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //验证用册户注阶段
        String userName = (String) permissionValidationMap.get("userName");
        String userId = (String) permissionValidationMap.get("userId");
        int stage = Member.getRegisterStage(userName);
        if (2 != stage) {
            return ModelMapUtil.init(1,null,"访问错误，您处于注册阶段的第"+stage+"阶段");
        }

        //数据有效性验证
        hres.setId(null);
        hres.setMemberNo(userId);
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "C"+formatter.format(new Date());
        hres.setAbilityNo(encode);
        hres.setJoinTime(new Date());


        //插入数据库
        tempHresService.insert(hres);
        //return ModelMapUtil.init(0,null,"新增临时加盟商硬资源能力信息成功！");

        //临时加盟商已经完成三个阶段的注册信息填写，向管理员事务表中添加事务
        Trans newTrans = new Trans("加盟申请",userId,userId,"审理中");
        transService.insert(newTrans);
        return ModelMapUtil.init(0,null,"新增临时加盟商硬资源能力信息成功！");

    }

    //软资源添加接口
    @PostMapping("/register/stage2/sres")
    public @ResponseBody ModelMap register2sres(@RequestBody Sres sres, @RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //验证用户注册阶段
        String userName = (String) permissionValidationMap.get("userName");
        String userId = (String) permissionValidationMap.get("userId");
        int stage = Member.getRegisterStage(userName);
        if (2 != stage) {
            return ModelMapUtil.init(1,null,"访问错误，您处于注册阶段的第"+stage+"阶段");
        }

        //数据有效性验证
        sres.setId(null);
        sres.setMemberNo(userId);
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "D"+formatter.format(new Date());
        sres.setAbilityNo(encode);
        sres.setJoinTime(new Date());

        //插入数据库
        tempSresService.insert(sres);
        //return ModelMapUtil.init(0,null,"新增临时加盟商硬资源能力信息成功！");

        //临时加盟商已经完成三个阶段的注册信息填写，向管理员事务表中添加事务
        Trans newTrans = new Trans("加盟申请",userId,userId,"审理中");
        transService.insert(newTrans);
        return ModelMapUtil.init(0,null,"新增临时加盟商软资源能力信息成功！");

    }

    //新技术添加接口
    @PostMapping("/register/stage2/tech")
    public @ResponseBody ModelMap register2tech(@RequestBody Tech tech, @RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //验证用户注册阶段
        String userName = (String) permissionValidationMap.get("userName");
        String userId = (String) permissionValidationMap.get("userId");
        int stage = Member.getRegisterStage(userName);
        if (2 != stage) {
            return ModelMapUtil.init(1,null,"访问错误，您处于注册阶段的第"+stage+"阶段");
        }

        //数据有效性验证
        tech.setId(null);
        tech.setMemberNo(userId);
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        String encode = "E"+formatter.format(new Date());
        tech.setAbilityNo(encode);
        tech.setSubmitTime(new Date());

        //插入数据库
        tempTechService.insert(tech);
        //return ModelMapUtil.init(0,null,"新增临时加盟商硬资源能力信息成功！");

        //临时加盟商已经完成三个阶段的注册信息填写，向管理员事务表中添加事务
        Trans newTrans = new Trans("加盟申请",userId,userId,"审理中");
        transService.insert(newTrans);
        return ModelMapUtil.init(0,null,"新增临时加盟商新技术能力信息成功！");

    }*/

    //注册阶段查询接口
    @GetMapping("/register/stage")
    public @ResponseBody ModelMap getStage(@RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //验证用户注册阶段
        String userName = (String) permissionValidationMap.get("userName");
        int stage = Member.getRegisterStage(userName);

        ModelMap data = ModelMapUtil.init(0,null,"您处于注册阶段的第"+stage+"阶段");
        data.addAttribute("data",stage);
        return data;
    }

    //从stage2返回stage1需要将会员的实体信息返回给前端
    //从stage3返回stage2需要将会员的三张能力
    @GetMapping("/register/backPreStage")
    public @ResponseBody ModelMap backPreStage(@RequestHeader String token, @RequestParam int type) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        String memberNo = (String) permissionValidationMap.get("userId");
        ModelMap modelMap  = ModelMapUtil.init(0,null,"查询成功");

        if(type == 1) {   //表示前端要求返回的是加盟商的临时实体信息
            List<Member> member = tempMemberService.selectByMemberNo(memberNo);
            modelMap.addAttribute("data",member);
        }
        else if(type == 2) {   //表示前端要求返回的是加盟商的三张资源信息表（硬资源，软资源，技术资源）
            List<Hres> hres = tempHresService.selectByMemberNo(memberNo);
            List<Sres> sres = tempSresService.selectByMemberNo(memberNo);
            List<Tech> tech = tempTechService.selectByMemberNo(memberNo);

            modelMap.addAttribute("hres",hres);
            modelMap.addAttribute("sres",sres);
            modelMap.addAttribute("tech",tech);
        }

        return modelMap;
    }
}
