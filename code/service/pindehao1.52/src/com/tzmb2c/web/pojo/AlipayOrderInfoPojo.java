package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 
 * @author fu 支付宝订单信息
 */

public class AlipayOrderInfoPojo extends SuperPojo {

  private Long id;// 编号ID
  private String outTradeNo;// 唯一订单号（后台生成）
  private String tradeNo;// 支付宝交易号,该交易在支付宝系统中的交易流水号。
  private String buyerEmail;// 买家支付宝账号，可以是Email或手机号码
  private String buyerId;// 买家支付宝账号对应的支付宝唯一用户号。
  private String totalFee;// 交易金额
  private String tradeStatus;// 交易状态
  private Integer payType;// 支付方式： 1 支付宝 2 网银
  private String outRefundNo;// 商户退款单号
  private String refundFee; // 退款金额
  private String refundReason;// 退款原因
  private String refundStatus;// 交易状态(PROCESSING;SUCCESS;FAIL)
  private Integer source;// 来源：0-拼团失败退款 ，1-订单退款

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public String getOutRefundNo() {
    return outRefundNo;
  }

  public void setOutRefundNo(String outRefundNo) {
    this.outRefundNo = outRefundNo;
  }

  public String getRefundFee() {
    return refundFee;
  }

  public void setRefundFee(String refundFee) {
    this.refundFee = refundFee;
  }

  public String getRefundReason() {
    return refundReason;
  }

  public void setRefundReason(String refundReason) {
    this.refundReason = refundReason;
  }

  public String getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(String refundStatus) {
    this.refundStatus = refundStatus;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String getBuyerEmail() {
    return buyerEmail;
  }

  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail;
  }

  public String getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId;
  }

  public String getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }

  public String getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus;
  }


}
