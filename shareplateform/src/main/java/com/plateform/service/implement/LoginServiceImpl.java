package com.plateform.service.implement;

import com.plateform.dao.LoginMapper;
import com.plateform.entity.Login;
import com.plateform.entity.LoginExample;
import com.plateform.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return loginMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Login record) {
        return loginMapper.insert(record);
    }

    @Override
    public Login selectByPrimaryKey(Integer id) {
        return loginMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Login> selectAll() {
        LoginExample example = new LoginExample();
        return loginMapper.selectByExample(example);
    }

    @Override
    public int updateByPrimaryKey(Login record) {
        return loginMapper.updateByPrimaryKey(record);
    }

    //登陆验证
    @Override
    public Login LoadAccount(Login login){
        LoginExample example = new LoginExample();
        LoginExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(login.getName());
        List<Login> listResult=loginMapper.selectByExample(example);
        //当数据库中不存在该用户名时，返回null，意为用户名已存在
        if (listResult.isEmpty()) {
            return null;
        }
        return listResult.get(0);
    }

    //注册
    @Override
    public int RegisterAccount(Login login){
        LoginExample example = new LoginExample();
        LoginExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(login.getName());
        List<Login> listResult=loginMapper.selectByExample(example);
        //当数据库中存在该用户名时，返回-1，意为用户名已存在
        if (!listResult.isEmpty()) {
            return 0;
        }
        loginMapper.insert(login);
        return 1;
    }

    @Override
    public List<Login>  selectByName(String name) {
        LoginExample example = new LoginExample();
        LoginExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        return loginMapper.selectByExample(example);
    }

    @Override
    public List<Login> selectByString(Login record) {
        return null;
    }

    @Override
    public List<Login> selectByLoginuser(String userId) {
        LoginExample example = new LoginExample();
        LoginExample.Criteria criteria = example.createCriteria();
        criteria.andLoginuserEqualTo(userId);
        return loginMapper.selectByExample(example);
    }
}
