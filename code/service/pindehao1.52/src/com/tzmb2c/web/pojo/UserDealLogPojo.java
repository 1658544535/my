/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 用户交易记录表; InnoDB free: 59392 kB
 * 
 * @version 1.0
 * @author
 */
public class UserDealLogPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 交易时间
   */
  private Date dealDate;
  /**
   * 交易金额
   */
  private Double dealAmount;
  /**
   * 交易类型（1-收入2-支出）
   */
  private Integer dealType;
  /**
   * 审核状态（0-待审核1-审核通过2-审核不通过3-转账完成）
   */
  private Integer status;
  /**
   * 拼团记录ID（返佣）
   */
  private Long groupId;
  /**
   * 真实姓名（提现）
   */
  private String name;
  /**
   * 转账方式（提现）
   */
  private String type;
  /**
   * 转账帐号（提现）
   */
  private String typeNo;
  /**
   * 交易单号（提现）
   */
  private String orderNo;
  /**
   * 审核不通过原因（提现）
   */
  private String returnMsg;
  /**
   * 转账完成时间（提现）
   */
  private Date overTime;
  /**
   * 备注（1-返佣2-提现）
   */
  private Integer remark;
  private String createDateStr;
  private String overTimeStr;
  private String dealDateStr;
  private String dealDateEndStr;
  private Long pdkId;
  private Double surplusPrice;
  private Date falseDate;
  private String falseDateStr;



  public String getFalseDateStr() {
    if (falseDate != null) {
      return StringUtil.dateString(falseDate);
    }
    return falseDateStr;
  }

  public void setFalseDateStr(String falseDateStr) {
    this.falseDateStr = falseDateStr;
  }

  public Date getFalseDate() {
    return falseDate;
  }

  public void setFalseDate(Date falseDate) {
    this.falseDate = falseDate;
  }

  public Double getSurplusPrice() {
    return surplusPrice;
  }

  public void setSurplusPrice(Double surplusPrice) {
    this.surplusPrice = surplusPrice;
  }

  public Long getPdkId() {
    return pdkId;
  }

  public void setPdkId(Long pdkId) {
    this.pdkId = pdkId;
  }

  public String getDealDateStr() {
    if (dealDate != null) {
      return StringUtil.dateString(dealDate);
    }
    return dealDateStr;
  }

  public void setDealDateStr(String dealDateStr) {
    this.dealDateStr = dealDateStr;
  }

  public String getOverTimeStr() {
    if (overTime != null) {
      return StringUtil.dateString(overTime);
    }
    return overTimeStr;
  }

  public void setOverTimeStr(String overTimeStr) {
    this.overTimeStr = overTimeStr;
  }

  public String getCreateDateStr() {
    if (createDate != null) {
      return StringUtil.dateString(createDate);
    }
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setUserId(Long value) {
    this.userId = value;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setDealDate(Date value) {
    this.dealDate = value;
  }

  public Date getDealDate() {
    return this.dealDate;
  }

  public void setDealAmount(Double value) {
    this.dealAmount = value;
  }

  public Double getDealAmount() {
    return this.dealAmount;
  }

  public void setDealType(Integer value) {
    this.dealType = value;
  }

  public Integer getDealType() {
    return this.dealType;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setGroupId(Long value) {
    this.groupId = value;
  }

  public Long getGroupId() {
    return this.groupId;
  }

  public void setName(String value) {
    this.name = value;
  }

  public String getName() {
    return this.name;
  }

  public void setType(String value) {
    this.type = value;
  }

  public String getType() {
    return this.type;
  }

  public void setTypeNo(String value) {
    this.typeNo = value;
  }

  public String getTypeNo() {
    return this.typeNo;
  }

  public void setOrderNo(String value) {
    this.orderNo = value;
  }

  public String getOrderNo() {
    return this.orderNo;
  }

  public void setReturnMsg(String value) {
    this.returnMsg = value;
  }

  public String getReturnMsg() {
    return this.returnMsg;
  }

  public void setOverTime(Date value) {
    this.overTime = value;
  }

  public Date getOverTime() {
    return this.overTime;
  }

  public void setRemark(Integer value) {
    this.remark = value;
  }

  public Integer getRemark() {
    return this.remark;
  }

  public String getDealDateEndStr() {
    return dealDateEndStr;
  }

  public void setDealDateEndStr(String dealDateEndStr) {
    this.dealDateEndStr = dealDateEndStr;
  }
}
