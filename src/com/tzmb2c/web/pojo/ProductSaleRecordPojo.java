package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 商品销售记录
 * 
 * @author creazylee
 * 
 */
public class ProductSaleRecordPojo extends SuperPojo {

  private Long id;// 编号
  private Long userId;// 用户编号
  private String consignee;// 收货人
  private String consigneeAddress;// 收货地址
  private String consigneePhone;// 收货人电话
  private Integer consigneeType;// 收货人方式 (取业务字典：1快递2邮局)
  private String buyerMessage;// 买家留言
  private Integer orderStatus;// 订单状态 (取业务字典：1.待付款2.已付款3.已发货4.已确认5.已评论)
  private String orderStatusName;
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String payStatusName;
  private Integer payStatus;// 支付状态(取业务字典：0待支付1支付成功)
  private String orderNo;// 对外订单号（当前时间加4位随机数）
  private String serialNumber;// 流水号/后台充值的账号
  private String createDateString;// 创建时间
  private String userName;// 用户昵称
  protected Date createDate;// 创建日期

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.createDateString = StringUtil.dateToString(this.createDate);
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

  public String getOrderStatusName() {
    return orderStatusName;
  }

  public void setOrderStatusName(String orderStatusName) {
    this.orderStatusName = orderStatusName;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getPayStatusName() {
    return payStatusName;
  }

  public void setPayStatusName(String payStatusName) {
    this.payStatusName = payStatusName;
  }

  public Integer getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getCreateDateString() {
    return createDateString;
  }

  public void setCreateDateString(String createDateString) {
    this.createDateString = createDateString;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}
