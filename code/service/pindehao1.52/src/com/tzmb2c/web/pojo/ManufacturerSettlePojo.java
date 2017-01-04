package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 供应商结算信息-- user_manufacturer_settle by Lie
 * 
 */

public class ManufacturerSettlePojo extends SuperPojo {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  /**
   * ID
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 结算日期
   */
  private Date settleDate;
  private String settleDateStr;
  /**
   * 结算总额
   */
  private Double settleAmount;
  /**
   * 结算余额
   */
  private Double settleBalance;
  /**
   * 状态：0-待审核，1-待确认，2-已结算
   */
  private Integer status;
  private String statusName;
  private String updateDateStr;
  private String statusAll;// 多个状态
  private String company;// 商家名称

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Date getSettleDate() {
    return settleDate;
  }

  public void setSettleDate(Date settleDate) {
    this.settleDate = settleDate;
    if (this.settleDate != null) {
      this.settleDateStr = StringUtil.dateToString(this.settleDate);
    }
  }

  public String getSettleDateStr() {
    return settleDateStr;
  }

  public void setSettleDateStr(String settleDateStr) {
    this.settleDateStr = settleDateStr;
  }

  public Double getSettleAmount() {
    return settleAmount;
  }

  public void setSettleAmount(Double settleAmount) {
    this.settleAmount = settleAmount;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
    if (this.status != null && this.status == 0) {
      this.statusName = "待审核";
    } else if (this.status != null && this.status == 1) {
      this.statusName = "待确认";
    } else if (this.status != null && this.status == 2) {
      this.statusName = "已结算";
    }
  }

  public String getUpdateDateStr() {
    if (getUpdateDate() != null) {
      return StringUtil.dateToString(getUpdateDate());
    }
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public String getStatusAll() {
    return statusAll;
  }

  public void setStatusAll(String statusAll) {
    this.statusAll = statusAll;
  }

  public Double getSettleBalance() {
    return settleBalance;
  }

  public void setSettleBalance(Double settleBalance) {
    this.settleBalance = settleBalance;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

}
