/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 团免券; InnoDB free: 149504 kB
 * 
 * @version 1.0
 * @author
 */
public class GroupFreeCouponPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 激活状态，0未激活，1已激活
   */
  private Integer status;
  /**
   * 激活时间
   */
  private Date activeTime;
  /**
   * 失效时间
   */
  private Date invalidTime;
  /**
   * 是否已使用，0否1是
   */
  private Integer used;
  /**
   * 上次使用时间
   */
  private Date lastUseTime;
  /**
   * 来源
   */
  private Integer source;
  private String createDateStr;
  private String activeTimeStr;
  private String userName;
  private String lastUseTimeStr;
  private String invalidTimeStr;
  private String userLogo;
  private String userPhone;
  private String currentTime;
  /**
   * invitationUserId:拼得客用户ID
   */
  private Long invitationUserId;

  public Long getInvitationUserId() {
    return invitationUserId;
  }

  public void setInvitationUserId(Long invitationUserId) {
    this.invitationUserId = invitationUserId;
  }

  public String getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(String currentTime) {
    this.currentTime = currentTime;
  }

  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public String getUserLogo() {
    return userLogo;
  }

  public void setUserLogo(String userLogo) {
    this.userLogo = userLogo;
  }

  public String getInvalidTimeStr() {
    if (this.invalidTime != null) {
      return StringUtil.dateString(invalidTime);
    }
    return invalidTimeStr;
  }

  public void setInvalidTimeStr(String invalidTimeStr) {
    this.invalidTimeStr = invalidTimeStr;
  }

  public String getLastUseTimeStr() {
    if (this.lastUseTime != null) {
      return StringUtil.dateString(this.lastUseTime);
    }
    return lastUseTimeStr;
  }

  public void setLastUseTimeStr(String lastUseTimeStr) {
    this.lastUseTimeStr = lastUseTimeStr;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getCreateDateStr() {
    if (createDate != null) {
      return StringUtil.dateString(createDate);
    }
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getActiveTimeStr() {
    if (activeTime != null) {
      return StringUtil.dateString(activeTime);
    }
    return activeTimeStr;
  }

  public void setActiveTimeStr(String activeTimeStr) {
    this.activeTimeStr = activeTimeStr;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setUserId(Long value) {
    this.userId = value;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setActiveTime(Date value) {
    this.activeTime = value;
  }

  public Date getActiveTime() {
    return this.activeTime;
  }

  public void setInvalidTime(Date value) {
    this.invalidTime = value;
  }

  public Date getInvalidTime() {
    return this.invalidTime;
  }

  public void setUsed(Integer value) {
    this.used = value;
  }

  public Integer getUsed() {
    return this.used;
  }

  public void setLastUseTime(Date value) {
    this.lastUseTime = value;
  }

  public Date getLastUseTime() {
    return this.lastUseTime;
  }

  public void setSource(Integer value) {
    this.source = value;
  }

  public Integer getSource() {
    return this.source;
  }
}
