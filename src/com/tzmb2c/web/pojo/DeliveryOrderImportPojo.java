/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 发货订单导入表; InnoDB free: 149504 kB
 * 
 * @version 1.0
 * @author
 */
public class DeliveryOrderImportPojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 批次号（当前时间戳）
   */
  private String batchNo;
  /**
   * 订单号
   */
  private String orderNo;
  /**
   * 快递公司
   */
  private String logisticsName;
  /**
   * 快递单号
   */
  private String logisticsNo;
  /**
   * 状态（0失败，1成功）
   */
  private Integer status;
  private String statusName;
  private String logisticsNameStr;

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setUserId(Long value) {
    userId = value;
  }

  public Long getUserId() {
    return userId;
  }

  public void setBatchNo(String value) {
    batchNo = value;
  }

  public String getBatchNo() {
    return batchNo;
  }

  public void setOrderNo(String value) {
    orderNo = value;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setLogisticsName(String value) {
    logisticsName = value;
  }

  public String getLogisticsName() {
    return logisticsName;
  }

  public void setLogisticsNo(String value) {
    logisticsNo = value;
  }

  public String getLogisticsNo() {
    return logisticsNo;
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

  public String getLogisticsNameStr() {
    return logisticsNameStr;
  }

  public void setLogisticsNameStr(String logisticsNameStr) {
    this.logisticsNameStr = logisticsNameStr;
  }
}
