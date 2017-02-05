package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 退货记录
 * 
 * @author creazylee
 * 
 */
public class UserOrderRefundPojo extends SuperPojo {

  private Long id;// 编号
  private Long orderId;// 订单id
  private String orderNo;// 订单号
  private Long userId;// 用户id
  private String loginName;// 下订单的用户名
  private Long shopId;// 店铺id
  private String shopName;// 店铺名称
  private Long productId;// 产品id
  private String productName;// 产品名称
  private String productImage;// 产品图片
  private String productModel;// 产品规格
  private Long stockId;// 库存id
  private Double stockPriceOld;// 产品原价
  private Double stockPrice;// 产品价格
  private Integer refundNum;// 退回数量
  private String refundReason;// 退货原因
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态字符
  private String images;// 图片
  private String serialNumber;// 物流信息
  private Date createDate;// 创建时间
  private Date updateDate;// 修改时间
  private String creatDateString;
  private String createDateStartStr;
  private String createDateEndStr;
  private String updateDateString;
  private long detailId;
  private int type;// 售后类型(1：退款 2：退货 3：售后服务)
  private String typeName;// 售后类型(1：退款 2：退货 3：售后服务)
  private String name;// 用户昵称
  private String logType;// 快递类型
  private String logisticsNameCN;// 快递名称
  private String logistics;// 快递号
  private Integer buyNum;// 购买数量
  private Integer skuLinkId;// sku关联id
  private Integer refundTotalPrice;// 退货订单总金额
  private Double refundSumPrice;// 退货总金额
  private Double sumPrice;// 总金额
  private Integer refundType;// 退货原因类型（1不喜欢，2质量不好，3尺码不对，4颜色不对，5其他）
  private String refundTypeName;// 退货原因类型名称
  private String address;// 退货地址
  private Double couponPrice;// 优惠金额
  private Double refundMoney;// 实际退回钱包的钱（0为未退，非0为已退）
  private String outTradeNo;// 唯一订单号（后台生成），对应支付宝订单号
  private Date refundMoneyDate;// 退款時間
  private String refundDateString;
  private String consigneePhone;
  private int serviceInvolved;// 是否申请客服介入(0否,1是)
  private String serviceInvolvedName;// 申请客服介入名
  private String rejectImages;// 商家驳回图片
  private String rejectReason;// 商家驳回原因
  private String userName;
  private Double allPrice;
  private Double factPrice;
  private Integer refundStatus;// 售后状态(0全部,1审核中,2审核通过,3审核不同,4完成)
  private String images2;
  private String images3;
  private Integer payMethod;;
  private String tradeNo;// 交易号
  private Integer isRefund;// 退款状态：0-未退款，1-处理中，2-退款成功，3-退款失败
  private String outRefundNo;// 商户退款单号
  private Date refundDate;// 退款时间
  private Integer oldRefund;
  private String loginname1;// 用户账号
  private Integer auditStatus;// 退货记录中用来搜索审核状态的
  private Date orderDate;// 下单时间
  private String orderDateString;
  private String consignee;// 收货人
  private String consigneeAddress;// 收货地址
  private String logisticsName;// 物流公司
  private String productNum;
  private Long activityId;
  private Integer source;
  private Integer reStatus;
  private String idStr;
  private String beginDate, endDate;

