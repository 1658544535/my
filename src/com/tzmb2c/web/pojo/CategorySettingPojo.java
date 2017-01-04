/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 分类展示设置; InnoDB free: 186368 kB
 * 
 * @version 1.0
 * @author
 */
public class CategorySettingPojo extends SuperPojo {

  /**
   * 编号id
   */
  private Long id;
  /**
   * 标题
   */
  private String title;
  /**
   * 图片地址
   */
  private String banner;
  /**
   * 类型(1-品类2-年龄)
   */
  private Integer type;
  /**
   * 排序
   */
  private Integer sorting;
  /**
   * 状态(0未审核1已审核)
   */
  private Integer status;
  private String statusName;
  private String typeName;

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
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

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
}
