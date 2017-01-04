package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 工厂玩具配件表 -- game_toy_parts
 * 
 */

public class GameToyPartsPojo extends SuperPojo {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private Long id;// 编号id
  private Long toyId;// 玩具id
  private String name;// 玩具配件名字
  private String images;// 玩具配件图片
  private Integer createTime; // 成所需时间（以分钟为单位）
  private Integer probability;// 概率（0-100）
  private Integer sorting;// 排序
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态字符
  private Date createDate;// 创建时间
  private Date updateDate;// 更新时间
  private String createDateStr;
  private String updateDateStr;
  private String toyName;// 玩具名字
  private String toyImages;// 玩具图片

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

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public Integer getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Integer createTime) {
    this.createTime = createTime;
  }

  public Integer getProbability() {
    return probability;
  }

  public void setProbability(Integer probability) {
    this.probability = probability;
  }

  public Long getToyId() {
    return toyId;
  }

  public void setToyId(Long toyId) {
    this.toyId = toyId;
  }

  public String getToyName() {
    return toyName;
  }

  public void setToyName(String toyName) {
    this.toyName = toyName;
  }

  public String getToyImages() {
    return toyImages;
  }

  public void setToyImages(String toyImages) {
    this.toyImages = toyImages;
  }
}