  public String getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(String beginDate) {
    this.beginDate = beginDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getIdStr() {
    return idStr;
  }

  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  public Integer getReStatus() {
    return reStatus;
  }

  public void setReStatus(Integer reStatus) {
    this.reStatus = reStatus;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public Integer getOldRefund() {
    return oldRefund;
  }

  public void setOldRefund(Integer oldRefund) {
    this.oldRefund = oldRefund;
  }

  public Integer getIsRefund() {
    return isRefund;
  }

  public void setIsRefund(Integer isRefund) {
    this.isRefund = isRefund;
  }

  public String getOutRefundNo() {
    return outRefundNo;
  }

  public void setOutRefundNo(String outRefundNo) {
    this.outRefundNo = outRefundNo;
  }

  public Date getRefundDate() {
    return refundDate;
  }

  public void setRefundDate(Date refundDate) {
    this.refundDate = refundDate;
  }

  public Integer getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(Integer refundStatus) {
    this.refundStatus = refundStatus;
  }

  public Double getAllPrice() {
    return allPrice;
  }

  public void setAllPrice(Double allPrice) {
    this.allPrice = allPrice;
  }

  public Double getFactPrice() {
    return factPrice;
  }

  public void setFactPrice(Double factPrice) {
    this.factPrice = factPrice;
  }

  public String getConsigneePhone() {
    return consigneePhone;
  }

  public void setConsigneePhone(String consigneePhone) {
    this.consigneePhone = consigneePhone;
  }

  public Integer getSkuLinkId() {
    return skuLinkId;
  }

  public void setSkuLinkId(Integer skuLinkId) {
    this.skuLinkId = skuLinkId;
  }

  public Integer getBuyNum() {
    return buyNum;
  }

  public void setBuyNum(Integer buyNum) {
    this.buyNum = buyNum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    creatDateString = StringUtil.dateString(this.createDate);
  }

  public String getCreatDateString() {
    if (getCreateDate() != null) {
      creatDateString = StringUtil.dateString(createDate);
    }
    return creatDateString;
  }

  public void setCreatDateString(String creatDateString) {
    this.creatDateString = creatDateString;
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    updateDateString = StringUtil.dateString(this.updateDate);
  }

  public String getUpdateDateString() {
    return updateDateString;
  }

  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
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

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
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

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public long getDetailId() {
    return detailId;
  }

  public void setDetailId(long detailId) {
    this.detailId = detailId;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getLogType() {
    return logType;
  }

  public void setLogType(String logType) {
    this.logType = logType;
  }

  public String getLogistics() {
    return logistics;
  }

  public void setLogistics(String logistics) {
    this.logistics = logistics;
  }

  public Integer getRefundTotalPrice() {
    return refundTotalPrice;
  }

  public void setRefundTotalPrice(Integer refundTotalPrice) {
    this.refundTotalPrice = refundTotalPrice;
  }

  public Integer getRefundType() {
    return refundType;
  }

  public void setRefundType(Integer refundType) {
    this.refundType = refundType;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getLogisticsNameCN() {
    return logisticsNameCN;
  }

  public void setLogisticsNameCN(String logisticsNameCN) {
    this.logisticsNameCN = logisticsNameCN;
  }

  public Double getCouponPrice() {
    return couponPrice;
  }

  public void setCouponPrice(Double couponPrice) {
    this.couponPrice = couponPrice;
  }

  public Double getRefundMoney() {
    return refundMoney;
  }

  public void setRefundMoney(Double refundMoney) {
    if (refundMoney != null) {
      this.refundMoney = refundMoney;
    }
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public Date getRefundMoneyDate() {
    return refundMoneyDate;
  }

  public void setRefundMoneyDate(Date refundMoneyDate) {
    this.refundMoneyDate = refundMoneyDate;
    refundDateString = StringUtil.dateString(this.refundMoneyDate);
  }

  public String getRefundDateString() {
    return refundDateString;
  }

  public void setRefundDateStringg(String refundDateString) {
    this.refundDateString = refundDateString;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public int getServiceInvolved() {
    return serviceInvolved;
  }

  public void setServiceInvolved(int serviceInvolved) {
    this.serviceInvolved = serviceInvolved;
  }

  public String getServiceInvolvedName() {
    return serviceInvolvedName;
  }

  public void setServiceInvolvedName(String serviceInvolvedName) {
    this.serviceInvolvedName = serviceInvolvedName;
  }

  public Double getRefundSumPrice() {
    return refundSumPrice;
  }

  public void setRefundSumPrice(Double refundSumPrice) {
    this.refundSumPrice = refundSumPrice;
  }

  public Double getSumPrice() {
    return sumPrice;
  }

  public void setSumPrice(Double sumPrice) {
    this.sumPrice = sumPrice;
  }

  public String getRejectImages() {
    return rejectImages;
  }

  public void setRejectImages(String rejectImages) {
    this.rejectImages = rejectImages;
  }

  public String getRejectReason() {
    return rejectReason;
  }

  public void setRejectReason(String rejectReason) {
    this.rejectReason = rejectReason;
  }

  public String getRefundTypeName() {
    return refundTypeName;
  }

  public void setRefundTypeName(String refundTypeName) {
    this.refundTypeName = refundTypeName;
  }

  public String getCreateDateStartStr() {
    return createDateStartStr;
  }

  public void setCreateDateStartStr(String createDateStartStr) {
    this.createDateStartStr = createDateStartStr;
  }

  public String getCreateDateEndStr() {
    return createDateEndStr;
  }

  public void setCreateDateEndStr(String createDateEndStr) {
    this.createDateEndStr = createDateEndStr;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public Integer getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(Integer payMethod) {
    this.payMethod = payMethod;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String getLoginname1() {
    return loginname1;
  }

  public void setLoginname1(String loginname1) {
    this.loginname1 = loginname1;
  }

  public Integer getAuditStatus() {
    return auditStatus;
  }

  public void setAuditStatus(Integer auditStatus) {
    this.auditStatus = auditStatus;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
    setOrderDateString(StringUtil.dateString(this.orderDate));
  }

  public String getOrderDateString() {
    return orderDateString;
  }

  public void setOrderDateString(String orderDateString) {
    this.orderDateString = orderDateString;
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

  public String getLogisticsName() {
    return logisticsName;
  }

  public void setLogisticsName(String logisticsName) {
    this.logisticsName = logisticsName;
  }

}
