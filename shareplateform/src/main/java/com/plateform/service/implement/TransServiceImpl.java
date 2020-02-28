package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.TransMapper;
import com.plateform.entity.Trans;
import com.plateform.entity.TransExample;
import com.plateform.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransServiceImpl implements TransService {


    @Autowired
    private TransMapper transMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return transMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Trans record) {
        return transMapper.insert(record);
    }

    @Override
    public Trans selectByPrimaryKey(Integer id) {
        return transMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Trans> selectAll() {
        TransExample example = new TransExample();
        return transMapper.selectByExample(example);
    }

    public List<Trans> selectAll(Integer pageNum, Integer pageSize) {
        TransExample example = new TransExample();
        PageHelper.startPage(pageNum, pageSize);
        return transMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Trans record) {
        return transMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Trans> selectByTransObjid(String transObjid) {
        TransExample transExample = new TransExample();
        TransExample.Criteria criteria = transExample.createCriteria();
        criteria.andTransObjidEqualTo(transObjid);
        transExample.setOrderByClause("APPLY_TIME DESC");
        return transMapper.selectByExample(transExample);
    }

    @Override
    public int updateMemberStatusByMemberNo(String memberNo,String memberstatus) {
        return transMapper.updateMemberStatusByMemberNo(memberNo,memberstatus);
    }

    @Override
    public List<Trans> selectByApplicantNo(String applicantNo) {
        TransExample example = new TransExample();
        TransExample.Criteria criteria = example.createCriteria();
        criteria.andApplicantNoEqualTo(applicantNo);
        example.setOrderByClause("APPLY_TIME DESC");
        return transMapper.selectByExample(example);
    }

    public List<Trans> selectByApplicantNo(String applicantNo,Integer pageNum, Integer pageSize) {
        TransExample example = new TransExample();
        TransExample.Criteria criteria = example.createCriteria();
        criteria.andApplicantNoEqualTo(applicantNo);
        example.setOrderByClause("APPLY_TIME DESC");
        PageHelper.startPage(pageNum, pageSize);
        return transMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(Trans record) {
        TransExample transExample = new TransExample();
        TransExample.Criteria criteria = transExample.createCriteria();
        criteria.andIdEqualTo(record.getId());
        return transMapper.updateByExampleSelective(record,transExample);
    }
}
