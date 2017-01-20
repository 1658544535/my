/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 支付宝红包口令表; InnoDB free: 43008 kB
 * 
 * @version 1.0
 * @author
 */
public class AliRedEnvelopePojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 邀请码
   */
  private String inviteCode;
  /**
   * 红包口令图片1
   */
  private String passwdImg1;
  /**
   * 红包口令图片2
   */
  private String passwdImg2;
  /**
   * 参团id
   */
  private Long attendId;
  /**
   * 团长用户id
   */
  private Long headerId;
  /**
   * 版本更新
   */
  private Integer versions;

  /**
   * 是否使用
   * 
   */
  private Integer isUsed;
  /**
   * 订单id
   * 
   */
  private Long orderId;



  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Integer getIsUsed() {
    return isUsed;
  }

  public void setIsUsed(Integer isUsed) {
    this.isUsed = isUsed;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setInviteCode(String value) {
    this.inviteCode = value;
  }

  public String getInviteCode() {
    return this.inviteCode;
  }

  public void setPasswdImg1(String value) {
    this.passwdImg1 = value;
  }

  public String getPasswdImg1() {
    return this.passwdImg1;
  }

  public void setPasswdImg2(String value) {
    this.passwdImg2 = value;
  }

  public String getPasswdImg2() {
    return this.passwdImg2;
  }

  public void setAttendId(Long value) {
    this.attendId = value;
  }

  public Long getAttendId() {
    return this.attendId;
  }

  public void setHeaderId(Long value) {
    this.headerId = value;
  }

  public Long getHeaderId() {
    return this.headerId;
  }

  public void setVersions(Integer value) {
    this.versions = value;
  }

  public Integer getVersions() {
    return this.versions;
  }
}
