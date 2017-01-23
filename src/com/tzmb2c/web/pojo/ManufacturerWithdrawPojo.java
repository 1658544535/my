package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 供应商提现信息-- user_manufacturer_withdraw by Lie
 * 
 */

public class ManufacturerWithdrawPojo extends SuperPojo {
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
  private String userName;
  /**
   * 提现日期
   */
  private Date withdrawDate;
  private String withdrawDateStr;
  /**
   * 提现金额
   */
  private Double withdrawAmount;
  /**
   * 结算余额
   */
  private Double settleBalance;
  /**
   * 审核状态:0-待审核，1-申请已取消，2-待提现，3-审核不通过，4-已提现
   */
  private Integer status;
  private String statusName;
  private String createDateStr;
  private String updateDateStr;
  private Double period; // 提交提现申请的间隔时间
  private String number;
  private Double withdrawalFee;
  private String bankName;
  private String bankCardNo;

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

  public Date getWithdrawDate() {
    return withdrawDate;
  }

  public void setWithdrawDate(Date withdrawDate) {
    this.withdrawDate = withdrawDate;
    if (this.withdrawDate != null) {
      withdrawDateStr = StringUtil.dateToString(this.withdrawDate);
    }
  }

  public String getWithdrawDateStr() {
    return withdrawDateStr;
  }

  public void setWithdrawDateStr(String withdrawDateStr) {
    this.withdrawDateStr = withdrawDateStr;
  }

  public Double getWithdrawAmount() {
    return withdrawAmount;
  }

  public void setWithdrawAmount(Double withdrawAmount) {
    this.withdrawAmount = withdrawAmount;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
    if (this.status != null && this.status == 0) {
      statusName = "待审核";
    } else if (this.status != null && this.status == 1) {
      statusName = "申请已取消";
    } else if (this.status != null && this.status == 2) {
      statusName = "待提现";
    } else if (this.status != null && this.status == 3) {
      statusName = "审核不通过";
    } else if (this.status != null && this.status == 4) {
      statusName = "已提现";
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

  public String getCreateDateStr() {
    if (getCreateDate() != null) {
      return StringUtil.dateToString(getCreateDate());
    }
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Double getSettleBalance() {
    return settleBalance;
  }

  public void setSettleBalance(Double settleBalance) {
    this.settleBalance = settleBalance;
  }

  public Double getPeriod() {
    return period;
  }

  public void setPeriod(Double period) {
    this.period = period;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Double getWithdrawalFee() {
    return withdrawalFee;
  }

  public void setWithdrawalFee(Double withdrawalFee) {
    this.withdrawalFee = withdrawalFee;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankCardNo() {
    return bankCardNo;
  }

  public void setBankCardNo(String bankCardNo) {
    this.bankCardNo = bankCardNo;
  }


}
