package com.plateform.entity;

public class Permission {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the trash column spt_permission.ID
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the trash column spt_permission.ROLE
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    private String role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the trash column spt_permission.PERMISSION
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    private String permission;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the trash column spt_permission.ID
     *
     * @return the value of spt_permission.ID
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the trash column spt_permission.ID
     *
     * @param id the value for spt_permission.ID
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the trash column spt_permission.ROLE
     *
     * @return the value of spt_permission.ROLE
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the trash column spt_permission.ROLE
     *
     * @param role the value for spt_permission.ROLE
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the trash column spt_permission.PERMISSION
     *
     * @return the value of spt_permission.PERMISSION
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    public String getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the trash column spt_permission.PERMISSION
     *
     * @param permission the value for spt_permission.PERMISSION
     *
     * @mbg.generated Sun Sep 29 19:13:57 CST 2019
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}