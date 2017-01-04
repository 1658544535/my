package com.tzmb2c.web.pojo;

import java.util.List;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 商品销售记录
 * 
 * @author creazylee
 * 
 */
public class UserOrderDetailPojo extends SuperPojo {

  private Long id;// 编号
  private Long orderId;// 订单id
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
  private Double sumStockPrice;
  private Integer num;// 数量
  private Integer sumNum;
  private Integer type;// 类型(取业务字典：待定)
  private String typeName;// 类型字符(取业务字典：待定)
  private Integer channel;// 来源渠道(取业务字典：0.PC端1.APP 2.微信)
  private String channelName;// 来源渠道字符
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态字符
  private String product_type_id;// 产品类型id
  private String product_num, productNum;// 产品货号
  private String product_no;// 产品序号
  private String create_date;// 创建时间
  private Integer postageType;// 是否包邮
  private String userName;// 用户昵称
  private String createDateString;// 创建时间
  private String beganday;
  private String endday;
  private String consignee;
  private String consigneeAddress;
  private String consigneePhone;
  private List<Integer> notuserIds;

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

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public Double getSumStockPrice() {
    return sumStockPrice;
  }

  public void setSumStockPrice(Double sumStockPrice) {
    this.sumStockPrice = sumStockPrice;
  }

  public Integer getSumNum() {
    return sumNum;
  }

  public void setSumNum(Integer sumNum) {
    this.sumNum = sumNum;
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

  public Integer getNum() {
    return num;
  }

  public List<Integer> getNotuserIds() {
    return notuserIds;
  }

  public void setNotuserIds(List<Integer> notuserIds) {
    this.notuserIds = notuserIds;
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

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
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

  public String getProduct_type_id() {
    return product_type_id;
  }

  public void setProduct_type_id(String product_type_id) {
    this.product_type_id = product_type_id;
  }

  public String getProduct_num() {
    return product_num;
  }

  public void setProduct_num(String product_num) {
    this.product_num = product_num;
  }

  public String getProduct_no() {
    return product_no;
  }

  public void setProduct_no(String product_no) {
    this.product_no = product_no;
  }

  public String getCreate_date() {
    return create_date;
  }

  public void setCreate_date(String create_date) {
    this.create_date = create_date;
  }

  public Integer getPostageType() {
    return postageType;
  }

  public void setPostageType(Integer postageType) {
    this.postageType = postageType;
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

}
