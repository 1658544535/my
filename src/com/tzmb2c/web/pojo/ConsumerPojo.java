package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 分销商信息-- user_consumer by Lie
 * 
 */

public class ConsumerPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private String contact;// 商务联系人
  private String tel;// 联系号码
  private String phone;// 手机号码
  private String fax;// 传真号码
  private String address;// 地址
  private String webSite;// 公司网址
  private String QQ;// QQ
  private String email;// 邮箱地址
  private String company;// 公司名称
  private String duty;// 职务
  private Integer groups;// 公司规模：1、小于50人2、50~100人3、100~150人4、150~200人5、200~300人5、大于300人
  private String platform;// 销售平台
  private String mainCategory, mainCategoryName;// 公司主营品类
  private String salesArea;// 销售市场
  private Integer channel;// 来源渠道(取业务字典：0.PC端1.APP 2.微信)
  private Integer isread;// 是否查看(取业务字典：0，否1，是)
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private Long createBy;// 创建者
  private Date createDate;// 创建时间
  private Long updateBy;// 更新者
  private Date updateDate;// 更新时间
  private String remarks; // 备注信息
  private Integer version;// 版本号
  private String groupsName, statusName, userName;
  private String corporateImage;// 公司形象
  private String createDateStr;
  private String loginname;



  public String getMainCategoryName() {
    return mainCategoryName;
  }

  public void setMainCategoryName(String mainCategoryName) {
    this.mainCategoryName = mainCategoryName;
  }

  public String getCorporateImage() {
    return corporateImage;
  }

  public void setCorporateImage(String corporateImage) {
    this.corporateImage = corporateImage;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getQQ() {
    return QQ;
  }

  public void setQQ(String qQ) {
    QQ = qQ;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getDuty() {
    return duty;
  }

  public void setDuty(String duty) {
    this.duty = duty;
  }

  public Integer getGroups() {
    return groups;
  }

  public void setGroups(Integer groups) {
    this.groups = groups;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
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

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public Integer getIsread() {
    return isread;
  }

  public void setIsread(Integer isread) {
    this.isread = isread;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
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

  public String getGroupsName() {
    return groupsName;
  }

  public void setGroupsName(String groupsName) {
    this.groupsName = groupsName;
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

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

}
