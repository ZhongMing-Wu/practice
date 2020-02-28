package com.plateform.service;

import com.plateform.entity.Hres;

import java.util.List;

public interface HresService {

    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(Hres record);
    Hres selectByPrimaryKey(Integer id);
    List<Hres> selectAll();
    int updateByPrimaryKey(Hres record);


    List<Hres> selectByMemberNo(String memberId);
    List<Hres> selectByAbilityNo(String abilityNo);
}
