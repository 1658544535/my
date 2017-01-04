/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 团购记录; InnoDB free: 149504 kB
 * 
 * @version 1.0
 * @author
 */
public class GrouponActivityRecordPojo extends SuperPojo {

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
   * 团长用户id
   */
  private Long userId;
  /**
   * 拼团人数
   */
  private Integer num;
  /**
   * 剩余人数
   */
  private Integer surplusNum;
  /**
   * 状态，1-成功2-失败
   */
  private Integer status;
  private String userName;
  private String userLogo;
  private Date beginTime;
  private Date endTime;
  private String beginTimeStr;
  private String endTimeStr;
  private String currentTime;
  private Double distributionPrice;
  private Long productId;
  private Integer groupNum;
  private Double groupPrice;
  private String productName;
  private String productImage;
  private String imageMain;
  private String imageSmall;
  private Integer productStatus;
  private Long groupId;
  private Integer activityStatus;
  private Integer groupStatus;
  /**
   * 参团人数
   */
  private Integer joinNum;
  private Long invitationUserId;
  private Integer isRebate;
  private Date rebateTime;

  private String productSketch;// 商品简介
  private Long pdkUid;// 拼得客userId



  public Long getPdkUid() {
    return pdkUid;
  }

  public void setPdkUid(Long pdkUid) {
    this.pdkUid = pdkUid;
  }

  public String getProductSketch() {
    return productSketch;
  }

  public void setProductSketch(String productSketch) {
    this.productSketch = productSketch;
  }

  public Long getInvitationUserId() {
    return invitationUserId;
  }

  public void setInvitationUserId(Long invitationUserId) {
    this.invitationUserId = invitationUserId;
  }

  public Integer getIsRebate() {
    return isRebate;
  }

  public void setIsRebate(Integer isRebate) {
    this.isRebate = isRebate;
  }

  public Date getRebateTime() {
    return rebateTime;
  }

  public void setRebateTime(Date rebateTime) {
    this.rebateTime = rebateTime;
  }

  public Integer getJoinNum() {
    return joinNum;
  }

  public void setJoinNum(Integer joinNum) {
    this.joinNum = joinNum;
  }

  public Integer getGroupStatus() {
    return groupStatus;
  }

  public void setGroupStatus(Integer groupStatus) {
    this.groupStatus = groupStatus;
  }

  public Integer getActivityStatus() {
    return activityStatus;
  }

  public void setActivityStatus(Integer activityStatus) {
    this.activityStatus = activityStatus;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
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

  public String getImageMain() {
    return imageMain;
  }

  public void setImageMain(String imageMain) {
    this.imageMain = imageMain;
  }

  public String getImageSmall() {
    return imageSmall;
  }

  public void setImageSmall(String imageSmall) {
    this.imageSmall = imageSmall;
  }

  public Integer getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(Integer productStatus) {
    this.productStatus = productStatus;
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

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
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

  public String getBeginTimeStr() {
    if (this.beginTime != null) {
      return StringUtil.dateString(beginTime);
    }
    return beginTimeStr;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public String getEndTimeStr() {
    if (this.endTime != null) {
      return StringUtil.dateString(endTime);
    }
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserLogo() {
    return userLogo;
  }

  public void setUserLogo(String userLogo) {
    this.userLogo = userLogo;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setActivityType(Integer value) {
    this.activityType = value;
  }

  public Integer getActivityType() {
    return this.activityType;
  }

  public void setActivityId(Long value) {
    this.activityId = value;
  }

  public Long getActivityId() {
    return this.activityId;
  }

  public void setUserId(Long value) {
    this.userId = value;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setNum(Integer value) {
    this.num = value;
  }

  public Integer getNum() {
    return this.num;
  }

  public void setSurplusNum(Integer value) {
    this.surplusNum = value;
  }

  public Integer getSurplusNum() {
    return this.surplusNum;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }
}
