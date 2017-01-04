package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 2016-01-29 ping++ charge
 * 
 * @author linyuting
 */

public class OrderPayinfoPojo extends SuperPojo {

  private static final long serialVersionUID = 1L;
  private Long id;// 编号id
  private String channel;// 支付渠道
  private String outTradeNo;// 状态字符
  private String chargeId;// 唯一订单号（后台生成）
  private String transactionNo;// 支付订交易流水号
  private String amount;// 交易金额(分)
  private String feeType;// 货币种类
  private String timePaid;// 支付完成时间
  private String tradeStatus;// 交易状态(WAIT_BUYER_PAY;TRADE_FAIL;TRADE_SUCCESS)
  private String failureCode;// 订单的错误码
  private String failureMsg;// 订单错误消息描述
  private Long createBy;// 创建者
  private Date createDate;// 创建时间
  private Long updateBy;// 更新者
  private Date updateDate;// 更新时间
  private String remarks;// 备注信息
  private int version;// 版本：0.中文版1.英文版
  private String cmbTradeNo;// 招行交流号



  public String getCmbTradeNo() {
    return cmbTradeNo;
  }

  public void setCmbTradeNo(String cmbTradeNo) {
    this.cmbTradeNo = cmbTradeNo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getChargeId() {
    return chargeId;
  }

  public void setChargeId(String chargeId) {
    this.chargeId = chargeId;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getFeeType() {
    return feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public String getTimePaid() {
    return timePaid;
  }

  public void setTimePaid(String timePaid) {
    this.timePaid = timePaid;
  }

  public String getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus;
  }

  public String getFailureCode() {
    return failureCode;
  }

  public void setFailureCode(String failureCode) {
    this.failureCode = failureCode;
  }

  public String getFailureMsg() {
    return failureMsg;
  }

  public void setFailureMsg(String failureMsg) {
    this.failureMsg = failureMsg;
  }

  @Override
  public Long getCreateBy() {
    return createBy;
  }

  @Override
  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public Long getUpdateBy() {
    return updateBy;
  }

  @Override
  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getTransactionNo() {
    return transactionNo;
  }

  public void setTransactionNo(String transactionNo) {
    this.transactionNo = transactionNo;
  }

}
