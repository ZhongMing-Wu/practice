package com.plateform.service;

import com.plateform.entity.Order;

import java.util.List;

public interface OrderService {
    //对应dao里面的方法
    int deleteByPrimaryKey(Integer id);
    int insert(Order record);
    Order selectByPrimaryKey(Integer id);
    List<Order> selectAll();
    int updateByPrimaryKey(Order order);

    List<Order> selectByDemandNo(String demandNo);
    List<Order> selectByMemberNo(String memberNo);

    int insertUserOrderInfor(Order order);

    List<Order> selectByUserNo(String userNo);
    int updateByDemandNo(Order order);

    List<Order> selectByMemberNoAndStatus(String memberNo, List<String> status);
}
