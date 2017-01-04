package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class RedPacketCodePojo extends SuperPojo {

  private Long id;// ID
  private Long userId;// 用户ID
  private String code;// 红包邀请码
  private Long createTime;// 添加时间
  private String createTimeStr;// 添加时间
  private String amout;// 红包金额
  private String outTradeId;// 微信红包流水账号
  private Integer status;// 状态：0无效 1有效
  private String userName;// 会员名称
  private String statusName;// 状态名称

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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public String getCreateTimeStr() {
    return createTimeStr;
  }

  public void setCreateTimeStr(String createTimeStr) {
    this.createTimeStr = createTimeStr;
  }

  public String getAmout() {
    return amout;
  }

  public void setAmout(String amout) {
    this.amout = amout;
  }

  public String getOutTradeId() {
    return outTradeId;
  }

  public void setOutTradeId(String outTradeId) {
    this.outTradeId = outTradeId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
}
