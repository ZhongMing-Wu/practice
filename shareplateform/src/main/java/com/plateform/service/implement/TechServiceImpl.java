package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.TechMapper;
import com.plateform.entity.Tech;
import com.plateform.entity.TechExample;
import com.plateform.service.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechServiceImpl implements TechService {
    @Autowired
    TechMapper techMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return techMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Tech record) {
        return techMapper.insert(record);
    }

    @Override
    public Tech selectByPrimaryKey(Integer id) {
        return techMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tech> selectAll() {
        TechExample hresExample = new TechExample();
        return techMapper.selectByExample(hresExample);
    }


    public List<Tech> selectAll(Integer pageNum, Integer pageSize) {
        TechExample example = new TechExample();
        PageHelper.startPage(pageNum, pageSize);
        return techMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Tech record) {
        return techMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Tech> selectByMemberNo(String memberNo) {
        TechExample example = new TechExample();
        TechExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return techMapper.selectByExample(example);
    }

    public List<Tech> selectByMemberNo(String memberNo,Integer pageNum, Integer pageSize) {
        TechExample example = new TechExample();
        TechExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        PageHelper.startPage(pageNum, pageSize);
        return techMapper.selectByExample(example);
    }


    @Override
    public List<Tech> selectByAbilityNo(String abilityNo) {
        TechExample techExample = new TechExample();
        TechExample.Criteria criteria = techExample.createCriteria();
        criteria.andAbilityNoEqualTo(abilityNo);
        return techMapper.selectByExample(techExample);
    }
}