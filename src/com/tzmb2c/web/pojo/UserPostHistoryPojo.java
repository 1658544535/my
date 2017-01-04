/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 帖子浏览历史
 * 
 * @version 1.0
 * @author
 */
public class UserPostHistoryPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 帖子ID
   */
  private Long postId;
  /**
   * 浏览日期
   */
  private Date viewDate;
  /**
   * 修改日期
   */
  private Date updateDate;
  /**
   * 浏览数
   */
  private Long lookNum;
  /**
   * 0-正常1-移除
   */
  private Integer isDelete;
  /**
   * 笔记标题
   */
  private String title;
  /**
   * 笔记banner
   */
  private String banner;
  /**
   * 笔记作者
   */
  private String author;
  /**
   * 笔记收藏数
   */
  private Integer collectNum;
  /**
   * 用户性别
   */
  private Integer authorSex;
  /**
   * 作者id
   */
  private Long authorId;
  /**
   * 作者logo
   */
  private String authorLogo;
  
  
  public String getAuthorLogo() {
    return authorLogo;
  }

  public void setAuthorLogo(String authorLogo) {
    this.authorLogo = authorLogo;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public Integer getAuthorSex() {
    return authorSex;
  }

  public void setAuthorSex(Integer authorSex) {
    this.authorSex = authorSex;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
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

  public void setPostId(Long value) {
    this.postId = value;
  }

  public Long getPostId() {
    return this.postId;
  }

  public void setViewDate(Date value) {
    this.viewDate = value;
  }

  public Date getViewDate() {
    return this.viewDate;
  }

  public void setLookNum(Long value) {
    this.lookNum = value;
  }

  public Long getLookNum() {
    return this.lookNum;
  }

  public void setIsDelete(Integer value) {
    this.isDelete = value;
  }

  public Integer getIsDelete() {
    return this.isDelete;
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
}
