package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 用户钱包交易记录-- userWalletLog by zzh
 * 
 */
public class UserWalletLogPojo extends SuperPojo {
  private Long id; // 序号id
  private Long userId; // 会员id
  private Double curBal; // 当前余额
  private Long type; // 交易方向（0收入1支出）
  private Double tradeAmt; // 交易金额
  private Long source; // 来源
  private String friendName; // 分享好友的名称
  private Long orderId; // 订单id
  private String outTradeNo; // 交易单号
  private String createDateStr; // 交易时间STR
  private String createDateStartStr;// 检索范围开始时间STR
  private String createDateEndStr; // 检索范围结束时间STR
  private String typeName; // 交易方向名称
  private String loginName; // 用户账号
  private String sourceName; // 来源账号
  private String orderNo; // 订单号
  private String machineCode; // 机器码
  private String registeredTime; // 来源账号注册时间

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
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

  public Double getCurBal() {
    return curBal;
  }

  public void setCurBal(Double curBal) {
    this.curBal = curBal;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public Double getTradeAmt() {
    return tradeAmt;
  }

  public void setTradeAmt(Double tradeAmt) {
    this.tradeAmt = tradeAmt;
  }

  public Long getSource() {
    return source;
  }

  public void setSource(Long source) {
    this.source = source;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (this.createDate != null) {
      this.createDateStr = StringUtil.dateString(this.createDate);
    }
  }

  public String getCreateDateStartStr() {
    return createDateStartStr;
  }

  public void setCreateDateStartStr(String createDateStartStr) {
    this.createDateStartStr = createDateStartStr;
  }

  public String getCreateDateEndStr() {
    return createDateEndStr;
  }

  public void setCreateDateEndStr(String createDateEndStr) {
    this.createDateEndStr = createDateEndStr;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getFriendName() {
    return friendName;
  }

  public void setFriendName(String friendName) {
    this.friendName = friendName;
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getMachineCode() {
    return machineCode;
  }

  public void setMachineCode(String machineCode) {
    this.machineCode = machineCode;
  }

  public String getRegisteredTime() {
    return registeredTime;
  }

  public void setRegisteredTime(String registeredTime) {
    this.registeredTime = registeredTime;
  }
}
