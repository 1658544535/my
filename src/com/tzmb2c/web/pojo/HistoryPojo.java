package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 2014-12-24 history 用户浏览记录
 * 
 * @author creazylee
 */

public class HistoryPojo extends SuperPojo {

  private Long id;// 编号ID
  private Long userId;// 用户id
  private String userName;// 用户昵称
  private Integer type;// 浏览类型(取业务字典：1商品2商家)
  private String typeName;// 浏览类型
  private Long businessId;// 业务id(浏览类型为1则取商品id，为2则取商家id)
  private Integer hid;// 浏览次数
  private String productName;// 产品名称
  private Double distributionPrice;// 分销价价
  private Double sellingPrice;// 建议零售价
  private Double lowestPrice;// 最低零售价
  private String image;// 产品主图名称
  private String sellNumber;
  private Integer baseNumber;
  private String shopName;
  private Date createDate;
  private Date updateDate;
  private String createDateString;
  private String updateDateString;
  private String proStatus;
  private Long activityId;// 活动ID


  public Integer getBaseNumber() {
    return baseNumber;
  }

  public void setBaseNumber(Integer baseNumber) {
    this.baseNumber = baseNumber;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.createDateString = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    this.updateDateString = StringUtil.dateToString(this.updateDate);
  }

  public String getCreateDateString() {
    return createDateString;
  }

  public void setCreateDateString(String createDateString) {
    this.createDateString = createDateString;
  }

  public String getUpdateDateString() {
    return updateDateString;
  }

  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public String getSellNumber() {
    return sellNumber;
  }

  public void setSellNumber(String sellNumber) {
    this.sellNumber = sellNumber;
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

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Long getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Long businessId) {
    this.businessId = businessId;
  }

  public Integer getHid() {
    return hid;
  }

  public void setHid(Integer hid) {
    this.hid = hid;
  }

  /**
   * @return the proStatus
   */
  public String getProStatus() {
    return proStatus;
  }

  /**
   * @param proStatus the proStatus to set
   */
  public void setProStatus(String proStatus) {
    this.proStatus = proStatus;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

}
