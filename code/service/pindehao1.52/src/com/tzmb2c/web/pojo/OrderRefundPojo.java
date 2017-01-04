/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 用户退货订单; InnoDB free: 44032 kB; (`sku_link_id`) REFER `maduoduo/product_sku_link`
 * 
 * @version 1.0
 * @author
 */
public class OrderRefundPojo extends SuperPojo {

  /**
   * 编号id
   */
  private Long id;
  /**
   * 订单id
   */
  private Long orderId;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 下订单的用户名
   */
  private String loginname;
  /**
   * 店铺id
   */
  private Long shopId;
  /**
   * 产品id
   */
  private Long productId;
  /**
   * 产品名称
   */
  private String productName;
  /**
   * 产品规格
   */
  private String productModel;
  /**
   * 库存id
   */
  private Long stockId;
  /**
   * 产品原价
   */
  private Double stockPriceOld;
  /**
   * 产品价格
   */
  private Double stockPrice;
  /**
   * 退回数量
   */
  private Integer refundNum;
  /**
   * 说明
   */
  private String refundReason;
  /**
   * 退货类型(1=>审核，2=>请退货，3=》退货中，4=》退货成功，5=》退货失败，6=》审核不成功，7=》退款成功)
   */
  private Integer status;
  /**
   * 图片1
   */
  private String images;
  /**
   * 图片2
   */
  private String images2;
  /**
   * 图片3
   */
  private String images3;
  /**
   * 物流编号
   */
  private String logistics;
  /**
   * 物流类型
   */
  private String logType;
  /**
   * 退货原因
   */
  private Integer refundType;
  /**
   * 退货地址
   */
  private String address;
  /**
   * 订单详情ID
   */
  private Long detailId;
  /**
   * 类型1：退款 2：退货 3：售后服务
   */
  private Integer type;
  /**
   * product_sku_link.id
   */
  private Integer skuLinkId;
  /**
   * 优惠金额
   */
  private Double couponPrice;
  /**
   * 实际退回钱包的钱（0为未退，非0为已退）
   */
  private Double refundMoney;
  /**
   * 是否申请客服介入,0为否,1为是
   */
  private Integer serviceInvolved;
  /**
   * 商家的驳回凭证
   */
  private String rejectImages;
  /**
   * 商家驳回理由
   */
  private String rejectReason;
  /**
   * 下订单的人手机号码
   */
  private String phone;
  /**
   * 退款状态：0-未退款，1-处理中，2-退款成功，3-退款失败
   */
  private Integer isRefund;
  /**
   * 商户退款单号
   */
  private String outRefundNo;
  /**
   * 退款时间
   */
  private Date refundDate;
  private String statusName;
  private String option;// 用于操作判断
  private String consigneePhone;
  private String beginTime;
  private String endTime;

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }

  public String getConsigneePhone() {
    return consigneePhone;
  }

  public void setConsigneePhone(String consigneePhone) {
    this.consigneePhone = consigneePhone;
  }

  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setOrderId(Long value) {
    this.orderId = value;
  }

  public Long getOrderId() {
    return this.orderId;
  }

  public void setUserId(Long value) {
    this.userId = value;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setLoginname(String value) {
    this.loginname = value;
  }

  public String getLoginname() {
    return this.loginname;
  }

  public void setShopId(Long value) {
    this.shopId = value;
  }

  public Long getShopId() {
    return this.shopId;
  }

  public void setProductId(Long value) {
    this.productId = value;
  }

  public Long getProductId() {
    return this.productId;
  }

  public void setProductName(String value) {
    this.productName = value;
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductModel(String value) {
    this.productModel = value;
  }

  public String getProductModel() {
    return this.productModel;
  }

  public void setStockId(Long value) {
    this.stockId = value;
  }

  public Long getStockId() {
    return this.stockId;
  }

  public void setStockPriceOld(Double value) {
    this.stockPriceOld = value;
  }

  public Double getStockPriceOld() {
    return this.stockPriceOld;
  }

  public void setStockPrice(Double value) {
    this.stockPrice = value;
  }

  public Double getStockPrice() {
    return this.stockPrice;
  }

  public void setRefundNum(Integer value) {
    this.refundNum = value;
  }

  public Integer getRefundNum() {
    return this.refundNum;
  }

  public void setRefundReason(String value) {
    this.refundReason = value;
  }

  public String getRefundReason() {
    return this.refundReason;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setImages(String value) {
    this.images = value;
  }

  public String getImages() {
    return this.images;
  }

  public void setImages2(String value) {
    this.images2 = value;
  }

  public String getImages2() {
    return this.images2;
  }

  public void setImages3(String value) {
    this.images3 = value;
  }

  public String getImages3() {
    return this.images3;
  }

  public void setLogistics(String value) {
    this.logistics = value;
  }

  public String getLogistics() {
    return this.logistics;
  }

  public void setLogType(String value) {
    this.logType = value;
  }

  public String getLogType() {
    return this.logType;
  }

  public void setRefundType(Integer value) {
    this.refundType = value;
  }

  public Integer getRefundType() {
    return this.refundType;
  }

  public void setAddress(String value) {
    this.address = value;
  }

  public String getAddress() {
    return this.address;
  }

  public void setDetailId(Long value) {
    this.detailId = value;
  }

  public Long getDetailId() {
    return this.detailId;
  }

  public void setType(Integer value) {
    this.type = value;
  }

  public Integer getType() {
    return this.type;
  }

  public void setSkuLinkId(Integer value) {
    this.skuLinkId = value;
  }

  public Integer getSkuLinkId() {
    return this.skuLinkId;
  }

  public void setCouponPrice(Double value) {
    this.couponPrice = value;
  }

  public Double getCouponPrice() {
    return this.couponPrice;
  }

  public void setRefundMoney(Double value) {
    if (value != null) {
      this.refundMoney = value;
    }
  }

  public Double getRefundMoney() {
    return this.refundMoney;
  }

  public void setServiceInvolved(Integer value) {
    this.serviceInvolved = value;
  }

  public Integer getServiceInvolved() {
    return this.serviceInvolved;
  }

  public void setRejectImages(String value) {
    this.rejectImages = value;
  }

  public String getRejectImages() {
    return this.rejectImages;
  }

  public void setRejectReason(String value) {
    this.rejectReason = value;
  }

  public String getRejectReason() {
    return this.rejectReason;
  }

  public void setPhone(String value) {
    this.phone = value;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setIsRefund(Integer value) {
    this.isRefund = value;
  }

  public Integer getIsRefund() {
    return this.isRefund;
  }

  public void setOutRefundNo(String value) {
    this.outRefundNo = value;
  }

  public String getOutRefundNo() {
    return this.outRefundNo;
  }

  public void setRefundDate(Date value) {
    this.refundDate = value;
  }

  public Date getRefundDate() {
    return this.refundDate;
  }
}
