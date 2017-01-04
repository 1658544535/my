package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 用户分销产品库
 * 
 * @author EricChen
 */
public class UserConsumerCollectPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;

  private Long id;// 编号
  private Long userId;// 用户编号
  private Long suserId;// 供应商用户id
  private Long productId;// 产品ID
  private String isExport;// 是否导出
  private String productName;// 产品名称
  private String image;// 产品主图名称
  private Double distributionPrice;// 产品分销价格
  private Integer sellNumber;// 产品销售数量
  private String userName, suserName, location;
  private Integer proStatus;


  public Integer getProStatus() {
    return proStatus;
  }

  public void setProStatus(Integer proStatus) {
    this.proStatus = proStatus;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getSuserName() {
    return suserName;
  }

  public void setSuserName(String suserName) {
    this.suserName = suserName;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Long getSuserId() {
    return suserId;
  }

  public void setSuserId(Long suserId) {
    this.suserId = suserId;
  }

  public String getIsExport() {
    return isExport;
  }

  public void setIsExport(String isExport) {
    this.isExport = isExport;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public Integer getSellNumber() {
    return sellNumber;
  }

  public void setSellNumber(Integer sellNumber) {
    this.sellNumber = sellNumber;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
