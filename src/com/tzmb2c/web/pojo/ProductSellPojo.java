/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 商品当日销售量记录表; InnoDB free: 58368 kB
 * 
 * @version 1.0
 * @author
 */
public class ProductSellPojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 商品ID
   */
  private Long productId;
  /**
   * 商品名称
   */
  private String productName;
  /**
   * 商品图片
   */
  private String productImage;
  /**
   * 价格（单独购价格）
   */
  private Double price;
  /**
   * 商品销量
   */
  private Integer sellNumber;
  /**
   * 当日销售量
   */
  private Integer daySell;
  private String productType1;
  private String productTypeIds;
  private Integer status;

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setProductId(Long value) {
    productId = value;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductName(String value) {
    productName = value;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductImage(String value) {
    productImage = value;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setPrice(Double value) {
    price = value;
  }

  public Double getPrice() {
    return price;
  }

  public void setSellNumber(Integer value) {
    sellNumber = value;
  }

  public Integer getSellNumber() {
    return sellNumber;
  }

  public void setDaySell(Integer value) {
    daySell = value;
  }

  public Integer getDaySell() {
    return daySell;
  }

  public String getProductType1() {
    return productType1;
  }

  public void setProductType1(String productType1) {
    this.productType1 = productType1;
  }

  public String getProductTypeIds() {
    return productTypeIds;
  }

  public void setProductTypeIds(String productTypeIds) {
    this.productTypeIds = productTypeIds;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


}
