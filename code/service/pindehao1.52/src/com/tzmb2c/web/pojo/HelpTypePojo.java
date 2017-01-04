package com.tzmb2c.web.pojo;

import java.util.List;

import maowu.framework.utils.pojo.SuperPojo;

/***
 * 
 * @author creazylee
 * 
 */
public class HelpTypePojo extends SuperPojo {

  private Long id;// 编号ID
  private String name;// 类型名称
  private String pname;// 父类型名称
  private String nameEn;// 英文类型名称
  private Long pid;// 父级id
  private Integer sorting;// 排序
  private Integer status;// 状态
  private String statusName;// 状态(字符串类型)
  private List<HelpPojo> helpPojoList;// 帮助信息列表
  private List<HelpTypePojo> helpTypeChildPojoList;



  public List<HelpTypePojo> getHelpTypeChildPojoList() {
    return helpTypeChildPojoList;
  }

  public void setHelpTypeChildPojoList(List<HelpTypePojo> helpTypeChildPojoList) {
    this.helpTypeChildPojoList = helpTypeChildPojoList;
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

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public List<HelpPojo> getHelpPojoList() {
    return helpPojoList;
  }

  public void setHelpPojoList(List<HelpPojo> helpPojoList) {
    this.helpPojoList = helpPojoList;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }


}
