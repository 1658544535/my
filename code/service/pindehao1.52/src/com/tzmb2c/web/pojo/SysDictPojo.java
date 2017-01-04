package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class SysDictPojo extends SuperPojo {
  private Long id;// 编号
  private String type;// 字典分类(例如：sys_status)
  private String name;// 字典名称
  private String nameEn;// 字典名称英文
  private String value;// 字典取值
  private Integer sorting;// 排序
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;
  private Integer shipin;// 用来查出视频类型的


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
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

  public Integer getShipin() {
    return shipin;
  }

  public void setShipin(Integer shipin) {
    this.shipin = shipin;
  }



}
