package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 弹窗-- popup by zzh
 * 
 */
public class PopupPojo extends SuperPojo {
  private Long id; // 序号id
  private Date startTime; // 开始时间
  private Date endTime; // 结束时间
  private String popupPic; // 弹窗图片
  private String parameter; // 参数值
  private Integer parameterSize; // 参数类型
  private Integer popupSize; // 弹窗类型
  private Integer status; // 状态
  private String startTimeStr; // 开始时间STR
  private String endTimeStr; // 结束时间STR
  private String parameterSizeName;// 参数类型名
  private String popupSizeName; // 弹窗类型名
  private String statusName; // 审核状态名
  private Integer option;
  private String title; // 标题

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
    if (startTime != null) {
      this.startTimeStr = StringUtil.dateString(this.startTime);
    }
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
    if (endTime != null) {
      this.endTimeStr = StringUtil.dateString(this.endTime);
    }
  }

  public String getPopupPic() {
    return popupPic;
  }

  public void setPopupPic(String popupPic) {
    this.popupPic = popupPic;
  }

  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  public Integer getParameterSize() {
    return parameterSize;
  }

  public void setParameterSize(Integer parameterSize) {
    this.parameterSize = parameterSize;
  }

  public Integer getPopupSize() {
    return popupSize;
  }

  public void setPopupSize(Integer popupSize) {
    this.popupSize = popupSize;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getStartTimeStr() {
    return startTimeStr;
  }

  public void setStartTimeStr(String startTimeStr) {
    this.startTimeStr = startTimeStr;
  }

  public String getEndTimeStr() {
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public String getParameterSizeName() {
    return parameterSizeName;
  }

  public void setParameterSizeName(String parameterSizeName) {
    this.parameterSizeName = parameterSizeName;
  }

  public String getPopupSizeName() {
    return popupSizeName;
  }

  public void setPopupSizeName(String popupSizeName) {
    this.popupSizeName = popupSizeName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Integer getOption() {
    return option;
  }

  public void setOption(Integer option) {
    this.option = option;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
