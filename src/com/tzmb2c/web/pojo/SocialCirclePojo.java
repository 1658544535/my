/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.tzmb2c.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 圈子表
 * @version 1.0
 * @author
 */
public class SocialCirclePojo implements Serializable {

	// ID
	private Long id;
	// 圈子类型ID
	private Long circleTypeId;
	// 标题
	private String title;
	// 图片
	private String banner;
	// 关注人数
	private Long followNum;
	// 排序
	private Integer sorting;
	// 状态（0未审核，1已审核）
	private Integer status;
	// 状态名称（0未审核，1已审核）
	private String statusName;
	// 社圈分类
	private String typeName;
	// 创建时间
	private Date createDate;
	// 创建人
	private Long createBy;
	// 修改时间
	private Date updateDate;
	// 修改人
	private Long updateBy;
	//个人昵称
	private String nickname;
	//排序类型标记
	private String orderBy;
	//排序类型标记
	private Integer isFollow;
	//用户id
	private Long uid;
	
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(Integer isFollow) {
		this.isFollow = isFollow;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}

	public void setCircleTypeId(Long value) {
		this.circleTypeId = value;
	}

	public Long getCircleTypeId() {
		return this.circleTypeId;
	}

	public void setTitle(String value) {
		this.title = value;
	}

	public String getTitle() {
		return this.title;
	}

	public void setBanner(String value) {
		this.banner = value;
	}

	public String getBanner() {
		return this.banner;
	}

	public void setFollowNum(Long value) {
		this.followNum = value;
	}

	public Long getFollowNum() {
		return this.followNum;
	}

	public void setSorting(Integer value) {
		this.sorting = value;
	}

	public Integer getSorting() {
		return this.sorting;
	}

	public void setStatus(Integer value) {
		this.status = value;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setCreateDate(Date value) {
		this.createDate = value;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateBy(Long value) {
		this.createBy = value;
	}

	public Long getCreateBy() {
		return this.createBy;
	}

	public void setUpdateDate(Date value) {
		this.updateDate = value;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateBy(Long value) {
		this.updateBy = value;
	}

	public Long getUpdateBy() {
		return this.updateBy;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
