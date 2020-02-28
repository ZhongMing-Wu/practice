package com.plateform.contorller;


import com.plateform.domain.PasswordUpdated;
import com.plateform.entity.Login;
import com.plateform.entity.Member;
import com.plateform.entity.User;
import com.plateform.service.implement.LoginServiceImpl;
import com.plateform.util.DaoUtil;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    //自动注入服务实例
    @Autowired
    private LoginServiceImpl loginService;


    //-------------------------------------------------------------------------------
    //---------------------------基本数据库功能---------------------------------------
    //-------------------------------------------------------------------------------



    //增加一条用户登录信息
    @PostMapping("/logindata")
    public @ResponseBody ModelMap addLogin(@RequestBody Login record){
        ModelMap data = new ModelMap();
        Login result = loginService.selectByPrimaryKey(record.getId());
        if (null != result){
            data.addAttribute("code",1);
            data.addAttribute("msg","该用户已存在");
        }else {
            loginService.insert(record);
            data.addAttribute("code",0);
            data.addAttribute("msg","插入成功！");
        }
        return data;
    }

    //删除一个用户登录信息
    @DeleteMapping("/logindata")
    public @ResponseBody ModelMap deleteLogin(@RequestParam int id){
        ModelMap data = new ModelMap();
        int result= loginService.deleteByPrimaryKey(id);
        if (1 == result){
            data.addAttribute("code",0);
            data.addAttribute("msg","删除成功");
        } else {
            data.addAttribute("code",1);
            data.addAttribute("msg","找不到该用户");
        }
        return data;
    }

    //更改一个用户登录信息
    @PutMapping("/logindata")
    public @ResponseBody ModelMap updateByPrimaryKey(@RequestBody Login record){
        ModelMap data = new ModelMap();
        int result= loginService.updateByPrimaryKey(record);
        if (1 == result){
            data.addAttribute("code",0);
            data.addAttribute("msg","更改成功");
        } else {
            data.addAttribute("code",1);
            data.addAttribute("msg","找不到该用户");
        }
        return data;
    }

    //查询用户登录信息
    @GetMapping("/logindata")
    public @ResponseBody ModelMap selectLogin(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String loginuser,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String role
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ModelMap data;
        List<Login> result;

        //主键查询
        if (null != id){
            result = new ArrayList<>();
            result.add(loginService.selectByPrimaryKey(id));
            data = DaoUtil.checkJsonDatalist(result);
            return data;
        }

        //字符串查询
        Login query = new Login();
        List<String> fieids = DaoUtil.formatNewObject(query);
        List<String> values =new ArrayList<>();
        values.add(loginuser);
        values.add(name);
        values.add(password);
        values.add(role);


        if (DaoUtil.queryByString(fieids,values,query)){
            result = loginService.selectByString(query);
            data = DaoUtil.checkJsonDatalist(result);
            return data;
        }

        result = loginService.selectAll();
        data = DaoUtil.checkJsonDatalist(result);
        return data;
    }


    //-------------------------------------------------------------------------------
    //------------------------------自定义功能----------------------------------------
    //-------------------------------------------------------------------------------

     //loadAccount登录接口
    @PostMapping("/login")
    public @ResponseBody ModelMap loadAccount(@RequestBody Login login) {
        Login result = loginService.LoadAccount(login);
        ModelMap data = new ModelMap();

        //当用户不存在
        if (null == result){
            data.addAttribute("code",1);
            data.addAttribute("status",200);
            data.addAttribute("msg","用户不存在！");
            return data;
        }

        //当密码错误
        if (!result.getPassword().equals(login.getPassword())){
            data.addAttribute("code",1);
            data.addAttribute("status",200);
            data.addAttribute("msg","密码错误！");
            return data;
        }

        result.setPassword("");
        data.addAttribute("code",0);
        data.addAttribute("status",200);
        //该用户未完成全部信息注册
        if ( 0 == result.getLoginuser().length()){
            data.addAttribute("msg","请填写用户信息！");
        } else {
            data.addAttribute("msg","登录成功！");
        }
        data.addAttribute("data", result);
        data.addAttribute("token", TokenUtil.createJWT(result.getLoginuser(),result.getName(),"login", 8 * 3600000L));
        //当用户为临时用户时，会返回当前的注册状态
        if (result.getRole().equals("tempMember")) {
            data.addAttribute("registerStage", Member.getRegisterStage(result.getName()));
        }
        if (result.getRole().equals("tempUser")) {
            System.out.println("打印用户名称");
            System.out.println(result.getName());
            data.addAttribute("registerStage", User.getRegisterStage(result.getName()));
        }

        return data;
    }


    @PostMapping("/password")
    public @ResponseBody ModelMap changePassword(@RequestBody PasswordUpdated passwordUpdated,@RequestHeader String token) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,null);
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        if (! permissionValidationMap.get("userName").equals(passwordUpdated.getUserName())) {
            ModelMapUtil.init(1,null,"当前用户与操作用户不符！");
        }
        List<Login> listResult = loginService.selectByName(passwordUpdated.getUserName());
        Login result = listResult.isEmpty()?null:listResult.get(0);
        ModelMap data = new ModelMap();

        //当用户名已存在
        if (null == result){
            data.addAttribute("code",1);
            data.addAttribute("status",200);
            data.addAttribute("msg","更改失败，没有该用户！");
            return data;
        }

        //当密码错误
        if (!result.getPassword().equals(passwordUpdated.getOldPassword())){
            data.addAttribute("code",1);
            data.addAttribute("status",200);
            data.addAttribute("msg","密码错误！");
            return data;
        }

        result.setPassword(passwordUpdated.getNewPassword());
        if (0 == loginService.updateByPrimaryKey(result)){
            data.addAttribute("code",0);
            data.addAttribute("status",200);
            data.addAttribute("msg","更改失败，没有该用户！");
        }

        data.addAttribute("code",0);
        data.addAttribute("status",200);
        data.addAttribute("msg","修改完成！");
        return data;
    }


    @GetMapping("/update")
    public @ResponseBody ModelMap getNewToken(@RequestHeader String token) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"tempMember"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        ModelMap data = ModelMapUtil.init(0,null,"获取成功!");
        data.addAttribute("token", TokenUtil.createJWT((String) permissionValidationMap.get("userId"),(String) permissionValidationMap.get("userName"),"login", 8 * 3600000L));
        return data;
    }
}