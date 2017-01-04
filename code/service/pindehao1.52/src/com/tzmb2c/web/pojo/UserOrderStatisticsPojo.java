package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 用户订单统计结算-- UserOrderStatistics by Lie
 * 
 */

public class UserOrderStatisticsPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private String year;// 年份
  private String month;// 月份
  private Double money;// 结算金额
  private String isSettleAccounts;// 是否结算
  private String userName, isSettleAccountsName;


  public String getIsSettleAccountsName() {
    return isSettleAccountsName;
  }

  public void setIsSettleAccountsName(String isSettleAccountsName) {
    this.isSettleAccountsName = isSettleAccountsName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public String getIsSettleAccounts() {
    return isSettleAccounts;
  }

  public void setIsSettleAccounts(String isSettleAccounts) {
    this.isSettleAccounts = isSettleAccounts;
  }



}
