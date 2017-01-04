package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 供应商信息-- user_manufacturer by Lie
 * 
 */

public class ManufacturerPojo extends SuperPojo {
  private Long id;// 编号
  private Long userId;// 用户编号
  private String company;// 公司名称
  private String address;// 地址
  private Integer scale;// 公司规模：1、小于50人2、50~100人3、100~150人4、150~200人5、200~300人5、大于300人
  private String webSite;// 公司网址
  private String logo;// 公司LOGO/企业形象
  private String content;// 公司简介
  private String contact;// 商务联系人
  private String duty;// 职务
  private String phone;// 联系号码
  private String fax;// 传真号码
  private String QQ;// QQ
  private String email;// 邮箱地址
  private String brand;// 自营品牌
  private String mainProduct;// 公司主营产品
  private String mainCategory;// 公司主营品类
  private String mainCategoryName;// 公司主营品类名称
  private String salesArea;// 主要销售市场
  private Integer channel;// 来源渠道(取业务字典：0.PC端1.APP 2.微信)
  private Integer isread;// 是否查看(取业务字典：0，否1，是)
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private Long createBy;// 创建者
  private Date createDate;// 创建时间
  private Long updateBy;// 更新者
  private Date updateDate;// 更新时间
  private String remarks; // 备注信息
  private Integer version;// 版本号
  private String scaleName, statusName, userName;
  private String createDateStr;// 创建时间
  private String loginname;
  private String name;// 店铺名称
  private Double amount;// 商户金额
  private Double balance;// 商户余额
  private Double addAmount;// 增加金额
  private String companyPhone;// 公司电话
  private String legalPerson;// 公司法人
  private String scopeBusiness;// 经营范围
  private String createByTime;// 创建时间时间部分
  private String createByDate;// 创建时间日期部分
  private String returnContent;// 退回原因
  private Double scoreAll;// 总的分数（商家剩余分数）
  private Double addBalance;// 增加余额
  private Long type;// 商家类型:0商家中心1创客
  private Integer selfSupport;
  private String selfSupportName;
  private Long shopId;

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public Integer getSelfSupport() {
    return selfSupport;
  }

  public void setSelfSupport(Integer selfSupport) {
    this.selfSupport = selfSupport;
  }

  public Double getAddAmount() {
    return addAmount;
  }

  public void setAddAmount(Double addAmount) {
    this.addAmount = addAmount;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getMainCategoryName() {
    return mainCategoryName;
  }

  public void setMainCategoryName(String mainCategoryName) {
    this.mainCategoryName = mainCategoryName;
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

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getScale() {
    return scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getDuty() {
    return duty;
  }

  public void setDuty(String duty) {
    this.duty = duty;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
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

  public String getScaleName() {
    return scaleName;
  }

  public void setScaleName(String scaleName) {
    this.scaleName = scaleName;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCompanyPhone() {
    return companyPhone;
  }

  public void setCompanyPhone(String companyPhone) {
    this.companyPhone = companyPhone;
  }

  public String getLegalPerson() {
    return legalPerson;
  }

  public void setLegalPerson(String legalPerson) {
    this.legalPerson = legalPerson;
  }

  public String getScopeBusiness() {
    return scopeBusiness;
  }

  public void setScopeBusiness(String scopeBusiness) {
    this.scopeBusiness = scopeBusiness;
  }

  public String getCreateByTime() {
    return createByTime;
  }

  public void setCreateByTime(String createByTime) {
    this.createByTime = createByTime;
  }

  public String getCreateByDate() {
    return createByDate;
  }

  public void setCreateByDate(String createByDate) {
    this.createByDate = createByDate;
  }

  public String getReturnContent() {
    return returnContent;
  }

  public void setReturnContent(String returnContent) {
    this.returnContent = returnContent;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Double getScoreAll() {
    return scoreAll;
  }

  public void setScoreAll(Double scoreAll) {
    this.scoreAll = scoreAll;
  }

  public Double getAddBalance() {
    return addBalance;
  }

  public void setAddBalance(Double addBalance) {
    this.addBalance = addBalance;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public String getSelfSupportName() {
    return selfSupportName;
  }

  public void setSelfSupportName(String selfSupportName) {
    this.selfSupportName = selfSupportName;
  }
}
