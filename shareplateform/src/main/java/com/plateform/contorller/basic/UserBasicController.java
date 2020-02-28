package com.plateform.contorller.basic;

import com.plateform.entity.*;
import com.plateform.service.implement.*;
import com.plateform.util.ModelMapUtil;
import com.plateform.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UserBasicController {

    //这个控制器负责用户的基本事务
    //1、用户查看自己的实体信息
    //2、用户修改自己的实体信息
    
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }


    //-------------------------------------------------------------------------------
    //---------------------------用户实体信息接口-----------------------------------
    //-------------------------------------------------------------------------------

    
    //1、用户查看自己的实体信息
    @GetMapping("/user/info")
    public @ResponseBody
    ModelMap getUserInfo(@RequestHeader("token") String token) {
        ModelMap returnData;
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"User"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String userId = (String) permissionValidationMap.get("userId");
        List<User> result = userService.selectByUserNo(userId);
        if (result.isEmpty()) {
            return ModelMapUtil.init(1,null,"该用户信息不存在！");
        }
        returnData = ModelMapUtil.init(0,null,"查询成功！");
        returnData.addAttribute("data",result);
        return returnData;
    }

    //2、用户修改自己的实体信息
    @PutMapping("/user/info")
    public @ResponseBody
    ModelMap putUserInfo(@RequestHeader("token") String token,@RequestBody User frontData) {
        //通过token验证身份
        Map permissionValidationMap = TokenUtil.validatePermission(token,new String[]{"User"});
        if ((permissionValidationMap instanceof ModelMap)) return (ModelMap) permissionValidationMap;
        String userId = (String) permissionValidationMap.get("userId");
        List<User> result = userService.selectByUserNo(userId);
        if (result.isEmpty()) {
            return ModelMapUtil.init(1,null,"该用户信息不存在！");
        }

        //输入数据格式标准化
        frontData.setId(result.get(0).getId());
        frontData.setUserNo(userId);
        userService.updateByPrimaryKey(frontData);
        return ModelMapUtil.init(0,null,"修改完成！");
    }
    


    //-------------------------------------------------------------------------------
    //---------------------------用户事务查询接口-----------------------------------
    //-------------------------------------------------------------------------------
    
    

    
}
