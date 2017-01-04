package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class CouponOrderPojo extends SuperPojo {

  private Long orderId;// 订单号
  private String couponNo;// 优惠券券码
  private Long relTime;// 关联时间

  private String relTimeDStr;// 关联时间

  private String couponName;// 优惠券名称
  private String orderNo;// 对外订单号
  private Double usedPrice;// 使用金额

  private Integer status;// 有效状态
  private String statusName;// 有效状态名称

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Double getUsedPrice() {
    return usedPrice;
  }

  public void setUsedPrice(Double usedPrice) {
    this.usedPrice = usedPrice;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getCouponNo() {
    return couponNo;
  }

  public void setCouponNo(String couponNo) {
    this.couponNo = couponNo;
  }

  public Long getRelTime() {
    return relTime;
  }

  public void setRelTime(Long relTime) {
    this.relTime = relTime;
  }

  public String getRelTimeDStr() {
    return relTimeDStr;
  }

  public void setRelTimeDStr(String relTimeDStr) {
    this.relTimeDStr = relTimeDStr;
  }

  public String getCouponName() {
    return couponName;
  }

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }
}
