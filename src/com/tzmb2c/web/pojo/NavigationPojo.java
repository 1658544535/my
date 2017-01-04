package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class NavigationPojo extends SuperPojo {
  private Long id; // ID
  private String name; // 导航分类名
  private Long level; // 分类级别，1-一级类目，2-二级类目
  private String levelName; // 分类级别名称
  private String categoryId; // 分类ID
  private Integer sorting; // 排序
  private Integer status; // 状态(取业务字典：0-未审核，1-已审核)
  private String statusName; // 状态名称
  private String createDateStr;// 创建时间
  private String updateDateStr;// 更新时间
  private Long levels; // 修改的分类级别
  private String levelAll; // 当前的分类级别和名称
  private String categoryId1; // 一级分类级别名称
  private String categoryId2; // 二级分类级别名称

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getLevel() {
    return level;
  }

  public void setLevel(Long level) {
    this.level = level;
  }

  public String getLevelName() {
    return levelName;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Long getLevels() {
    return levels;
  }

  public void setLevels(Long levels) {
    this.levels = levels;
  }

  public String getLevelAll() {
    return levelAll;
  }

  public void setLevelAll(String levelAll) {
    this.levelAll = levelAll;
  }

  public String getCategoryId1() {
    return categoryId1;
  }

  public void setCategoryId1(String categoryId1) {
    this.categoryId1 = categoryId1;
  }

  public String getCategoryId2() {
    return categoryId2;
  }

  public void setCategoryId2(String categoryId2) {
    this.categoryId2 = categoryId2;
  }
}
