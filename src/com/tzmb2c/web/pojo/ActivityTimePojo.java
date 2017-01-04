package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 活动时间表 -- Activity_Time
 * 
 */

public class ActivityTimePojo extends SuperPojo {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private Long id;// 编号id
  private String title;// 标题
  private String beginTime;// 活动开始时间
  private String endTime;// 活动结束时间
  private Date createDate;
  private Date updateDate;
  private String createDateStr;
  private String updateDateStr;
  private String activityTime;// 开始时间到结束时间
  private Date activityDate;// 活动日期
  private String activityDateStr;// 活动日期
  private Integer channel;// 渠道，1-app；2-微商城
  private String banner;// 标题图片名
  private Integer type;// 活动类型
  private String typeName;// 活动类型名称
  private String isdelete;// 判断是否删除
  private Long productId;// 产品id(getActivityTimeList方法专用)
  private Long specialStatus; // 状态(取业务字典：1-待审核，2-待排期，3-退回修改，4-排期完成)


  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public String getActivityDateStr() {
    return activityDateStr;
  }

  public void setActivityDateStr(String activityDateStr) {
    this.activityDateStr = activityDateStr;
  }

  public Date getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(Date activityDate) {
    this.activityDate = activityDate;
    if (activityDate != null) {
      activityDateStr = StringUtil.dateToString(this.activityDate);
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
    createDateStr = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    updateDateStr = StringUtil.dateToString(this.updateDate);
  }

  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getActivityTime() {
    return activityTime;
  }

  public void setActivityTime(String activityTime) {
    this.activityTime = activityTime;
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

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getIsdelete() {
    return isdelete;
  }

  public void setIsdelete(String isdelete) {
    this.isdelete = isdelete;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getSpecialStatus() {
    return specialStatus;
  }

  public void setSpecialStatus(Long specialStatus) {
    this.specialStatus = specialStatus;
  }
}
