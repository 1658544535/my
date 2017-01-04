package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * brand_dic表
 */
public class TaskSettingPojo extends SuperPojo {
  private Long id; // 序号id
  private Long taskId; // 标签名
  private Long taskAgeType; // 标签审核状态
  private Long isDelete;
  private Date taskDate;
  private String taskAgeTypeName;
  private String taskDateStr;
  private String taskTitle;
  private String taskContent;
  private Long taskAge;
  private Long ability;
  private Long taskType;
  private Long taskScore;
  private String taskAgeMessage;
  private String abilityMessage;
  private String taskTypeMessage;
  private String taskTypeLinkMessage;
  private String taskTypeAllMessage;
  private Integer taskStatus;
  private Date startTime;
  private Long userTaskId;

  
  
  
  
  public Long getUserTaskId() {
    return userTaskId;
  }

  public void setUserTaskId(Long userTaskId) {
    this.userTaskId = userTaskId;
  }

  public Integer getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(Integer taskStatus) {
    this.taskStatus = taskStatus;
  }

  public Date getStartTime() {
    return startTime;
  }
  
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public Long getTaskAgeType() {
    return taskAgeType;
  }

  public void setTaskAgeType(Long taskAgeType) {
    this.taskAgeType = taskAgeType;
  }

  public Long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Long isDelete) {
    this.isDelete = isDelete;
  }

  public String getTaskAgeTypeName() {
    return taskAgeTypeName;
  }

  public void setTaskAgeTypeName(String taskAgeTypeName) {
    this.taskAgeTypeName = taskAgeTypeName;
  }


  public Date getTaskDate() {
    return taskDate;
  }

  public void setTaskDate(Date taskDate) {
    this.taskDate = taskDate;
    if (taskDate != null) {
      this.taskDateStr = StringUtil.dateToString(taskDate);
    }
  }

  public String getTaskDateStr() {
    return taskDateStr;
  }

  public void setTaskDateStr(String taskDateStr) {
    this.taskDateStr = taskDateStr;
  }

  public String getTaskTitle() {
    return taskTitle;
  }

  public void setTaskTitle(String taskTitle) {
    this.taskTitle = taskTitle;
  }

  public String getTaskContent() {
    return taskContent;
  }

  public void setTaskContent(String taskContent) {
    this.taskContent = taskContent;
  }

  public Long getTaskAge() {
    return taskAge;
  }

  public void setTaskAge(Long taskAge) {
    this.taskAge = taskAge;
  }

  public Long getAbility() {
    return ability;
  }

  public void setAbility(Long ability) {
    this.ability = ability;
  }

  public Long getTaskType() {
    return taskType;
  }

  public void setTaskType(Long taskType) {
    this.taskType = taskType;
  }

  public Long getTaskScore() {
    return taskScore;
  }

  public void setTaskScore(Long taskScore) {
    this.taskScore = taskScore;
  }

  public String getTaskAgeMessage() {
    return taskAgeMessage;
  }

  public void setTaskAgeMessage(String taskAgeMessage) {
    this.taskAgeMessage = taskAgeMessage;
  }

  public String getAbilityMessage() {
    return abilityMessage;
  }

  public void setAbilityMessage(String abilityMessage) {
    this.abilityMessage = abilityMessage;
  }

  public String getTaskTypeMessage() {
    return taskTypeMessage;
  }

  public void setTaskTypeMessage(String taskTypeMessage) {
    this.taskTypeMessage = taskTypeMessage;
  }

  public String getTaskTypeLinkMessage() {
    return taskTypeLinkMessage;
  }

  public void setTaskTypeLinkMessage(String taskTypeLinkMessage) {
    this.taskTypeLinkMessage = taskTypeLinkMessage;
  }

  public String getTaskTypeAllMessage() {
    return taskTypeAllMessage;
  }

  public void setTaskTypeAllMessage(String taskTypeAllMessage) {
    this.taskTypeAllMessage = taskTypeAllMessage;
  }


}
