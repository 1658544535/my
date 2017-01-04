/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 消息模板表; InnoDB free: 58368 kB
 * @version 1.0
 * @author
 */
public class NoticeTemplatePojo extends SuperPojo {

	/**
     * ID
     */
	private Long id;
	/**
     * 模板类型1-支付成功2-开团成功3-拼团成功4-抽奖未中奖5-抽奖已中奖6-发货7-退款
     */
	private Integer type;
	/**
     * 模板标题
     */
	private String title;
	/**
     * 模板信息
     */
	private String template;
	/**
     * 删除标识（0-否1-是）
     */
	private Integer isDelete;
	/**
     * 审核状态（0-未审核1-审核通过）
     */
	private Integer status;
	private String info;
	private String remark;
	private String lname;
	private String param;
	
		
  public String getInfo() {
  return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

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
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTemplate(String value) {
		this.template = value;
	}
	
	public String getTemplate() {
		return this.template;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
}
