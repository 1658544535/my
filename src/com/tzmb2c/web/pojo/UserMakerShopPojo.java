package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * brand_dic表
 */
public class UserMakerShopPojo extends SuperPojo {
  private Long id; // id
  private Long userId;// 用户ID
  private String userName;// 用户昵称
  private Long shopId;// 店铺ID
  private String shopName;// 店铺名称
  private String address;// 地址
  private String phone;// 联系电话
  private Long ageType;// 年龄分层
  private String ageTypeName;// 年龄分层名称
  private Long ability;// 能力分层
  private String abilityName;// 能力分层名称
  private Long status;// 审核状态（0-未审核，1-审核成功，2-审核失败）
  private String statusName;// 审核状态名称
  private String contact;// 联系人
  private String servicePhone;// 客服电话
  private String alipayAccount;// 支付宝账号
  private String alipayName;// 支付宝实名
  private Long shopType;// 店铺类型(0-企业，1-平台）
  private String shopTypeNames;// 店铺类型名称
  private String imageOriginal;// 营业执照正本/店主手持身份证照片
  private String imageCopy;// 营业执照副本
  private String shopTypeName;// 企业全称/店铺平台(shop_type为0则前者，为1则后者）
  private String shopTypeUrl;// 店铺地址URL
  private Long contentOutput;// 单平台内容产出量（0-1/7天，1-1/5天，2-1/3天，3-1/1天，4-2/1天，5-3/1天以上）
  private String contentOutputName;// 单平台内容产出量名称
  private Long contentOutputOriginal;// 单平台原创内容产出量（0-1/7天，1-1/5天，2-1/3天，3-1/1天，4-2/1天，5-3/1天以上）
  private String contentOutputOriginalName;// 单平台原创内容产出量名称
  private String createDateStr;// 创建日期STR
  private String shopLOGO;// 店铺LOGO，等同user_shop的top_image
  private String shopMainImage;// 店铺主图，等同user_shop的images
  private Long province;// 省
  private Long city;// 市
  private Long area;// 区域
  private String detailedAddress;// 详细地址
  private String provinceName;// 省名
  private String cityName;// 市名
  private String areaName;// 区域名
  // 用户logo
  private String userImage;
  private String shopTypeName1;// shopType=0的 shopTypeName（创客中心使用）
  private String shopTypeName2;// shopType=1的 shopTypeName（创客中心使用）
  
  private Long isGenCabinet;//是否是设置为创阁(0：非创阁；1：创阁)
  private String isGenCabinetStr; //创阁状态

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public Long getAgeType() {
    return ageType;
  }

  public void setAgeType(Long ageType) {
    this.ageType = ageType;
  }

  public String getAgeTypeName() {
    return ageTypeName;
  }

  public void setAgeTypeName(String ageTypeName) {
    this.ageTypeName = ageTypeName;
  }

  public Long getAbility() {
    return ability;
  }

  public void setAbility(Long ability) {
    this.ability = ability;
  }

  public String getAbilityName() {
    return abilityName;
  }

  public void setAbilityName(String abilityName) {
    this.abilityName = abilityName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getAlipayAccount() {
    return alipayAccount;
  }

  public void setAlipayAccount(String alipayAccount) {
    this.alipayAccount = alipayAccount;
  }

  public String getAlipayName() {
    return alipayName;
  }

  public void setAlipayName(String alipayName) {
    this.alipayName = alipayName;
  }

  public Long getShopType() {
    return shopType;
  }

  public void setShopType(Long shopType) {
    this.shopType = shopType;
  }

  public String getShopTypeNames() {
    return shopTypeNames;
  }

  public void setShopTypeNames(String shopTypeNames) {
    this.shopTypeNames = shopTypeNames;
  }

  public String getImageOriginal() {
    return imageOriginal;
  }

  public void setImageOriginal(String imageOriginal) {
    this.imageOriginal = imageOriginal;
  }

  public String getImageCopy() {
    return imageCopy;
  }

  public void setImageCopy(String imageCopy) {
    this.imageCopy = imageCopy;
  }

  public String getShopTypeName() {
    return shopTypeName;
  }

  public void setShopTypeName(String shopTypeName) {
    this.shopTypeName = shopTypeName;
  }

  public String getShopTypeUrl() {
    return shopTypeUrl;
  }

  public void setShopTypeUrl(String shopTypeUrl) {
    this.shopTypeUrl = shopTypeUrl;
  }

  public Long getContentOutput() {
    return contentOutput;
  }

  public void setContentOutput(Long contentOutput) {
    this.contentOutput = contentOutput;
  }

  public String getContentOutputName() {
    return contentOutputName;
  }

  public void setContentOutputName(String contentOutputName) {
    this.contentOutputName = contentOutputName;
  }

  public Long getContentOutputOriginal() {
    return contentOutputOriginal;
  }

  public void setContentOutputOriginal(Long contentOutputOriginal) {
    this.contentOutputOriginal = contentOutputOriginal;
  }

  public String getContentOutputOriginalName() {
    return contentOutputOriginalName;
  }

  public void setContentOutputOriginalName(String contentOutputOriginalName) {
    this.contentOutputOriginalName = contentOutputOriginalName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
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

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  public String getServicePhone() {
    return servicePhone;
  }

  public void setServicePhone(String servicePhone) {
    this.servicePhone = servicePhone;
  }

  public String getShopLOGO() {
    return shopLOGO;
  }

  public void setShopLOGO(String shopLOGO) {
    this.shopLOGO = shopLOGO;
  }

  public String getShopMainImage() {
    return shopMainImage;
  }

  public void setShopMainImage(String shopMainImage) {
    this.shopMainImage = shopMainImage;
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

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public Long getProvince() {
    return province;
  }

  public void setProvince(Long province) {
    this.province = province;
  }

  public Long getCity() {
    return city;
  }

  public void setCity(Long city) {
    this.city = city;
  }

  public Long getArea() {
    return area;
  }

  public void setArea(Long area) {
    this.area = area;
  }

  public String getDetailedAddress() {
    return detailedAddress;
  }

  public void setDetailedAddress(String detailedAddress) {
    this.detailedAddress = detailedAddress;
  }

  public String getShopTypeName1() {
    return shopTypeName1;
  }

  public void setShopTypeName1(String shopTypeName1) {
    this.shopTypeName1 = shopTypeName1;
  }

  public String getShopTypeName2() {
    return shopTypeName2;
  }

  public void setShopTypeName2(String shopTypeName2) {
    this.shopTypeName2 = shopTypeName2;
  }

  public Long getIsGenCabinet() {
    return isGenCabinet;
  }

  public void setIsGenCabinet(Long isGenCabinet) {
    this.isGenCabinet = isGenCabinet;
  }

  public String getIsGenCabinetStr() {
    return isGenCabinetStr;
  }

  public void setIsGenCabinetStr(String isGenCabinetStr) {
    this.isGenCabinetStr = isGenCabinetStr;
  }
  
  
}
