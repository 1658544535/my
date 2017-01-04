package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * @author EricChen
 */
public class ActivityProductPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id;// id编号
  private String type;// 活动类型(取业务字典：1每日10件2限时秒杀3大牌驾到4首页列表活动5web活动页)
  private String activityType;// 活动类型，0-秒杀，1-专题，2-场景，3-专场
  private String productType;// 产品展位类型:1单品2专场
  private Long activityId;// 活动时间表Id
  private Double activePrice;// 活动价格
  private Double sellPrice;// 原价
  private String activityTitle;// 活动表（activity_time）标题/名称
  private Long productId;// 产品id
  private Integer sorting;// 排序
  private Integer status;// 推送状态
  private String statusName;// 审核状态名称
  private String remark;// 备注
  private String remarks;// 备注(关联活动标题表id)
  private String recommendation;// 推荐语
  private Date createDate;// 创建时间
  private String creatDateString;
  private Date updateDate;// 创建时间
  private String updateDateString;
  private Date beginTime;// 活动开始时间
  private String beginTimeString;
  private Date endTime;// 活动结束时间
  private String endTimeString;
  private String productNo;// 产品序号
  private String productNum;// 产品货号
  private Long userId;// 用户编号
  private String userName;// 用户名称
  private String image;// 产品主图名称
  private String productTypeId;// 产品类型ID
  private String productTypeName;// 产品类型
  private String productName;// 产品名称
  private Double distributionPrice;// 产品价格
  private Double sellingPrice;// 产品原价
  private Integer proStatus;// 产品状态
  private String proStatusName;// 产品状态限时
  private Integer sellNumber;// 销售数量
  private Integer baseNumber;// 销售数量基数
  private Long titleId;// 首页列表活动标题编号
  private String title;// 标题
  private String name;// 标题
  private String timeIdIsN;// 判断time_id是否为空
  private Integer limitNo;// 查询条数
  private Long specialId;// 专场id
  private String productImg;
  // 导航分类ID
  private String categoryId;
  // 导航分类名称
  private String categoryName;

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getProductImg() {
    return productImg;
  }

  public void setProductImg(String productImg) {
    this.productImg = productImg;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getSellNumber() {
    return sellNumber;
  }

  public void setSellNumber(Integer sellNumber) {
    this.sellNumber = sellNumber;
  }

  public Integer getBaseNumber() {
    return baseNumber;
  }

  public void setBaseNumber(Integer baseNumber) {
    this.baseNumber = baseNumber;
  }

  public Integer getProStatus() {
    return proStatus;
  }

  public void setProStatus(Integer proStatus) {
    this.proStatus = proStatus;
  }

  public String getProStatusName() {
    return proStatusName;
  }

  public void setProStatusName(String proStatusName) {
    this.proStatusName = proStatusName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getRecommendation() {
    return recommendation;
  }

  public void setRecommendation(String recommendation) {
    this.recommendation = recommendation;
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
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    this.updateDateString = StringUtil.dateToString(this.updateDate);
  }

  public String getUpdateDateString() {
    return updateDateString;
  }

  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.creatDateString = StringUtil.dateToString(this.createDate);
  }

  public String getCreatDateString() {
    return creatDateString;
  }

  public void setCreatDateString(String creatDateString) {
    this.creatDateString = creatDateString;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getProductTypeId() {
    return productTypeId;
  }

  public void setProductTypeId(String productTypeId) {
    this.productTypeId = productTypeId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public Long getTitleId() {
    return titleId;
  }

  public void setTitleId(Long titleId) {
    this.titleId = titleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getActivityType() {
    return activityType;
  }

  public void setActivityType(String activityType) {
    this.activityType = activityType;
  }

  public Double getActivePrice() {
    return activePrice;
  }

  public void setActivePrice(Double activePrice) {
    this.activePrice = activePrice;
  }

  public String getTimeIdIsN() {
    return timeIdIsN;
  }

  public void setTimeIdIsN(String timeIdIsN) {
    this.timeIdIsN = timeIdIsN;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getActivityTitle() {
    return activityTitle;
  }

  public void setActivityTitle(String activityTitle) {
    this.activityTitle = activityTitle;
  }

  public Double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
    this.beginTimeString = StringUtil.dateToString(this.beginTime);
  }

  public String getBeginTimeString() {
    return beginTimeString;
  }

  public void setBeginTimeString(String beginTimeString) {
    this.beginTimeString = beginTimeString;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
    this.endTimeString = StringUtil.dateToString(this.endTime);
  }

  public String getEndTimeString() {
    return endTimeString;
  }

  public void setEndTimeString(String endTimeString) {
    this.endTimeString = endTimeString;
  }

  public Integer getLimitNo() {
    return limitNo;
  }

  public void setLimitNo(Integer limitNo) {
    this.limitNo = limitNo;
  }

  public Long getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Long specialId) {
    this.specialId = specialId;
  }
}
