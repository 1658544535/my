/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 店铺推荐表; InnoDB free: 186368 kB
 * 
 * @version 1.0
 * @author
 */
public class ShopRecommendPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * user_shop表对应ID
   */
  private Long shopId;
  /**
   * 排序序号
   */
  private Integer sorting;
  /**
   * 店铺顶部图片
   */
  private String topImage;
  /**
   * 店铺名称
   */
  private String name;
  /**
   * 店铺Logo
   */
  private String images;
  /**
   * 店铺用户ID
   */
  private Long userId;
  private String createDateStr;
  private String updateDateStr;

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    setCreateDateStr(StringUtil.dateString(this.createDate));
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    setUpdateDateStr(StringUtil.dateString(this.updateDate));
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setShopId(Long value) {
    shopId = value;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setSorting(Integer value) {
    sorting = value;
  }

  public Integer getSorting() {
    return sorting;
  }

  public String getTopImage() {
    return topImage;
  }

  public void setTopImage(String topImage) {
    this.topImage = topImage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
