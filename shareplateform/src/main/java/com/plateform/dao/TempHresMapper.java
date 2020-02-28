package com.plateform.dao;

import com.plateform.entity.Hres;
import com.plateform.entity.HresExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TempHresMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    long countByExample(HresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int deleteByExample(HresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int insert(Hres record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int insertSelective(Hres record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    List<Hres> selectByExample(HresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    Hres selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int updateByExampleSelective(@Param("record") Hres record, @Param("example") HresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int updateByExample(@Param("record") Hres record, @Param("example") HresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int updateByPrimaryKeySelective(Hres record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the trash table spp_hres
     *
     * @mbg.generated Sat Oct 05 10:08:15 CST 2019
     */
    int updateByPrimaryKey(Hres record);
}