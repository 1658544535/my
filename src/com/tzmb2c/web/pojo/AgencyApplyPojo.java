package com.tzmb2c.web.pojo;



import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;


public class AgencyApplyPojo extends SuperPojo {
  private Long id;// 编号
  private String contact;// 联系人
  private String tel;// 联系电话
  private String phone;// 手机
  private String fax;// 传真
  private String QQ;// QQ
  private Integer provinceId;// 省
  private Integer cityId;// 市
  private Integer areaId;// 区
  private String province;// 省
  private String city;// 市
  private String area;// 区
  private String address;// 地址
  private String mainProduct;// 主营产品
  private Integer status;// 状态
  private String statusName;// 状态
  private String createDateStr;
  private String updateDateStr;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getQQ() {
    return QQ;
  }

  public void setQQ(String qQ) {
    QQ = qQ;
  }

  public Integer getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(Integer provinceId) {
    this.provinceId = provinceId;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public Integer getAreaId() {
    return areaId;
  }

  public void setAreaId(Integer areaId) {
    this.areaId = areaId;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMainProduct() {
    return mainProduct;
  }

  public void setMainProduct(String mainProduct) {
    this.mainProduct = mainProduct;
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

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    if (this.updateDate != null) {
      updateDateStr = StringUtil.dateToString(this.updateDate);
    }
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (this.createDate != null) {
      createDateStr = StringUtil.dateToString(this.createDate);
    }
  }
}
