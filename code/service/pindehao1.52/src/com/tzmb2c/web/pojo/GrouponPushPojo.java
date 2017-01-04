/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 首页管理(首页推送图); InnoDB free: 59392 kB
 * @version 1.0
 * @author
 */
public class GrouponPushPojo extends SuperPojo {

	/**
     * ID
     */
	private Long id;
	/**
     * 商品图片
     */
	private String productImage;
	/**
     * 地址（省份）
     */
	private String address;
	/**
     * 用户呢称
     */
	private String name;
	/**
     * 订单ID
     */
	private Long orderId;
	/**
     * 参与ID
     */
	private Long attendId;
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setProductImage(String value) {
		this.productImage = value;
	}
	
	public String getProductImage() {
		return this.productImage;
	}
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setOrderId(Long value) {
		this.orderId = value;
	}
	
	public Long getOrderId() {
		return this.orderId;
	}
	public void setAttendId(Long value) {
		this.attendId = value;
	}
	
	public Long getAttendId() {
		return this.attendId;
	}
}
