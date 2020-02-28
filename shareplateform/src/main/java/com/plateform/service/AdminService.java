package com.plateform.service;

import com.plateform.entity.Admin;

import java.util.List;

public interface AdminService {
    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(Admin record);
    Admin selectByPrimaryKey(Integer id);
    List<Admin> selectAll();
    int updateByPrimaryKey(Admin record);

    List<Admin> selectByString(Admin record);
}
