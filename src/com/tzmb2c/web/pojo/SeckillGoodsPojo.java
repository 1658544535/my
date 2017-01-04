/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 秒杀商品表; InnoDB free: 59392 kB
 * 
 * @version 1.0
 * @author
 */
public class SeckillGoodsPojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 秒杀表id
   */
  private Long seckillId;
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
  /**
   * 排序
   */
  private Integer sorting;
  private String productNo;// 产品序号
  private String productNum;// 产品货号
  private String productName;
  private String image;
  private Integer limitNum;// 活动数量
  private Double price;// 拼团价格
  private Integer surplusNum;// 剩余数量
  private Integer num;// 几人团
  private Double alonePrice;// 单独购价格
  private Long numNow;// 参与人数
  private String statusName;
  private String sellNumber;// 销量
  private Integer isHome;// 是否上首页
  private String imageMain; // 商品主图
  private Double sellingPrice;// 商品原价



  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public String getImageMain() {
    return imageMain;
  }

  public void setImageMain(String imageMain) {
    this.imageMain = imageMain;
  }

  public Integer getIsHome() {
    return isHome;
  }

  public void setIsHome(Integer isHome) {
    this.isHome = isHome;
  }

  public String getSellNumber() {
    return sellNumber;
  }

  public void setSellNumber(String sellNumber) {
    this.sellNumber = sellNumber;
  }

  public Double getAlonePrice() {
    return alonePrice;
  }

  public void setAlonePrice(Double alonePrice) {
    this.alonePrice = alonePrice;
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setSeckillId(Long value) {
    seckillId = value;
  }

  public Long getSeckillId() {
    return seckillId;
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


  public Integer getLimitNum() {
    return limitNum;
  }

  public void setLimitNum(Integer limitNum) {
    this.limitNum = limitNum;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getSurplusNum() {
    return surplusNum;
  }

  public void setSurplusNum(Integer surplusNum) {
    this.surplusNum = surplusNum;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Long getNumNow() {
    return numNow;
  }

  public void setNumNow(Long numNow) {
    this.numNow = numNow;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
}
