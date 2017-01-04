package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 用户订单发货管理-- user_order_ship by Lie
 * 
 */

public class OrderShipPojo extends SuperPojo {
  private Long id;// 编号
  private Long orderId;// 订单ID
  private Long userId;// 用户编号
  private String logisticsName, logisticsNameCN;// 物流名称
  private String logisticsNo;// 物流单号
  private String consignor;// 发货人
  private String consignorAddress;// 发货地址
  private String shipPhone;// 发货电话
  private String consignee;// 收货人
  private String consigneeAddress;// 收货地址
  private String consigneePhone;// 收货人电话
  private Integer consigneeType;// 收货人方式 (取业务字典：1快递2邮局)
  private String buyerMessage;// 买家留言
  private String remarks;// 买家留言
  private Integer orderStatus;// 订单状态 (取业务字典：1.待付款2.已付款3.已发货4.已确认5.已评论)
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String orderNo;// 对外订单号（当前时间加4位随机数）
  private String orderStatusName, consigneeTypeName, createDateName;
  private String productImages;// 订单产品的图片
  private String createDateStr;
  private Integer panduan;// 用来判断是否根据订单号来精确查询

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getLogisticsName() {
    return logisticsName;
  }

  public void setLogisticsName(String logisticsName) {
    this.logisticsName = logisticsName;
  }

  public String getLogisticsNo() {
    return logisticsNo;
  }

  public void setLogisticsNo(String logisticsNo) {
    this.logisticsNo = logisticsNo;
  }

  public String getConsignor() {
    return consignor;
  }

  public void setConsignor(String consignor) {
    this.consignor = consignor;
  }

  public String getConsignorAddress() {
    return consignorAddress;
  }

  public void setConsignorAddress(String consignorAddress) {
    this.consignorAddress = consignorAddress;
  }

  public String getShipPhone() {
    return shipPhone;
  }

  public void setShipPhone(String shipPhone) {
    this.shipPhone = shipPhone;
  }

  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  public String getConsigneeAddress() {
    return consigneeAddress;
  }

  public void setConsigneeAddress(String consigneeAddress) {
    this.consigneeAddress = consigneeAddress;
  }

  public String getConsigneePhone() {
    return consigneePhone;
  }

  public void setConsigneePhone(String consigneePhone) {
    this.consigneePhone = consigneePhone;
  }

  public Integer getConsigneeType() {
    return consigneeType;
  }

  public void setConsigneeType(Integer consigneeType) {
    this.consigneeType = consigneeType;
  }

  public String getBuyerMessage() {
    return buyerMessage;
  }

  public void setBuyerMessage(String buyerMessage) {
    this.buyerMessage = buyerMessage;
  }

  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  @Override
  public Long getCreateBy() {
    return createBy;
  }

  @Override
  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    createDateName = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Long getUpdateBy() {
    return updateBy;
  }

  @Override
  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getOrderStatusName() {
    return orderStatusName;
  }

  public void setOrderStatusName(String orderStatusName) {
    this.orderStatusName = orderStatusName;
  }

  public String getConsigneeTypeName() {
    return consigneeTypeName;
  }

  public void setConsigneeTypeName(String consigneeTypeName) {
    this.consigneeTypeName = consigneeTypeName;
  }

  public String getCreateDateName() {
    return createDateName;
  }

  public void setCreateDateName(String createDateName) {
    this.createDateName = createDateName;
  }

  public String getLogisticsNameCN() {
    return logisticsNameCN;
  }

  public void setLogisticsNameCN(String logisticsNameCN) {
    this.logisticsNameCN = logisticsNameCN;
  }

  public String getProductImages() {
    return productImages;
  }

  public void setProductImages(String productImages) {
    this.productImages = productImages;
  }

  public String getCreateDateStr() {
    if (getCreateDate() != null) {
      return StringUtil.dateString(getCreateDate());
    }
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public Integer getPanduan() {
    return panduan;
  }

  public void setPanduan(Integer panduan) {
    this.panduan = panduan;
  }


}
