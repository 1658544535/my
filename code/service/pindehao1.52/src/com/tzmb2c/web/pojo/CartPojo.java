package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 用户购物车-- user_cart by Lie
 * 
 */

public class CartPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private Long shopId;// 店铺ID
  private Long shopUserid;// 店铺用户ID
  private Long productId;// 产品ID
  private String productName;// 产品名称
  private String productModel;// 产品规格
  private Long stockId;// 库存ID
  private Double stockPriceOld;// 产品原价
  private Double stockPrice;// 产品价格
  private Integer num;// 数量
  private String allStockPrice;// 产品价格
  private Integer type;// 类型(取业务字典：待定)
  private Integer channel;// 来源渠道(取业务字典：0.PC端1.APP 2.微信)
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName, channelName;
  private Double pricess;// 总价
  private Integer proStatus;// 产品审核状态0未审核1已审核
  private String userName, shopName;
  private String productImage, size, color;
  private Integer postageType;// 是否包邮
  private String weight;// 产品重量
  private String allweights;// 总重量
  // private Date createDate;
  private String createDateStr;
  private String productNum;
  // sku关联id
  private Integer skuLinkId;
  // 活动id
  private Long activityId;
  // 活动名
  private String activityName;
  // 是否检查状态
  private Integer checkStatus;

  /**
   * source:来源,1 平台专题 2 创客专题 3笔记  4微页面
   */
  private Integer source;

  /**
   * sourceId:来源ID
   */
  private Long sourceId;
  /**
   * shopImage:店铺图片
   */
  private String shopImage;
  public String getShopImage() {
    return shopImage;
  }

  public void setShopImage(String shopImage) {
    this.shopImage = shopImage;
  }
  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public Long getSourceId() {
    return sourceId;
  }

  public void setSourceId(Long sourceId) {
    this.sourceId = sourceId;
  }

  public Integer getCheckStatus() {
    return checkStatus;
  }

  public void setCheckStatus(Integer checkStatus) {
    this.checkStatus = checkStatus;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Integer getSkuLinkId() {
    return skuLinkId;
  }

  public void setSkuLinkId(Integer skuLinkId) {
    this.skuLinkId = skuLinkId;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public Integer getProStatus() {
    return proStatus;
  }

  public void setProStatus(Integer proStatus) {
    this.proStatus = proStatus;
  }

  public Double getPricess() {
    return pricess;
  }

  public void setPricess(Double pricess) {
    this.pricess = pricess;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
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

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
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

  public String getProductModel() {
    return productModel;
  }

  public void setProductModel(String productModel) {
    this.productModel = productModel;
  }

  public Long getStockId() {
    return stockId;
  }

  public void setStockId(Long stockId) {
    this.stockId = stockId;
  }

  public Double getStockPriceOld() {
    return stockPriceOld;
  }

  public void setStockPriceOld(Double stockPriceOld) {
    this.stockPriceOld = stockPriceOld;
  }

  public Double getStockPrice() {
    return stockPrice;
  }

  public void setStockPrice(Double stockPrice) {
    this.stockPrice = stockPrice;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getAllStockPrice() {
    return allStockPrice;
  }

  public void setAllStockPrice(String allStockPrice) {
    this.allStockPrice = allStockPrice;
  }

  public Integer getPostageType() {
    return postageType;
  }

  public void setPostageType(Integer postageType) {
    this.postageType = postageType;
  }

  public Long getShopUserid() {
    return shopUserid;
  }

  public void setShopUserid(Long shopUserid) {
    this.shopUserid = shopUserid;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getAllweights() {
    return allweights;
  }

  public void setAllweights(String allweights) {
    this.allweights = allweights;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.createDateStr = StringUtil.dateString(this.createDate);
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

}
