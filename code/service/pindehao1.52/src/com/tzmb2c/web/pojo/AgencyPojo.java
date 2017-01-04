package com.tzmb2c.web.pojo;


import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 代理商信息-- user_agency by Lie
 * 
 */

public class AgencyPojo extends SuperPojo {
  private int pushStatus;// 推送状态
  private String pushStatusName;// 推送状态
  private String baiduUid;// 设备id
  private Long agencyId;
  private Long userId;
  private String contact;
  private String tel;
  private String phone;
  private String fax;
  private String addressHome;
  private String addressCompany;
  private String address;
  private String corporation;// 法人代表
  private Long proxyMarket;// 代理市场
  private Long province;// 省
  private Long city;// 市
  private Long area;// 区
  private String lat;// 横坐标
  private String lng;// 纵坐标
  private Long manufacturerId;// 代理厂家ID
  private String company;
  private String busLicence;
  private String email;
  private String mainCategory;
  private String mainProduct;
  private int creditLevel;
  private String QQ;
  private String shopName;
  private Long shopId;


  private int channel;
  private int isread;
  private int status;// 审核状态（取业务字典：0未审核1已审核）
  private Long createBy;
  private Date createDate;
  private Long updateBy;
  private Date updateDate;
  private String remarks;
  private Integer version;

  private String loginname;
  private String name;
  private String statusName, typeName;
  private String agencydate;



  private String createDateString;
  private String updateDateString;

  public String getPushStatusName() {
    return pushStatusName;
  }

  public void setPushStatusName(String pushStatusName) {
    this.pushStatusName = pushStatusName;
  }

  public int getPushStatus() {
    return pushStatus;
  }

  public void setPushStatus(int pushStatus) {
    this.pushStatus = pushStatus;
  }

  public String getBaiduUid() {
    return baiduUid;
  }

  public void setBaiduUid(String baiduUid) {
    this.baiduUid = baiduUid;
  }

  public Long getAgencyId() {

    return agencyId;
  }

  public void setAgencyId(Long agencyId) {
    this.agencyId = agencyId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getAddressHome() {
    return addressHome;
  }

  public void setAddressHome(String addressHome) {
    this.addressHome = addressHome;
  }

  public String getAddressCompany() {
    return addressCompany;
  }

  public void setAddressCompany(String addressCompany) {
    this.addressCompany = addressCompany;
  }

  public String getCorporation() {
    return corporation;
  }

  public void setCorporation(String corporation) {
    this.corporation = corporation;
  }

  public Long getProxyMarket() {
    return proxyMarket;
  }

  public void setProxyMarket(Long proxyMarket) {
    this.proxyMarket = proxyMarket;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMainCategory() {
    return mainCategory;
  }

  public void setMainCategory(String mainCategory) {
    this.mainCategory = mainCategory;
  }

  public String getMainProduct() {
    return mainProduct;
  }

  public void setMainProduct(String mainProduct) {
    this.mainProduct = mainProduct;
  }

  public int getCreditLevel() {
    return creditLevel;
  }

  public void setCreditLevel(int creditLevel) {
    this.creditLevel = creditLevel;
  }

  public String getQQ() {
    return QQ;
  }

  public void setQQ(String QQ) {
    this.QQ = QQ;
  }

  public int getChannel() {
    return channel;
  }

  public void setChannel(int channel) {
    this.channel = channel;
  }

  public int getIsread() {
    return isread;
  }

  public void setIsread(int isread) {
    this.isread = isread;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
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
    this.createDateString = StringUtil.dateToString(this.createDate);
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
    this.updateDateString = StringUtil.dateToString(this.updateDate);
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

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getAgencydate() {
    return agencydate;
  }

  public void setAgencydate(String agencydate) {
    this.agencydate = agencydate;
  }

  public String getCreateDateString() {
    return createDateString;
  }

  public void setCreateDateString(String createDateString) {
    this.createDateString = createDateString;
  }

  public String getUpdateDateString() {
    return updateDateString;
  }

  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }

  /**
   * @return the province
   */
  public Long getProvince() {
    return province;
  }

  /**
   * @param province the province to set
   */
  public void setProvince(Long province) {
    this.province = province;
  }

  /**
   * @return the city
   */
  public Long getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(Long city) {
    this.city = city;
  }

  /**
   * @return the lat
   */
  public String getLat() {
    return lat;
  }

  /**
   * @param lat the lat to set
   */
  public void setLat(String lat) {
    this.lat = lat;
  }

  /**
   * @return the lng
   */
  public String getLng() {
    return lng;
  }

  /**
   * @param lng the lng to set
   */
  public void setLng(String lng) {
    this.lng = lng;
  }

  /**
   * @return the manufacturer_id
   */
  public Long getManufacturerId() {
    return manufacturerId;
  }

  /**
   * @param manufacturer_id the manufacturer_id to set
   */
  public void setManufacturerId(Long manufacturerId) {
    this.manufacturerId = manufacturerId;
  }

  public Long getArea() {
    return area;
  }

  public void setArea(Long area) {
    this.area = area;
  }

  public String getBusLicence() {
    return busLicence;
  }

  public void setBusLicence(String busLicence) {
    this.busLicence = busLicence;
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
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

}
