package com.plateform.service.implement;

import com.github.pagehelper.PageHelper;
import com.plateform.dao.UserMapper;
import com.plateform.entity.MemberExample;
import com.plateform.entity.User;
import com.plateform.entity.UserExample;
import com.plateform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        UserExample example = new UserExample();
        //MemberExample.Criteria criteria = memberExample.createCriteria();
        //criteria.andIdEqualTo(1);
        return userMapper.selectByExample(example);
    }

    public List<User> selectAll(Integer pageNum, Integer pageSize) {
        UserExample example = new UserExample();
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<User> selectByUserNo(String userNo){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNoEqualTo(userNo);
        return userMapper.selectByExample(userExample);
    }
}