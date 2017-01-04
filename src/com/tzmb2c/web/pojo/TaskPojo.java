package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 任务表 -- task_library
 */
public class TaskPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id; // 专场id
  private String taskTitle; // 任务标题
  private String taskContent; // 任务简介
  private Long taskAge; // 任务年龄
  private Long ability; // 能力
  private Long taskType; // 任务类型
  private Long taskTypeLink; // 次任务类型
  private Long taskScore; // 分值
  private Date effectiveDate; // 生效时间
  private Long is_delete; // 是否删除（0：不删除；1：已删除）
  private Date createDate; // 创建时间
  private String createName; // 创建人
  private Date updateDate; // 更新时间
  private String updateName; // 更新人
  private String taskAgeMessage;
  private String abilityMessage;
  private String taskTypeMessage;
  private String taskTypeLinkMessage;
  private String taskTypeAllMessage;
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Long getIs_delete() {
    return is_delete;
  }

  public void setIs_delete(Long is_delete) {
    this.is_delete = is_delete;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getCreateName() {
    return createName;
  }

  public void setCreateName(String createName) {
    this.createName = createName;
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public String getUpdateName() {
    return updateName;
  }

  public void setUpdateName(String updateName) {
    this.updateName = updateName;
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

  @Override
  public String toString() {
    return "TaskPojo [id=" + id + ", taskTitle=" + taskTitle + ", taskContent=" + taskContent
        + ", taskAge=" + taskAge + ", ability=" + ability + ", taskType=" + taskType
        + ", taskScore=" + taskScore + ", effectiveDate=" + effectiveDate + ", is_delete="
        + is_delete + ", createDate=" + createDate + ", createName=" + createName + ", updateDate="
        + updateDate + ", updateName=" + updateName + "]";
  }

  public Long getTaskTypeLink() {
    return taskTypeLink;
  }

  public void setTaskTypeLink(Long taskTypeLink) {
    this.taskTypeLink = taskTypeLink;
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
