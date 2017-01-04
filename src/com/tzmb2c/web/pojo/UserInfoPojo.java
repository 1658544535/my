package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 用户资料-- user_info by Lie
 * 
 */

public class UserInfoPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private Integer sex;// 性别(取业务字典：1，男2，女)
  private String birthday;// 生日
  private String email;// 邮箱地址
  private String tel;// 联系电话
  private String phone;// 手机号码
  private String fax;// 传真号码
  private String address;// 地址
  private String webSite;// 网址
  private String QQ;// QQ
  private String content;// 介绍
  private Integer channel;// 来源渠道(取业务字典：0.PC端1.APP 2.微信)
  private Integer isread;// 是否查看(取业务字典：0，否1，是)
  private String qrCode;// 二维码图片路径
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态(取业务字典：0未审核1已审核)
  private String loginName;// 登录名
  private String userName;// 用户昵称
  private String image;// 用户logo
  private String createDateStr;
  private String babyBirthday;// 宝宝出生日期
  private String userImage;
  private String sexName;
  private Integer babySex;




  public Integer getBabySex() {
    return babySex;
  }

  public void setBabySex(Integer babySex) {
    this.babySex = babySex;
  }

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public String getBabyBirthday() {
    return babyBirthday;
  }

  public void setBabyBirthday(String babyBirthday) {
    this.babyBirthday = babyBirthday;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getQQ() {
    return QQ;
  }

  public void setQQ(String qQ) {
    QQ = qQ;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public Integer getIsread() {
    return isread;
  }

  public void setIsread(Integer isread) {
    this.isread = isread;
  }

  public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getSexName() {
    return sexName;
  }

  public void setSexName(String sexName) {
    this.sexName = sexName;
  }
}
