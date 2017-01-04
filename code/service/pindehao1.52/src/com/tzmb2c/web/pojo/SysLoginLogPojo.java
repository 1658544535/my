package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 登录信息记录-- sys_login_log by Lie
 * 
 */

public class SysLoginLogPojo extends SuperPojo {
  private Long id; // 编号
  private Long userId; // 用户id
  private String loginIp; // 最后登录ip
  private Date loginDate;// 最后登录时间
  private String userName, loginDateName, nums;
  private Long activeUser;// 活跃人数

  public String getNums() {
    return nums;
  }

  public void setNums(String nums) {
    this.nums = nums;
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

  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
    this.loginDateName = StringUtil.dateToString(this.loginDate);
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLoginDateName() {
    return loginDateName;
  }

  public void setLoginDateName(String loginDateName) {
    this.loginDateName = loginDateName;
  }

  public Long getActiveUser() {
    return activeUser;
  }

  public void setActiveUser(Long activeUser) {
    this.activeUser = activeUser;
  }


}
