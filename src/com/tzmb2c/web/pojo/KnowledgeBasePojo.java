/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 知识库表
 * 
 * @version 1.0
 * @author
 */
public class KnowledgeBasePojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 小图标（36*36）
   */
  private String smallIcon;
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
   * 标签4/备选ID
   */
  private Long optionType;
  /**
   * 标签3/商品
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
   * 状态字符
   */
  private String statusName;
  /**
   * 标题
   */
  private String title;
  private Date createDate;// 创建时间
  private Date updateDate;// 更新时间
  private String createDateStr;
  private String updateDateStr;
  private String ageTypeName;// 年龄
  private String skillTypeName;// 能力
  private String optionTypeName;
  private String productTypeName;
  // 排序条件
  private String orderBy;
  // 次能力
  private Long secSkillType;
  private String secSkillTypeName;
  // 评分
  private Integer score;
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
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    createDateStr = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    updateDateStr = StringUtil.dateToString(this.updateDate);
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setSmallIcon(String value) {
    smallIcon = value;
  }

  public String getSmallIcon() {
    return smallIcon;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
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

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public Long getSecSkillType() {
    return secSkillType;
  }

  public void setSecSkillType(Long secSkillType) {
    this.secSkillType = secSkillType;
  }

  public String getSecSkillTypeName() {
    return secSkillTypeName;
  }

  public void setSecSkillTypeName(String secSkillTypeName) {
    this.secSkillTypeName = secSkillTypeName;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
