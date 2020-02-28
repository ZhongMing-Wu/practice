package com.plateform.service;

import com.plateform.entity.Tech;

import java.util.List;

public interface TempTechService {

    int deleteByPrimaryKey(Integer id);
    int insert(Tech record);
    Tech selectByPrimaryKey(Integer id);
    List<Tech> selectAll();
    int updateByPrimaryKey(Tech record);

    List<Tech> selectByMemberNo(String memberId);


    List<Tech> selectByAbilityNo(String abilityNo);
}