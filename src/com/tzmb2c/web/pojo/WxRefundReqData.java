package com.tzmb2c.web.pojo;

/**
 * 
 * @author Administrator
 * @微信退款请求数据实体类
 */
public class WxRefundReqData {
  private String outRefundNo;
  private String tradeNo;
  private Double totalFee;
  private Double refundFee;
  private String opUserId;



  public String getOutRefundNo() {
    return outRefundNo;
  }

  public void setOutRefundNo(String outRefundNo) {
    this.outRefundNo = outRefundNo;
  }

  public Double getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Double totalFee) {
    this.totalFee = totalFee;
  }

  public Double getRefundFee() {
    return refundFee;
  }

  public void setRefundFee(Double refundFee) {
    this.refundFee = refundFee;
  }

  public String getOpUserId() {
    return opUserId;
  }

  public void setOpUserId(String opUserId) {
    this.opUserId = opUserId;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }


}
