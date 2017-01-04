/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 首页团购推荐; InnoDB free: 149504 kB
 * 
 * @version 1.0
 * @author
 */
public class GrouponRecommendPojo extends SuperPojo {

  /**
   * 编号id
   */
  private Long id;
  /**
   * 团购活动ID
   */
  private Long activityId;
  /**
   * 商品ID
   */
  private Long productId;
  /**
   * 排序
   */
  private Integer sorting;
  /**
   * 商品名称
   */
  private String productName;
  /**
   * 单购价
   */
  private Double distributionPrice;
  /**
   * 拼团价格
   */
  private Double price;
  /**
   * 商品图片
   */
  private String productImage;
  /**
   * 商品banner图片
   */
  private String imageMain;
  /**
   * 商品销量
   */
  private Integer sellNumber;
  /**
   * 商品团购人数
   */
  private Integer groupNum;

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setActivityId(Long value) {
    activityId = value;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setProductId(Long value) {
    productId = value;
  }

  public Long getProductId() {
    return productId;
  }

  public void setSorting(Integer value) {
    sorting = value;
  }

  public Integer getSorting() {
    return sorting;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
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

  public Integer getSellNumber() {
    return sellNumber;
  }

  public void setSellNumber(Integer sellNumber) {
    this.sellNumber = sellNumber;
  }

  public Integer getGroupNum() {
    return groupNum;
  }

  public void setGroupNum(Integer groupNum) {
    this.groupNum = groupNum;
  }
}
