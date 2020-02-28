package com.plateform.service;

import com.plateform.entity.Advice;

import java.util.List;

public interface AdviceService {

    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(Advice record);
    Advice selectByPrimaryKey(Integer id);
    List<Advice> selectAll();
    int updateByPrimaryKey(Advice record);

    List<Advice> selectByString(Advice record);
}
