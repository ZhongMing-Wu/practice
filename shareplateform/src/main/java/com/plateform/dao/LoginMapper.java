package com.plateform.dao;

import com.plateform.entity.Login;
import com.plateform.entity.LoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    long countByExample(LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    int deleteByExample(LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    int insert(Login record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    int insertSelective(Login record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    List<Login> selectByExample(LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    Login selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    int updateByExampleSelective(@Param("record") Login record, @Param("example") LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    int updateByExample(@Param("record") Login record, @Param("example") LoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    int updateByPrimaryKeySelective(Login record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spt_login
     *
     * @mbg.generated Thu Oct 10 20:42:10 CST 2019
     */
    int updateByPrimaryKey(Login record);
}