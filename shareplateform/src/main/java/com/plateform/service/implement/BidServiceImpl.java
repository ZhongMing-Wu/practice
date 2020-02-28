package com.plateform.service.implement;

import com.plateform.dao.BidMapper;
import com.plateform.entity.Bid;
import com.plateform.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    BidMapper bidMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bidMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Bid record) {
        return bidMapper.insert(record);
    }

    @Override
    public Bid selectByPrimaryKey(Integer id) {
        return bidMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Bid> selectAll() {
        return bidMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Bid record) {
        return bidMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Bid> selectByString(Bid record) {
        return bidMapper.selectByString(record);
    }


}