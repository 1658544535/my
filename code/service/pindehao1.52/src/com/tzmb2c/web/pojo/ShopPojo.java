package com.tzmb2c.web.pojo;

import java.util.List;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 店铺信息-- user_shop by Lie
 * 
 */

public class ShopPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private String name;// 店铺名称
  private String images;// 店铺图片路径
  private String address;// 店铺地址
  private String phone;// 联系号码
  private String mainProduct;// 公司主营产品
  private String mainCategory;// 公司主营品类
  private String mainCategoryName;
  private String salesArea;// 主要销售市场
  private String content;// 店铺介绍
  private Double lat;// 纬度
  private Double lng;// 经度
  private String isNew;// 是否新品推荐
  private Integer sorting;// 排序
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName, userName, isNewName;
  private List<ProductPojo> productPojos;
  private Integer province;// 省份
  private Integer city;// 市区
  private Integer area;// 地区
  private Long recommendType;// 首页推介类型
  private String locationCertification;// 实地认证(取业务字典：0否1是)
  private String identityCertification;// 身份认证(取业务字典：0否1是)
  private String provinceName, cityName, areaName;
  private String topImage;// 店铺顶部图片
  private String temporarily;// 暂时存数据
  private String searchKey;// 搜索关键字
  private Long shopId;// 编号
  private String shopName;// 店铺名称
  private String productCommt;
  private String deliverCommt;
  private String logisticsCommt;
  private String selfSupportName;
  /**
   * selfSupport:是否自营 0-否1-是
   */
  private Integer selfSupport;

  public Integer getSelfSupport() {
    return selfSupport;
  }

  public void setSelfSupport(Integer selfSupport) {
    this.selfSupport = selfSupport;
  }

  public String getTopImage() {
    return topImage;
  }

  public void setTopImage(String topImage) {
    this.topImage = topImage;
  }

  public String getTemporarily() {
    return temporarily;
  }

  public void setTemporarily(String temporarily) {
    this.temporarily = temporarily;
  }

  public String getMainCategoryName() {
    return mainCategoryName;
  }

  public void setMainCategoryName(String mainCategoryName) {
    this.mainCategoryName = mainCategoryName;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public Integer getProvince() {
    return province;
  }

  public void setProvince(Integer province) {
    this.province = province;
  }

  public Integer getCity() {
    return city;
  }

  public void setCity(Integer city) {
    this.city = city;
  }

  public Integer getArea() {
    return area;
  }

  public void setArea(Integer area) {
    this.area = area;
  }

  public Long getRecommendType() {
    return recommendType;
  }

  public void setRecommendType(Long recommendType) {
    this.recommendType = recommendType;
  }

  public String getLocationCertification() {
    return locationCertification;
  }

  public void setLocationCertification(String locationCertification) {
    this.locationCertification = locationCertification;
  }

  public String getIdentityCertification() {
    return identityCertification;
  }

  public void setIdentityCertification(String identityCertification) {
    this.identityCertification = identityCertification;
  }

  public List<ProductPojo> getProductPojos() {
    return productPojos;
  }

  public void setProductPojos(List<ProductPojo> productPojos) {
    this.productPojos = productPojos;
  }

  public String getIsNewName() {
    return isNewName;
  }

  public void setIsNewName(String isNewName) {
    this.isNewName = isNewName;
  }

  public String getIsNew() {
    return isNew;
  }

  public void setIsNew(String isNew) {
    this.isNew = isNew;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getMainProduct() {
    return mainProduct;
  }

  public void setMainProduct(String mainProduct) {
    this.mainProduct = mainProduct;
  }

  public String getMainCategory() {
    return mainCategory;
  }

  public void setMainCategory(String mainCategory) {
    this.mainCategory = mainCategory;
  }

  public String getSalesArea() {
    return salesArea;
  }

  public void setSalesArea(String salesArea) {
    this.salesArea = salesArea;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getSearchKey() {
    return searchKey;
  }

  public void setSearchKey(String searchKey) {
    this.searchKey = searchKey;
  }

  /**
   * @return the shopId
   */
  public Long getShopId() {
    return shopId;
  }

  /**
   * @param shopId the shopId to set
   */
  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  /**
   * @return the shopName
   */
  public String getShopName() {
    return shopName;
  }

  /**
   * @param shopName the shopName to set
   */
  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  /**
   * @return the productCommt
   */
  public String getProductCommt() {
    return productCommt;
  }

  /**
   * @param productCommt the productCommt to set
   */
  public void setProductCommt(String productCommt) {
    this.productCommt = productCommt;
  }

  /**
   * @return the deliverCommt
   */
  public String getDeliverCommt() {
    return deliverCommt;
  }

  /**
   * @param deliverCommt the deliverCommt to set
   */
  public void setDeliverCommt(String deliverCommt) {
    this.deliverCommt = deliverCommt;
  }

  /**
   * @return the logisticsCommt
   */
  public String getLogisticsCommt() {
    return logisticsCommt;
  }

  /**
   * @param logisticsCommt the logisticsCommt to set
   */
  public void setLogisticsCommt(String logisticsCommt) {
    this.logisticsCommt = logisticsCommt;
  }

  public String getSelfSupportName() {
    return selfSupportName;
  }

  public void setSelfSupportName(String selfSupportName) {
    this.selfSupportName = selfSupportName;
  }
}
