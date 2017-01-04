package com.tzmb2c.web.pojo;

import java.util.Date;
import maowu.framework.utils.pojo.SuperPojo;

/**
 * 用户帖子点赞记录表
 */
public class UserPostLikeHistoryPojo extends SuperPojo {

	// ID
	private Long id;
	// 用户ID
	private Long userId;
	// 帖子ID
	private Long postId;
	// 帖子作者ID
	private Long authorId;
	// 点赞标识（1默认点赞，0取消点赞）
	private Integer isLike;
	// 创建时间
	private Date createTime;
	// 创建人
	private Long createBy;
	// 修改时间
	private Date updateTime;
	// 修改人
	private Long updateBy;
		
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
	
	public void setPostId(Long value) {
		this.postId = value;
	}
	
	public Long getPostId() {
		return this.postId;
	}
	
	public void setAuthorId(Long value) {
		this.authorId = value;
	}
	
	public Long getAuthorId() {
		return this.authorId;
	}
	
	public void setIsLike(Integer value) {
		this.isLike = value;
	}
	
	public Integer getIsLike() {
		return this.isLike;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateBy(Long value) {
		this.createBy = value;
	}
	
	public Long getCreateBy() {
		return this.createBy;
	}
	
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setUpdateBy(Long value) {
		this.updateBy = value;
	}
	
	public Long getUpdateBy() {
		return this.updateBy;
	}
	
}
