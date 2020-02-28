package com.plateform.service;

import com.plateform.entity.Sres;
import java.util.List;

public interface TempSresService {

    int deleteByPrimaryKey(Integer id);
    int insert(Sres record);
    Sres selectByPrimaryKey(Integer id);
    List<Sres> selectAll();
    int updateByPrimaryKey(Sres record);

    List<Sres> selectByMemberNo(String memberId);
    List<Sres> selectByAbilityNo(String abilityNo);
}