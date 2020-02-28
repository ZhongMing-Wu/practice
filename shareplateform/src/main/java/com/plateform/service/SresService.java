package com.plateform.service;

import com.plateform.entity.Sres;

import java.util.List;

public interface SresService {

    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(Sres record);
    Sres selectByPrimaryKey(Integer id);
    List<Sres> selectAll();
    int updateByPrimaryKey(Sres record);
    List<Sres> selectByMemberNo(String memberId);

    List<Sres> selectByAbilityNo(String abilityNo);
}
