/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 商品限购表; InnoDB free: 58368 kB
 * @version 1.0
 * @author
 */
public class ProductRestrictionPojo extends SuperPojo {

	/**
     * ID
     */
	private Long id;
	/**
     * 用户ID
     */
	private Long userId;
	/**
     * 活动ID
     */
	private Long activityId;
	/**
     * 商品ID
     */
	private Long productId;
	/**
     * buyNum
     */
	private Integer buyNum;
	/**
     * maxNum
     */
	private Integer maxNum;
		
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
	public void setActivityId(Long value) {
		this.activityId = value;
	}
	
	public Long getActivityId() {
		return this.activityId;
	}
	public void setProductId(Long value) {
		this.productId = value;
	}
	
	public Long getProductId() {
		return this.productId;
	}
	public void setBuyNum(Integer value) {
		this.buyNum = value;
	}
	
	public Integer getBuyNum() {
		return this.buyNum;
	}
	public void setMaxNum(Integer value) {
		this.maxNum = value;
	}
	
	public Integer getMaxNum() {
		return this.maxNum;
	}
}
