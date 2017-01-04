/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 招行用户签约表; InnoDB free: 187392 kB
 * @version 1.0
 * @author
 */
public class CmbUserAgreePojo extends SuperPojo {

	/**
     * 编号
     */
	private Long id;
	/**
     * 用户ID
     */
	private Long userId;
	/**
     * 客户协议号
     */
	private String custArgno;
	/**
     * 协议开通请求流水号
     */
	private String reqSerial;
	/**
     * 协议用户ID
     */
	private String custNo;
	/**
     * 协议手机号
     */
	private String mobile;
	/**
     * 状态（1：有效；0：无效）
     */
	private Integer status;
	/**
     * 解约时间
     */
	private Date cancelDate;
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	public void setCustArgno(String value) {
		this.custArgno = value;
	}
	
	public String getCustArgno() {
		return this.custArgno;
	}
	public void setReqSerial(String value) {
		this.reqSerial = value;
	}
	
	public String getReqSerial() {
		return this.reqSerial;
	}
	public void setCustNo(String value) {
		this.custNo = value;
	}
	
	public String getCustNo() {
		return this.custNo;
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	public void setCancelDate(Date value) {
		this.cancelDate = value;
	}
	
	public Date getCancelDate() {
		return this.cancelDate;
	}
}
