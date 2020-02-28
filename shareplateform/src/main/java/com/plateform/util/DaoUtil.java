package com.plateform.util;

import org.springframework.ui.ModelMap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DaoUtil {
    //将查询对象的string字段全部设置为"%"
    public static List<String> formatNewObject(Object entity){
        Class cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        List<String> result = new ArrayList<>();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            try {
                //使用getGenericType()返回Type类型
                if (f.getGenericType().toString().equals("class java.lang.String")){
                    f.set(entity,"%");
                    result.add(f.getName());
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ModelMap checkJsonDatalist(List list){
        ModelMap modelMap = new ModelMap();
        if (list.isEmpty() || list.contains(null)){
            modelMap.addAttribute("code",1);
            modelMap.addAttribute("msg", "没有查询到的对象");
        }else{
            modelMap.addAttribute("code",0);
            modelMap.addAttribute("data", list);
        }
        return modelMap;
    }

    public static boolean queryByString(List<String> fieldName,List<String> fieldValue,Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        boolean flag=false;
        Class cls = entity.getClass();
        Method method;
        for (String value : fieldValue){
            if (null != value){
                try {
                    flag=true;
                    String name = fieldName.get(fieldValue.indexOf(value));
                    method = cls.getMethod("set"+upperCase(name), String.class);
                    method.invoke(entity, new Object[]{value});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }


    private static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
