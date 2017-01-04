package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class UserDistributionInfoPojo extends SuperPojo {

  private Long id;// id
  private Long userId;// 用户编码, 对应sys_login.id
  private String name;// 姓名
  private Integer sex;// 性别(取业务字典：1，男2，女)
  private String personCode;// 身份证号
  private String personCodeImg;// 身份证图片
  private Integer status;// 状态： 1：通过审核 0：审核中 -1：未通过

  private String sexName;// 性别
  private String statusName;// 状态名称
  private String userName;// 用户名称

  private String loginname;// 账号
  private String createDateStr;// 创建时间
  private String externalSignCode;// 邀请码

  public String getExternalSignCode() {
    return externalSignCode;
  }

  public void setExternalSignCode(String externalSignCode) {
    this.externalSignCode = externalSignCode;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getPersonCode() {
    return personCode;
  }

  public void setPersonCode(String personCode) {
    this.personCode = personCode;
  }

  public String getPersonCodeImg() {
    return personCodeImg;
  }

  public void setPersonCodeImg(String personCodeImg) {
    this.personCodeImg = personCodeImg;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getSexName() {
    return sexName;
  }

  public void setSexName(String sexName) {
    this.sexName = sexName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }
}
