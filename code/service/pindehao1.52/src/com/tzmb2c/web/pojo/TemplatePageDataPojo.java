/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 模板页面数据表; InnoDB free: 186368 kB
 * @version 1.0
 * @author
 */
public class TemplatePageDataPojo extends SuperPojo {

	/**
     * ID
     */
	private Long id;
	/**
     * 类型：1-平台专题2-创客专题3-笔记4-微页面
     */
	private Integer type;
	/**
     * 对应类型的ID
     */
	private Long pageId;
	/**
     * 页面中各模块的设置信息，json格式
     */
	private String data;
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setType(Integer value) {
		this.type = value;
	}
	
	public Integer getType() {
		return this.type;
	}
	public void setPageId(Long value) {
		this.pageId = value;
	}
	
	public Long getPageId() {
		return this.pageId;
	}
	public void setData(String value) {
		this.data = value;
	}
	
	public String getData() {
		return this.data;
	}
}
