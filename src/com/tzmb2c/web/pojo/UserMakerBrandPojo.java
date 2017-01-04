package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 创客品牌表
 */
public class UserMakerBrandPojo extends SuperPojo {

  // ID
  private Long id;
  // 用户ID
  private Long userId;
  // Logo（大小号）
  private String logo;
  // 品牌名称
  private String brandName;
  // 图文介绍
  private String content;
  // 年龄标签字典ID
  private Long ageType;
  // 能力标签字典ID
  private Long skillType;
  // 标签3/备选ID
  private Long optionType;
  // 标签4/商品
  private Long productType;

  // 年龄标签字典Str
  private String ageTypeStr;
  // 能力标签字典Str
  private String skillTypeStr;
  // 标签3/备选Str
  private String optionTypeStr;
  // 标签4/商品Str
  private String productTypeStr;

  // 排序
  private Integer sorting;
  // 状态（0未审核，1已审核）
  private Integer status;
  // 创建时间
  private Date createDate;
  // 创建人
  private Long createBy;
  // 修改时间
  private Date updateDate;
  // 修改人
  private Long updateBy;
  // 用户昵称
  private String userName;
  // 用户logo
  private String userImage;
  // 审核名称
  private String statusName;
  // 品牌类型
  private Long brandType;
  // 品牌类型名称
  private String brandTypeName;
  // 创建时间STR
  private String createDateStr;
  // 商标注册证明
  private String registrationCertificate;
  // 用户性别
  private Integer userSex;
  private Long brandId;
  private String mainCategory;// 公司主营品类
  private String mainCategoryName;// 公司主营品类名称
  private Long userBrandId;// 用户品牌表id
  private Long brandDicId;// 品牌字典表id

  public String getAgeTypeStr() {
    return ageTypeStr;
  }

  public void setAgeTypeStr(String ageTypeStr) {
    this.ageTypeStr = ageTypeStr;
  }

  public String getSkillTypeStr() {
    return skillTypeStr;
  }

  public void setSkillTypeStr(String skillTypeStr) {
    this.skillTypeStr = skillTypeStr;
  }

  public String getOptionTypeStr() {
    return optionTypeStr;
  }

  public void setOptionTypeStr(String optionTypeStr) {
    this.optionTypeStr = optionTypeStr;
  }

  public String getProductTypeStr() {
    return productTypeStr;
  }

  public void setProductTypeStr(String productTypeStr) {
    this.productTypeStr = productTypeStr;
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setUserId(Long value) {
    userId = value;
  }

  public Long getUserId() {
    return userId;
  }

  public void setLogo(String value) {
    logo = value;
  }

  public String getLogo() {
    return logo;
  }

  public void setBrandName(String value) {
    brandName = value;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setContent(String value) {
    content = value;
  }

  public String getContent() {
    return content;
  }

  public void setAgeType(Long value) {
    ageType = value;
  }

  public Long getAgeType() {
    return ageType;
  }

  public void setSkillType(Long value) {
    skillType = value;
  }

  public Long getSkillType() {
    return skillType;
  }

  public void setOptionType(Long value) {
    optionType = value;
  }

  public Long getOptionType() {
    return optionType;
  }

  public void setProductType(Long value) {
    productType = value;
  }

  public Long getProductType() {
    return productType;
  }

  public void setSorting(Integer value) {
    sorting = value;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  @Override
  public void setCreateDate(Date value) {
    createDate = value;
    if (createDate != null) {
      createDateStr = StringUtil.dateString(createDate);
    }
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateBy(Long value) {
    createBy = value;
  }

  @Override
  public Long getCreateBy() {
    return createBy;
  }

  @Override
  public void setUpdateDate(Date value) {
    updateDate = value;
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateBy(Long value) {
    updateBy = value;
  }

  @Override
  public Long getUpdateBy() {
    return updateBy;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Long getBrandType() {
    return brandType;
  }

  public void setBrandType(Long brandType) {
    this.brandType = brandType;
  }

  public String getBrandTypeName() {
    return brandTypeName;
  }

  public void setBrandTypeName(String brandTypeName) {
    this.brandTypeName = brandTypeName;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getRegistrationCertificate() {
    return registrationCertificate;
  }

  public void setRegistrationCertificate(String registrationCertificate) {
    this.registrationCertificate = registrationCertificate;
  }

  public Integer getUserSex() {
    return userSex;
  }

  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
  }

  public Long getBrandId() {
    return brandId;
  }

  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }

  public String getMainCategoryName() {
    return mainCategoryName;
  }

  public void setMainCategoryName(String mainCategoryName) {
    this.mainCategoryName = mainCategoryName;
  }

  public String getMainCategory() {
    return mainCategory;
  }

  public void setMainCategory(String mainCategory) {
    this.mainCategory = mainCategory;
  }

  public Long getUserBrandId() {
    return userBrandId;
  }

  public void setUserBrandId(Long userBrandId) {
    this.userBrandId = userBrandId;
  }

  public Long getBrandDicId() {
    return brandDicId;
  }

  public void setBrandDicId(Long brandDicId) {
    this.brandDicId = brandDicId;
  }


}
