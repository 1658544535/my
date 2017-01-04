/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import com.tzmb2c.utils.StringUtil;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 平台微页面; InnoDB free: 186368 kB
 * @version 1.0
 * @author
 */
public class MicroPagePojo extends SuperPojo {

  private static final long serialVersionUID = 1L;
  /**
     * 微页面id
     */
	private Long id;
	/**
     * 页面标题
     */
	private String title;
	private String createDateStr;// 创建时间
	private String updateDateStr;// 更新时间
		
	
	
	public String getCreateDateStr() {
	  createDateStr = StringUtil.dateString(this.createDate);
      return createDateStr;
    }
  
    public void setCreateDateStr(String createDateStr) {
      this.createDateStr = createDateStr;
    }
  
    public String getUpdateDateStr() {
      updateDateStr = StringUtil.dateString(this.updateDate);
      return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr) {
      this.updateDateStr = updateDateStr;
    }
  
    public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return this.title;
	}
}
