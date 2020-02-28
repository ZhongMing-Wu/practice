package com.plateform.service;

import com.plateform.entity.Bid;

import java.util.List;

public interface BidService {
    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(Bid record);
    Bid selectByPrimaryKey(Integer id);
    List<Bid> selectAll();
    int updateByPrimaryKey(Bid record);


    List<Bid> selectByString(Bid record);

}
