package com.plateform.entity;

import com.plateform.service.implement.LoginServiceImpl;
import com.plateform.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class User {

    private Integer id;

    private String userNo;

    private String userName;

    private String userProperty;

    private String regulatedProof;

    private String userAddr;

    private String userPhone;

    private String userEmail;

    private String contact;

    private String contactTelephone;

    private Date registerTime;

    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.ID
     *
     * @return the value of spt_user.ID
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.ID
     *
     * @param id the value for spt_user.ID
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.USER_NO
     *
     * @return the value of spt_user.USER_NO
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.USER_NO
     *
     * @param userNo the value for spt_user.USER_NO
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.USER_NAME
     *
     * @return the value of spt_user.USER_NAME
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.USER_NAME
     *
     * @param userName the value for spt_user.USER_NAME
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.USER_PROPERTY
     *
     * @return the value of spt_user.USER_PROPERTY
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getUserProperty() {
        return userProperty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.USER_PROPERTY
     *
     * @param userProperty the value for spt_user.USER_PROPERTY
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setUserProperty(String userProperty) {
        this.userProperty = userProperty == null ? null : userProperty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.REGULATED_PROOF
     *
     * @return the value of spt_user.REGULATED_PROOF
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getRegulatedProof() {
        return regulatedProof;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.REGULATED_PROOF
     *
     * @param regulatedProof the value for spt_user.REGULATED_PROOF
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setRegulatedProof(String regulatedProof) {
        this.regulatedProof = regulatedProof == null ? null : regulatedProof.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.USER_ADDR
     *
     * @return the value of spt_user.USER_ADDR
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getUserAddr() {
        return userAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.USER_ADDR
     *
     * @param userAddr the value for spt_user.USER_ADDR
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr == null ? null : userAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.USER_PHONE
     *
     * @return the value of spt_user.USER_PHONE
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.USER_PHONE
     *
     * @param userPhone the value for spt_user.USER_PHONE
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.USER_EMAIL
     *
     * @return the value of spt_user.USER_EMAIL
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.USER_EMAIL
     *
     * @param userEmail the value for spt_user.USER_EMAIL
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.CONTACT
     *
     * @return the value of spt_user.CONTACT
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getContact() {
        return contact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.CONTACT
     *
     * @param contact the value for spt_user.CONTACT
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.CONTACT_TELEPHONE
     *
     * @return the value of spt_user.CONTACT_TELEPHONE
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getContactTelephone() {
        return contactTelephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.CONTACT_TELEPHONE
     *
     * @param contactTelephone the value for spt_user.CONTACT_TELEPHONE
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone == null ? null : contactTelephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.REGISTER_TIME
     *
     * @return the value of spt_user.REGISTER_TIME
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.REGISTER_TIME
     *
     * @param registerTime the value for spt_user.REGISTER_TIME
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column spt_user.REMARK
     *
     * @return the value of spt_user.REMARK
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column spt_user.REMARK
     *
     * @param remark the value for spt_user.REMARK
     *
     * @mbg.generated Sun Oct 06 18:58:14 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


    private static LoginServiceImpl loginService;
    private static UserServiceImpl userService;


    @Autowired
    public void setLoginService(LoginServiceImpl loginService) {
        User.loginService = loginService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        User.userService = userService;
    }

    //获得注册状态
    public static int getRegisterStage(String name){
        //用户的注册步骤
        //1、填写登录信息
        //2、填写身份信息
        //全部填完才能变成正式用户
        List<Login> loginResult = loginService.selectByName(name);
        if (loginService.selectByName(name).isEmpty())
        {
            return 0;
        }

        //检查该用户的角色属性
        if (!loginResult.get(0).getRole().equals("tempUser"))
        {
            return -1;
        }

        //当该用户没有编号时，判断为阶段1
        String userId = loginResult.get(0).getLoginuser();
        if (0 == userId.length())
        {
            return 1;
        }

        //当该用户有编号但是在spt_user表中没有查询到该用户的详细信息时，判断为阶段1
        List<User> infoResult = userService.selectByUserNo(userId);
        if (infoResult.isEmpty())
        {
            return 1;
        }


        //查询到时，用户处于各种资料都完备的状态，返回阶段2
        return 2;
    }

}