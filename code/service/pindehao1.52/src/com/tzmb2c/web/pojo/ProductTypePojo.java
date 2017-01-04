package com.tzmb2c.web.pojo;

import java.io.File;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * @author EricChen
 */
public class ProductTypePojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id;// 类型编号
  private Long userId;// 用户编号
  private Long pid;// 父级id
  private Integer level;// 级别
  private String name;// 名称
  private String nameEn;// 英文名称
  private Integer status;// 状态
  private Integer sorting;// 排序
  private String isRed;// 是否红色
  private String userName;// 用户名称
  private String statusName;// 审核状态名称
  private String isRedName;
  private String image;
  private File upfile2;
  private String ageType;// 年龄段，1:0-3岁,2:3-6岁,3:6岁以上
  private String topLevel;// 一级分类
  private Integer visable;// 0-不可见，1-可见
  private String visableName;// 0-不可见，1-可见
  private String parentName;// 所属父类
  private Long topLevel1;
  private Integer categoryFlag;// 特殊分类标记



  public Integer getCategoryFlag() {
    return categoryFlag;
  }

  public void setCategoryFlag(Integer categoryFlag) {
    this.categoryFlag = categoryFlag;
  }

  public String getVisableName() {
    return visableName;
  }

  public void setVisableName(String visableName) {
    this.visableName = visableName;
  }

  public Long getTopLevel1() {
    return topLevel1;
  }

  public void setTopLevel1(Long topLevel1) {
    this.topLevel1 = topLevel1;
  }

  public Integer getVisable() {
    return visable;
  }

  public void setVisable(Integer visable) {
    this.visable = visable;
    if (this.visable != null && this.visable == 1) {
      visableName = "显示";
    } else {
      visableName = "不显示";
    }
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public String getIsRed() {
    return isRed;
  }

  public void setIsRed(String isRed) {
    this.isRed = isRed;
    if (this.isRed != null && this.isRed.equals("1")) {
      isRedName = "是";
    } else {
      isRedName = "否";
    }
  }

  public String getIsRedName() {
    return isRedName;
  }

  public void setIsRedName(String isRedName) {
    this.isRedName = isRedName;
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

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameEn() {
    return nameEn;
  }

  public void setNameEn(String nameEn) {
    this.nameEn = nameEn;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getAgeType() {
    return ageType;
  }

  public void setAgeType(String ageType) {
    this.ageType = ageType;
  }

  public String getTopLevel() {
    return topLevel;
  }

  public void setTopLevel(String topLevel) {
    this.topLevel = topLevel;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }


}
