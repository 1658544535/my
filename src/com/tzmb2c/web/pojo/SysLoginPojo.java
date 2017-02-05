package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

public class SysLoginPojo extends SuperPojo {
  private Long id;// 编号
  private String loginname;// 帐号
  private String password;// 密码
  private String name;// 昵称
  private Integer sorting;// 排序
  private String type;// 管理类型：0.管理员1.普通用户2.供应商3.采购商
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String channel;// 来源渠道
  private String openid;// 微信openid
  private String qqOpenId;// QQ登录openid
  private String taobao_user_id;// 淘宝登录id
  private String statusName, typeName;
  private String createDateStr;// 创建时间（年月日）
  private String createDateStr1;// 创建时间（时分秒）
  private String createDateStr2;// 创建时间（时分）
  private String QQ;// 采购商qq
  private String manufacturerdate;// 供应商注册时间
  private String consumerdate;// 采购商注册时间
  private String agencydate;// 批发商注册时间
  private String login_date;// 最后登录时间
  private String login_date2;// 最后登录时间
  private String baidu_uid;// 百度id
  private String QQs;// 批发商qq
  private String image;// 用户登陆logo
  private Integer sex;// 性别(取业务字典：1，男2，女)
  private String birthday;// 生日
  private String token;// 融云token
  private String sinaid;// 新浪id
  private String distribName;// 分销商姓名
  private String sexName;// 分销商性别
  private Long inviterId;// 推荐者,对应sys_login.id
  private String inviterAct;// 推荐者帐号
  private String inviterName;// 推荐者名称
  private String externalSignCode;// 外部标识
  private String unionid;//
  private String babySexName;// 性别名称
  private String babyBirthday;// 生日yyyy-mm-dd
  private Integer babySex;// 性别(取业务字典：1-王子;2-公主;3-孕育中)
  private String createDateStartStr;
  private String createDateEndStr;
  private String regChannel;// 注册者来源
  private String userType;// 用于判断用户类型
  private String invitationCode;// 分享邀请码
  private String loginname2;// 用于获取验证码的手机号
  private Integer blackFlag;// 嫌疑对象
  private String imei;// 设备标识
  private Integer appapi;// 接口调用标识：1-youmiRegisNum.do
  private Integer userManufacturerIsNotNull;// 1-userManufacturer信息不为空
  private String form;
  // 用户性别
  private Integer userSex;
  private Integer source;// 用户来源：1-ios,2-android,3-weixin
  private Integer judgeSource;// 用来判断查询来源app或微信的用户
  private Integer isPindeke;// 拼得客标识
  private Double balance;
  private Double totalBalance;
  private Double balanceAdd;
  private Double totalBalanceAdd;
  private Double balanceReduce;
  private String remarks;


  public Double getBalanceReduce() {
    return balanceReduce;
  }

  public void setBalanceReduce(Double balanceReduce) {
    this.balanceReduce = balanceReduce;
  }

  public Double getTotalBalanceAdd() {
    return totalBalanceAdd;
  }

  public void setTotalBalanceAdd(Double totalBalanceAdd) {
    this.totalBalanceAdd = totalBalanceAdd;
  }

  public Double getBalanceAdd() {
    return balanceAdd;
  }

