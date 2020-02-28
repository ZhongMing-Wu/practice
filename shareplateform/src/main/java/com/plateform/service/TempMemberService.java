package com.plateform.service;

import com.plateform.entity.Member;

import java.util.List;

public interface TempMemberService {

    int deleteByPrimaryKey(Integer id);
    int insert(Member record);
    Member selectByPrimaryKey(Integer id);
    List<Member> selectAll();
    int updateByPrimaryKey(Member record);



    List<Member> selectByMemberNo(String memberNo);
}