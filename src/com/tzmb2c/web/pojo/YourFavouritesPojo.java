/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 有你喜欢表
 * 
 * @version 1.0
 * @author
 */
public class YourFavouritesPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 标签名
   */
  private String name;
  /**
   * 标签图
   */
  private String image;
  /**
   * 所属年龄，格式：:1:2:
   */
  private String ageType;
  /**
   * 1-商品单品;2-内容标签
   */
  private Integer contentType;
  /**
   * 排序序号
   */
  private Integer sorting;
  /**
   * 删除状态（0-正常;1-删除）
   */
  private Integer isDelete;
  private String createDateStr;

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getName() {
    return this.name;
  }

  public void setImage(String value) {
    this.image = value;
  }

  public String getImage() {
    return this.image;
  }

  public void setAgeType(String value) {
    this.ageType = value;
  }

  public String getAgeType() {
    return this.ageType;
  }

  public void setContentType(Integer value) {
    this.contentType = value;
  }

  public Integer getContentType() {
    return this.contentType;
  }

  public void setSorting(Integer value) {
    this.sorting = value;
  }

  public Integer getSorting() {
    return this.sorting;
  }

  public void setIsDelete(Integer value) {
    this.isDelete = value;
  }

  public Integer getIsDelete() {
    return this.isDelete;
  }

  public String getCreateDateStr() {
    return StringUtil.dateString(createDate);
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }
}
