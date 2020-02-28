package com.plateform.contorller;

import com.plateform.entity.Member;
import com.plateform.service.implement.TempMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {


    //private TempMemberServiceImpl tempMemberService;
    //
    //@Autowired
    //public void setTempMemberService(TempMemberServiceImpl tempMemberService) {
    //    this.tempMemberService = tempMemberService;
    //}


    @GetMapping("/api")
    public String api(){
        return "newapi";
    }
    @GetMapping("/test")
    public @ResponseBody ModelMap test(@RequestParam("name") String name ){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("hahaha",Member.getRegisterStage(name));
        return modelMap;
    }
}
