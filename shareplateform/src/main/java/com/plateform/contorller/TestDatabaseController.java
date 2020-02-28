package com.plateform.contorller;

import com.plateform.entity.User;
import com.plateform.service.implement.DemandServiceImpl;
import com.plateform.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestDatabaseController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    DemandServiceImpl demandService;

    //增加一个用户
    @PostMapping("/user")
    public @ResponseBody ModelMap addUser(@RequestBody User record,@RequestParam(required = false) String generateNo){
        record.setRegisterTime(new java.util.Date());
        ModelMap data = new ModelMap();
        User result = userService.selectByPrimaryKey(record.getId());
        if (null != result){
            data.addAttribute("code",1);
            data.addAttribute("msg","该用户已存在");
        }else {
            userService.insert(record);
            data.addAttribute("code",0);
            data.addAttribute("msg","插入成功！");
            //生成编号
            if (null != generateNo) data.addAttribute("no","me004");
        }
        return data;
    }

    //删除一个或多个用户
    @DeleteMapping("/user")
    public @ResponseBody ModelMap deleteUser(@RequestParam String id){
        ModelMap data = new ModelMap();
        List<Integer> list=new ArrayList();
        for(int i=0;i<id.length();i++){
            if(id.charAt(i)>='0'&&id.charAt(i)<='9'){
                list.add((id.charAt(i)-'0'));
            }
        }
        int result=1,deleteTag;
        for(int i=0;i<list.size();i++){
            deleteTag=userService.deleteByPrimaryKey(list.get(i));
            if(deleteTag==0){
                result=0;
                break;
            }
        }
        if (1 == result){
            data.addAttribute("code",0);
            data.addAttribute("msg","删除成功");
        } else {
            data.addAttribute("code",1);
            data.addAttribute("msg","找不到该用户");
        }
        return data;
    }

    //更改一个用户
    @PutMapping("/user")
    public @ResponseBody ModelMap updateByPrimaryKey(@RequestBody User record){
        ModelMap data = new ModelMap();
        int result= userService.updateByPrimaryKey(record);
        if (1 == result){
            data.addAttribute("code",0);
            data.addAttribute("msg","更改成功");
        } else {
            data.addAttribute("code",1);
            data.addAttribute("msg","找不到该用户");
        }
        return data;
    }

    //查询所有用户
    @GetMapping("/users")
    public @ResponseBody ModelMap selectAll(){
        ModelMap data = new ModelMap();
        List<User> result=userService.selectAll();
        if (result.isEmpty()){
            data.addAttribute("code",1);
            data.addAttribute("msg", "没有查询到的对象");
        }else{
            data.addAttribute("code",0);
            data.addAttribute("data", result);
        }
        return data;
    }

    ////查询一个用户
    //@GetMapping("/user")
    //public @ResponseBody ModelMap selectOne(
    //        @RequestParam(required = false) Integer id,
    //        @RequestParam(required = false) String userNo)
    //{
    //    ModelMap data = new ModelMap();
    //    if (null != userNo) {
    //        data.addAttribute("code",0);
    //        data.addAttribute("data", userService.selectByUserNo(userNo));
    //        return data;
    //    }
    //
    //    if (null != id){
    //        data.addAttribute("code",0);
    //        data.addAttribute("data", userService.selectByPrimaryKey(id));
    //        return data;
    //    }
    //
    //    data.addAttribute("code",1);
    //    data.addAttribute("msg","请传入查询参数！");
    //    return data;
    //}

    ////查询该用户提出的所有需求
    //@GetMapping("/user/demand")
    //public @ResponseBody ModelMap getDemand(@RequestParam String userNo)
    //{
    //    ModelMap data = new ModelMap();
    //    List<Demand> result = demandService.selectByUserNo(userNo);
    //    if (result.isEmpty()){
    //        data.addAttribute("code",1);
    //        data.addAttribute("msg","该用户没有记载的需求！");
    //        return data;
    //    }
    //
    //    data.addAttribute("code",0);
    //    data.addAttribute("data",result);
    //    return data;
    //}

    ////查询建议
    //@GetMapping("/user")
    //public @ResponseBody ModelMap selectUser(
    //        @RequestParam(required = false) Integer id,
    //        @RequestParam(required = false) String userNo,
    //        @RequestParam(required = false) String userName,
    //        @RequestParam(required = false) String userProperty,
    //        @RequestParam(required = false) String regulatedProof,
    //        @RequestParam(required = false) String userAddr,
    //        @RequestParam(required = false) String userPhone,
    //        @RequestParam(required = false) String userEmail,
    //        @RequestParam(required = false) String contact,
    //        @RequestParam(required = false) String contactTelephone,
    //        @RequestParam(required = false) String remark
    //) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    //    ModelMap data;
    //    List<User> result;
    //
    //    //主键查询
    //    if (null != id){
    //        result = new ArrayList<>();
    //        result.add(userService.selectByPrimaryKey(id));
    //        data = DaoUtil.checkJsonDatalist(result);
    //        return data;
    //    }
    //
    //    //字符串查询
    //    User query = new User();
    //    List<String> fieids = DaoUtil.formatNewObject(query);
    //    List<String> values =new ArrayList<>();
    //
    //    values.add(userNo);
    //    values.add(userName);
    //    values.add(userProperty);
    //    values.add(regulatedProof);
    //    values.add(userAddr);
    //    values.add(userPhone);
    //    values.add(userEmail);
    //    values.add(contact);
    //    values.add(contactTelephone);
    //    values.add(remark);
    //
    //
    //    if (DaoUtil.queryByString(fieids,values,query)){
    //        result = userService.selectByString(query);
    //        data = DaoUtil.checkJsonDatalist(result);
    //        return data;
    //    }
    //
    //    result = userService.selectAll();
    //    data = DaoUtil.checkJsonDatalist(result);
    //    return data;
    //}
}
