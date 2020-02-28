package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.TempSresMapper;
import com.plateform.entity.Member;
import com.plateform.entity.Sres;
import com.plateform.entity.SresExample;
import com.plateform.service.TempMemberService;
import com.plateform.service.TempSresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempSresServiceImpl implements TempSresService {


    @Autowired
    private TempSresMapper tempSresMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tempSresMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Sres record) {
        return tempSresMapper.insert(record);
    }

    @Override
    public Sres selectByPrimaryKey(Integer id) {
        return tempSresMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Sres> selectAll() {
        SresExample sresExample = new SresExample();
        return tempSresMapper.selectByExample(sresExample);
    }

    @Override
    public int updateByPrimaryKey(Sres record) {
        return tempSresMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<Sres> selectByMemberNo(String memberNo) {
        SresExample example = new SresExample();
        SresExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return tempSresMapper.selectByExample(example);
    }


    public List<Sres> selectByMemberNo(String memberNo,Integer pageNum, Integer pageSize) {
        SresExample example = new SresExample();
        SresExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        PageHelper.startPage(pageNum, pageSize);
        return tempSresMapper.selectByExample(example);
    }



    @Override
    public List<Sres> selectByAbilityNo(String abilityNo) {
        SresExample example = new SresExample();
        SresExample.Criteria criteria = example.createCriteria();
        criteria.andAbilityNoEqualTo(abilityNo);
        return tempSresMapper.selectByExample(example);
    }

}
