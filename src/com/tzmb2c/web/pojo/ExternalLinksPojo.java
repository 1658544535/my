/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 外链管理表; InnoDB free: 186368 kB
 * 
 * @version 1.0
 * @author
 */
public class ExternalLinksPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 标题
   */
  private String title;
  /**
   * 链接
   */
  private String link;
  /**
   * 排序序号
   */
  private Integer sorting;
  private Date createDate;
  private Date updateDate;
  private String createDateStr;
  private String updateDateStr;

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    setCreateDateStr(StringUtil.dateString(this.createDate));
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    setUpdateDateStr(StringUtil.dateString(this.updateDate));
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setTitle(String value) {
    title = value;
  }

  public String getTitle() {
    return title;
  }

  public void setLink(String value) {
    link = value;
  }

  public String getLink() {
    return link;
  }

  public void setSorting(Integer value) {
    sorting = value;
  }

  public Integer getSorting() {
    return sorting;
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
}
