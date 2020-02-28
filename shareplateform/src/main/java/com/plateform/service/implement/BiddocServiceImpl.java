package com.plateform.service.implement;

import com.plateform.dao.BiddocMapper;
import com.plateform.entity.Biddoc;
import com.plateform.service.BiddocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiddocServiceImpl implements BiddocService {
    @Autowired
    BiddocMapper biddocMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return biddocMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Biddoc record) {
        return biddocMapper.insert(record);
    }

    @Override
    public Biddoc selectByPrimaryKey(Integer id) {
        return biddocMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Biddoc> selectAll() {
        return biddocMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Biddoc record) {
        return biddocMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Biddoc> selectByString(Biddoc record) {
        return biddocMapper.selectByString(record);
    }
}
