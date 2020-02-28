package com.plateform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plateform.entity.classset.EntityWithStatus;

import java.util.Date;

public class Demand implements EntityWithStatus {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.ID
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.DEMAND_NO
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    private String demandNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.USER_NO
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    private String userNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.DEMAND_TIME
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
         @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date demandTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.DEMAND_DESCRIPTION
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    private String demandDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.DEMAND_CATEGORY
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    private String demandCategory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.RESTRICTION
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    private String restriction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.REMARK
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column spt_demand.STATUS
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.ID
     *
     * @return the value of spt_demand.ID
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.ID
     *
     * @param id the value for spt_demand.ID
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.DEMAND_NO
     *
     * @return the value of spt_demand.DEMAND_NO
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public String getDemandNo() {
        return demandNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.DEMAND_NO
     *
     * @param demandNo the value for spt_demand.DEMAND_NO
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo == null ? null : demandNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.USER_NO
     *
     * @return the value of spt_demand.USER_NO
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.USER_NO
     *
     * @param userNo the value for spt_demand.USER_NO
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.DEMAND_TIME
     *
     * @return the value of spt_demand.DEMAND_TIME
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public Date getDemandTime() {
        return demandTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.DEMAND_TIME
     *
     * @param demandTime the value for spt_demand.DEMAND_TIME
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setDemandTime(Date demandTime) {
        this.demandTime = demandTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.DEMAND_DESCRIPTION
     *
     * @return the value of spt_demand.DEMAND_DESCRIPTION
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public String getDemandDescription() {
        return demandDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.DEMAND_DESCRIPTION
     *
     * @param demandDescription the value for spt_demand.DEMAND_DESCRIPTION
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setDemandDescription(String demandDescription) {
        this.demandDescription = demandDescription == null ? null : demandDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.DEMAND_CATEGORY
     *
     * @return the value of spt_demand.DEMAND_CATEGORY
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public String getDemandCategory() {
        return demandCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.DEMAND_CATEGORY
     *
     * @param demandCategory the value for spt_demand.DEMAND_CATEGORY
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setDemandCategory(String demandCategory) {
        this.demandCategory = demandCategory == null ? null : demandCategory.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.RESTRICTION
     *
     * @return the value of spt_demand.RESTRICTION
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public String getRestriction() {
        return restriction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.RESTRICTION
     *
     * @param restriction the value for spt_demand.RESTRICTION
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setRestriction(String restriction) {
        this.restriction = restriction == null ? null : restriction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.REMARK
     *
     * @return the value of spt_demand.REMARK
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.REMARK
     *
     * @param remark the value for spt_demand.REMARK
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_demand.STATUS
     *
     * @return the value of spt_demand.STATUS
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_demand.STATUS
     *
     * @param status the value for spt_demand.STATUS
     *
     * @mbg.generated Tue Oct 29 16:19:12 CST 2019
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}