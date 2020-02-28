package com.plateform.service.implement;

import com.plateform.dao.AdviceMapper;
import com.plateform.entity.Advice;
import com.plateform.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {
    @Autowired
    AdviceMapper adviceMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return adviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Advice record) {
        return adviceMapper.insert(record);
    }

    @Override
    public Advice selectByPrimaryKey(Integer id) {
        return adviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Advice> selectAll() {
        return adviceMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Advice record) {
        return adviceMapper.updateByPrimaryKey(record);
    }

    //@Override
    //public List<Advice> selectByMemberNo(String memberNo) {
    //    return adviceMapper.selectByMemberNo(memberNo);
    //}

    @Override
    public List<Advice> selectByString(Advice record) {
        return adviceMapper.selectByString(record);
    }
}
