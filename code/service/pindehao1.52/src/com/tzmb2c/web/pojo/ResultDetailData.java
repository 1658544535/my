package com.tzmb2c.web.pojo;

public class ResultDetailData {
  private String tradeNo;// 交易号
  private Double refundPrice;// 退款金额
  private String result;// 结果


  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public Double getRefundPrice() {
    return refundPrice;
  }

  public void setRefundPrice(Double refundPrice) {
    this.refundPrice = refundPrice;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
