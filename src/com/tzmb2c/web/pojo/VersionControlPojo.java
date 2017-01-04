/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 版本控制表; InnoDB free: 58368 kB
 * @version 1.0
 * @author
 */
public class VersionControlPojo extends SuperPojo {

	/**
     * ID
     */
	private Long id;
	/**
     * 渠道：1-IOS,2-ANDROID
     */
	private Integer channel;
	/**
     * 开启状态：0-未开启1-开启
     */
	private Integer state;
	/**
     * 最新版本
     */
	private Double lastVersion;
	/**
     * 强制更新版本
     */
	private Double forceVersion;
	/**
     * 更新语
     */
	private String sketch;
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setChannel(Integer value) {
		this.channel = value;
	}
	
	public Integer getChannel() {
		return this.channel;
	}
	public void setState(Integer value) {
		this.state = value;
	}
	
	public Integer getState() {
		return this.state;
	}
	public void setLastVersion(Double value) {
		this.lastVersion = value;
	}
	
	public Double getLastVersion() {
		return this.lastVersion;
	}
	public void setForceVersion(Double value) {
		this.forceVersion = value;
	}
	
	public Double getForceVersion() {
		return this.forceVersion;
	}
	public void setSketch(String value) {
		this.sketch = value;
	}
	
	public String getSketch() {
		return this.sketch;
	}
}
