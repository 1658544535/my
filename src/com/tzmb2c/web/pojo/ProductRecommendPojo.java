/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 商品推荐表; InnoDB free: 186368 kB
 * 
 * @version 1.0
 * @author
 */
public class ProductRecommendPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 1-每日上新;2-销量排行
   */
  private Integer type;
  /**
   * 商品ID
   */
  private Long productId;
  /**
   * 活动ID
   */
  private Long activityId;
  /**
   * 排序序号
   */
  private Integer sorting;
  /**
   * 商品名称
   */
  private String name;
  /**
   * 商品图片
   */
  private String image;
  /**
   * 推荐语
   */
  private String recommendation;
  /**
   * 商品原价
   */
  private Double sellingPrice;
  /**
   * 商品价格
   */
  private Double price;
  /**
   * 商品活动价格
   */
  private Double activePrice;
  private String createDateStr;

  public Double getActivePrice() {
    return activePrice;
  }

  public void setActivePrice(Double activePrice) {
    this.activePrice = activePrice;
  }

  public String getCreateDateStr() {
    createDateStr = StringUtil.dateString(createDate);
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getRecommendation() {
    return recommendation;
  }

  public void setRecommendation(String recommendation) {
    this.recommendation = recommendation;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setType(Integer value) {
    this.type = value;
  }

  public Integer getType() {
    return this.type;
  }

  public void setProductId(Long value) {
    this.productId = value;
  }

  public Long getProductId() {
    return this.productId;
  }

  public void setActivityId(Long value) {
    this.activityId = value;
  }

  public Long getActivityId() {
    return this.activityId;
  }

  public void setSorting(Integer value) {
    this.sorting = value;
  }

  public Integer getSorting() {
    return this.sorting;
  }
}
