package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 批发商推送信息
 * 
 * @author creazylee
 */
public class AgencyPushPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Long agencyId;
  private Long userId;
  private Long orderId;
  private Date createDate;
  private Date updateDate;
  private int type;// 待定
  private int status;// 审核状态
  private String statusName;
  private String agencyName;
  private String orderNo;// 对外订单号（当前时间加4位随机数）
  private String pushStatus;// 推送状态
  private String createDateString;// 推送时间
  private String updateDateString;// 确认时间
  private String beganday, endday;
  private String beganday2, endday2;



  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Long getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(Long agencyId) {
    this.agencyId = agencyId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (this.createDate != null) {
      this.createDateString = StringUtil.dateToString(this.createDate);
    }
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    if (this.updateDate != null) {
      this.updateDateString = StringUtil.dateToString(this.updateDate);
    }
  }

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

  /**
   * @return the agencyName
   */
  public String getAgencyName() {
    return agencyName;
  }

  /**
   * @param agencyName the agencyName to set
   */
  public void setAgencyName(String agencyName) {
    this.agencyName = agencyName;
  }

  /**
   * @return the orderNo
   */
  public String getOrderNo() {
    return orderNo;
  }

  /**
   * @param orderNo the orderNo to set
   */
  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  /**
   * @return the pushStatus
   */
  public String getPushStatus() {
    return pushStatus;
  }

  /**
   * @param pushStatus the pushStatus to set
   */
  public void setPushStatus(String pushStatus) {
    this.pushStatus = pushStatus;
  }

  /**
   * @return the createDateString
   */
  public String getCreateDateString() {
    return createDateString;
  }

  /**
   * @param createDateString the createDateString to set
   */
  public void setCreateDateString(String createDateString) {
    this.createDateString = createDateString;
  }

  /**
   * @return the updateDateString
   */
  public String getUpdateDateString() {
    return updateDateString;
  }

  /**
   * @param updateDateString the updateDateString to set
   */
  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }

  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public String getEndday() {
    return endday;
  }

  public void setEndday(String endday) {
    this.endday = endday;
  }

  public String getBeganday2() {
    return beganday2;
  }

  public void setBeganday2(String beganday2) {
    this.beganday2 = beganday2;
  }

  public String getEndday2() {
    return endday2;
  }

  public void setEndday2(String endday2) {
    this.endday2 = endday2;
  }
}
