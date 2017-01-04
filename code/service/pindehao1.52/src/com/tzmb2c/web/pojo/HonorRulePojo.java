/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 荣誉规则表
 * @version 1.0
 * @author
 */
public class HonorRulePojo extends SuperPojo {

	/**
     * ID
     */
	private Long id;
	/**
     * 年龄标签字典value
     */
	private Long ageType;
	/**
     * 能力标签字典value
     */
	private Long skillType;
	/**
     * 荣誉称号
     */
	private String honor;
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setAgeType(Long value) {
		this.ageType = value;
	}
	
	public Long getAgeType() {
		return this.ageType;
	}
	public void setSkillType(Long value) {
		this.skillType = value;
	}
	
	public Long getSkillType() {
		return this.skillType;
	}
	public void setHonor(String value) {
		this.honor = value;
	}
	
	public String getHonor() {
		return this.honor;
	}
}
