package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * brand_dic表
 */
public class BrandDicPojo extends SuperPojo {
  private Long id; // id
  private String brand; // 品牌名
  private String logo; // 品牌LOGO
  private Integer sorting; // 排序
  private Integer status; // 审核状态
  private String createDateStr;// 创建日期STR
  private String updateDateStr;// 更新日期STR
  private String statusName; // 审核状态名
  private Integer option; // 用于判断操作类型
  private String title; // 专场标题
  private Integer activityId; // 活动ID

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  @Override
  public void setUpdateDate(Date updateDate) {
    super.updateDate = updateDate;
    if (updateDate != null) {
      this.updateDateStr = StringUtil.dateString(updateDate);
    }
  }

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Integer getOption() {
    return option;
  }

  public void setOption(Integer option) {
    this.option = option;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }
}
