package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.DemandMapper;
import com.plateform.entity.Demand;
import com.plateform.entity.DemandExample;
import com.plateform.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    DemandMapper demandMapper;


    @Override
    public void alterId(){ demandMapper.alterId();}
    @Override
    public void deleteAll(){ demandMapper.deleteAll();}

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return demandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Demand record) {
        return demandMapper.insert(record);
    }

    @Override
    public Demand selectByPrimaryKey(Integer id) {
        return demandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Demand> selectAll() {
        DemandExample example = new DemandExample();
        DemandExample.Criteria criteria = example.createCriteria();
        return demandMapper.selectByExample(example);
    }

    public List<Demand> selectAll(Integer pageNum, Integer pageSize) {
        DemandExample example = new DemandExample();
        PageHelper.startPage(pageNum, pageSize);
        return demandMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Demand record) {
        return demandMapper.updateByPrimaryKey(record);
    }



    //@Override
    //public List<Demand> selectByMemberNo(String memberNo){
    //    DemandExample example = new DemandExample();
    //    DemandExample.Criteria criteria = example.createCriteria();
    //    criteria.andMemberNoEqualTo(memberNo);
    //    return demandMapper.selectByExample(example);
    //}

    @Override
    public int insertUserDemandInfor(Demand demand){
        return demandMapper.insert(demand);
    }

    @Override
    public int updateByDemandNo(Demand demand){
        DemandExample example = new DemandExample();
        DemandExample.Criteria criteria = example.createCriteria();
        criteria.andDemandNoEqualTo(demand.getDemandNo());
        return demandMapper.updateByExample(demand, example);
    }

    @Override
    public List<Demand> selectByUserNo(String userNo){
        DemandExample example = new DemandExample();
        DemandExample.Criteria criteria = example.createCriteria();
        criteria.andUserNoEqualTo(userNo);
        return demandMapper.selectByExample(example);
    }

    @Override
    public List<Demand> selectByDemandNo(String demandNo) {
        DemandExample example = new DemandExample();
        DemandExample.Criteria criteria = example.createCriteria();
        criteria.andDemandNoEqualTo(demandNo);
        return demandMapper.selectByExample(example);
    }

}