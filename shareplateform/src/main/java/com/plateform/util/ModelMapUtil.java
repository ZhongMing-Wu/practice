package com.plateform.util;

import org.springframework.ui.ModelMap;

public class ModelMapUtil {

    public static ModelMap init(Integer code,Integer status,String msg){
        ModelMap result = new ModelMap();
        if (null != code) {
            result.addAttribute("code",code);
        }
        if (null != status) {
            result.addAttribute("status",status);
        }
        if (null != msg) {
            result.addAttribute("msg",msg);
        }
        return result;
    }

    public static ModelMap initError(String msg) {
        return init(1, null, msg);
    }
}
