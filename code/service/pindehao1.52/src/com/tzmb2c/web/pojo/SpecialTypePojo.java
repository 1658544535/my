/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import com.tzmb2c.utils.StringUtil;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 专题分类表; InnoDB free: 58368 kB
 * @version 1.0
 * @author
 */
public class SpecialTypePojo extends SuperPojo {

	/**
     * id
     */
	private Long id;
	/**
     * 名称
     */
	private String name;
	/**
     * 排序
     */
	private Integer sorting;
	/**
     * 审核状态
     */
    private Integer status;
    /**
     * 显示状态
     */
    private Integer statusDisplay;

    private Date createDate;// 创建时间
    private String creatDateString;
    private Date updateDate; // 更新时间
    private String updateDateString;
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setSorting(Integer value) {
		this.sorting = value;
	}
	
	public Integer getSorting() {
		return this.sorting;
	}

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getStatusDisplay() {
    return statusDisplay;
  }

  public void setStatusDisplay(Integer statusDisplay) {
    this.statusDisplay = statusDisplay;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.creatDateString = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    this.setUpdateDateString(StringUtil.dateString(this.updateDate));
  }

  public String getCreatDateString() {
    return creatDateString;
  }

  public void setCreatDateString(String creatDateString) {
    this.creatDateString = creatDateString;
  }

  public String getUpdateDateString() {
    return updateDateString;
  }

  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }
}
