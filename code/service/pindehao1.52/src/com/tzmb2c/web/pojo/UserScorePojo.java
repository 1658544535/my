package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 用户积分表 -- User_score
 * 
 */

public class UserScorePojo extends SuperPojo {
  /**
   * 序列化id
   */
  private static final long serialVersionUID = 1L;
  /**
   * 编号id
   */
  private Long id;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 用户昵称
   */
  private String name;
  /**
   * 总积分
   */
  private Long score;
  /**
   * 摇一摇日期
   */
  private Date shakeDate;
  /**
   * 摇一摇剩余次数
   */
  private Integer shakeNum;
  /**
   * 绑定微信号
   */
  private Integer bunding;
  /**
   * 上传头像
   */
  private Integer upload;
  /**
   * 完善资料
   */
  private Integer improve;
  /**
   * 摇一摇日期
   */
  private String shakeDateStr;
  /**
   * 创建日期
   */
  private String createDateStr;
  /**
   * 更新日期
   */
  private String updateDateStr;
  /**
   * 绑定微信号情况
   */
  private String bundingName;
  /**
   * 上传头像情况
   */
  private String uploadName;
  /**
   * 完善资料情况
   */
  private String improveName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  public Date getShakeDate() {
    return shakeDate;
  }

  public void setShakeDate(Date shakeDate) {
    this.shakeDate = shakeDate;
    if (shakeDate != null) {
      this.shakeDateStr = StringUtil.dateToString(shakeDate);
    }
  }

  public String getShakeDateStr() {
    return shakeDateStr;
  }

  public void setShakeDateStr(String shakeDateStr) {
    this.shakeDateStr = shakeDateStr;
  }

  public Integer getShakeNum() {
    return shakeNum;
  }

  public void setShakeNum(Integer shakeNum) {
    this.shakeNum = shakeNum;
  }

  public Integer getBunding() {
    return bunding;
  }

  public void setBunding(Integer bunding) {
    this.bunding = bunding;
  }

  public Integer getUpload() {
    return upload;
  }

  public void setUpload(Integer upload) {
    this.upload = upload;
  }

  public Integer getImprove() {
    return improve;
  }

  public void setImprove(Integer improve) {
    this.improve = improve;
  }

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    super.updateDate = updateDate;
    if (updateDate != null) {
      this.updateDateStr = StringUtil.dateString(updateDate);
    }
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public String getBundingName() {
    return bundingName;
  }

  public void setBundingName(String bundingName) {
    this.bundingName = bundingName;
  }

  public String getUploadName() {
    return uploadName;
  }

  public void setUploadName(String uploadName) {
    this.uploadName = uploadName;
  }

  public String getImproveName() {
    return improveName;
  }

  public void setImproveName(String improveName) {
    this.improveName = improveName;
  }


}
