package com.plateform.service;

import com.plateform.entity.Hres;

import java.util.List;

public interface TempHresService{

    int deleteByPrimaryKey(Integer id);
    int insert(Hres record);
    Hres selectByPrimaryKey(Integer id);
    List<Hres> selectAll();
    int updateByPrimaryKey(Hres record);

    List<Hres> selectByMemberNo(String memberNo);

    List<Hres> selectByAbilityNo(String abilityNo);
}