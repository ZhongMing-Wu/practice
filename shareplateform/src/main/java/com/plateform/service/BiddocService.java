package com.plateform.service;

import com.plateform.entity.Biddoc;

import java.util.List;

public interface BiddocService {
    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(Biddoc record);
    Biddoc selectByPrimaryKey(Integer id);
    List<Biddoc> selectAll();
    int updateByPrimaryKey(Biddoc record);

    List<Biddoc> selectByString(Biddoc record);
}
