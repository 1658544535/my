/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * InnoDB free: 58368 kB
 * @version 1.0
 * @author
 */
public class UserCouponFlagPojo extends SuperPojo {

	/**
     * ID
     */
	private Long id;
	/**
     * 用户id
     */
	private Long userId;
	/**
     * 获取时间
     */
	private Date gainDate;
		
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
	public void setGainDate(Date value) {
		this.gainDate = value;
	}
	
	public Date getGainDate() {
		return this.gainDate;
	}
}
