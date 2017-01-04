package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class UserShopCollectPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private Long suserId;// 供应商id
  private Long shopId;// 编号
  private String shopName;
  private String shopImages;
  private String shopAddress;
  private String suserName;
  private int status;


  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getShopImages() {
    return shopImages;
  }

  public void setShopImages(String shopImages) {
    this.shopImages = shopImages;
  }

  public String getShopAddress() {
    return shopAddress;
  }

  public void setShopAddress(String shopAddress) {
    this.shopAddress = shopAddress;
  }

  public String getSuserName() {
    return suserName;
  }

  public void setSuserName(String suserName) {
    this.suserName = suserName;
  }

  public Long getSuserId() {
    return suserId;
  }

  public void setSuserId(Long suserId) {
    this.suserId = suserId;
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



}
