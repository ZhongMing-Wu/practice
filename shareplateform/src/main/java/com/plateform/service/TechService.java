package com.plateform.service;

import com.plateform.entity.Tech;

import java.util.List;

public interface TechService {
    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(Tech record);
    Tech selectByPrimaryKey(Integer id);
    List<Tech> selectAll();
    int updateByPrimaryKey(Tech record);

    List<Tech> selectByMemberNo(String memberNo);

    List<Tech> selectByAbilityNo(String abilityNo);
}
