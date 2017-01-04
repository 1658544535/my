package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class HongbaoLogPojo extends SuperPojo {

  private Long id;// 红包记录id
  private Long userId;// 会员id
  private Long logTime;// 记录时间
  private Float money;// 红包金额，正为增加，负为减少
  private String remark;// 备注说明

  private String userName;// 会员名称
  private String logTimeDStr;// 记录时间

  private String moneyStr;// 红包金额，正为增加，负为减少

  public String getMoneyStr() {
    return moneyStr;
  }

  public void setMoneyStr(String moneyStr) {
    this.moneyStr = moneyStr;
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

  public Long getLogTime() {
    return logTime;
  }

  public void setLogTime(Long logTime) {
    this.logTime = logTime;
  }

  public Float getMoney() {
    return money;
  }

  public void setMoney(Float money) {
    this.money = money;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLogTimeDStr() {
    return logTimeDStr;
  }

  public void setLogTimeDStr(String logTimeDStr) {
    this.logTimeDStr = logTimeDStr;
  }
}
