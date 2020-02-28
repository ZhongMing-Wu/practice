package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.HresMapper;
import com.plateform.entity.Hres;
import com.plateform.entity.HresExample;
import com.plateform.service.HresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class            HresServiceImpl implements HresService {

    @Autowired
    HresMapper hresMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return hresMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Hres record) {
        return hresMapper.insert(record);
    }

    @Override
    public Hres selectByPrimaryKey(Integer id) {
        return hresMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Hres> selectAll() {
        HresExample example = new HresExample();
        return hresMapper.selectByExample(example);
    }

    public List<Hres> selectAll(Integer pageNum, Integer pageSize) {
        HresExample example = new HresExample();
        PageHelper.startPage(pageNum, pageSize);
        return hresMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Hres record) {
        return hresMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Hres> selectByMemberNo(String memberNo) {
        HresExample hresExample = new HresExample();
        HresExample.Criteria criteria = hresExample.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return hresMapper.selectByExample(hresExample);
    }

    public List<Hres> selectByMemberNo(String memberNo,Integer pageNum, Integer pageSize) {
        HresExample hresExample = new HresExample();
        HresExample.Criteria criteria = hresExample.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        PageHelper.startPage(pageNum, pageSize);
        return hresMapper.selectByExample(hresExample);
    }

    @Override
    public List<Hres> selectByAbilityNo(String abilityNo) {
        HresExample hresExample = new HresExample();
        HresExample.Criteria criteria = hresExample.createCriteria();
        criteria.andAbilityNoEqualTo(abilityNo);
        return hresMapper.selectByExample(hresExample);
    }
}
