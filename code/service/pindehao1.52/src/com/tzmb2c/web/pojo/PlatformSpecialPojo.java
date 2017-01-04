/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 平台专题表
 * 
 * @version 1.0
 * @author
 */
public class PlatformSpecialPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 类型（1首页，2经典，3热门）
   */
  private Integer type;
  /**
   * 标题
   */
  private String title;
  /**
   * 图片
   */
  private String banner;
  /**
   * 图文介绍
   */
  private String content;
  /**
   * 年龄标签字典ID
   */
  private Long ageType;
  /**
   * 能力标签字典ID
   */
  private Long skillType;
  /**
   * 标签3/备选ID
   */
  private Long optionType;
  /**
   * 标签4/商品
   */
  private Long productType;
  /**
   * 排序
   */
  private Integer sorting;
  /**
   * 状态（0未审核，1已审核）
   */
  private Integer status;
  /**
   * 更新时间
   */
  private Date updateDate;
  /**
   * 创建时间Str
   */
  private String createDateStr;
  /**
   * 更新时间Str
   */
  private String updateDateStr;
  /**
   * 年龄类型Str
   */
  private String ageTypeStr;
  /**
   * 技能类型Str
   */
  private String skillTypeStr;
  /**
   * 品类Str
   */
  private String optionTypeStr;
  /**
   * 商品类型Str
   */
  private String productTypeStr;
  /**
   * 审核情况Str
   */
  private String statusStr;
  /**
   * 专题类型Str
   */
  private String typeStr;

  /**
   * 专题类别
   */
  private Long specialCategories;
  /**
   * 专题类别Str
   */
  private String specialCategoriesStr;
  /**
   * 评分
   */
  private String score;
  /**
   * 次能力
   */
  private Long secSkillType;

  /**
   * 次能力str
   */
  private String secSkillTypeStr;
  /**
   * 是否上首页
   */
  private Integer isHomePage;
  /**
   * 自定义编辑(0非自定义1自定义)
   */
  private Integer version;
  /**
   * sketch:简要
   */
  private String sketch;
  /**
   * 收藏数
   */
  private Long collectNum;
  /**
   * 有你喜欢id
   */
  private Long yourFavouritesId;
  /**
   * 有你喜欢标题
   */
  private String yourFavouritesName;

  
  public String getYourFavouritesName() {
    return yourFavouritesName;
  }

  public void setYourFavouritesName(String yourFavouritesName) {
    this.yourFavouritesName = yourFavouritesName;
  }

  public Long getYourFavouritesId() {
    return yourFavouritesId;
  }

  public void setYourFavouritesId(Long yourFavouritesId) {
    this.yourFavouritesId = yourFavouritesId;
  }

  public Long getCollectNum() {
    return collectNum;
  }

  public void setCollectNum(Long collectNum) {
    this.collectNum = collectNum;
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

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setType(Integer value) {
    this.type = value;
  }

  public Integer getType() {
    return this.type;
  }

  public void setTitle(String value) {
    this.title = value;
  }

  public String getTitle() {
    return this.title;
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

  public String getStatusStr() {
    return statusStr;
  }

  public void setStatusStr(String statusStr) {
    this.statusStr = statusStr;
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    if (this.updateDate != null) {
      updateDateStr = StringUtil.dateString(this.updateDate);
    }
  }

  public String getTypeStr() {
    return typeStr;
  }

  public void setTypeStr(String typeStr) {
    this.typeStr = typeStr;
  }

  public Long getSpecialCategories() {
    return specialCategories;
  }

  public void setSpecialCategories(Long specialCategories) {
    this.specialCategories = specialCategories;
  }

  public String getSpecialCategoriesStr() {
    return specialCategoriesStr;
  }

  public void setSpecialCategoriesStr(String specialCategoriesStr) {
    this.specialCategoriesStr = specialCategoriesStr;
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


}
