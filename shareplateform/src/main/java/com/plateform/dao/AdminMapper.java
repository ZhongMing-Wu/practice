package com.plateform.dao;

import com.plateform.entity.Admin;
import java.util.List;

public interface AdminMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Admin record);
    Admin selectByPrimaryKey(Integer id);
    List<Admin> selectAll();
    int updateByPrimaryKey(Admin record);

    List<Admin> selectByString(Admin record);
}