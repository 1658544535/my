/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 限时秒杀表; InnoDB free: 59392 kB
 * 
 * @version 1.0
 * @author
 */
public class SeckillPojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 开始时间
   */
  private Date beginTime;
  /**
   * 结束时间
   */
  private Date endTime;
  /**
   * 活动类型：1-限时秒杀
   */
  private Integer type;
  /**
   * 审核状态（0未审核，1已审核）
   */
  private Integer status;
  private String statusName;
  /**
   * 排序
   */
  private Integer sorting;
  /**
   * 删除标记0正常1删除
   */
  private Integer isDelete;
  private Date createDate;
  private String beginTimeStr;
  private String endTimeStr;
  private String createDateStr;
  private Integer numNow;

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (this.createDate != null) {
      createDateStr = StringUtil.dateString(this.createDate);
    }
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public Integer getNumNow() {
    return numNow;
  }

  public void setNumNow(Integer numNow) {
    this.numNow = numNow;
  }

  public void setBeginTime(Date value) {
    beginTime = value;
    if (beginTime != null) {
      beginTimeStr = StringUtil.dateString(beginTime);
    }
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setEndTime(Date value) {
    endTime = value;
    if (endTime != null) {
      endTimeStr = StringUtil.dateString(endTime);
    }
  }

  public Date getEndTime() {
    return endTime;
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

  public String getBeginTimeStr() {
    return beginTimeStr;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public String getEndTimeStr() {
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
}
