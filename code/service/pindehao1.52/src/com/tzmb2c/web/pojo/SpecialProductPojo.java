package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 专场产品表 -- special_product
 */
public class SpecialProductPojo extends SuperPojo {
  private Long id; // 专场产品表ID
  private Long specialId; // 专场表ID
  private String specialName; // 专场名称
  private Long productId; // 产品ID
  private String productName; // 产品名称
  private String productNum; // 产品货号
  private String productNo; // 产品编号
  private String title; // 产品标题
  private String image; // 产品图片
  private String introduction; // 产品推荐介绍
  private Integer sorting; // 产品排序
  private Integer status; // 产品状态
  private String statusName; // 产品状态名
  private Double activePrice; // 活动价
  private String createDateStr; // 创建时间STR
  private String updateDateStr; // 更新时间STR
  private Double sellPrice; // 平台产品原价/（建议零售价）
  private Double specialPrice; // 专场活动产品价格/（分销价）
  private Long specialStock; // 专场活动产品库存
  private Long specialNum; // 专场活动产品总量
  private Long goodId; // activity_goods编号id
  private String tips; // 折扣
  private Integer source; // 设置排序1销量降序、11销量升序、2价格降序、22价格升序、3点击率降序、33点击率升序
  private Long userBrandId; // SPECIAL_SHOW的USER_BRAND_ID
  private String brandName; // 品牌名称
  private Long visualGoods; // 视觉商品标记
  private String visualGoodsName;// 视觉商品名
  private String videoUrl;// 视频URL
  private String videoUrlImage;// 视频图片
  private Integer proStatus;// 商品状态
  private Integer specialStatus;// 专场状态
  private Long activityId;// 活动ID
  private Double distributionPrice;// 分销价
  private Double sellingPrice;// 建议零售价
  private Integer sellNumber;// 销售数量
  private Integer baseNumber;// 销售基数
  private String productTypeName;// 产品类型名称

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Long getGoodId() {
    return goodId;
  }

  public void setGoodId(Long goodId) {
    this.goodId = goodId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Double getActivePrice() {
    return activePrice;
  }

  public void setActivityPrice(Double activePrice) {
    this.activePrice = activePrice;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    super.updateDate = updateDate;
    if (updateDate != null) {
      this.updateDateStr = StringUtil.dateString(updateDate);
    }
  }

  public String getSpecialName() {
    return specialName;
  }

  public void setSpecialName(String specialName) {
    this.specialName = specialName;
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

  public Double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Double getSpecialPrice() {
    return specialPrice;
  }

  public void setSpecialPrice(Double specialPrice) {
    this.specialPrice = specialPrice;
  }

  public Long getSpecialStock() {
    return specialStock;
  }

  public void setSpecialStock(Long specialStock) {
    this.specialStock = specialStock;
  }

  public Long getSpecialNum() {
    return specialNum;
  }

  public void setSpecialNum(Long specialNum) {
    this.specialNum = specialNum;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Long getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Long specialId) {
    this.specialId = specialId;
  }

  public String getTips() {
    return tips;
  }

  public void setTips(String tips) {
    this.tips = tips;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public Long getUserBrandId() {
    return userBrandId;
  }

  public void setUserBrandId(Long userBrandId) {
    this.userBrandId = userBrandId;
  }

  public Long getVisualGoods() {
    return visualGoods;
  }

  public void setVisualGoods(Long visualGoods) {
    this.visualGoods = visualGoods;
  }

  public String getVisualGoodsName() {
    return visualGoodsName;
  }

  public void setVisualGoodsName(String visualGoodsName) {
    this.visualGoodsName = visualGoodsName;
  }

  public Integer getProStatus() {
    return proStatus;
  }

  public void setProStatus(Integer proStatus) {
    this.proStatus = proStatus;
  }

  public Integer getSpecialStatus() {
    return specialStatus;
  }

  public void setSpecialStatus(Integer specialStatus) {
    this.specialStatus = specialStatus;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  public String getVideoUrlImage() {
    return videoUrlImage;
  }

  public void setVideoUrlImage(String videoUrlImage) {
    this.videoUrlImage = videoUrlImage;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
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

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

}
