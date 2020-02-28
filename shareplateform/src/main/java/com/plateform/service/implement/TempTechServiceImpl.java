package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.TempTechMapper;
import com.plateform.entity.Tech;
import com.plateform.entity.TechExample;
import com.plateform.service.TempTechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempTechServiceImpl implements TempTechService {


    @Autowired
    private TempTechMapper tempTechMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tempTechMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Tech record) {
        return tempTechMapper.insert(record);
    }

    @Override
    public Tech selectByPrimaryKey(Integer id) {
        return tempTechMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tech> selectAll() {
        TechExample techExample = new TechExample();
        return tempTechMapper.selectByExample(techExample);
    }

    @Override
    public int updateByPrimaryKey(Tech record) {
        return tempTechMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<Tech> selectByMemberNo(String memberNo) {
        TechExample example = new TechExample();
        TechExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return tempTechMapper.selectByExample(example);
    }

    public List<Tech> selectByMemberNo(String memberNo,Integer pageNum, Integer pageSize) {
        TechExample example = new TechExample();
        TechExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        PageHelper.startPage(pageNum, pageSize);
        return tempTechMapper.selectByExample(example);
    }


    @Override
    public List<Tech> selectByAbilityNo(String abilityNo) {
        TechExample example = new TechExample();
        TechExample.Criteria criteria = example.createCriteria();
        criteria.andAbilityNoEqualTo(abilityNo);
        return tempTechMapper.selectByExample(example);
    }

}
