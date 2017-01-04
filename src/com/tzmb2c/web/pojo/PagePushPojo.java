package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 2014-12-13
 * 
 * @author creazylee 推送综合管理
 */

public class PagePushPojo extends SuperPojo {

  private Long id;// 焦点图编号id
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态字符
  private String typeName;// 类型字符
  private Integer type;// 类型(取业务字典：1首页2内页)
  private String title;// 标题
  private String url;// 链接地址
  private String images;// 图片地址
  private Date createDate;// 创建日期
  private String createDateStr;// 创建日期(字符串)
  private Date updateDate; // 更新日期
  private String updateDateStr; // 更新日期(字符串)
  private String sizeTips;
  private String backgroundColor;// 广告图背景颜色值
  private Integer location;// 位置值
  private Integer sorting;// 排序
  private Integer subType;// App端首页焦点图子类型

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    this.updateDateStr = StringUtil.dateToString(this.updateDate);
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public Integer getSubType() {
    return subType;
  }

  public void setSubType(Integer subType) {
    this.subType = subType;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public String getSizeTips() {
    return sizeTips;
  }

  public void setSizeTips(String sizeTips) {
    this.sizeTips = sizeTips;
  }

  public Integer getLocation() {
    return location;
  }

  public void setLocation(Integer location) {
    this.location = location;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.createDateStr = StringUtil.dateToString(this.createDate);
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }
}
