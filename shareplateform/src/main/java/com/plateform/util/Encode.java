package com.plateform.util;

import com.plateform.entity.EncodableEntity;
import com.plateform.entity.Member;
import com.plateform.service.implement.LoginServiceImpl;
import com.plateform.service.implement.TempMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class Encode {

    private static LoginServiceImpl loginService;

    @Autowired
    public void setLoginService(LoginServiceImpl loginService) {
        Encode.loginService = loginService;
    }

    public static String getEncode(String category, EncodableEntity entity){

        String encode = "";
        encode = encode.concat(category);
        switch (category){
            case "A":
                Member obj = (Member) entity;



                for (int i = 0; i<5; i++) {
                    //取拼音组成四位编码
                    String nameHead = Pinyin.getPinYinHeadChar((obj).getMemberName());
                    if (nameHead.length() == 0) return "-2";
                    while (nameHead.length() < 4) nameHead = nameHead.concat(nameHead);
                    encode = encode.concat(Pinyin.getPinYinHeadChar(obj.getMemberName()).substring(0,3));
                    //生成识别码
                    encode = encode.concat(UUID.randomUUID().toString().substring(0,3));
                    //验证数据库中是否有相同的编号
                    //Login
                    //loginService.s
                }
                break;
            case "B":
                break;
            case "C":
            case "D":
            case "E":
                break;
            default:encode="-1";
        }
        return encode;
    }




}
