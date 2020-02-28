package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.HresMapper;
import com.plateform.dao.TempHresMapper;
import com.plateform.entity.Hres;
import com.plateform.entity.HresExample;
import com.plateform.service.TempHresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempHresServiceImpl implements TempHresService {


    @Autowired
    private TempHresMapper tempHresMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tempHresMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Hres record) {
        return tempHresMapper.insert(record);
    }

    @Override
    public Hres selectByPrimaryKey(Integer id) {
        return tempHresMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Hres> selectAll() {
        HresExample hresExample = new HresExample();
        return tempHresMapper.selectByExample(hresExample);
    }

    @Override
    public int updateByPrimaryKey(Hres record) {
        return tempHresMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<Hres> selectByMemberNo(String memberNo) {
        HresExample hresExample = new HresExample();
        HresExample.Criteria criteria = hresExample.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return tempHresMapper.selectByExample(hresExample);
    }

    public List<Hres> selectByMemberNo(String memberNo,Integer pageNum, Integer pageSize) {
        HresExample example = new HresExample();
        HresExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        PageHelper.startPage(pageNum, pageSize);
        return tempHresMapper.selectByExample(example);
    }

    @Override
    public List<Hres> selectByAbilityNo(String abilityNo) {
        HresExample hresExample = new HresExample();
        HresExample.Criteria criteria = hresExample.createCriteria();
        criteria.andAbilityNoEqualTo(abilityNo);
        return tempHresMapper.selectByExample(hresExample);
    }

}
