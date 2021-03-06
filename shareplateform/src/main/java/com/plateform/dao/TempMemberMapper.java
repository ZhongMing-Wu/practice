package com.plateform.dao;

import com.plateform.entity.Member;
import com.plateform.entity.MemberExample;
import java.util.List;


import org.apache.ibatis.annotations.Param;

public interface TempMemberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    long countByExample(MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int deleteByExample(MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int insert(Member record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int insertSelective(Member record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    List<Member> selectByExample(MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    Member selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_member
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int updateByPrimaryKey(Member record);
}