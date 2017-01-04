/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 专区商品表; InnoDB free: 59392 kB
 * 
 * @version 1.0
 * @author
 */
public class ZoneGoodsPojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 专区id
   */
  private Long zoneId;
  /**
   * 团购活动表id
   */
  private Long activityId;
  /**
   * 商品id
   */
  private Long productId;
  /**
   * 审核状态（0未审核，1已审核）
   */
  private Integer status;
  private String statusName;
  /**
   * 排序
   */
  private Integer sorting;
  private String productNo;// 产品序号
  private String productNum;// 产品货号
  private String productName;
  private String image;
  private Double alonePrice;// 分销价
  private Double groupPrice;// 拼团价格
  private Integer sellNumber;// 商品销量
  private Integer num;// 几人团

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Double getAlonePrice() {
    return alonePrice;
  }

  public void setAlonePrice(Double alonePrice) {
    this.alonePrice = alonePrice;
  }

  public Double getGroupPrice() {
    return groupPrice;
  }

  public void setGroupPrice(Double groupPrice) {
    this.groupPrice = groupPrice;
  }

  public Integer getSellNumber() {
    return sellNumber;
  }

  public void setSellNumber(Integer sellNumber) {
    this.sellNumber = sellNumber;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setZoneId(Long value) {
    zoneId = value;
  }

  public Long getZoneId() {
    return zoneId;
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

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  public void setSorting(Integer value) {
    sorting = value;
  }

  public Integer getSorting() {
    return sorting;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

}
