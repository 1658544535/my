/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 商家银行账户表; InnoDB free: 43008 kB
 * 
 * @version 1.0
 * @author
 */
public class SellerBankPojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 银行名
   */
  private String bankName;
  /**
   * 省
   */
  private Integer province;
  /**
   * 城市
   */
  private Integer city;
  /**
   * 区域
   */
  private Integer area;
  /**
   * 开户支行
   */
  private String bankBranch;
  /**
   * 银行卡号
   */
  private String bankCardNo;
  /**
   * 开户人姓名
   */
  private String userName;
  /**
   * 手机号
   */
  private String phone;
  /**
   * 审核状态（0-未审核1-审核成功2-审核失败）
   */
  private Integer status;
  private Double balance;

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

  public void setBankName(String value) {
    bankName = value;
  }

  public String getBankName() {
    return bankName;
  }

  public void setProvince(Integer value) {
    province = value;
  }

  public Integer getProvince() {
    return province;
  }

  public void setCity(Integer value) {
    city = value;
  }

  public Integer getCity() {
    return city;
  }

  public void setArea(Integer value) {
    area = value;
  }

  public Integer getArea() {
    return area;
  }

  public void setBankBranch(String value) {
    bankBranch = value;
  }

  public String getBankBranch() {
    return bankBranch;
  }

  public void setBankCardNo(String value) {
    bankCardNo = value;
  }

  public String getBankCardNo() {
    return bankCardNo;
  }

  public void setUserName(String value) {
    userName = value;
  }

  public String getUserName() {
    return userName;
  }

  public void setPhone(String value) {
    phone = value;
  }

  public String getPhone() {
    return phone;
  }

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }
}
