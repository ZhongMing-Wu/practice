package com.plateform.service.implement;

import com.plateform.dao.TempMemberMapper;
import com.plateform.entity.Member;
import com.plateform.entity.MemberExample;
import com.plateform.service.TempMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempMemberServiceImpl implements TempMemberService {


    @Autowired
    //private TempMemberMapper tempMemberMapper;
    private TempMemberMapper tempMemberMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tempMemberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member record) {
        return tempMemberMapper.insert(record);
    }

    @Override
    public Member selectByPrimaryKey(Integer id) {
        return tempMemberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Member> selectAll() {
        MemberExample memberExample = new MemberExample();
        //MemberExample.Criteria criteria = memberExample.createCriteria();
        //criteria.andIdEqualTo(1);
        return tempMemberMapper.selectByExample(memberExample);
    }

    @Override
    public int updateByPrimaryKey(Member record) {
        return tempMemberMapper.updateByPrimaryKey(record);
    }



    @Override
    public List<Member> selectByMemberNo(String memberNo) {
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return tempMemberMapper.selectByExample(example);
    }

}
