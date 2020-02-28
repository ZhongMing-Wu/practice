package com.plateform.service.implement;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plateform.dao.MemberMapper;
import com.plateform.entity.Member;
import com.plateform.entity.MemberExample;
import com.plateform.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void deleteAll(){ memberMapper.deleteAll();}

    @Override
    public void alterId(){  memberMapper.alterId(); }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member record) {
        return memberMapper.insert(record);
    }

    @Override
    public Member selectByPrimaryKey(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Member> selectAll() {
        MemberExample memberExample = new MemberExample();
        return memberMapper.selectByExample(memberExample);
    }

    public List<Member> selectAll(Integer pageNum, Integer pageSize) {
        MemberExample memberExample = new MemberExample();
        PageHelper.startPage(pageNum, pageSize);
        return memberMapper.selectByExample(memberExample);
    }

    @Override
    public int updateByPrimaryKey(Member record) {
        return memberMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Member> selectByMemberNo(String memberNo) {
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andMemberNoEqualTo(memberNo);
        return memberMapper.selectByExample(memberExample);
    }

}
