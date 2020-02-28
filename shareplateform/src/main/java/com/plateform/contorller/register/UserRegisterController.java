package com.plateform.contorller.register;

import com.plateform.entity.*;
import com.plateform.service.implement.*;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserRegisterController {

    private LoginServiceImpl loginService;
    private UserServiceImpl userService;

    @Autowired
    public void setLoginService(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
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
        login.setRole("tempUser");
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
    public @ResponseBody ModelMap register1(@RequestBody User user , @RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempUser"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //验证用户注册阶段
        String userName = (String) permissionValidationMap.get("userName");
        int stage = User.getRegisterStage(userName);
        if (1 != stage) {
            return ModelMapUtil.init(1,null,"访问错误，您处于注册阶段的第"+stage+"阶段");
        }

        //数据有效性验证
        user.setId(null);
        user.setRegisterTime(new Date());

        //信息编码，返回编号
        Login result = loginService.selectByName(userName).get(0);
        String encode = result.getId().toString();
        result.setLoginuser(encode);
        user.setUserNo(encode);

        //完成注册，将login表中的角色改为正式的User
        result.setRole("User");
        loginService.updateByPrimaryKey(result);

        //插入数据库
        userService.insert(user);

        ModelMap data = ModelMapUtil.init(0,null,"新增临时用户信息成功！");
        data.addAttribute("token", TokenUtil.createJWT(result.getLoginuser(),result.getName(),"login", 8 * 3600000L));
        return data;
    }

    //注册阶段查询接口
    @GetMapping("/register/stage")
    public @ResponseBody ModelMap getStage(@RequestHeader String token) {

        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempUser","User"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        //验证用户注册阶段
        String userName = (String) permissionValidationMap.get("userName");
        int stage = User.getRegisterStage(userName);

        ModelMap data = ModelMapUtil.init(0,null,"您处于注册阶段的第"+stage+"阶段");
        data.addAttribute("data",stage);
        return data;
    }

    //用户返回stage1时端需，后要将会员的实体信息返回给前端
    @GetMapping("/register/backPreStage")
    public @ResponseBody ModelMap backPreStage(@RequestHeader String token){
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempUser"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;

        String userNo = (String) permissionValidationMap.get("userId");
        User user = userService.selectByUserNo(userNo).get(0);

        ModelMap modelMap  = new ModelMap();
        modelMap.addAttribute("data",user);

        return modelMap;
    }
}
