package com.plateform.service;

import com.plateform.entity.Login;

import java.util.List;

public interface LoginService {

    int deleteByPrimaryKey(Integer id);
    int insert(Login record);
    Login selectByPrimaryKey(Integer id);
    List<Login> selectAll();
    int updateByPrimaryKey(Login record);


    Login LoadAccount(Login login);
    int RegisterAccount(Login login);
    List<Login> selectByName(String name);

    List<Login> selectByString(Login record);

    List<Login> selectByLoginuser(String userId);
}
