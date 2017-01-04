package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 用户关注表
 */
public class UserCircleFollowPojo extends SuperPojo {

  // ID
  private Long id;
  // 用户ID
  private Long userId;
  // 类型（1用户，2圈子）
  private Integer type;
  // 被关注用户/圈子ID
  private Long typeId;
  // 关注标识（1默认关注，0取消关注）
  private Integer isFollow;
  // 创建时间
  private Date createDate;
  // 创建人
  private Long createBy;
  // 修改时间
  private Date updateDate;
  // 修改人
  private Long updateBy;
  //昵称
  private String userName;
  //头像
  private String userImage;
  //帖子id
  private Long postId;
  //帖子标题
  private String title;
  //帖子文本
  private String content;
  //帖子阅读数
  private Integer lookNum;
  //帖子评论数
  private Integer commentNum;
  //排序标记
  private String orderBy;
  //是否查询用户关注热帖标记
  private Integer hotPostFlag;
  //圈子图片
  private String banner;
  //圈子名称
  private String scTitle;
  //圈子关注人数
  private Integer followNum;
  //圈子id
  private Long socialCircleId;
  //用户性别
  private Integer userSex;
  
  public Integer getUserSex() {
    return userSex;
  }

  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
  }

  public Long getSocialCircleId() {
    return socialCircleId;
  }

  public void setSocialCircleId(Long socialCircleId) {
    this.socialCircleId = socialCircleId;
  }

  public String getBanner() {
	return banner;
  }

  public void setBanner(String banner) {
	this.banner = banner;
  }

  public String getScTitle() {
	return scTitle;
  }

  public void setScTitle(String scTitle) {
	this.scTitle = scTitle;
  }

  public Integer getFollowNum() {
	return followNum;
  }

  public void setFollowNum(Integer followNum) {
	this.followNum = followNum;
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

  public void setType(Integer value) {
    this.type = value;
  }

  public Integer getType() {
    return this.type;
  }

  public void setTypeId(Long value) {
    this.typeId = value;
  }

  public Long getTypeId() {
    return this.typeId;
  }

  public void setIsFollow(Integer value) {
    this.isFollow = value;
  }

  public Integer getIsFollow() {
    return this.isFollow;
  }

  @Override
  public void setCreateDate(Date value) {
    this.createDate = value;
  }

  @Override
  public Date getCreateDate() {
    return this.createDate;
  }

  @Override
  public void setCreateBy(Long value) {
    this.createBy = value;
  }

  @Override
  public Long getCreateBy() {
    return this.createBy;
  }

  @Override
  public void setUpdateDate(Date value) {
    this.updateDate = value;
  }

  @Override
  public Date getUpdateDate() {
    return this.updateDate;
  }

  @Override
  public void setUpdateBy(Long value) {
    this.updateBy = value;
  }

  @Override
  public Long getUpdateBy() {
    return this.updateBy;
  }

  public String getUserName() {
	return userName;
  }

  public void setUserName(String userName) {
	this.userName = userName;
  }

  public String getUserImage() {
	return userImage;
  }

  public void setUserImage(String userImage) {
	this.userImage = userImage;
  }

  public Long getPostId() {
	return postId;
  }

  public void setPostId(Long postId) {
	this.postId = postId;
  }

  public String getTitle() {
	return title;
  }

  public void setTitle(String title) {
	this.title = title;
  }

  public String getContent() {
	return content;
  }

  public void setContent(String content) {
	this.content = content;
  }

  public Integer getLookNum() {
	return lookNum;
  }

  public void setLookNum(Integer lookNum) {
	this.lookNum = lookNum;
  }

  public Integer getCommentNum() {
	return commentNum;
  }

  public void setCommentNum(Integer commentNum) {
	this.commentNum = commentNum;
  }

  public String getOrderBy() {
	return orderBy;
  }

  public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
  }

  public Integer getHotPostFlag() {
	return hotPostFlag;
  }

  public void setHotPostFlag(Integer hotPostFlag) {
	this.hotPostFlag = hotPostFlag;
  }
  
  

}
