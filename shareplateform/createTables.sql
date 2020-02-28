/*
共享平台项目部分表
建表语句
schemas：spdatabase
url：jdbc:mysql://localhost:3306/spdatabase?serverTimezone=GMT

2019-9-17 21.44
*/


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for SPT_LOGIN (登录数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_LOGIN`;
create table spt_login
(
    ID int auto_increment comment '主键'
        primary key,
    LOGINUSER varchar(64) default '' null comment '登陆者编号',
    NAME varchar(64) default '' null comment '用户名',
    PASSWORD varchar(64) default '' null comment '密码',
    ROLE varchar(64) default '' null comment '角色'
)
    charset=utf8;


-- ----------------------------
-- Table structure for SPT_PERMISSION (角色权限表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_PERMISSION`;
create table spt_permission
(
    ID int auto_increment comment '主键'
        primary key,
    ROLE varchar(64) default '' null comment '角色名',
    PERMISSION varchar(64) default '' null comment '权限名'
)
    charset=utf8;




-- ----------------------------
-- Table structure for SPT_MEMBER (加盟会员数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_MEMBER`;
create table spt_member
(
    ID int auto_increment comment '主键'
        primary key,
    MEMBER_NO varchar(64) default '' null comment '会员编号',
    MEMBER_NAME varchar(255) default '' null comment '会员名称',
    MEMBER_ABBR varchar(255) default '' null comment '会员简称',
    MEMBER_REGISTER_ADDR varchar(512) default '' null comment '会员注册地址',
    MEMBER_ADDR varchar(512) default '' null comment '会员运营地址',
    CORPORATION varchar(255) default '' null comment '法定代表人',
    TOP_MANAGER varchar(255) default '' null comment '最高管理人',
    CONTACT varchar(255) default '' null comment '联系人',
    CONTACT_INFORMATION varchar(512) default '' null comment '联系人联系方式',
    UNIT_PROPERTY varchar(64) default '' null comment '单位性质',
    INDUSTRY varchar(64) default '' null comment '所属行业',
    BUSINESS_AREA varchar(64) default '' null comment '经营范围',
    BUSINESS_EXPIRE varchar(64) default '' null comment '营业期限',
    CAPTIAL varchar(64) default '' null comment '注册资本',
    STAFF_COUNT varchar(64) default '' null comment '员工人数',
    PRODUCT_VALUE varchar(64) default '' null comment '近年产值',
    REMARK varchar(512) default '' null comment '其它',
    RES_DESC  varchar(512) default '' null comment '资源概要',
    ABILITY_DESC varchar(512) default '' null comment '能力概要',
    JOIN_TIME date default '1980-01-01' null comment '加盟时间',
    SERVICE_TIME int default 0 null comment '平台提供服务次数',
    SERVICE_SUCCESS_TIME int default 0 null comment '服务成功次数',
    PROTOCOL_URL varchar(255) default '' null comment '加盟协议地址'
)
    charset=utf8;


-- ----------------------------
-- Table structure for SPP_MEMBER (加盟会员临时数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPP_MEMBER`;
create table spp_member
(
    ID int auto_increment comment '主键'
        primary key,
    MEMBER_NO varchar(64) default '' null comment '会员编号',
    MEMBER_NAME varchar(255) default '' null comment '会员名称',
    MEMBER_ABBR varchar(255) default '' null comment '会员简称',
    MEMBER_REGISTER_ADDR varchar(512) default '' null comment '会员注册地址',
    MEMBER_ADDR varchar(512) default '' null comment '会员运营地址',
    CORPORATION varchar(255) default '' null comment '法定代表人',
    TOP_MANAGER varchar(255) default '' null comment '最高管理人',
    CONTACT varchar(255) default '' null comment '联系人',
    CONTACT_INFORMATION varchar(512) default '' null comment '联系人联系方式',
    UNIT_PROPERTY varchar(64) default '' null comment '单位性质',
    INDUSTRY varchar(64) default '' null comment '所属行业',
    BUSINESS_AREA varchar(64) default '' null comment '经营范围',
    BUSINESS_EXPIRE varchar(64) default '' null comment '营业期限',
    CAPTIAL varchar(64) default '' null comment '注册资本',
    STAFF_COUNT varchar(64) default '' null comment '员工人数',
    PRODUCT_VALUE varchar(64) default '' null comment '近年产值',
    REMARK varchar(512) default '' null comment '其它',
    RES_DESC  varchar(512) default '' null comment '资源概要',
    ABILITY_DESC varchar(512) default '' null comment '能力概要',
    JOIN_TIME date default '1980-01-01' null comment '加盟时间',
    SERVICE_TIME int default 0 null comment '平台提供服务次数',
    SERVICE_SUCCESS_TIME int default 0 null comment '服务成功次数',
    PROTOCOL_URL varchar(255) default '' null comment '加盟协议地址'
)
    charset=utf8;



-- ----------------------------
-- Table structure for SPT_HRES (加盟能力（硬资源）数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_HRES`;
create table spt_hres
(
    ID int auto_increment comment '主键'
        primary key,
    ABILITY_NO varchar(64) default '' null comment '能力编号',
    MEMBER_NO varchar(64) default '' null comment '所属会员编号',
    ABILITY_DESCRIPTION varchar(512) default '' null comment '能力描述',
    HUMAN_RESOURCE varchar(512) default '' null comment '对应人力资源',
    FACILITY_RESOURCE varchar(512) default '' null comment '对应设备资源',
    ENV_RESOURCE varchar(512) default '' null comment '对应环境条件',
    SOFT_RESOURCE varchar(512) default '' null comment '对应软资源',
    OTHER_RESOURCE varchar(512) default '' null comment '其它资源',
    SHARING_CONDITION varchar(512) default '' null comment '共享条件',
    COST_ESTIMATE varchar(512) default '' null comment '成本估算',
    REMARK varchar(512) default '' null comment '其它备注',
    JOIN_TIME date default '1980-01-01' null comment '提交时间'
)
    charset=utf8;

-- ----------------------------
-- Table structure for SPP_HRES (加盟能力（硬资源）临时数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPP_HRES`;
create table spp_hres
(
    ID int auto_increment comment '主键'
        primary key,
    ABILITY_NO varchar(64) default '' null comment '能力编号',
    MEMBER_NO varchar(64) default '' null comment '所属会员编号',
    ABILITY_DESCRIPTION varchar(512) default '' null comment '能力描述',
    HUMAN_RESOURCE varchar(512) default '' null comment '对应人力资源',
    FACILITY_RESOURCE varchar(512) default '' null comment '对应设备资源',
    ENV_RESOURCE varchar(512) default '' null comment '对应环境条件',
    SOFT_RESOURCE varchar(512) default '' null comment '对应软资源',
    OTHER_RESOURCE varchar(512) default '' null comment '其它资源',
    SHARING_CONDITION varchar(512) default '' null comment '共享条件',
    COST_ESTIMATE varchar(512) default '' null comment '成本估算',
    REMARK varchar(512) default '' null comment '其它备注',
    JOIN_TIME date default '1980-01-01' null comment '提交时间'
)
    charset=utf8;



-- ----------------------------
-- Table structure for SPT_SRES (加盟能力（软资源）数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_SRES`;
create table spt_sres
(
    ID int auto_increment comment '主键'
        primary key,
    ABILITY_NO varchar(64) default '' null comment '能力编号',
    MEMBER_NO varchar(64) default '' null comment '所属会员编号',
    ABILITY_DESCRIPTION varchar(512) default '' null comment '能力描述',
    RESEARCH_DIRECTION varchar(512) default '' null comment '主要研究方向',
    TEAM_RESOURCE_DESC varchar(512) default '' null comment '团队资源描述',
    TEAM_BELONG_TO varchar(512) default '' null comment '团队隶属',
    ABILITY_BASE varchar(512) default '' null comment '能力基础',
    OTHER_RESOURCE varchar(512) default '' null comment '其它资源',
    SHARING_CONDITION varchar(512) default '' null comment '共享条件',
    COST_ESTIMATE varchar(512) default '' null comment '成本估算',
    REMARK varchar(512) default '' null comment '其它备注',
    JOIN_TIME date default '1980-01-01' null comment '提交时间'
)
    charset=utf8;
-- ----------------------------
-- Table structure for SPP_SRES (加盟能力（软资源）临时数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPP_SRES`;
create table spp_sres
(
    ID int auto_increment comment '主键'
        primary key,
    ABILITY_NO varchar(64) default '' null comment '能力编号',
    MEMBER_NO varchar(64) default '' null comment '所属会员编号',
    ABILITY_DESCRIPTION varchar(512) default '' null comment '能力描述',
    RESEARCH_DIRECTION varchar(512) default '' null comment '主要研究方向',
    TEAM_RESOURCE_DESC varchar(512) default '' null comment '团队资源描述',
    TEAM_BELONG_TO varchar(512) default '' null comment '团队隶属',
    ABILITY_BASE varchar(512) default '' null comment '能力基础',
    OTHER_RESOURCE varchar(512) default '' null comment '其它资源',
    SHARING_CONDITION varchar(512) default '' null comment '共享条件',
    COST_ESTIMATE varchar(512) default '' null comment '成本估算',
    REMARK varchar(512) default '' null comment '其它备注',
    JOIN_TIME date default '1980-01-01' null comment '提交时间'
)
    charset=utf8;



-- ----------------------------
-- Table structure for SPT_TECH (加盟能力（新技术）数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_TECH`;
create table spt_tech
(
    ID int auto_increment comment '主键'
        primary key,
    ABILITY_NO varchar(64) default '' null comment '能力编号',
    MEMBER_NO varchar(64) default '' null comment '所属会员编号',
    ABILITY_DESCRIPTION varchar(512) default '' null comment '能力描述',
    ABILITY_CATEGORY varchar(512) default '' null comment '能力类别',
    PRO_DIRECTION varchar(512) default '' null comment '专业方向',
    PRINCIPLE varchar(512) default '' null comment '原理功能',
    ACHIVEMENT varchar(512) default '' null comment '项目成果',
    APPLYING_AREA varchar(512) default '' null comment '适用范围',
    COOP_INTENTION varchar(512) default '' null comment '合作意向',
    RESTRICTION varchar(512) default '' null comment '约束条件',
    COST_ESTIMATE varchar(512) default '' null comment '成本估算',
    REMARK varchar(512) default '' null comment '其它备注',
    SUBMIT_TIME date default '1980-01-01' null comment '提交时间',
    SERVICE_TIME int default 0 null comment '平台提供服务次数',
    SERVICE_SUCCESS_TIME int default 0 null comment '服务成功次数',
    GRADE_EVALUATION varchar(64) default '' null comment '能力等级评价'
)
    charset=utf8;


-- ----------------------------
-- Table structure for SPP_TECH (加盟能力（新技术）临时数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPP_TECH`;
create table spp_tech
(
    ID int auto_increment comment '主键'
        primary key,
    ABILITY_NO varchar(64) default '' null comment '能力编号',
    MEMBER_NO varchar(64) default '' null comment '所属会员编号',
    ABILITY_DESCRIPTION varchar(512) default '' null comment '能力描述',
    ABILITY_CATEGORY varchar(512) default '' null comment '能力类别',
    PRO_DIRECTION varchar(512) default '' null comment '专业方向',
    PRINCIPLE varchar(512) default '' null comment '原理功能',
    ACHIVEMENT varchar(512) default '' null comment '项目成果',
    APPLYING_AREA varchar(512) default '' null comment '适用范围',
    COOP_INTENTION varchar(512) default '' null comment '合作意向',
    RESTRICTION varchar(512) default '' null comment '约束条件',
    COST_ESTIMATE varchar(512) default '' null comment '成本估算',
    REMARK varchar(512) default '' null comment '其它备注',
    SUBMIT_TIME date default '1980-01-01' null comment '提交时间',
    SERVICE_TIME int default 0 null comment '平台提供服务次数',
    SERVICE_SUCCESS_TIME int default 0 null comment '服务成功次数',
    GRADE_EVALUATION varchar(64) default '' null comment '能力等级评价'
)
    charset=utf8;



-- ----------------------------
-- Table structure for SPT_USER (平台用户数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_USER`;
create table spt_user
(
    ID int auto_increment comment '主键'
        primary key,
    USER_NO varchar(64) default '' null comment '用户编号',
    USER_NAME varchar(255) default '' null comment '用户名称',
    USER_PROPERTY varchar(64) default '' null comment '用户性质',
    REGULATED_PROOF varchar(512) default '' null comment '法定证明',
    USER_ADDR varchar(512) default '' null comment '用户地址',
    USER_PHONE varchar(20) default '' null comment '用户联系方式（固定电话）',
    USER_EMAIL varchar(64) default '' null comment '用户联系方式（邮箱）',
    CONTACT varchar(255) default '' null comment '联系人',
    CONTACT_TELEPHONE varchar(20) default '' null comment '联系人联系方式（手机号）',
    REGISTER_TIME date default '1980-01-01' null comment '提交时间',
    REMARK varchar(512) default '' null comment '其它备注'
)
    charset=utf8;



-- ----------------------------
-- Table structure for SPT_DEMAND (用户提交问题数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_DEMAND`;
create table spt_demand
(
    ID int auto_increment comment '主键'
        primary key,
    DEMAND_NO varchar(64) default '' null comment '问题编号',
    USER_NO varchar(64) default '' null comment '提问用户编号',
    DEMAND_TIME date default '1980-01-01' null comment '问题提出时间',
    DEMAND_DESCRIPTION varchar(512) default '' null comment '问题描述',
    DEMAND_CATEGORY varchar(512) default '' null comment '问题分类',
    RESTRICTION varchar(512) default '' null comment '约束条件',
    REMARK varchar(512) default '' null comment '其它备注',
    STATUS varchar(64) default '' null comment '问题状态'
)
    charset=utf8;

-- ----------------------------
-- Table structure for SPT_ORDER (用户需求订单表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_ORDER`;
create table spt_order
(
    ID int auto_increment comment '主键'
        primary key,
    DEMAND_NO varchar(64) default '' null comment '问题编号',
    USER_NO varchar(64) default '' null comment '提问用户编号',
    MEMBER_NO varchar(64) default '' null comment '拟定合作加盟商编号',
    ABILITY_NO varchar(64) default '' null comment '相关能力编号',
    HANDLER varchar(255) default '' null comment '平台受理人',
    GENERATED_TIME date default '1980-01-01' null comment '订单生成时间',
    USER_OPINION varchar(64) default '' null comment '用户意见',
    MEMBER_OPINION varchar(64) default '' null comment '加盟商意见',
    ADMIN_OPINION varchar(64) default '' null comment '管理员意见',
    MEETING_RESULT varchar(64) default '' null comment '会谈结果',
    STATUS varchar(64) default '' null comment '处理状态',
    SOLVE_TIME date default '1980-01-01' null comment '问题解决时间',
    CONTRACT_AMOUNT varchar(64) default '' null comment '合作合同金额',
    USER_EVALUATION varchar(64) default '' null comment '用户评价',
    MEMBER_EVALUATION varchar(64) default '' null comment '加盟会员评价',
    REMARK varchar(512) default '' null comment '其它备注'
)
    charset=utf8;

-- ----------------------------
-- Table structure for SPT_ADVICE (会员意见数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_ADVICE`;
create table spt_advice
(
    ID int auto_increment comment '主键'
        primary key,
    ADVICE_NO varchar(64) default '' null comment '意见编号',
    MEMBER_NO varchar(64) default '' null comment '会员编号',
    ADVICE_TIME date default '1980-01-01' null comment '提出时间',
    ADVICE_CONTENT varchar(512) default '' null comment '意见内容',
    HANDLER varchar(255) default '' null comment '平台受理人',
    HANDLE_TIME date default '1980-01-01' null comment '受理时间',
    HANDLE_RESULT varchar(512) default '' null comment '受理结果',
    DETAIL varchar(512) default '' null comment '双方交流详情',
    REMARK varchar(512) default '' null comment '其它备注'
)
    charset=utf8;



-- ----------------------------
-- Table structure for SPT_BIDDOC (会员竞标台发标数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_BIDDOC`;
create table spt_biddoc
(
    ID int auto_increment comment '主键'
        primary key,
    BIDDOC_NO varchar(64) default '' null comment '标书编号',
    BIDDER varchar(64) default '' null comment '发标者编号',
    BIDDOC_TIME date default '1980-01-01' null comment '发布时间',
    BIDDOC_CONTENT varchar(512) default '' null comment '标书内容',
    STATUS varchar(64) default '' null comment '投标状态',
    BID_RESULT varchar(512) default '' null comment '中标结果'
)
    charset=utf8;



-- ----------------------------
-- Table structure for SPT_BID (会员投标数据表)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_BID`;
create table spt_bid
(
    ID int auto_increment comment '主键'
        primary key,
    BID_NO varchar(64) default '' null comment '投标编号',
    MEMBER_NO varchar(64) default '' null comment '投标会员编号',
    BIDDOC_NO varchar(64) default '' null comment '发标标书编号',
    BID_TIME date default '1980-01-01' null comment '投标时间',
    BID_CONTENT varchar(512) default '' null comment '投标内容',
    STATUS varchar(64) default '' null comment '投标状态',
    BID_RESULT varchar(512) default '' null comment '中标结果',
    HANDLER varchar(255) default '' null comment '平台受理人',
    DETAIL varchar(512) default '' null comment '交流详情'
)
    charset=utf8;



-- ----------------------------
-- Table structure for SPT_ADMIN (管理人员数据库)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_ADMIN`;
create table spt_admin
(
    ID int auto_increment comment '主键'
        primary key,
    ADMIN_NO varchar(64) default '' null comment '管理人员编号',
    ADMIN_NAME varchar(255) default '' null comment '管理人员名称',
    PERMISSION_LEVEL varchar(64) default '' null comment '权限等级',
    ADMIN_DESCRIBE varchar(64) default '' null comment '管理人员描述'
)
    charset=utf8;


-- ----------------------------
-- Table structure for SPT_TRANS (管理人员事务数据库)
-- ----------------------------
DROP TABLE IF EXISTS `SPT_TRANS`;
create table spt_trans
(
    ID int auto_increment comment '主键'
        primary key,
    TRANS_TYPE varchar(64) default '' null comment '事务类型',
    APPLICANT_NO varchar(64) default '' null comment '申请人编号',
    APPLY_TIME datetime default '1980-01-01 00:00:00' null comment '申请时间',
    HANDLER_ID varchar(64) default '' null comment '处理人编号',
    HANDLE_TIME datetime default '1980-01-01 00:00:00' null comment '处理时间',
    TRANS_OBJID varchar(64) default '' null comment '关联事物编号',
    TRANS_STATUS varchar(64) default '' null comment '事务处理状态',
    REMARK varchar(255) default '' null comment '备注'
)
    charset=utf8;

-- ----------------------------
-- Table structure for spt_member_image
-- ----------------------------
DROP TABLE IF EXISTS `spt_member_image`;
CREATE TABLE `spt_member_image`  (
  ID int auto_increment comment '主键'
        primary key,
  `MEMBER_NO` varchar(512)  default '' null comment '加盟商编号',
  `FILENAME` varchar(512)   default '' null comment '存储在服务器上的文件名',
  `type` varchar(128)  default '' null comment '用于表示是哪种图片'
)
  charset=utf8;

  -- ----------------------------
-- Table structure for spp_member_image
-- ----------------------------
DROP TABLE IF EXISTS `spp_member_image`;
CREATE TABLE `spp_member_image`  (
  ID int auto_increment comment '主键'
        primary key,
  `MEMBER_NO` varchar(512)  default '' null comment '加盟商编号',
  `FILENAME` varchar(512)   default '' null comment '存储在服务器上的文件名',
  `type` varchar(128)  default '' null comment '用于表示是哪种图片'
)
  charset=utf8;

  -- Table structure for spt_user_image
-- ----------------------------
DROP TABLE IF EXISTS `spt_user_image`;
CREATE TABLE `spt_user_image`  (
  ID int auto_increment comment '主键'
        primary key,
  `USER_NO` varchar(512)  default '' null comment '用户编号',
  `FILENAME` varchar(512)   default '' null comment '存储在服务器上的文件名',
  `type` varchar(128)  default '' null comment '用于表示是哪种图片'
)
  charset=utf8;

SET FOREIGN_KEY_CHECKS=1;