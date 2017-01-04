package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 2015-1-6
 * 
 * @author creazylee 安全问题表
 */

public class SysSecurityQuestionPojo extends SuperPojo {

  private Long id;// 用户编号
  private String quesion;// 问题
  private String quesionEn;// 问题英文
  private Integer sorting;// 排序
  private String status;// 显示状态(取业务字典：0隐藏1显示)

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getQuesion() {
    return quesion;
  }

  public void setQuesion(String quesion) {
    this.quesion = quesion;
  }

  public String getQuesionEn() {
    return quesionEn;
  }

  public void setQuesionEn(String quesionEn) {
    this.quesionEn = quesionEn;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
