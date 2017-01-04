/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 主题/帖子评论表
 * 
 * @version 1.0
 * @author
 */
public class UserPostCommentPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 类型（1主题，2帖子）
   */
  private Integer type;
  /**
   * 主题/帖子ID
   */
  private Long typeId;
  /**
   * 主题/帖子作者ID
   */
  private Long authorId;
  /**
   * 评论内容（200字）
   */
  private String content;
  /**
   * 审核状态（0未审核1审核通过2审核不通过）
   */
  private Integer status;
  /**
   * 创客主题/笔记名称
   */
  private String title;
  /**
   * 创客主题/笔记banner
   */
  private String banner;
  /**
   * 作者
   */
  private String author;
  /**
   * 收藏数
   */
  private Integer collectNum;
  // 用户昵称
  private String userName;
  // 选项
  private String option;
  // 用户logo
  private String userImage;
  // 用户logo
  private String userImage2;
  // 用户昵称
  private String userName2;
  /**
   * 作者性别
   */
  private Integer authorSex;
  /**
   *分组标记 
   */
  private String groupBy;


  public String getGroupBy() {
    return groupBy;
  }

  public void setGroupBy(String groupBy) {
    this.groupBy = groupBy;
  }

  public Integer getAuthorSex() {
    return authorSex;
  }

  public void setAuthorSex(Integer authorSex) {
    this.authorSex = authorSex;
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

  public void setAuthorId(Long value) {
    this.authorId = value;
  }

  public Long getAuthorId() {
    return this.authorId;
  }

  public void setContent(String value) {
    this.content = value;
  }

  public String getContent() {
    return this.content;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBanner() {
    return banner;
  }

  public void setBanner(String banner) {
    this.banner = banner;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getCollectNum() {
    return collectNum;
  }

  public void setCollectNum(Integer collectNum) {
    this.collectNum = collectNum;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public String getUserImage2() {
    return userImage2;
  }

  public void setUserImage2(String userImage2) {
    this.userImage2 = userImage2;
  }

  public String getUserName2() {
    return userName2;
  }

  public void setUserName2(String userName2) {
    this.userName2 = userName2;
  }
}
