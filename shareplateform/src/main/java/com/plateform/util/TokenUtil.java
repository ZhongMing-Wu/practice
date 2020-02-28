package com.plateform.util;

import com.plateform.dao.TempMemberMapper;
import com.plateform.entity.Login;
import com.plateform.entity.PermissionExample;
import com.plateform.service.implement.LoginServiceImpl;
import com.plateform.service.implement.TempMemberServiceImpl;
import io.jsonwebtoken.*;
import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@Component
public class TokenUtil {

    private static byte[] JWT_SECERT="hahaha".getBytes();
    private static final long hour2mil = 3600000L;
    private static final long day2mil = 86400000L;
    private static final long week2mil = 604800000L;


    private static LoginServiceImpl loginService;


    @Autowired
    public void setLoginService(LoginServiceImpl loginService) {
        TokenUtil.loginService = loginService;
    }

    /**
     * 签发JWT
     * @param id
     * @param subject 可以是JSON数据 尽可能少
     * @param ttlMillis
     * @return  String
     *
     */
    public static String createJWT(String id, String audience, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setAudience(audience)
                //.setIssuer("user")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    /**
     * 验证JWT
     * @param jwtStr
     * @return
     */
    private static Map<String,Object> validateJWT(String jwtStr) {
        Map<String,Object> result = new HashMap<String,Object>();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            result.put("code",0);
            result.put("data",claims);
        } catch (ExpiredJwtException e) {
            result.put("code",1);
            result.put("msg",e.getMessage());
        } catch (SignatureException e) {
            result.put("code",1);
            result.put("msg",e.getMessage());
        } catch (Exception e) {
            result.put("code",1);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.encode(JWT_SECERT);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }



    //验证身份
    public static Map validatePermission(String token,String[] requestRole) {
        //1.验证token有效性,无效时直接返回ModelMap
        Map<String,Object> jwtResult = validateJWT(token);
        ModelMap modelMapResult = new ModelMap();
        if (0 != (int) jwtResult.get("code")){
            modelMapResult.putAll(jwtResult);
            return modelMapResult;
        }

        //2.查询数据库，获得该成员的身份，当查询结果为空时直接返回ModelMap
        String userId = ((Claims) jwtResult.get("data")).getId();
        String userName = ((Claims) jwtResult.get("data")).getAudience();
        Map<String,Object> hashMapResult = new HashMap<>();
        if (0 == userName.length()) {
            modelMapResult.put("code",1);
            modelMapResult.put("msg","当前用户数据缺失！");
            return modelMapResult;
        }
        List<Login> daoResult;
        if (null != userId && 0 != userId.length()){
            daoResult = loginService.selectByLoginuser(userId);
            if (daoResult.isEmpty()) {
                modelMapResult.put("code",1);
                modelMapResult.put("msg","没有查询到当前登录用户！");
                return modelMapResult;
            }
        } else {
            //当用户没有用户id时，认定其为阶段一注册用户
            daoResult = loginService.selectByName(userName);
            if (daoResult.isEmpty()) {
                modelMapResult.put("code",1);
                modelMapResult.put("msg","没有查询到当前登录用户！");
                return modelMapResult;
            }
        }


        //3.比对身份,身份正确返回null，不正确返回ModelMap
        String userRole = daoResult.get(0).getRole();
        if (null != requestRole && !Arrays.asList(requestRole).contains(userRole)) {
            modelMapResult.put("code",1);
            modelMapResult.put("msg","没有权限！");
            modelMapResult.put("requestRole",requestRole);
            modelMapResult.put("userRole",userRole);
            return  modelMapResult;
        }
        hashMapResult.put("code",0);
        hashMapResult.put("userId",userId);
        hashMapResult.put("userRole",userRole);
        hashMapResult.put("userName",userName);
        return hashMapResult;
    }


    /**
     *
     * 解析JWT字符串
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


    public static void main(String[] args) {


        //String id = "Ad001" , subject = "login";
        //long ttlMillis = 8 * hour2mil;
        ////long ttlMillis = 1;
        //Date date = new Date();
        //
        //
        //
        //
        //String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJvayIsInN1YiI6ImxvZ2luIiwiaWF0IjoxNTY5NjQxMTIwLCJleHAiOjE1Njk2Njk5MjB9.FiYaLVpMQ9Tu4npJO3FV3Xm0AkDukPY0CqKpYe_Id24";
        //String result;
        //List<String> requestRole = new ArrayList<String>();
        //requestRole.add("admin");
        //Map a = TokenUtil.validatePermission(token,new String[]{"admin"});
        //
        ////validateJWT(result);
        //System.out.println(a instanceof ModelMap);
    }
}
