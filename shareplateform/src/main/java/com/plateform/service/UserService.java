package com.plateform.service;

import com.plateform.entity.User;

import java.util.List;

public interface UserService {
    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(User record);
    User selectByPrimaryKey(Integer id);
    List<User> selectAll();
    int updateByPrimaryKey(User record);
    List<User> selectByUserNo(String userNo);

}
