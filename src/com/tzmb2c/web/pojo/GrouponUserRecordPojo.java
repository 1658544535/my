/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 团购用户记录; InnoDB free: 149504 kB
 * 
 * @version 1.0
 * @author
 */
public class GrouponUserRecordPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 活动类型,1-普通团2-团免3-猜价
   */
  private Integer activityType;
  /**
   * 活动id
   */
  private Long activityId;
  /**
   * 参与团购记录id
   */
  private Long attendId;
  /**
   * 参与用户id
   */
  private Long userId;
  /**
   * 状态，0-普通1-团免2-猜价中奖
   */
  private Integer status;
  /**
   * 是否团长，0-否1-是
   */
  private Integer isHead;
  /**
   * 参与时间
   */
  private Date attendTime;
  /**
   * 团免券ID
   */
  private Long couponId;
  /**
   * 猜价价格
   */
  private Double price;
  /**
   * 中奖，1-一等奖2-二等奖3-三等奖
   */
  private Integer prize;
  /**
   * 用户昵称
   */
  private String userName;
  /**
   * 关键字
   */
  private String keywords;
  private String userLogo;
  /**
   * 活动状态，0-未开始1-活动中2-活动结束
   */
  private Integer activityStatus;
  /**
   * 猜价价格区间起
   */
  private String priceMin;
  /**
   * 猜价价格区间止
   */
  private String priceMax;
  /**
   * 商品id
   */
  private String productId;
  /**
   * 商品名称
   */
  private String productName;
  /**
   * 商品名称
   */
  private String productImage;
  private Double distributionPrice;
  private Integer groupNum;
  private Double groupPrice;
  private Long orderId;
  /**
   * 开始时间
   */
  private Date beginTime;
  /**
   * 结束时间
   */
  private Date endTime;
  private String currentTime;
  /**
   * 开奖价格
   */
  private Double realPrice;
  private String prizeName;// 开奖情况
  private Integer recordStatus;
  /**
   * 是否领取优惠券
   */
  private Integer isRecCoupon;

  private Integer orderStatus;// 订单状态
  private Integer payStatus;// 支付状态
  private Double allPrice;// 订单金额
  private Double factPrice;// 支付金额
  private Integer orderSource;// 订单来源

  private Integer recStatus;
  private Long gaActivityId;
  /**
   * 是否弹窗(0否1是)
   */
  private Integer isAlert;
  private String loginName;
  private String beginTimeStr;
  private String endTimeStr;
  private String attendTimeStr;
  private Long pdkId;
  private String orderNo;
  private Integer counts;
  private Integer num, num1;
  private Integer groupStatus;
  private Integer isPrize;
  private Integer prizeNum,prizeNum1;

  /**
   * 拼团信息导出查询所需字段
   */
  private String attendTimeStartStr;
  private String attendTimeEndStr;
  private Integer openGroup;// 用来查询人员名单（1查询开团人员，0查询参团人员）


  
  public Integer getPrizeNum() {
    return prizeNum;
  }

  public void setPrizeNum(Integer prizeNum) {
    this.prizeNum = prizeNum;
  }

  public Integer getPrizeNum1() {
    return prizeNum1;
  }

  public void setPrizeNum1(Integer prizeNum1) {
    this.prizeNum1 = prizeNum1;
  }

  public Integer getGroupStatus() {
    return groupStatus;
  }

  public void setGroupStatus(Integer groupStatus) {
    this.groupStatus = groupStatus;
  }

  public Integer getIsPrize() {
    return isPrize;
  }

  public void setIsPrize(Integer isPrize) {
    this.isPrize = isPrize;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Integer getNum1() {
    return num1;
  }

  public void setNum1(Integer num1) {
    this.num1 = num1;
  }

  public Integer getCounts() {
    return counts;
  }

  public void setCounts(Integer counts) {
    this.counts = counts;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public Long getPdkId() {
    return pdkId;
  }

  public void setPdkId(Long pdkId) {
    this.pdkId = pdkId;
  }

  public String getEndTimeStr() {
    if (endTime != null) {
      return StringUtil.dateString(endTime);
    }
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public String getBeginTimeStr() {
    if (beginTime != null) {
      return StringUtil.dateString(beginTime);
    }
    return beginTimeStr;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public Integer getIsAlert() {
    return isAlert;
  }

  public void setIsAlert(Integer isAlert) {
    this.isAlert = isAlert;
  }


  public Long getGaActivityId() {
    return gaActivityId;
  }

  public void setGaActivityId(Long gaActivityId) {
    this.gaActivityId = gaActivityId;
  }

  public Integer getRecStatus() {
    return recStatus;
  }

  public void setRecStatus(Integer recStatus) {
    this.recStatus = recStatus;
  }

  public Integer getOrderSource() {
    return orderSource;
  }

  public void setOrderSource(Integer orderSource) {
    this.orderSource = orderSource;
  }

  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Integer getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }

  public Double getAllPrice() {
    return allPrice;
  }

  public void setAllPrice(Double allPrice) {
    this.allPrice = allPrice;
  }

  public Double getFactPrice() {
    return factPrice;
  }

  public void setFactPrice(Double factPrice) {
    this.factPrice = factPrice;
  }

  public Integer getIsRecCoupon() {
    return isRecCoupon;
  }

  public void setIsRecCoupon(Integer isRecCoupon) {
    this.isRecCoupon = isRecCoupon;
  }

  public Integer getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(Integer recordStatus) {
    this.recordStatus = recordStatus;
  }

  public Double getRealPrice() {
    return realPrice;
  }

  public void setRealPrice(Double realPrice) {
    this.realPrice = realPrice;
  }

  public String getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(String currentTime) {
    this.currentTime = currentTime;
  }


  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public Integer getGroupNum() {
    return groupNum;
  }

  public void setGroupNum(Integer groupNum) {
    this.groupNum = groupNum;
  }

  public Double getGroupPrice() {
    return groupPrice;
  }

  public void setGroupPrice(Double groupPrice) {
    this.groupPrice = groupPrice;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public String getPriceMin() {
    return priceMin;
  }

  public void setPriceMin(String priceMin) {
    this.priceMin = priceMin;
  }

  public String getPriceMax() {
    return priceMax;
  }

  public void setPriceMax(String priceMax) {
    this.priceMax = priceMax;
  }

  public Integer getActivityStatus() {
    return activityStatus;
  }

  public void setActivityStatus(Integer activityStatus) {
    this.activityStatus = activityStatus;
  }

  public String getUserLogo() {
    return userLogo;
  }

  public void setUserLogo(String userLogo) {
    this.userLogo = userLogo;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getAttendTimeStr() {
    if (attendTime != null) {
      return StringUtil.dateString(attendTime);
    }
    return attendTimeStr;
  }

  public void setAttendTimeStr(String attendTimeStr) {}

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setActivityType(Integer value) {
    activityType = value;
  }

  public Integer getActivityType() {
    return activityType;
  }

  public void setActivityId(Long value) {
    activityId = value;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setAttendId(Long value) {
    attendId = value;
  }

  public Long getAttendId() {
    return attendId;
  }

  public void setUserId(Long value) {
    userId = value;
  }

  public Long getUserId() {
    return userId;
  }

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  public void setIsHead(Integer value) {
    isHead = value;
  }

  public Integer getIsHead() {
    return isHead;
  }

  public void setAttendTime(Date value) {
    attendTime = value;
  }

  public Date getAttendTime() {
    return attendTime;
  }

  public void setCouponId(Long value) {
    couponId = value;
  }

  public Long getCouponId() {
    return couponId;
  }

  public void setPrice(Double value) {
    price = value;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrize(Integer value) {
    prize = value;
  }

  public Integer getPrize() {
    return prize;
  }

  public String getPrizeName() {
    return prizeName;
  }

  public void setPrizeName(String prizeName) {
    this.prizeName = prizeName;
  }

  public String getAttendTimeStartStr() {
    return attendTimeStartStr;
  }

  public void setAttendTimeStartStr(String attendTimeStartStr) {
    this.attendTimeStartStr = attendTimeStartStr;
  }

  public String getAttendTimeEndStr() {
    return attendTimeEndStr;
  }

  public void setAttendTimeEndStr(String attendTimeEndStr) {
    this.attendTimeEndStr = attendTimeEndStr;
  }

  public Integer getOpenGroup() {
    return openGroup;
  }

  public void setOpenGroup(Integer openGroup) {
    this.openGroup = openGroup;
  }


}
