/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 热门推荐(来源: 平台专题和创客专题); InnoDB free: 186368 kB
 * 
 * @version 1.0
 * @author
 */
public class HotPushPojo extends SuperPojo {

  /**
   * 推荐id
   */
  private Long id;
  /**
   * 专题id
   */
  private Long specialId;
  /**
   * 专题类型(1平台专题2创客专题)
   */
  private Integer type;
  /**
   * 排序
   */
  private Long sorting;
  /**
   * 专题名称
   */
  private String specialName;
  /**
   * 专题类型
   */
  private Long specialType;
  /**
   * 年龄标签
   */
  private String ageTypeName;
  /**
   * 能力标签
   */
  private String skillTypeName;
  /**
   * 商品标签
   */
  private String productTypeName;
  /**
   * 状态
   */
  private String status;
  /**
   * 笔记主图
   */
  private String banner;
  /**
   * sketch:简要
   */
  private String sketch;
  /**
   * 用户ID
   */
  private Long userId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getSketch() {
    return sketch;
  }

  public void setSketch(String sketch) {
    this.sketch = sketch;
  }


  public String getBanner() {
    return banner;
  }

  public void setBanner(String banner) {
    this.banner = banner;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getSpecialName() {
    return specialName;
  }

  public void setSpecialName(String specialName) {
    this.specialName = specialName;
  }

  public Long getSpecialType() {
    return specialType;
  }

  public void setSpecialType(Long specialType) {
    this.specialType = specialType;
  }

  public String getAgeTypeName() {
    return ageTypeName;
  }

  public void setAgeTypeName(String ageTypeName) {
    this.ageTypeName = ageTypeName;
  }

  public String getSkillTypeName() {
    return skillTypeName;
  }

  public void setSkillTypeName(String skillTypeName) {
    this.skillTypeName = skillTypeName;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setSpecialId(Long value) {
    this.specialId = value;
  }

  public Long getSpecialId() {
    return this.specialId;
  }

  public void setType(Integer value) {
    this.type = value;
  }

  public Integer getType() {
    return this.type;
  }

  public void setSorting(Long value) {
    this.sorting = value;
  }

  public Long getSorting() {
    return this.sorting;
  }
}
