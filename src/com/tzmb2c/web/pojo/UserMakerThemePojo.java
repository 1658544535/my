package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 创客主题表
 */
public class UserMakerThemePojo extends SuperPojo {

  // ID
  private Long id;
  // 用户ID
  private Long userId;
  // 图片
  private String banner;
  // 图文介绍
  private String content;
  // 收藏数
  private Long collectNum;
  // 年龄标签字典ID
  private Long ageType;
  // 能力标签字典ID
  private Long skillType;
  // 标签3/备选ID
  private Long optionType;
  // 标签4/商品
  private Long productType;
  // 排序
  private Integer sorting;
  // 状态（0=>待审核、1=>审核成功、2=>审核失败）
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
  // 创客昵称
  private String makerName;
  // 创客品牌ID
  private Long makerBrandId;
  // 创客品牌名称
  private String makerBrandName;
  // 专题ID
  private Long specialId;
  // 专题名称
  private String specialName;
  // 类型
  private Long type;
  // 提交时间str
  private String createDateStr;
  // 审核状态str
  private String statusStr;
  // 年龄类型Str
  private String ageTypeStr;
  // 技能类型Str
  private String skillTypeStr;
  // 品类Str
  private String optionTypeStr;
  // 商品类型Str
  private String productTypeStr;
  // 类型str
  private String typeStr;
  // 评分
  private String score;
  // 次能力
  private Long secSkillType;
  // 次能力str
  private String secSkillTypeStr;
  // 排序标记
  private String orderBy;
  // 年龄阶段名称
  private String ageTypeName;
  // 能力名称
  private String skillTypeName;
  // 备选名称
  private String optionTypeName;
  // 商品名称
  private String productTypeName;
  // 用户性别
  private Integer userSex;
  // 是否删除
  private Long isDelete;
  // 是否上首页
  private Integer isHomePage;
  // 自定义编辑(0非自定义1自定义)
  private Integer version;
  /**
   * sketch:简要
   */
  private String sketch;
  /**
   * 有你喜欢id
   */
  private Long yourFavouritesId;

  public Long getYourFavouritesId() {
    return yourFavouritesId;
  }

  public void setYourFavouritesId(Long yourFavouritesId) {
    this.yourFavouritesId = yourFavouritesId;
  }

  public String getSketch() {
    return sketch;
  }

  public void setSketch(String sketch) {
    this.sketch = sketch;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  public Integer getIsHomePage() {
    return isHomePage;
  }

  public void setIsHomePage(Integer isHomePage) {
    this.isHomePage = isHomePage;
  }

  public String getAgeTypeName() {
    return ageTypeName;
  }

  public void setAgeTypeName(String ageTypeName) {
    this.ageTypeName = ageTypeName;
  }

  public String getSkillTypeName() {
    return skillTypeName;
  }

  public void setSkillTypeName(String skillTypeName) {
    this.skillTypeName = skillTypeName;
  }

  public String getOptionTypeName() {
    return optionTypeName;
  }

  public void setOptionTypeName(String optionTypeName) {
    this.optionTypeName = optionTypeName;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setUserId(Long value) {
    this.userId = value;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setBanner(String value) {
    this.banner = value;
  }

  public String getBanner() {
    return this.banner;
  }

  public void setContent(String value) {
    this.content = value;
  }

  public String getContent() {
    return this.content;
  }

  public void setCollectNum(Long value) {
    this.collectNum = value;
  }

  public Long getCollectNum() {
    return this.collectNum;
  }

  public void setAgeType(Long value) {
    this.ageType = value;
  }

  public Long getAgeType() {
    return this.ageType;
  }

  public void setSkillType(Long value) {
    this.skillType = value;
  }

  public Long getSkillType() {
    return this.skillType;
  }

  public void setOptionType(Long value) {
    this.optionType = value;
  }

  public Long getOptionType() {
    return this.optionType;
  }

  public void setProductType(Long value) {
    this.productType = value;
  }

  public Long getProductType() {
    return this.productType;
  }

  public void setSorting(Integer value) {
    this.sorting = value;
  }

  public Integer getSorting() {
    return this.sorting;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }

  @Override
  public void setCreateDate(Date value) {
    this.createDate = value;
    createDateStr = StringUtil.dateString(this.createDate);
  }

  @Override
  public Date getCreateDate() {
    return this.createDate;
  }

  @Override
  public void setCreateBy(Long value) {
    this.createBy = value;
  }

  @Override
  public Long getCreateBy() {
    return this.createBy;
  }

  @Override
  public void setUpdateDate(Date value) {
    this.updateDate = value;
  }

  @Override
  public Date getUpdateDate() {
    return this.updateDate;
  }

  @Override
  public void setUpdateBy(Long value) {
    this.updateBy = value;
  }

  @Override
  public Long getUpdateBy() {
    return this.updateBy;
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



  public String getMakerName() {
    return makerName;
  }

  public void setMakerName(String makerName) {
    this.makerName = makerName;
  }

  public Long getMakerBrandId() {
    return makerBrandId;
  }

  public void setMakerBrandId(Long makerBrandId) {
    this.makerBrandId = makerBrandId;
  }

  public String getMakerBrandName() {
    return makerBrandName;
  }

  public void setMakerBrandName(String makerBrandName) {
    this.makerBrandName = makerBrandName;
  }

  public Long getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Long specialId) {
    this.specialId = specialId;
  }

  public String getSpecialName() {
    return specialName;
  }

  public void setSpecialName(String specialName) {
    this.specialName = specialName;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getStatusStr() {
    return statusStr;
  }

  public void setStatusStr(String statusStr) {
    this.statusStr = statusStr;
  }

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

  public String getTypeStr() {
    return typeStr;
  }

  public void setTypeStr(String typeStr) {
    this.typeStr = typeStr;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public Long getSecSkillType() {
    return secSkillType;
  }

  public void setSecSkillType(Long secSkillType) {
    this.secSkillType = secSkillType;
  }

  public String getSecSkillTypeStr() {
    return secSkillTypeStr;
  }

  public void setSecSkillTypeStr(String secSkillTypeStr) {
    this.secSkillTypeStr = secSkillTypeStr;
  }

  public Integer getUserSex() {
    return userSex;
  }

  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
  }

  public Long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Long isDelete) {
    this.isDelete = isDelete;
  }


}
