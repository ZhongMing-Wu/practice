package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.OrderMapper;
import com.plateform.entity.Order;
import com.plateform.entity.OrderExample;
import com.plateform.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public Order selectByPrimaryKey(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> selectAll() {
        OrderExample example = new OrderExample();
        return orderMapper.selectByExample(example);
    }

    public List<Order> selectAll(Integer pageNum, Integer pageSize) {
        OrderExample example = new OrderExample();
        PageHelper.startPage(pageNum, pageSize);
        return orderMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }



    @Override
    public List<Order> selectByDemandNo(String demandNo) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andDemandNoEqualTo(demandNo);
        return orderMapper.selectByExample(example);
    }

    @Override
    public int insertUserOrderInfor(Order demand){
        return orderMapper.insert(demand);
    }

    @Override
    public List<Order> selectByUserNo(String userNo){
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserNoEqualTo(userNo);
        return orderMapper.selectByExample(example);
    }

    @Override
    public int updateByDemandNo(Order order) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andDemandNoEqualTo(order.getDemandNo());
        return orderMapper.updateByExample(order, example);
    }

    @Override
    public List<Order> selectByMemberNo(String memberNo) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return orderMapper.selectByExample(example);
    }

    @Override
    public List<Order> selectByMemberNoAndStatus(String memberNo, List<String> status) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        criteria.andStatusIn(status);
        return orderMapper.selectByExample(example);
    }

}