package com.tzmb2c.web.pojo;

import java.util.Date;
import java.util.List;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.export.excel.ExcelConf;

/**
 * 
 * 用户订单-- user_order by Lie
 * 
 */

public class OrderPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private Long suserId;// 供应商用户ID
  private Long shopId;// 供应商店铺id
  private Double allPrice;// 订单原价
  private Double factPrice;// 实收金额
  private Double espressPrice;// 物流费
  private String consignee;// 收货人
  private String consigneeAddress;// 收货地址
  private String consigneePhone;// 收货人电话
  private Integer consigneeType;// 收货人方式 (取业务字典：1快递2自提)
  private String consigneeTypeName;// 收货人方式名称
  private String buyerMessage, sellerMessage;// 买家留言/商家留言
  private Integer orderStatus;// 订单状态 (取业务字典：1.待付款2.已付款3.已发货4.已确认5.已评论)
  private String orderStatusName;
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String payStatusName;
  private Integer payStatus;// 支付状态(取业务字典：0待支付1支付成功)
  private String orderNo;// 对外订单号（当前时间加4位随机数）
  private String serialNumber;// 流水号/后台充值的账号
  private String createDateString;// 创建时间
  private String userName;// 用户昵称
  private String logisticsName;// 快递名称
  private String logisticsNo;// 快递单号
  private String name;// 物流名称
  private String beganday, endday, beganday1, endday1;
  private String beganSendDate, endSendDate;
  private Double payN, payY;
  private String shopName;
  private String allNum, dfkNum, dfhNum, dshNum, dplNum;
  private Integer isDeleteOrder;// 状态(取业务字典：0正常1删除)
  private Integer isCancelOrder;// 状态(取业务字典：0正常1取消订单2退货3退货成功)
  private String productImages;// 订单产品的图片
  private String outTradeNo;// 唯一订单号（后台生成），对应支付宝订单号
  private List<OrderDetailPojo> cartList;
  private String creatDateString;
  private Integer channel;// 来源渠道
  private String channelName;
  private Long updateBy;// 更新者id
  private String updateByName;// 更新者昵称
  private String os;// 用于传递数值的订单状态
  private int count;// 后台订单中产品数量
  private Integer overdue;// 发货超过15天
  private String times;// 发货user_order_ship创建时间
  private String sendTimes; // 发货时间
  private Date sendDate; // 发货时间
  private String sendDateStr;
  private String[] tids;
  private String updateDateStr;
  private String province;
  private String city;
  private String area;
  private String productId;
  private int num;
  private String pay;
  private int userAddressId;
  private String remarks;
  private Long orderId;// 订单id
  private Integer agencyUserId;// 订单批发商id
  private Integer cityId;// 城市id
  private Integer pushStatus;// 推送状态
  private long pushAgencyUid;// 推送订单批发商id
  private Integer agencyId;// /推送订单批发商id
  private Integer agencyUid;// /推送订单批发商id
  private String consignor;// 发货人
  private String consignorAddress;// 发货地址
  private String shipPhone;// 发货人电话
  private Date createDate;
  private Integer payMethod;// 支付方式：1-支付宝，2-微信支付，3-货到付款，4-钱包全额付款，5-银联支付，6-苹果支付
  private String payMethodName;// 支付方式：1-支付宝，2-微信支付，3-货到付款，4-钱包全额付款，5-银联支付，6-苹果支付
  private String pushName;// 推送者名称
  private Integer firstFlag;// 首单标识：0-否、1-是

  private Double price;// 实付金额
  private Double usedPrice;// 优惠金额（使用金额）
  private String couponNo;// 优惠券券码
  // 退货状态
  private Integer refundStatus;
  private String refundStatusName;
  private String loginname;// 账号
  private String createDateStr;// 创建日期+时间
  private Date createDateStart;
  private Date createDateEnd;
  private String createDateStartStr;
  private String createDateEndStr;
  private List<Integer> notuserIds;

  private String brand;
  private String productName;
  private String productNum;
  private String stockPrice;
  private String provinces;

  private String beginTime;// 活动开始时间
  private String endTime;// 活动结束时间
  private Integer orderType;// 订单来源(0正常1导入2钱包支付订单)
  private Integer option;// 1-商家中心调用，null-后台调用
  private Integer oldOrderStatus;// 原订单状态
  private Date paymentDate; // 付款时间
  private String paymentDateStr;
  private Date confirmDate; // 确定订单时间
  private String confirmDateStr;
  private String productImage;// 商品图片
  private Integer source;// 订单来源
  private String sourceName;// 来源名称
  private Long skuLinkId;// skuId
  private Long sourceId;// 参团ID
  private Integer isSuccess;// 是否拼团成功
  private Integer groupNum;// N人团人数
  private Date groupDate;// 成团时间
  private String groupDateStr;// 成团时间
  private Integer waitSendNum;// 待发货数量
  private Integer groupingNum;// 拼团中数量
  private Long orderDid;// 订单详情ID
  private Integer saleSerNum;// 售后/退货数量
  private Integer isRefund;// 退款状态：0-未退款，1-处理中，2-退款成功，3-退款失败
  private String outRefundNo;// 商户退款单号
  private Date refundDate;// 退款时间
  private String refundDateStr;//
  private String tradeNo;// 交易号
  private String tradeNos;// 交易号
  private String refundStatus1;// 退货退款状态
  private Integer gurStatus;
  private Date attendTime; // 团员参与时间
  private Date headAttendTime; // 团长参与时间
  private String attendTimeStr;
  private String headAttendTimeStr;
  private Integer isHead;
  private String ip;// 下单ip
  private String groupBeginDateStr;
  private String groupEndDateStr;
  private String skuColor;
  private String skuFormat;
  private String businessCode;// 商家编码
  private Integer maxNum;// 最大购买数量
  private Integer notShip;// 24小时未发货（1为是）
  /**
   * 活动ID
   */
  private Long activityId;
  /**
   * 活动名称
   */
  private String activityName;
  /**
   * 专场折扣类型 0-未配置1-满减2-满折
   */
  private Integer discountType;
  /**
   * 折扣内容
   */
  private String discountContext;
  /**
   * 折扣金额
   */
  private Double discountPrice;
  /**
   * 是否结算
   */
  private Integer isSettle;
  /**
   * 结算日期
   */
  private Date settleDate;
  private String settleDateStr;
  /**
   * 结算金额
   */
  private Double settleAmount;
  /**
   * 结算条目ID
   */
  private Long settleId;
  private Integer settleStatus;
  private String settleStatusName;
  private String isCancelOrderName;
  /**
   * 钱包使用金额
   */
  private Double walletPrice;
  private Integer gaNum;
  private Long attendId;
  private Integer prize;
  /**
   * 返佣金额
   */
  private Double rebatePrice;
  /**
   * 是否返佣
   */
  private Integer isRebate;
  /**
   * 返佣时间
   */
  private Date rebateTime;
  /**
   * 拼得客user_id
   */
  private Long pdkUid;
  /**
   * 拼得客账号
   */
  private String pdkLoginname;
  /**
   * 商家扣款
   */
  private Double sellerDeduct;
  /**
   * 平台留言
   */
  private String platformMsg;
  /**
   * 订单利润
   */
  private Double orderProfit;
  /**
   * 商家货款
   */
  private Double sellerGoodsPrice;
  private Integer saleApplyNum;// 退款申请待处理
  private Integer saleOverNum;// 已退货待处理
  /**
   * 邀请码
   */
  private String inviteCode;
  private Long inviterId;// 推荐者
  private Double factPriceAll;// 销售额
  private Integer isHandle;// 是否待处理
  private String csRemarks;
  /**
   * 自动退款时间
   */
  private Date autoRecTime;
  /**
   * 自动退款时间字符串
   */
  private String autoRecTimeStr;

  public Integer getSaleApplyNum() {
    return saleApplyNum;
  }

  public void setSaleApplyNum(Integer saleApplyNum) {
    this.saleApplyNum = saleApplyNum;
  }

  public String getAutoRecTimeStr() {
    if (autoRecTime != null) {
      autoRecTimeStr = StringUtil.dateString(autoRecTime);
    }
    return autoRecTimeStr;
  }

  public void setAutoRecTimeStr(String autoRecTimeStr) {
    this.autoRecTimeStr = autoRecTimeStr;
  }


  public Integer getSaleOverNum() {
    return saleOverNum;
  }

  public void setSaleOverNum(Integer saleOverNum) {
    this.saleOverNum = saleOverNum;
  }

  public Double getSellerDeduct() {
    return sellerDeduct;
  }

  public void setSellerDeduct(Double sellerDeduct) {
    this.sellerDeduct = sellerDeduct;
  }

  public String getPlatformMsg() {
    return platformMsg;
  }

  public void setPlatformMsg(String platformMsg) {
    this.platformMsg = platformMsg;
  }

  public Double getOrderProfit() {
    return orderProfit;
  }

  public void setOrderProfit(Double orderProfit) {
    this.orderProfit = orderProfit;
  }

  public Double getSellerGoodsPrice() {
    return sellerGoodsPrice;
  }

  public void setSellerGoodsPrice(Double sellerGoodsPrice) {
    this.sellerGoodsPrice = sellerGoodsPrice;
  }

  public Date getAutoRecTime() {
    return autoRecTime;
  }

  public void setAutoRecTime(Date autoRecTime) {
    this.autoRecTime = autoRecTime;
  }

  public String getInviteCode() {
    return inviteCode;
  }

  public void setInviteCode(String inviteCode) {
    this.inviteCode = inviteCode;
  }

  public String getPdkLoginname() {
    return pdkLoginname;
  }

  public void setPdkLoginname(String pdkLoginname) {
    this.pdkLoginname = pdkLoginname;
  }

  public Long getPdkUid() {
    return pdkUid;
  }

  public void setPdkUid(Long pdkUid) {
    this.pdkUid = pdkUid;
  }

  public Date getRebateTime() {
    return rebateTime;
  }

  public void setRebateTime(Date rebateTime) {
    this.rebateTime = rebateTime;
  }

  public Integer getIsRebate() {
    return isRebate;
  }

  public void setIsRebate(Integer isRebate) {
    this.isRebate = isRebate;
  }

  public Double getRebatePrice() {
    return rebatePrice;
  }

  public void setRebatePrice(Double rebatePrice) {
    this.rebatePrice = rebatePrice;
  }

  public Integer getPrize() {
    return prize;
  }

  public void setPrize(Integer prize) {
    this.prize = prize;
  }

  public Long getAttendId() {
    return attendId;
  }

  public void setAttendId(Long attendId) {
    this.attendId = attendId;
  }

  public Integer getGaNum() {
    return gaNum;
  }

  public void setGaNum(Integer gaNum) {
    this.gaNum = gaNum;
  }

  public Double getWalletPrice() {
    return walletPrice;
  }

  public void setWalletPrice(Double walletPrice) {
    this.walletPrice = walletPrice;
  }

  public String getSettleDateStr() {
    return settleDateStr;
  }

  public void setSettleDateStr(String settleDateStr) {
    this.settleDateStr = settleDateStr;
  }

  public Integer getIsSettle() {
    return isSettle;
  }

  public void setIsSettle(Integer isSettle) {
    this.isSettle = isSettle;
  }

  public Date getSettleDate() {
    return settleDate;
  }

  public void setSettleDate(Date settleDate) {
    this.settleDate = settleDate;
    if (this.settleDate != null) {
      settleDateStr = StringUtil.dateToString(this.settleDate);
    }
  }

  public Double getSettleAmount() {
    return settleAmount;
  }

  public void setSettleAmount(Double settleAmount) {
    this.settleAmount = settleAmount;
  }

  public Long getSettleId() {
    return settleId;
  }

  public void setSettleId(Long settleId) {
    this.settleId = settleId;
  }

  public Double getDiscountPrice() {
    return discountPrice;
  }

  public void setDiscountPrice(Double discountPrice) {
    this.discountPrice = discountPrice;
  }

  public Integer getDiscountType() {
    return discountType;
  }

  public void setDiscountType(Integer discountType) {
    this.discountType = discountType;
  }

  public String getDiscountContext() {
    return discountContext;
  }

  public void setDiscountContext(String discountContext) {
    this.discountContext = discountContext;
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

  public Integer getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(Integer refundStatus) {
    this.refundStatus = refundStatus;
    if (this.refundStatus != null) {
      if (refundStatus == 0) {
        refundStatusName = "无";
      } else {
        refundStatusName = "有";
      }
    }

  }

  public String getRefundStatusName() {
    return refundStatusName;
  }

  public void setRefundStatusName(String refundStatusName) {
    this.refundStatusName = refundStatusName;
  }

  public Integer getFirstFlag() {
    return firstFlag;
  }

  public void setFirstFlag(Integer firstFlag) {
    this.firstFlag = firstFlag;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Date getSendDate() {
    return sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
    if (this.sendDate != null) {
      sendTimes = StringUtil.dateToString(this.sendDate);
      sendDateStr = StringUtil.dateString(this.sendDate);
    }
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public int getUserAddressId() {
    return userAddressId;
  }

  public void setUserAddressId(int userAddressId) {
    this.userAddressId = userAddressId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getPay() {
    return pay;
  }

  public void setPay(String pay) {
    this.pay = pay;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public Integer getOverdue() {
    return overdue;
  }

  public void setOverdue(Integer overdue) {
    this.overdue = overdue;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  @ExcelConf(headName = "操作者", order = "10")
  public String getUpdateByName() {
    return updateByName;
  }

  public void setUpdateByName(String updateByName) {
    this.updateByName = updateByName;
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

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public String getTimes() {
    return times;
  }

  public void setTimes(String times) {
    this.times = times;
  }

  public String getSendTimes() {
    return sendTimes;
  }

  public void setSendTimes(String sendTimes) {
    this.sendTimes = sendTimes.substring(0, sendTimes.length() - 2);
  }

  public String getCreatDateString() {
    return creatDateString;
  }

  public void setCreatDateString(String creatDateString) {
    this.creatDateString = creatDateString;
  }

  private Double sumprice;


  public Double getSumprice() {
    return sumprice;
  }

  public void setSumprice(Double sumprice) {
    this.sumprice = sumprice;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getProductImages() {
    return productImages;
  }

  public void setProductImages(String productImages) {
    this.productImages = productImages;
  }

  public Integer getIsCancelOrder() {
    return isCancelOrder;
  }

  public void setIsCancelOrder(Integer isCancelOrder) {
    this.isCancelOrder = isCancelOrder;
  }

  public Integer getIsDeleteOrder() {
    return isDeleteOrder;
  }

  public void setIsDeleteOrder(Integer isDeleteOrder) {
    this.isDeleteOrder = isDeleteOrder;
  }

  public String getAllNum() {
    return allNum;
  }

  public void setAllNum(String allNum) {
    this.allNum = allNum;
  }

  public String getDfkNum() {
    return dfkNum;
  }

  public void setDfkNum(String dfkNum) {
    this.dfkNum = dfkNum;
  }

  public String getDfhNum() {
    return dfhNum;
  }

  public void setDfhNum(String dfhNum) {
    this.dfhNum = dfhNum;
  }

  public String getDshNum() {
    return dshNum;
  }

  public void setDshNum(String dshNum) {
    this.dshNum = dshNum;
  }

  public String getDplNum() {
    return dplNum;
  }

  public void setDplNum(String dplNum) {
    this.dplNum = dplNum;
  }

  public Double getPayN() {
    return payN;
  }

  public void setPayN(Double payN) {
    this.payN = payN;
  }

  public Double getPayY() {
    return payY;
  }

  public void setPayY(Double payY) {
    this.payY = payY;
  }

  public Long getSuserId() {
    return suserId;
  }

  public void setSuserId(Long suserId) {
    this.suserId = suserId;
    // if(this.suserId==0)
    // this.shopName = "官方旗舰店";
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
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

  public Double getAllPrice() {
    return allPrice;
  }

  public void setAllPrice(Double allPrice) {
    this.allPrice = allPrice;
  }

  @ExcelConf(headName = "结算金额", order = "3")
  public Double getFactPrice() {
    return factPrice;
  }

  public void setFactPrice(Double factPrice) {
    this.factPrice = factPrice;
  }

  @ExcelConf(headName = "收货人", order = "6")
  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  @ExcelConf(headName = "收货地址", order = "7")
  public String getConsigneeAddress() {
    return consigneeAddress;
  }

  public void setConsigneeAddress(String consigneeAddress) {
    this.consigneeAddress = consigneeAddress;
  }

  @ExcelConf(headName = "联系人电话", order = "8")
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

  public Integer getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(Integer payStatus) {
    this.payStatus = payStatus;
  }

  @ExcelConf(headName = "订单号", order = "2")
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
    if (this.createDate != null) {
      createDateString = StringUtil.dateToString(this.createDate);
      creatDateString = StringUtil.dateString(this.createDate);
    }
  }

  @ExcelConf(headName = "创建时间", order = "9")
  public String getCreateDateString() {
    return createDateString;
  }

  public void setCreateDateString(String createDateString) {
    this.createDateString = createDateString;
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
    updateDateStr = StringUtil.dateString(this.updateDate);
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  @ExcelConf(headName = "订单状态", order = "4")
  public String getOrderStatusName() {
    return orderStatusName;
  }

  public void setOrderStatusName(String orderStatusName) {
    this.orderStatusName = orderStatusName;
  }

  @ExcelConf(headName = "支付状态", order = "5")
  public String getPayStatusName() {
    return payStatusName;
  }

  public void setPayStatusName(String payStatusName) {
    this.payStatusName = payStatusName;
  }

  @ExcelConf(headName = "用户昵称", order = "1")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public Double getEspressPrice() {
    return espressPrice;
  }

  public void setEspressPrice(Double espressPrice) {
    this.espressPrice = espressPrice;
  }

  public List<OrderDetailPojo> getCartList() {
    return cartList;
  }

  public void setCartList(List<OrderDetailPojo> cartList) {
    this.cartList = cartList;
  }

  public String getConsigneeTypeName() {
    return consigneeTypeName;
  }

  public void setConsigneeTypeName(String consigneeTypeName) {
    this.consigneeTypeName = consigneeTypeName;
  }

  public Integer getAgencyUserId() {
    return agencyUserId;
  }

  public void setAgencyUserId(Integer agencyUserId) {
    this.agencyUserId = agencyUserId;
  }

  public Integer getPushStatus() {
    return pushStatus;
  }

  public void setPushStatus(Integer pushStatus) {
    this.pushStatus = pushStatus;
  }

  public long getPushAgencyUid() {
    return pushAgencyUid;
  }

  public void setPushAgencyUid(long pushAgencyUid) {
    this.pushAgencyUid = pushAgencyUid;
  }

  public Integer getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId) {
    this.agencyId = agencyId;
  }

  public Integer getAgencyUid() {
    return agencyUid;
  }

  public void setAgencyUid(Integer agencyUid) {
    this.agencyUid = agencyUid;
  }

  /**
   * @return the consignor
   */
  public String getConsignor() {
    return consignor;
  }

  /**
   * @param consignor the consignor to set
   */
  public void setConsignor(String consignor) {
    this.consignor = consignor;
  }

  /**
   * @return the consignorAddress
   */
  public String getConsignorAddress() {
    return consignorAddress;
  }

  /**
   * @param consignorAddress the consignorAddress to set
   */
  public void setConsignorAddress(String consignorAddress) {
    this.consignorAddress = consignorAddress;
  }

  /**
   * @return the shipPhone
   */
  public String getShipPhone() {
    return shipPhone;
  }

  /**
   * @param shipPhone the shipPhone to set
   */
  public void setShipPhone(String shipPhone) {
    this.shipPhone = shipPhone;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  public Integer getPayMethod() {
    return payMethod;
  }

  public void setPayMethod(Integer payMethod) {
    this.payMethod = payMethod;
  }

  public String getPayMethodName() {
    return payMethodName;
  }

  public void setPayMethodName(String payMethodName) {
    this.payMethodName = payMethodName;
  }

  /**
   * @return the pushName
   */
  public String getPushName() {
    return pushName;
  }

  /**
   * @param pushName the pushName to set
   */
  public void setPushName(String pushName) {
    this.pushName = pushName;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getUsedPrice() {
    return usedPrice;
  }

  public void setUsedPrice(Double usedPrice) {
    this.usedPrice = usedPrice;
  }

  public String getCouponNo() {
    return couponNo;
  }

  public void setCouponNo(String couponNo) {
    this.couponNo = couponNo;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public String getCreateDateStr() {
    if (getCreateDate() != null) {
      createDateStr = StringUtil.dateString(createDate);
    }
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public Integer getSettleStatus() {
    return settleStatus;
  }

  public void setSettleStatus(Integer settleStatus) {
    this.settleStatus = settleStatus;
  }

  public String getSettleStatusName() {
    if (settleStatus != null && settleStatus == 0) {
      settleStatusName = "待审核";
    } else if (settleStatus != null && settleStatus == 1) {
      settleStatusName = "待确认";
    } else if (settleStatus != null && settleStatus == 2) {
      settleStatusName = "已结算";
    }
    return settleStatusName;
  }

  public void setSettleStatusName(String settleStatusName) {
    this.settleStatusName = settleStatusName;
  }


  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public String getStockPrice() {
    return stockPrice;
  }

  public void setStockPrice(String stockPrice) {
    this.stockPrice = stockPrice;
  }

  public String getProvinces() {
    return provinces;
  }

  public void setProvinces(String provinces) {
    this.provinces = provinces;
  }

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

  public Integer getOrderType() {
    return orderType;
  }

  public void setOrderType(Integer orderType) {
    this.orderType = orderType;
  }

  public String getSellerMessage() {
    return sellerMessage;
  }

  public void setSellerMessage(String sellerMessage) {
    this.sellerMessage = sellerMessage;
  }

  public Integer getOption() {
    return option;
  }

  public void setOption(Integer option) {
    this.option = option;
  }

  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
    if (this.paymentDate != null) {
      paymentDateStr = StringUtil.dateString(this.paymentDate);
    }

  }

  public Date getConfirmDate() {
    return confirmDate;
  }

  public void setConfirmDate(Date confirmDate) {
    this.confirmDate = confirmDate;
    if (this.confirmDate != null) {
      confirmDateStr = StringUtil.dateString(this.confirmDate);
    }
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public String getPaymentDateStr() {
    return paymentDateStr;
  }

  public void setPaymentDateStr(String paymentDateStr) {
    this.paymentDateStr = paymentDateStr;
  }

  public String getConfirmDateStr() {
    return confirmDateStr;
  }

  public void setConfirmDateStr(String confirmDateStr) {
    this.confirmDateStr = confirmDateStr;
  }

  public String getSendDateStr() {
    return sendDateStr;
  }

  public void setSendDateStr(String sendDateStr) {
    this.sendDateStr = sendDateStr;
  }

  public String getIsCancelOrderName() {
    return isCancelOrderName;
  }

  public void setIsCancelOrderName(String isCancelOrderName) {
    this.isCancelOrderName = isCancelOrderName;
  }

  public String getRefundStatus1() {
    return refundStatus1;
  }

  public void setRefundStatus1(String refundStatus1) {
    this.refundStatus1 = refundStatus1;
  }

  public Date getAttendTime() {
    return attendTime;
  }

  public void setAttendTime(Date attendTime) {
    this.attendTime = attendTime;
    if (this.attendTime != null) {
      attendTimeStr = StringUtil.dateString(this.attendTime);
    }
  }

  public Date getHeadAttendTime() {
    return headAttendTime;
  }

  public void setHeadAttendTime(Date headAttendTime) {
    this.headAttendTime = headAttendTime;
    if (this.headAttendTime != null) {
      headAttendTimeStr = StringUtil.dateString(this.headAttendTime);
    }
  }

  public String getAttendTimeStr() {
    return attendTimeStr;
  }

  public void setAttendTimeStr(String attendTimeStr) {
    this.attendTimeStr = attendTimeStr;
  }

  public String getHeadAttendTimeStr() {
    return headAttendTimeStr;
  }

  public void setHeadAttendTimeStr(String headAttendTimeStr) {
    this.headAttendTimeStr = headAttendTimeStr;
  }

  public Integer getIsHead() {
    return isHead;
  }

  public void setIsHead(Integer isHead) {
    this.isHead = isHead;
  }

  public String getBeganSendDate() {
    return beganSendDate;
  }

  public void setBeganSendDate(String beganSendDate) {
    this.beganSendDate = beganSendDate;
  }

  public String getEndSendDate() {
    return endSendDate;
  }

  public void setEndSendDate(String endSendDate) {
    this.endSendDate = endSendDate;
  }

  public Integer getNotShip() {
    return notShip;
  }

  public void setNotShip(Integer notShip) {
    this.notShip = notShip;
  }

  public Integer getMaxNum() {
    return maxNum;
  }

  public void setMaxNum(Integer maxNum) {
    this.maxNum = maxNum;
  }

  public String getTradeNos() {
    return tradeNos;
  }

  public void setTradeNos(String tradeNos) {
    this.tradeNos = tradeNos;
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

  public String getBusinessCode() {
    return businessCode;
  }

  public void setBusinessCode(String businessCode) {
    this.businessCode = businessCode;
  }

  public String getGroupBeginDateStr() {
    return groupBeginDateStr;
  }

  public void setGroupBeginDateStr(String groupBeginDateStr) {
    this.groupBeginDateStr = groupBeginDateStr;
  }

  public String getGroupEndDateStr() {
    return groupEndDateStr;
  }

  public void setGroupEndDateStr(String groupEndDateStr) {
    this.groupEndDateStr = groupEndDateStr;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Integer getGurStatus() {
    return gurStatus;
  }

  public void setGurStatus(Integer gurStatus) {
    this.gurStatus = gurStatus;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public Date getRefundDate() {
    return refundDate;
  }

  public void setRefundDate(Date refundDate) {
    this.refundDate = refundDate;
    if (this.refundDate != null) {
      refundDateStr = StringUtil.dateString(this.refundDate);
    }
  }

  public String getRefundDateStr() {
    return refundDateStr;
  }

  public void setRefundDateStr(String refundDateStr) {
    this.refundDateStr = refundDateStr;
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

  public Integer getSaleSerNum() {
    return saleSerNum;
  }

  public void setSaleSerNum(Integer saleSerNum) {
    this.saleSerNum = saleSerNum;
  }

  public Long getOrderDid() {
    return orderDid;
  }

  public void setOrderDid(Long orderDid) {
    this.orderDid = orderDid;
  }

  public Integer getGroupingNum() {
    return groupingNum;
  }

  public void setGroupingNum(Integer groupingNum) {
    this.groupingNum = groupingNum;
  }

  public Integer getWaitSendNum() {
    return waitSendNum;
  }

  public void setWaitSendNum(Integer waitSendNum) {
    this.waitSendNum = waitSendNum;
  }

  public String getGroupDateStr() {
    return groupDateStr;
  }

  public void setGroupDateStr(String groupDateStr) {
    this.groupDateStr = groupDateStr;
  }

  public Date getGroupDate() {
    return groupDate;
  }

  public void setGroupDate(Date groupDate) {
    this.groupDate = groupDate;
    if (this.groupDate != null) {
      groupDateStr = StringUtil.dateString(this.groupDate);
    }
  }

  public Integer getGroupNum() {
    return groupNum;
  }

  public void setGroupNum(Integer groupNum) {
    this.groupNum = groupNum;
  }

  public Integer getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(Integer isSuccess) {
    this.isSuccess = isSuccess;
  }

  public Long getSourceId() {
    return sourceId;
  }

  public void setSourceId(Long sourceId) {
    this.sourceId = sourceId;
  }

  public Long getSkuLinkId() {
    return skuLinkId;
  }

  public void setSkuLinkId(Long skuLinkId) {
    this.skuLinkId = skuLinkId;
  }

  public Integer getOldOrderStatus() {
    return oldOrderStatus;
  }

  public void setOldOrderStatus(Integer oldOrderStatus) {
    this.oldOrderStatus = oldOrderStatus;
  }

  public String getCreateDateEndStr() {
    return createDateEndStr;
  }

  public void setCreateDateEndStr(String createDateEndStr) {
    this.createDateEndStr = createDateEndStr;
  }

  public void setCreateDateStartStr(String createDateStartStr) {
    this.createDateStartStr = createDateStartStr;
  }

  public List<Integer> getNotuserIds() {
    return notuserIds;
  }

  public void setNotuserIds(List<Integer> notuserIds) {
    this.notuserIds = notuserIds;
  }

  public Date getCreateDateStart() {
    return createDateStart;
  }

  public void setCreateDateStart(Date createDateStart) {
    this.createDateStart = createDateStart;
  }

  public Date getCreateDateEnd() {
    return createDateEnd;
  }

  public void setCreateDateEnd(Date createDateEnd) {
    this.createDateEnd = createDateEnd;
  }

  public String getCreateDateStartStr() {
    return createDateStartStr;
  }

  public String getBeganday1() {
    return beganday1;
  }

  public void setBeganday1(String beganday1) {
    this.beganday1 = beganday1;
  }

  public String getEndday1() {
    return endday1;
  }

  public void setEndday1(String endday1) {
    this.endday1 = endday1;
  }

  public Long getInviterId() {
    return inviterId;
  }

  public void setInviterId(Long inviterId) {
    this.inviterId = inviterId;
  }

  public Double getFactPriceAll() {
    return factPriceAll;
  }

  public void setFactPriceAll(Double factPriceAll) {
    this.factPriceAll = factPriceAll;
  }

  public Integer getIsHandle() {
    return isHandle;
  }

  public void setIsHandle(Integer isHandle) {
    this.isHandle = isHandle;
  }

  public String getCsRemarks() {
    return csRemarks;
  }

  public void setCsRemarks(String csRemarks) {
    this.csRemarks = csRemarks;
  }

}
