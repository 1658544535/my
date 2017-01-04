package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * brand_dic表
 */
public class LabelPojo extends SuperPojo {
  private Long id; // id
  private String name; // 标签名
  private Long status; // 标签审核状态
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

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }
}