  public void setBalanceAdd(Double balanceAdd) {
    this.balanceAdd = balanceAdd;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Double getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(Double totalBalance) {
    this.totalBalance = totalBalance;
  }

  public Integer getIsPindeke() {
    return isPindeke;
  }

  public void setIsPindeke(Integer isPindeke) {
    this.isPindeke = isPindeke;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public String getForm() {
    return form;
  }

  public void setForm(String form) {
    this.form = form;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getInvitationCode() {
    return invitationCode;
  }

  public void setInvitationCode(String invitationCode) {
    this.invitationCode = invitationCode;
  }

  public String getRegChannel() {
    return regChannel;
  }

  public void setRegChannel(String regChannel) {
    this.regChannel = regChannel;
  }

  public String getBabySexName() {
    return babySexName;
  }

  public void setBabySexName(String babySexName) {
    this.babySexName = babySexName;
  }

  public String getBabyBirthday() {
    return babyBirthday;
  }

  public void setBabyBirthday(String babyBirthday) {
    this.babyBirthday = babyBirthday;
  }

  public String getExternalSignCode() {
    return externalSignCode;
  }

  public void setExternalSignCode(String externalSignCode) {
    this.externalSignCode = externalSignCode;
  }

  public String getUnionid() {
    return unionid;
  }

  public void setUnionid(String unionid) {
    this.unionid = unionid;
  }

  public String getInviterAct() {
    return inviterAct;
  }

  public void setInviterAct(String inviterAct) {
    this.inviterAct = inviterAct;
  }

  public Long getInviterId() {
    return inviterId;
  }

  public void setInviterId(Long inviterId) {
    this.inviterId = inviterId;
  }

  public String getInviterName() {
    return inviterName;
  }

  public void setInviterName(String inviterName) {
    this.inviterName = inviterName;
  }

  public String getDistribName() {
    return distribName;
  }

  public void setDistribName(String distribName) {
    this.distribName = distribName;
  }

  public String getSexName() {
    return sexName;
  }

  public void setSexName(String sexName) {
    this.sexName = sexName;
  }

  public String getSinaid() {
    return sinaid;
  }

  public void setSinaid(String sinaid) {
    this.sinaid = sinaid;
  }

  public String getQqOpenId() {
    return qqOpenId;
  }

  public void setQqOpenId(String qqOpenId) {
    this.qqOpenId = qqOpenId;
  }

  public String getQQ() {
    return QQ;
  }

  public void setQQ(String qQ) {
    QQ = qQ;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (this.createDate != null) {
      createDateStr = StringUtil.dateToString(this.createDate);
      createDateStr1 = StringUtil.dateString(this.createDate);
      createDateStr2 = StringUtil.dateStr(this.createDate);
    }
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getTaobao_user_id() {
    return taobao_user_id;
  }

  public void setTaobao_user_id(String taobao_user_id) {
    this.taobao_user_id = taobao_user_id;
  }

  public String getManufacturerdate() {
    return manufacturerdate;
  }

  public void setManufacturerdate(String manufacturerdate) {
    this.manufacturerdate = manufacturerdate;
  }

  public String getConsumerdate() {
    return consumerdate;
  }

  public void setConsumerdate(String consumerdate) {
    this.consumerdate = consumerdate;
  }

  public String getAgencydate() {
    return agencydate;
  }

  public void setAgencydate(String agencydate) {
    this.agencydate = agencydate;
  }

  public String getLogin_date() {
    return login_date;
  }

  public void setLogin_date(String login_date) {
    this.login_date = login_date;
    if (this.login_date != null) {
      setLogin_date2(StringUtil.dateStr(StringUtil.stringDate(this.login_date)));
    }
  }

  public String getBaidu_uid() {
    return baidu_uid;
  }

  public void setBaidu_uid(String baidu_uid) {
    this.baidu_uid = baidu_uid;
  }

  /**
   * @return the qQs
   */
  public String getQQs() {
    return QQs;
  }

  /**
   * @param qQs the qQs to set
   */
  public void setQQs(String qQs) {
    QQs = qQs;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Integer getBabySex() {
    return babySex;
  }

  public void setBabySex(Integer babySex) {
    this.babySex = babySex;
  }

  public String getCreateDateEndStr() {
    return createDateEndStr;
  }

  public void setCreateDateEndStr(String createDateEndStr) {
    this.createDateEndStr = createDateEndStr;
  }

  public String getCreateDateStartStr() {
    return createDateStartStr;
  }

  public void setCreateDateStartStr(String createDateStartStr) {
    this.createDateStartStr = createDateStartStr;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getLoginname2() {
    return loginname2;
  }

  public void setLoginname2(String loginname2) {
    this.loginname2 = loginname2;
  }

  public Integer getBlackFlag() {
    return blackFlag;
  }

  public void setBlackFlag(Integer blackFlag) {
    this.blackFlag = blackFlag;
  }

  public Integer getAppapi() {
    return appapi;
  }

  public void setAppapi(Integer appapi) {
    this.appapi = appapi;
  }

  public String getCreateDateStr1() {
    return createDateStr1;
  }

  public void setCreateDateStr1(String createDateStr1) {
    this.createDateStr1 = createDateStr1;
  }

  public Integer getUserManufacturerIsNotNull() {
    return userManufacturerIsNotNull;
  }

  public void setUserManufacturerIsNotNull(Integer userManufacturerIsNotNull) {
    this.userManufacturerIsNotNull = userManufacturerIsNotNull;
  }

  public String getCreateDateStr2() {
    return createDateStr2;
  }

  public void setCreateDateStr2(String createDateStr2) {
    this.createDateStr2 = createDateStr2;
  }

  public String getLogin_date2() {
    return login_date2;
  }

  public void setLogin_date2(String login_date2) {
    this.login_date2 = login_date2;
  }

  public Integer getUserSex() {
    return userSex;
  }

  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
  }

  public Integer getJudgeSource() {
    return judgeSource;
  }

  public void setJudgeSource(Integer judgeSource) {
    this.judgeSource = judgeSource;
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

}
