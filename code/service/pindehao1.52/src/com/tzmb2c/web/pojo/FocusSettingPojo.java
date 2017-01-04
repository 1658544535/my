/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 焦点图设置; InnoDB free: 149504 kB
 * 
 * @version 1.0
 * @author
 */
public class FocusSettingPojo extends SuperPojo {

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
   * 图片尺寸提示
   */
  private String sizeTips;
  /**
   * 广告图背景颜色值
   */
  private String backgroundColor;
  /**
   * 类型，1-首页2-猜价
   */
  private Integer type;
  /**
   * 参数类型(0-无;1-商品;2-普通拼团;3-猜价格;)
   */
  private Integer paramType;
  /**
   * 对应参数类型的ID
   */
  private Long paramId;
  /**
   * 商品活动ID
   */
  private Long activityId;
  /**
   * 链接
   */
  private String url;
  /**
   * 排序
   */
  private Integer sorting;
  /**
   * 状态(0未审核1已审核)
   */
  private Integer status;
  /**
   * 开始时间
   */
  private Date startTime;
  /**
   * 结束时间
   */
  private Date endTime;
  private String startTimeStr;
  private String statusName;

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

  public void setSizeTips(String value) {
    sizeTips = value;
  }

  public String getSizeTips() {
    return sizeTips;
  }

  public void setBackgroundColor(String value) {
    backgroundColor = value;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }

  public void setType(Integer value) {
    type = value;
  }

  public Integer getType() {
    return type;
  }

  public void setParamType(Integer value) {
    paramType = value;
  }

  public Integer getParamType() {
    return paramType;
  }

  public void setParamId(Long value) {
    paramId = value;
  }

  public Long getParamId() {
    return paramId;
  }

  public void setActivityId(Long value) {
    activityId = value;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setUrl(String value) {
    url = value;
  }

  public String getUrl() {
    return url;
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

  public void setStartTime(Date value) {
    startTime = value;
    setStartTimeStr(StringUtil.dateString(startTime));
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setEndTime(Date value) {
    endTime = value;
  }

  public Date getEndTime() {
    return endTime;
  }

  public String getStartTimeStr() {
    return startTimeStr;
  }

  public void setStartTimeStr(String startTimeStr) {
    this.startTimeStr = startTimeStr;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
}
