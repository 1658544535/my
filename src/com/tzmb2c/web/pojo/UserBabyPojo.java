/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 用户宝宝表; InnoDB free: 187392 kB
 * @version 1.0
 * @author
 */
public class UserBabyPojo extends SuperPojo {

	/**
     * 编号
     */
	private Long id;
	/**
     * 用户ID
     */
	private Long userId;
	/**
     * 宝宝昵称
     */
	private String babyName;
	/**
     * 宝宝性别
     */
	private Integer babySex;
	/**
     * 宝宝生日
     */
	private Date babyBirthday;
	/**
     * 是否默认:0否1是
     */
	private Integer isDefault;
	/**
     * 是否删除（0：不删除；1：已删除）
     */
	private Integer isDelete;
	/**
	 * 是否删除（0：不删除；1：已删除）
	 */
	private String babySexStr;
	
	
	
	public String getBabySexStr() {
      return babySexStr;
    }
  
    public void setBabySexStr(String babySexStr) {
      this.babySexStr = babySexStr;
    }
  
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
	public void setBabyName(String value) {
		this.babyName = value;
	}
	
	public String getBabyName() {
		return this.babyName;
	}
	public void setBabySex(Integer value) {
		this.babySex = value;
	}
	
	public Integer getBabySex() {
		return this.babySex;
	}
	public void setBabyBirthday(Date value) {
		this.babyBirthday = value;
	}
	
	public Date getBabyBirthday() {
		return this.babyBirthday;
	}
	public void setIsDefault(Integer value) {
		this.isDefault = value;
	}
	
	public Integer getIsDefault() {
		return this.isDefault;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
}
