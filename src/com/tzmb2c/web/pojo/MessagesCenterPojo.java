package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 消息中心表--message_center by zzh on 2016/1/7
 */
public class MessagesCenterPojo extends SuperPojo {
  private Long id; // 序号id
  private Long userId; // 用户id
  private String message; // 内容
  private Long isRead; // 已读标志
  private Long isDelete; // 删除标志
  private Long status; // 审核状态
  private String loginName; // 用户账户名
  private String isReadName; // 已读标志名称
  private String isDeleteName; // 删除标志名称
  private String statusName; // 审核状态名称
  private String beginTimeStr; // 检索开始时间
  private String endTimeStr; // 检索结束时间
  private String createDateStr;// 创建时间STR
  private Long paixu; // 排序标志（等于1-按id倒序;等于2-按sorting倒序、create_date倒序）
  private Integer sorting; // 排序

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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getIsRead() {
    return isRead;
  }

  public void setIsRead(Long isRead) {
    this.isRead = isRead;
  }

  public Long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Long isDelete) {
    this.isDelete = isDelete;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getIsReadName() {
    return isReadName;
  }

  public void setIsReadName(String isReadName) {
    this.isReadName = isReadName;
  }

  public String getIsDeleteName() {
    return isDeleteName;
  }

  public void setIsDeleteName(String isDeleteName) {
    this.isDeleteName = isDeleteName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getBeginTimeStr() {
    return beginTimeStr;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public String getEndTimeStr() {
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  public Long getPaixu() {
    return paixu;
  }

  public void setPaixu(Long paixu) {
    this.paixu = paixu;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }
}
