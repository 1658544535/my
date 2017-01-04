package com.tzmb2c.web.pojo;

import java.text.DecimalFormat;
import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 用户订单详情-- user_order_detail by Lie
 * 
 */

public class OrderDetailPojo extends SuperPojo {
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
  private Integer num;// 数量
  private Integer refundNum;// 退货数量
  private Double allStockPrice;// 产品价格小计
  private Integer type;// 类型(取业务字典：待定)
  private Integer channel;// 来源渠道(取业务字典：0.PC端1.APP 2.微信)
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private Long createBy;// 创建者
  private Date createDate;// 创建时间
  private Long updateBy;// 更新者
  private Date updateDate;// 更新时间
  private String remarks; // 备注信息
  private Integer version;// 版本号
  private String productImage;// 产品图片
  private String productImages;
  private String productNum;// 订单产品的货号
  private String size, color;
  private String shopName;// 店铺名称
  private String shopImages;// 店铺的图片
  private String channelName;
  private String orderNo;// 对外订单号（当前时间加4位随机数）
  private String createDateStr;
  private String updateDateStr;
  private Double subtotal;// 小计的金额
  private String sumtotal;// 产品价格总数
  private Integer postageType;// 是否包邮
  private String userName;// 产品价格总数
  private Long reStatus;
  private String weight;// 产品重量
  private String allweights;// 总重量
  private String isCancelOrder;
  private Integer productStatus;// 产品状态
  // sku关联id
  private Integer skuLinkId;
  private String outTradeNo; // 备注信息
  private String sentTime;// 发货时间
  private String sentTimes;
  private String tradeNo;
  private String tradeNos;
  private String value;// sku_attribute value
  private String productSku; // 商品sku
  private String businessCode;// 商家编码
  private String stockPriceMultiplyNum;// 产品价格乘以数量
  private String orderStatusName;// 订单状态名称
  private String serialNumber;// 物流流水号
  private String contentGuide;// 用于区别是否为内容导购
  private String userType;// 发表人类型名称
  private String userType1;
  private String contentTitle;// 平台专题内容标题
  private String contentTitle1;// 创客专题内容标题
  private String contentTitle2;// 笔记内容标题
  private String contentTitle3;// 微页面内容标题
  private String sourceName;// 内容类型
  private String beganday, endday;
  private Integer types;// 发表人类型
  private Integer types1;
  private String sysLoginName;// 创客专题所对应的用户昵称
  private String sysLoginName1;// 笔记所对应的用户昵称
  private Date createDate1;// 创建时间
  private String createDateStr1;
  private String skuColor;
  private String skuFormat;
  private Double allPrice;
  private Double walletPrice;

  public Double getWalletPrice() {
    return walletPrice;
  }

  public void setWalletPrice(Double walletPrice) {
    this.walletPrice = walletPrice;
  }

  /**
   * 活动ID
   */
  private Long activityId;
  /**
   * 活动名称
   */
  private String activityName;

  /**
   * source:来源,1 平台专题 2 创客专题 3笔记 4微页面
   */
  private Integer source;

  /**
   * sourceId:来源ID
   */
  private Long sourceId;

  public String getContentGuide() {
    return contentGuide;
  }

