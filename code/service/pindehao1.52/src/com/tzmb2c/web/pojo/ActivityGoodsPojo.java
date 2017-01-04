package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 活动商品表 -- Activity_Goods
 * 
 */

public class ActivityGoodsPojo extends SuperPojo {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private Long id;// 编号id
  private Long productId;// 产品id
  private Long timeId;// 活动时间对应id
  private Double sellPrice;// 原价
  private Double activePrice;// 活动价格
  private String tips;// 活动标示
  private String productImage;// 产品图片
  private String productName;// 产品名称
  private String productNo;// 产品序号
  private String productNum;// 产品货号
  private Integer sorting;// 排序
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private Integer proStatus;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态字符
  private Date createDate;
  private Date updateDate;
  private String createDateStr;
  private String updateDateStr;
  private String beginTime;
  private String endTime;
  private String activityTime;// 开始时间到结束时间
  private Long activityStock;// 活动商品库存
  private Long activityNum;// 活动商品总量
  private Long userId;// 产品用户id
  private Date activityDate;// 活动日期
  private String activityDateStr;// 活动日期
  // private Integer skuLinkId;//product_sku_link.id
  // private String skuLinkStr;
  private Integer activityType;// 活动类型
  private String activityTitle; // 活动标题
  private Integer type;// 活动类型，0-秒杀，1-专题（钱包专区），2-场景，3-专场
  private String brandName;// 品牌
  private Integer minusStock;// 减少库存的数量

  public Integer getMinusStock() {
    return minusStock;
  }

  public void setMinusStock(Integer minusStock) {
    this.minusStock = minusStock;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public Integer getProStatus() {
    return proStatus;
  }

  public void setProStatus(Integer proStatus) {
    this.proStatus = proStatus;
  }

  public String getActivityTitle() {
    return activityTitle;
  }

  public void setActivityTitle(String activityTitle) {
    this.activityTitle = activityTitle;
  }

  public Integer getActivityType() {
    return activityType;
  }

  public void setActivityType(Integer activityType) {
    this.activityType = activityType;
  }

  public Date getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(Date activityDate) {
    this.activityDate = activityDate;
    if (activityDate != null) {
      activityDateStr = StringUtil.dateToString(this.activityDate);
    }
  }

  public String getActivityDateStr() {
    return activityDateStr;
  }

  public void setActivityDateStr(String activityDateStr) {
    this.activityDateStr = activityDateStr;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    updateDateStr = StringUtil.dateToString(this.updateDate);
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
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

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  public Double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Double getActivePrice() {
    return activePrice;
  }

  public void setActivePrice(Double activePrice) {
    this.activePrice = activePrice;
  }

  public String getTips() {
    return tips;
  }

  public void setTips(String tips) {
    this.tips = tips;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
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

  public String getActivityTime() {
    return activityTime;
  }

  public void setActivityTime(String activityTime) {
    this.activityTime = activityTime;
  }

  public Long getActivityStock() {
    return activityStock;
  }

  public void setActivityStock(Long activityStock) {
    this.activityStock = activityStock;
  }

  public Long getActivityNum() {
    return activityNum;
  }

  public void setActivityNum(Long activityNum) {
    this.activityNum = activityNum;
  }

  /*
   * public Integer getSkuLinkId() { return skuLinkId; } public void setSkuLinkId(Integer skuLinkId)
   * { this.skuLinkId = skuLinkId; } public String getSkuLinkStr() { return skuLinkStr; } public
   * void setSkuLinkStr(String skuLinkStr) { this.skuLinkStr = skuLinkStr; }
   */
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
