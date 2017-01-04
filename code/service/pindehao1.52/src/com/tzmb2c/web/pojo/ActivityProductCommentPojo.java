/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 活动商品评论表; InnoDB free: 59392 kB
 * 
 * @version 1.0
 * @author
 */
public class ActivityProductCommentPojo extends SuperPojo {

  /**
   * id
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 活动类型：1-0.1抽奖
   */
  private Integer type;
  /**
   * 团购活动表id
   */
  private Long activityId;
  /**
   * 商品id
   */
  private Long productId;
  /**
   * 评论内容
   */
  private String content;
  /**
   * 评论图片1
   */
  private String img1;
  /**
   * 评论图片2
   */
  private String img2;
  /**
   * 评论图片3
   */
  private String img3;
  /**
   * 评论图片4
   */
  private String img4;
  /**
   * 评论图片5
   */
  private String img5;
  /**
   * 评论图片6
   */
  private String img6;
  /**
   * 评论图片7
   */
  private String img7;
  /**
   * 评论图片8
   */
  private String img8;
  /**
   * 评论图片9
   */
  private String img9;
  /**
   * 审核状态（0未审核，1已审核）
   */
  private Integer status;
  /**
   * 排序
   */
  private Integer sorting;
  private String userImage;
  private String userName;
  private String productName;
  private String productImage;
  private Date createDate;// 创建时间
  private String creatDateString;
  private Date updateDate; // 更新时间
  private String updateDateString;
  private String keywords;
  /**
   * attendId:团参与ID
   */
  private Long attendId;
  private String loginname;

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public Long getAttendId() {
    return attendId;
  }

  public void setAttendId(Long attendId) {
    this.attendId = attendId;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public void setContent(String value) {
    this.content = value;
  }

  public String getContent() {
    return this.content;
  }

  public void setImg1(String value) {
    this.img1 = value;
  }

  public String getImg1() {
    return this.img1;
  }

  public void setImg2(String value) {
    this.img2 = value;
  }

  public String getImg2() {
    return this.img2;
  }

  public void setImg3(String value) {
    this.img3 = value;
  }

  public String getImg3() {
    return this.img3;
  }

  public void setImg4(String value) {
    this.img4 = value;
  }

  public String getImg4() {
    return this.img4;
  }

  public void setImg5(String value) {
    this.img5 = value;
  }

  public String getImg5() {
    return this.img5;
  }

  public void setImg6(String value) {
    this.img6 = value;
  }

  public String getImg6() {
    return this.img6;
  }

  public void setImg7(String value) {
    this.img7 = value;
  }

  public String getImg7() {
    return this.img7;
  }

  public void setImg8(String value) {
    this.img8 = value;
  }

  public String getImg8() {
    return this.img8;
  }

  public void setImg9(String value) {
    this.img9 = value;
  }

  public String getImg9() {
    return this.img9;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setSorting(Integer value) {
    this.sorting = value;
  }

  public Integer getSorting() {
    return this.sorting;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.creatDateString = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    this.setUpdateDateString(StringUtil.dateString(this.updateDate));
  }

  public String getCreatDateString() {
    return creatDateString;
  }

  public void setCreatDateString(String creatDateString) {
    this.creatDateString = creatDateString;
  }

  public String getUpdateDateString() {
    return updateDateString;
  }

  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }
}