  public void setContentGuide(String contentGuide) {
    this.contentGuide = contentGuide;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public Long getSourceId() {
    return sourceId;
  }

  public void setSourceId(Long sourceId) {
    this.sourceId = sourceId;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }


  public Integer getSkuLinkId() {
    return skuLinkId;
  }

  public void setSkuLinkId(Integer skuLinkId) {
    this.skuLinkId = skuLinkId;
  }

  public Integer getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(Integer productStatus) {
    this.productStatus = productStatus;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public String getIsCancelOrder() {
    return isCancelOrder;
  }

  public void setIsCancelOrder(String isCancelOrder) {
    this.isCancelOrder = isCancelOrder;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public Double getSubtotal() {
    return subtotal;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getShopImages() {
    return shopImages;
  }

  public void setShopImages(String shopImages) {
    this.shopImages = shopImages;
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
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

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
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
    createDateStr = StringUtil.dateToString(this.createDate);
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
    updateDateStr = StringUtil.dateToString(this.updateDate);
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

  public String getProductImages() {
    return productImages;
  }

  public void setProductImages(String productImages) {
    this.productImages = productImages;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public Double getAllStockPrice() {
    return allStockPrice;
  }

  public void setAllStockPrice(Double allStockPrice) {
    this.allStockPrice = allStockPrice;
  }

  public String getSumtotal() {
    return sumtotal;
  }

  public void setSumtotal(String sumtotal) {
    this.sumtotal = sumtotal;
  }

  public Integer getPostageType() {
    return postageType;
  }

  public void setPostageType(Integer postageType) {
    this.postageType = postageType;
  }

  public Long getReStatus() {
    return reStatus;
  }

  public void setReStatus(Long reStatus) {
    this.reStatus = reStatus;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getAllweights() {
    return allweights;
  }

  public void setAllweights(String allweights) {
    this.allweights = allweights;
  }

  public String getSentTime() {
    return sentTime;
  }

  public void setSentTime(String sentTime) {
    this.sentTime = sentTime;
  }

  public String getSentTimes() {
    return sentTimes;
  }

  public void setSentTimes(String sentTimes) {
    this.sentTimes = sentTimes;
  }

  public Integer getRefundNum() {
    return refundNum;
  }

  public void setRefundNum(Integer refundNum) {
    this.refundNum = refundNum;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String getTradeNos() {
    return tradeNos;
  }

  public void setTradeNos(String tradeNos) {
    this.tradeNos = tradeNos;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getProductSku() {
    return productSku;
  }

  public void setProductSku(String productSku) {
    this.productSku = productSku;
  }

  public String getBusinessCode() {
    return businessCode;
  }

  public void setBusinessCode(String businessCode) {
    this.businessCode = businessCode;
  }

  public String getStockPriceMultiplyNum() {
    if (getStockPrice() != null && getNum() != null) {
      DecimalFormat df = new DecimalFormat("#.##");
      return df.format(getStockPrice() * getNum());
    }
    return stockPriceMultiplyNum;
  }

  public void setStockPriceMultiplyNum(String stockPriceMultiplyNum) {
    this.stockPriceMultiplyNum = stockPriceMultiplyNum;
  }

  public String getOrderStatusName() {
    return orderStatusName;
  }

  public void setOrderStatusName(String orderStatusName) {
    this.orderStatusName = orderStatusName;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getContentTitle() {
    return contentTitle;
  }

  public void setContentTitle(String contentTitle) {
    this.contentTitle = contentTitle;
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
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

  public Integer getTypes() {
    return types;
  }

  public void setTypes(Integer types) {
    this.types = types;
  }

  public String getSysLoginName() {
    return sysLoginName;
  }

  public void setSysLoginName(String sysLoginName) {
    this.sysLoginName = sysLoginName;
  }

  public String getUserType1() {
    return userType1;
  }

  public void setUserType1(String userType1) {
    this.userType1 = userType1;
  }

  public Integer getTypes1() {
    return types1;
  }

  public void setTypes1(Integer types1) {
    this.types1 = types1;
  }

  public String getSysLoginName1() {
    return sysLoginName1;
  }

  public void setSysLoginName1(String sysLoginName1) {
    this.sysLoginName1 = sysLoginName1;
  }

  public String getContentTitle1() {
    return contentTitle1;
  }

  public void setContentTitle1(String contentTitle1) {
    this.contentTitle1 = contentTitle1;
  }

  public String getContentTitle2() {
    return contentTitle2;
  }

  public void setContentTitle2(String contentTitle2) {
    this.contentTitle2 = contentTitle2;
  }

  public String getContentTitle3() {
    return contentTitle3;
  }

  public void setContentTitle3(String contentTitle3) {
    this.contentTitle3 = contentTitle3;
  }

  public Date getCreateDate1() {
    return createDate1;
  }

  public void setCreateDate1(Date createDate1) {
    this.createDate1 = createDate1;
    setCreateDateStr1(StringUtil.dateToString(this.createDate1));
  }

  public String getCreateDateStr1() {
    return createDateStr1;
  }

  public void setCreateDateStr1(String createDateStr1) {
    this.createDateStr1 = createDateStr1;
  }

  public String getSkuColor() {
    return skuColor;
  }

  public void setSkuColor(String skuColor) {
    this.skuColor = skuColor;
  }

  public String getSkuFormat() {
    return skuFormat;
  }

  public void setSkuFormat(String skuFormat) {
    this.skuFormat = skuFormat;
  }

  public Double getAllPrice() {
    return allPrice;
  }

  public void setAllPrice(Double allPrice) {
    this.allPrice = allPrice;
  }

}
