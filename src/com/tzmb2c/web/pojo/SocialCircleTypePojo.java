/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://www.cncounter.com/
 */
package com.tzmb2c.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 圈子类型表
 * 
 * @version 1.0
 * @author
 */
public class SocialCircleTypePojo implements Serializable {

  // ID
  private Long id;
  // 图标
  private String images;
  // 类型名称
  private String name;
  // 状态（0未审核，1已审核）
  private Integer status;
  // 状态名称
  private String statusName;
  // 创建时间
  private Date createDate;
  // 创建人
  private Long createBy;
  // 修改时间
  private Date updateDate;
  // 修改人
  private Long updateBy;
  // 排序
  private Integer sorting;


  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public void setName(String value) {
    name = value;
  }

  public String getName() {
    return name;
  }

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  public void setCreateDate(Date value) {
    createDate = value;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateBy(Long value) {
    createBy = value;
  }

  public Long getCreateBy() {
    return createBy;
  }

  public void setUpdateDate(Date value) {
    updateDate = value;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateBy(Long value) {
    updateBy = value;
  }

  public Long getUpdateBy() {
    return updateBy;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

}
