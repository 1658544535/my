/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 用户收藏表
 * 
 * @version 1.0
 * @author
 */
public class UserPostCollectPojo extends SuperPojo {

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
   * 收藏标识（1默认收藏，0取消收藏）
   */
  private Integer isCollect;
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
  // 用户性别
  private Integer userSex;
  // 用户昵称
  private String userName;
  // 用户logo
  private String userImage;

  /**
   * subType:子类型： 1-软文专题2-商品专题3-创客专题
   */
  private Integer subType;

  public Integer getSubType() {
    return subType;
  }

  public void setSubType(Integer subType) {
    this.subType = subType;
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

  public void setIsCollect(Integer value) {
    this.isCollect = value;
  }

  public Integer getIsCollect() {
    return this.isCollect;
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

  public Integer getUserSex() {
    return userSex;
  }

  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
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
}
