/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 有你喜欢内容表
 * @version 1.0
 * @author
 */
public class YourFavouritesDetailPojo extends SuperPojo {

  /**
     * ID
     */
  private Long id;
  /**
     * 有你喜欢表ID
     */
  private Long favId;
  /**
     * 1-商品单品;2-笔记
     */
  private Integer type;
  /**
     * 笔记ID
     */
  private Long postId;
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
     * 三级分类ID
     */
  private Long productTypeId;

  public Long getProductTypeId() {
    return productTypeId;
  }

  public void setProductTypeId(Long productTypeId) {
    this.productTypeId = productTypeId;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setFavId(Long value) {
    this.favId = value;
  }

  public Long getFavId() {
    return this.favId;
  }

  public void setType(Integer value) {
    this.type = value;
  }

  public Integer getType() {
    return this.type;
  }

  public void setPostId(Long value) {
    this.postId = value;
  }

  public Long getPostId() {
    return this.postId;
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
