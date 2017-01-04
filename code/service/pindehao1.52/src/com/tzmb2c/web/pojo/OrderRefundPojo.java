package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 用户退货订单-- user_order_refund by Lie
 * 
 */

public class OrderRefundPojo extends SuperPojo {
  private Long id;// 编号
  private Long orderId;// 订单ID
  private Long userId;// 用户编号
  private String loginname;// 下订单的用户登录名
  private Long shopId;// 店铺ID
  private Long productId;// 产品ID
  private String productName;// 产品名称
  private String productModel;// 产品规格
  private Long stockId;// 库存ID
  private Double stockPriceOld;// 产品原价
  private Double stockPrice;// 产品价格
  private Integer refundNum;// 数量
  private String refundReason;// 退货原因
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;
  private Long createBy;// 创建者
  private Date createDate;// 创建时间
  private Long updateBy;// 更新者
  private Date updateDate;// 更新时间
  private String remarks; // 备注信息
  private Integer version;// 版本号
  private Integer refundType;// 退货类型
  private String images;// 图片
  private String logistics;// 物流编号
  private String logType;// 物流类型
  private Long detailId;
  private Long type;// 退货\退款状态
  // sku关联id
  private Integer skuLinkId;
  private Double couponPrice;// 优惠金额
  private String option;// 用于操作判断
  private int serviceInvolved;// 是否申请客服介入1否2为是
  private String consigneePhone;
  private String images2;
  private String images3;
  private String phone;
  private String beginTime;
  private String endTime;
  private Double refundMoney;


  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getConsigneePhone() {
    return consigneePhone;
  }

  public void setConsigneePhone(String consigneePhone) {
    this.consigneePhone = consigneePhone;
  }

  public int getServiceInvolved() {
    return serviceInvolved;
  }

  public void setServiceInvolved(int serviceInvolved) {
    this.serviceInvolved = serviceInvolved;
  }

  public Integer getSkuLinkId() {
    return skuLinkId;
  }

  public void setSkuLinkId(Integer skuLinkId) {
    this.skuLinkId = skuLinkId;
  }

  public String getLogistics() {
    return logistics;
  }

  public void setLogistics(String logistics) {
    this.logistics = logistics;
  }

  public String getLogType() {
    return logType;
  }

  public void setLogType(String logType) {
    this.logType = logType;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public Integer getRefundType() {
    return refundType;
  }

  public void setRefundType(Integer refundType) {
    this.refundType = refundType;
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

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductModel() {
    return productModel;
  }

  public void setProductModel(String productModel) {
    this.productModel = productModel;
  }

  public Long getStockId() {
    return stockId;
  }

  public void setStockId(Long stockId) {
    this.stockId = stockId;
  }

  public Double getStockPriceOld() {
    return stockPriceOld;
  }

  public void setStockPriceOld(Double stockPriceOld) {
    this.stockPriceOld = stockPriceOld;
  }

  public Double getStockPrice() {
    return stockPrice;
  }

  public void setStockPrice(Double stockPrice) {
    this.stockPrice = stockPrice;
  }

  public Integer getRefundNum() {
    return refundNum;
  }

  public void setRefundNum(Integer refundNum) {
    this.refundNum = refundNum;
  }

  public String getRefundReason() {
    return refundReason;
  }

  public void setRefundReason(String refundReason) {
    this.refundReason = refundReason;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  public Long getDetailId() {
    return detailId;
  }

  public void setDetailId(Long detailId) {
    this.detailId = detailId;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public Double getCouponPrice() {
    return couponPrice;
  }

  public void setCouponPrice(Double couponPrice) {
    this.couponPrice = couponPrice;
  }

  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }

  public String getImages2() {
    return images2;
  }

  public void setImages2(String images2) {
    this.images2 = images2;
  }

  public String getImages3() {
    return images3;
  }

  public void setImages3(String images3) {
    this.images3 = images3;
  }

  public Double getRefundMoney() {
    return refundMoney;
  }

  public void setRefundMoney(Double refundMoney) {
    if (refundMoney != null) {
      this.refundMoney = refundMoney;
    }
  }
}
