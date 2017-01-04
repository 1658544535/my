package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 专题收藏表 -- user_special_collect
 * 
 */

public class UserSpecialCollectPojo extends SuperPojo {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  /**
   * ID
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 专场ID
   */
  private Long specialId;
  /**
   * 专场名称
   */
  private String specialName;
  /**
   * 专场海报
   */
  private String banner;
  /**
   * 专场活动ID
   */
  private Long activityId;
  /**
   * 专场活动开始时间
   */
  private Date beginTime;
  /**
   * 专场活动结束时间
   */
  private Date endTime;

  private String createDateStr;
  private String updateDateStr;

  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public String getBanner() {
    return banner;
  }

  public void setBanner(String banner) {
    this.banner = banner;
  }

  public String getSpecialName() {
    return specialName;
  }

  public void setSpecialName(String specialName) {
    this.specialName = specialName;
  }

  public Long getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Long specialId) {
    this.specialId = specialId;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (this.createDate != null) {
      createDateStr = StringUtil.dateToString(this.createDate);
    }
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    if (this.updateDate != null) {
      updateDateStr = StringUtil.dateToString(this.updateDate);
    }
  }
}
