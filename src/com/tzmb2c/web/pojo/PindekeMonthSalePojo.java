/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 拼得客当月销售额记录表; InnoDB free: 43008 kB
 * 
 * @version 1.0
 * @author
 */
public class PindekeMonthSalePojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 类型 :1为排行榜2为30天销售额
   */
  private Integer type;
  /**
   * 区间时间
   */
  private String sectionTime;
  /**
   * 累计返佣金额
   */
  private Double total;
  /**
   * 是否已返佣奖励(1是0否)
   */
  private Integer isSettle;
  /**
   * 奖励金额
   */
  private Double settleAmt;
  /**
   * 排名
   */
  private Integer ranking;
  /**
   * 邀请者id
   */
  private Long inviterId;
  private String userLogo;
  private String userName;
  private String name, phone, applyDate, inviterName;
  private String pindekeNumber;

  public String getInviterName() {
    return inviterName;
  }

  public void setInviterName(String inviterName) {
    this.inviterName = inviterName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getApplyDate() {
    return applyDate;
  }

  public void setApplyDate(String applyDate) {
    this.applyDate = applyDate;
  }

  public String getUserLogo() {
    return userLogo;
  }

  public void setUserLogo(String userLogo) {
    this.userLogo = userLogo;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setUserId(Long value) {
    userId = value;
  }

  public Long getUserId() {
    return userId;
  }

  public void setType(Integer value) {
    type = value;
  }

  public Integer getType() {
    return type;
  }

  public void setSectionTime(String value) {
    sectionTime = value;
  }

  public String getSectionTime() {
    return sectionTime;
  }

  public void setTotal(Double value) {
    total = value;
  }

  public Double getTotal() {
    return total;
  }

  public void setIsSettle(Integer value) {
    isSettle = value;
  }

  public Integer getIsSettle() {
    return isSettle;
  }

  public void setSettleAmt(Double value) {
    settleAmt = value;
  }

  public Double getSettleAmt() {
    return settleAmt;
  }

  public void setRanking(Integer value) {
    ranking = value;
  }

  public Integer getRanking() {
    return ranking;
  }

  public void setInviterId(Long value) {
    inviterId = value;
  }

  public Long getInviterId() {
    return inviterId;
  }

  public String getPindekeNumber() {
    return pindekeNumber;
  }

  public void setPindekeNumber(String pindekeNumber) {
    this.pindekeNumber = pindekeNumber;
  }
}
