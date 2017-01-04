package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 少儿频道表 -- children_channel_history
 * 
 */

public class ChildrenChannelHistoryPojo extends SuperPojo {

  private Long id; // 编号id
  private Long userId; // 用户id
  private Integer type; // 浏览类型:1频道2专场3商品
  private String typeName; // 用户名
  private Long businessId; // 业务id(浏览类型为1则取频道id,为2则取专场id,为3则取商品id)
  private Integer hid; // 浏览次数
  private Long activityId; // 活动id
  private Date createDate; // 创建时间
  private String createDateStr;
  private Long createBy; // 创建者
  private Date updateDate; // 更新时间
  private String updateDateStr;
  private Long updateBy; // 更新者
  private String userName; // 用户名
  private Long paixu; // 用来排序
  private String businessName; // 浏览类型名
  private String businessNames;// 浏览类型名2

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
    createDateStr = StringUtil.dateString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    updateDateStr = StringUtil.dateString(this.updateDate);
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Long businessId) {
    this.businessId = businessId;
  }

  public Integer getHid() {
    return hid;
  }

  public void setHid(Integer hid) {
    this.hid = hid;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  @Override
  public Long getCreateBy() {
    return createBy;
  }

  @Override
  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  @Override
  public Long getUpdateBy() {
    return updateBy;
  }

  @Override
  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Long getPaixu() {
    return paixu;
  }

  public void setPaixu(Long paixu) {
    this.paixu = paixu;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getBusinessNames() {
    return businessNames;
  }

  public void setBusinessNames(String businessNames) {
    this.businessNames = businessNames;
  }


}
