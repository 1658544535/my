/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 分类明细设置; InnoDB free: 186368 kB
 * 
 * @version 1.0
 * @author
 */
public class CategoryDetailSettingPojo extends SuperPojo {

  /**
   * 编号id
   */
  private Long id;
  /**
   * 分类设置ID
   */
  private Long categoryId;
  /**
   * 三级分类ID
   */
  private Long typeId;
  /**
   * 排序
   */
  private Integer sorting;

  /**
   * name:品类名称
   */
  private String name;

  /**
   * image:品类图标
   */
  private String image;
  private String parentName;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setCategoryId(Long value) {
    categoryId = value;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setTypeId(Long value) {
    typeId = value;
  }

  public Long getTypeId() {
    return typeId;
  }

  public void setSorting(Integer value) {
    sorting = value;
  }

  public Integer getSorting() {
    return sorting;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }
}
