/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 团购活动表; InnoDB free: 149504 kB
 * 
 * @version 1.0
 * @author
 */
public class GrouponActivityPojo extends SuperPojo {

  /**
   * 编号id
   */
  private Long id;
  /**
   * 商品ID
   */
  private Long productId;
  /**
   * 开始时间
   */
  private Date beginTime;
  /**
   * 结束时间
   */
  private Date endTime;
  /**
   * 添加时间
   */
  private Date createDate;
  /**
   * 拼团人数
   */
  private Integer num;
  /**
   * 中奖团数
   */
  private Integer prizeNum;
  /**
   * 拼团价格
   */
  private Double price;
  /**
   * 是否默认，0-否1-是
   */
  private Integer isDefault;
  /**
   * 猜价价格区间起
   */
  private String priceMin;
  /**
   * 猜价价格区间止
   */
  private String priceMax;
  /**
   * 审核状态，0-未审核1-审核通过
   */
  private Integer status;
  /**
   * 活动状态，0-未开始1-活动中2-活动结束
   */
  private Integer activityStatus;
  /**
   * 活动类型，1-普通拼团2-团免3-猜价4-0.1抽奖5-限时秒杀
   */
  private Integer type;
  /**
   * 排序
   */
  private Integer sorting;
  /**
   * 标题
   */
  private String title;
  /**
   * banner
   */
  private String banner;
  /**
   * 删除状态，0-否1-是
   */
  private Integer isDelete;
  /**
   * 商品名称
   */
  private String productName;
  /**
   * 商品图片
   */
  private String productImage;
  /**
   * 查询关键字
   */
  private String keywords;
  private String currentTime;
  private String currentTime2;
  /**
   * productNo:商品序号
   */
  private String productNo;
  /**
   * productNum:商品货号
   */
  private String productNum;
  /**
   * distributionPrice:独购价
   */
  private Double distributionPrice;
  /**
   * imageMain:商品banner图
   */
  private String imageMain;
  /**
   * imageSmall:商品小图
   */
  private String imageSmall;
  /**
   * sellNumber:商品销量
   */
  private Integer sellNumber;
  private String productSketch;
  private Integer productStatus;
  /**
   * 市场价
   */
  private Double sellingPrice;
  private Integer limitNum; // 活动数量
  private Integer surplusNum; // 剩余数量
  private Integer surplusNumM; // 减库存标记
  private Integer numNow1;
  /**
   * 商品当日销售量表里对应的字段
   */
  private Double price1;
  private Integer sellNumber1;
  private String productImage1;
  private String productName1;
  private Integer daySell;
  
  private Double rebateRatio; //返佣比例
  private Integer restrictionNum; //限购数量
  



  public Integer getNumNow1() {
    return numNow1;
  }

  public void setNumNow1(Integer numNow1) {
    this.numNow1 = numNow1;
  }

  public Double getRebateRatio() {
    return rebateRatio;
  }

  public void setRebateRatio(Double rebateRatio) {
    this.rebateRatio = rebateRatio;
  }

  public Integer getRestrictionNum() {
    return restrictionNum;
  }

  public void setRestrictionNum(Integer restrictionNum) {
    this.restrictionNum = restrictionNum;
  }

  public Integer getSurplusNumM() {
    return surplusNumM;
  }

  public void setSurplusNumM(Integer surplusNumM) {
    this.surplusNumM = surplusNumM;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public Integer getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(Integer productStatus) {
    this.productStatus = productStatus;
  }

  public String getProductSketch() {
    return productSketch;
  }

  public void setProductSketch(String productSketch) {
    this.productSketch = productSketch;
  }

  public String getCurrentTime2() {
    return currentTime2;
  }

  public void setCurrentTime2(String currentTime2) {
    this.currentTime2 = currentTime2;
  }

  private Long numNow;


  public Long getNumNow() {
    return numNow;
  }

  public void setNumNow(Long numNow) {
    this.numNow = numNow;
  }

  public String getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(String currentTime) {
    this.currentTime = currentTime;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getBeginTimeStr() {
    if (beginTime != null) {
      return StringUtil.dateString(beginTime);
    }
    return "";
  }

  public void setBeginTimeStr(String beginTimeStr) {}

  public String getEndTimeStr() {
    if (endTime != null) {
      return StringUtil.dateString(endTime);
    }
    return "";
  }

  public void setEndTimeStr(String endTimeStr) {}

  public String getCreateDateStr() {
    if (createDate != null) {
      return StringUtil.dateString(createDate);
    }
    return "";
  }

  public void setCreateDateStr() {}

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

  public void setBeginTime(Date value) {
    beginTime = value;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setEndTime(Date value) {
    endTime = value;
  }

  public Date getEndTime() {
    return endTime;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Integer getLimitNum() {
    return limitNum;
  }

  public void setLimitNum(Integer limitNum) {
    this.limitNum = limitNum;
  }

  public Integer getSurplusNum() {
    return surplusNum;
  }

  public void setSurplusNum(Integer surplusNum) {
    this.surplusNum = surplusNum;
  }

  public void setNum(Integer value) {
    num = value;
  }

  public Integer getNum() {
    return num;
  }

  public Integer getPrizeNum() {
    return prizeNum;
  }

  public void setPrizeNum(Integer prizeNum) {
    this.prizeNum = prizeNum;
  }

  public void setPrice(Double value) {
    price = value;
  }

  public Double getPrice() {
    return price;
  }

  public void setIsDefault(Integer value) {
    isDefault = value;
  }

  public Integer getIsDefault() {
    return isDefault;
  }

  public void setPriceMin(String value) {
    priceMin = value;
  }

  public String getPriceMin() {
    return priceMin;
  }

  public void setPriceMax(String value) {
    priceMax = value;
  }

  public String getPriceMax() {
    return priceMax;
  }

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  public void setActivityStatus(Integer value) {
    activityStatus = value;
  }

  public Integer getActivityStatus() {
    return activityStatus;
  }

  public void setType(Integer value) {
    type = value;
  }

  public Integer getType() {
    return type;
  }

  public void setSorting(Integer value) {
    sorting = value;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setTitle(String value) {
    title = value;
  }

  public String getTitle() {
    return title;
  }

  public void setBanner(String value) {
    banner = value;
  }

  public String getBanner() {
    return banner;
  }

  public void setIsDelete(Integer value) {
    isDelete = value;
  }

  public Integer getIsDelete() {
    return isDelete;
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

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
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

  public String getImageSmall() {
    return imageSmall;
  }

  public void setImageSmall(String imageSmall) {
    this.imageSmall = imageSmall;
  }

  public Double getPrice1() {
    return price1;
  }

  public void setPrice1(Double price1) {
    this.price1 = price1;
  }

  public Integer getSellNumber1() {
    return sellNumber1;
  }

  public void setSellNumber1(Integer sellNumber1) {
    this.sellNumber1 = sellNumber1;
  }

  public String getProductImage1() {
    return productImage1;
  }

  public void setProductImage1(String productImage1) {
    this.productImage1 = productImage1;
  }

  public String getProductName1() {
    return productName1;
  }

  public void setProductName1(String productName1) {
    this.productName1 = productName1;
  }

  public Integer getDaySell() {
    return daySell;
  }

  public void setDaySell(Integer daySell) {
    this.daySell = daySell;
  }

}
