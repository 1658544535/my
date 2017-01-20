package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * @author EricChen
 * 
 */
public class SysAreaPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;

  private Long id;// 编号
  private String name;// 名称
  private String nameEn;// 英文名称
  private Integer type;// 类型（1国家 2省份 3地市 4区县）
  private String postcode;// 区域编码
  private Integer level;
  private Long pid;// 父级编号
  private Integer sorting;// 排序
  private Integer status;// 审核状态

  private Double postage;// 首重（3公斤内）
  private Double postage2;// 首重（3公斤外）
  private Double addPostage;// 续重（3公斤内）
  private Double addPostage2;// 续重（3公斤外）
  private Double fare;// 运费
  private Double fare2;// 运费
  private Integer isOften;// 是否常用地区



  public Integer getIsOften() {
    return isOften;
  }

  public void setIsOften(Integer isOften) {
    this.isOften = isOften;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
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

  public Double getPostage() {
    return postage;
  }

  public void setPostage(Double postage) {
    this.postage = postage;
  }

  public Double getPostage2() {
    return postage2;
  }

  public void setPostage2(Double postage2) {
    this.postage2 = postage2;
  }

  public Double getAddPostage() {
    return addPostage;
  }

  public void setAddPostage(Double addPostage) {
    this.addPostage = addPostage;
  }

  public Double getAddPostage2() {
    return addPostage2;
  }

  public void setAddPostage2(Double addPostage2) {
    this.addPostage2 = addPostage2;
  }

  public Double getFare() {
    return fare;
  }

  public void setFare(Double fare) {
    this.fare = fare;
  }

  public Double getFare2() {
    return fare2;
  }

  public void setFare2(Double fare2) {
    this.fare2 = fare2;
  }

}
