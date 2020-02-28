package com.plateform.dao;

import com.plateform.entity.Bid;
import java.util.List;

public interface BidMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Bid record);
    Bid selectByPrimaryKey(Integer id);
    List<Bid> selectAll();
    int updateByPrimaryKey(Bid record);

    List<Bid> selectByString(Bid record);
}