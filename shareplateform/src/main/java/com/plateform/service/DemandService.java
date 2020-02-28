package com.plateform.service;

import com.plateform.entity.Demand;

import java.util.List;

public interface DemandService {
    //对应dao里面的方法
    void alterId();
    void deleteAll();
    int deleteByPrimaryKey(Integer id);
    int insert(Demand record);
    Demand selectByPrimaryKey(Integer id);
    List<Demand> selectAll();
    int updateByPrimaryKey(Demand record);


    //List<Demand> selectByMemberNo(String memberNo);

    int insertUserDemandInfor(Demand demand);
    int updateByDemandNo(Demand demand);

    List<Demand> selectByUserNo(String userNo);
    List<Demand> selectByDemandNo(String demandNo);
}
