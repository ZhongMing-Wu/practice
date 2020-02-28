package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.SresMapper;
import com.plateform.entity.Sres;
import com.plateform.entity.SresExample;
import com.plateform.service.SresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SresServiceImpl implements SresService {
    @Autowired
    SresMapper sresMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sresMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Sres record) {
        return sresMapper.insert(record);
    }

    @Override
    public Sres selectByPrimaryKey(Integer id) {
        return sresMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Sres> selectAll() {
        SresExample hresExample = new SresExample();
        return sresMapper.selectByExample(hresExample);
    }

    public List<Sres> selectAll(Integer pageNum, Integer pageSize) {
        SresExample example = new SresExample();
        PageHelper.startPage(pageNum, pageSize);
        return sresMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Sres record) {
        return sresMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Sres> selectByMemberNo(String memberNo) {
        SresExample example = new SresExample();
        SresExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return sresMapper.selectByExample(example);
    }

    public List<Sres> selectByMemberNo(String memberNo,Integer pageNum, Integer pageSize) {
        SresExample example = new SresExample();
        SresExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        PageHelper.startPage(pageNum, pageSize);
        return sresMapper.selectByExample(example);
    }

    @Override
    public List<Sres> selectByAbilityNo(String abilityNo) {
        SresExample sresExample = new SresExample();
        SresExample.Criteria criteria = sresExample.createCriteria();
        criteria.andAbilityNoEqualTo(abilityNo);
        return sresMapper.selectByExample(sresExample);
    }
}
