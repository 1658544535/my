package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 用户购物车-- user_cart by Lie
 * 
 */

public class UserCollectPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private Long productId;// 产品ID
  private String productName;// 产品名称
  private Double sellingPrice;// 建议零售价
  private Double lowestPrice;// 最低零售价
  private String image;// 产品主图名称
  private String isIntroduce;// 是否推荐
  private Double distributionPrice;//
  private Integer proStatus;// 产品审核状态
  private Long activityId;// 活动ID
  private Long type;// 类型：0-正常，1-儿童
  private Long sellNumber;
  /**
   * 拼团人数
   */
  private Integer groupNum;
  /**
   * 拼团价格
   */
  private Double groupPrice;
  /**
   * 拼团价格
   */
  private Integer isDelete;
  /**
   * 活动类型
   */
  private Integer activityType;


  public Integer getActivityType() {
    return activityType;
  }

  public void setActivityType(Integer activityType) {
    this.activityType = activityType;
  }

  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
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

  public Long getSellNumber() {
    return sellNumber;
  }

  public void setSellNumber(Long sellNumber) {
    this.sellNumber = sellNumber;
  }

  public Integer getProStatus() {
    return proStatus;
  }

  public void setProStatus(Integer proStatus) {
    this.proStatus = proStatus;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
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

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public Double getLowestPrice() {
    return lowestPrice;
  }

  public void setLowestPrice(Double lowestPrice) {
    this.lowestPrice = lowestPrice;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getIsIntroduce() {
    return isIntroduce;
  }

  public void setIsIntroduce(String isIntroduce) {
    this.isIntroduce = isIntroduce;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }



}
