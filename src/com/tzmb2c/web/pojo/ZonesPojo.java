/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 专区表; InnoDB free: 59392 kB
 * 
 * @version 1.0
 * @author
 */
public class ZonesPojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 标题
   */
  private String title;
  /**
   * 专题顶部图
   */
  private String image;
  /**
   * 专区类型：1-7.7专区
   */
  private Integer type;
  /**
   * 审核状态（0未审核，1已审核）
   */
  private Integer status;
  /**
   * 排序
   */
  private Integer sorting;
  /**
   * 删除标记0正常1删除
   */
  private Integer isDelete;
  private String statusName;
  private String icon;
  private String shareDesc;

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getShareDesc() {
    return shareDesc;
  }

  public void setShareDesc(String shareDesc) {
    this.shareDesc = shareDesc;
  }

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

  public void setImage(String value) {
    image = value;
  }

  public String getImage() {
    return image;
  }

  public void setType(Integer value) {
    type = value;
  }

  public Integer getType() {
    return type;
  }

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  public void setSorting(Integer value) {
    sorting = value;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setIsDelete(Integer value) {
    isDelete = value;
  }

  public Integer getIsDelete() {
    return isDelete;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
}
