package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class SysRolePojo extends SuperPojo {
  private Long id;// 编号
  private String type;// 角色分类(例如：sys_status)
  private String name;// 角色名称
  private String nameEn;// 角色名称英文
  private Integer sorting;// 排序
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;

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



}
