package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 用户品牌表 -- user_brand
 */
public class UserBrandPojo extends SuperPojo {
  private Long id;
  private Long userId; // 用户ID
  private Long brandId; // 品牌ID
  private String brandDisc; // 品牌描述
  private Integer grantType; // 授权类型，1-自由品牌，2-独家代理，3-一级代理，4-二级代理，5-三级代理
  private String grantTypeName; // 授权类型名
  private Date startDate; // 品牌有效期/授权有效期开始时间
  private Date endDate; // 品牌有效期/授权有效期结束时间
  private String startDateStr; // 品牌有效期/授权有效期开始时间STR
  private String endDateStr; // 品牌有效期/授权有效期结束时间STR
  private String mainCategory; // 主营品类
  private String manageLevel; // 经营等级，1-品牌，2-单品
  private String manageLevelName; // 经营等级名
  private String image1; // 证书图片1
  private String image2; // 证书图片2
  private String image3; // 证书图片3
  private Integer sorting; // 排序
  private Integer status; // 审核状态，0-未审核，1-已审核
  private String statusName; // 审核状态名
  private String brandName; // 品牌名称
  private String logo; // 品牌logo
  private Integer brandStatus;
  private String brandStatusName;
  private String mainCategoryName;// 主营品类名称
  private String userName; // 用户名称
  private String productCommt; // 商品评分
  private String deliverCommt; // 卖家服务评分
  private String logisticsCommt; // 物流评分

  public String getProductCommt() {
    return productCommt;
  }

  public void setProductCommt(String productCommt) {
    this.productCommt = productCommt;
  }

  public String getDeliverCommt() {
    return deliverCommt;
  }

  public void setDeliverCommt(String deliverCommt) {
    this.deliverCommt = deliverCommt;
  }

  public String getLogisticsCommt() {
    return logisticsCommt;
  }

  public void setLogisticsCommt(String logisticsCommt) {
    this.logisticsCommt = logisticsCommt;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public String getBrandDisc() {
    return brandDisc;
  }

  public void setBrandDisc(String brandDisc) {
    this.brandDisc = brandDisc;
  }

  public Integer getGrantType() {
    return grantType;
  }

  public void setGrantType(Integer grantType) {
    this.grantType = grantType;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getMainCategory() {
    return mainCategory;
  }

  public void setMainCategory(String mainCategory) {
    this.mainCategory = mainCategory;
  }

  public String getManageLevel() {
    return manageLevel;
  }

  public void setManageLevel(String manageLevel) {
    this.manageLevel = manageLevel;
  }

  public String getImage1() {
    return image1;
  }

  public void setImage1(String image1) {
    this.image1 = image1;
  }

  public String getImage2() {
    return image2;
  }

  public void setImage2(String image2) {
    this.image2 = image2;
  }

  public String getImage3() {
    return image3;
  }

  public void setImage3(String image3) {
    this.image3 = image3;
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

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getEndDateStr() {
    if (endDate != null) {
      this.endDateStr = StringUtil.dateToString(endDate);
    }
    return endDateStr;
  }

  public void setEndDateStr(String endDateStr) {
    this.endDateStr = endDateStr;
  }

  public String getStartDateStr() {
    if (startDate != null) {
      this.startDateStr = StringUtil.dateToString(startDate);
    }
    return startDateStr;
  }

  public void setStartDateStr(String startDateStr) {
    this.startDateStr = startDateStr;
  }

  public String getGrantTypeName() {
    return grantTypeName;
  }

  public void setGrantTypeName(String grantTypeName) {
    this.grantTypeName = grantTypeName;
  }

  public String getManageLevelName() {
    return manageLevelName;
  }

  public void setManageLevelName(String manageLevelName) {
    this.manageLevelName = manageLevelName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public Integer getBrandStatus() {
    return brandStatus;
  }

  public void setBrandStatus(Integer brandStatus) {
    this.brandStatus = brandStatus;
  }

  public String getBrandStatusName() {
    return brandStatusName;
  }

  public void setBrandStatusName(String brandStatusName) {
    this.brandStatusName = brandStatusName;
  }

  public String getMainCategoryName() {
    return mainCategoryName;
  }

  public void setMainCategoryName(String mainCategoryName) {
    this.mainCategoryName = mainCategoryName;
  }
}
