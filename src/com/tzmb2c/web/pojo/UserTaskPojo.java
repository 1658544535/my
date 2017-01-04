/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 用户任务表
 * 
 * @version 1.0
 * @author
 */
public class UserTaskPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 任务ID
   */
  private Long taskSettingId;
  /**
   * 日期
   */
  private Date taskDate;
  /**
   * 状态（0未完成,，1已完成）
   */
  private Integer status;
  /**
   * 开始时间
   */
  private Date startTime;
  /**
   * 完成时间
   */
  private Date endTime;
  /**
   * 任务标题
   */
  private String taskTitle;
  /**
   * 任务简介
   */
  private String taskContent;
  /**
   * library用户年龄
   */
  private String taskAge;
  /**
   * library用户能力
   */
  private String ability;
  /**
   * 任务类型
   */
  private Integer taskType;
  /**
   * library次任务类型
   */
  private String taskTypeLink;
  /**
   * library分值
   */
  private Integer taskScore;


  public Integer getTaskType() {
    return taskType;
  }

  public void setTaskType(Integer taskType) {
    this.taskType = taskType;
  }

  public Integer getTaskScore() {
    return taskScore;
  }

  public void setTaskScore(Integer taskScore) {
    this.taskScore = taskScore;
  }

  public String getTaskTypeLink() {
    return taskTypeLink;
  }

  public void setTaskTypeLink(String taskTypeLink) {
    this.taskTypeLink = taskTypeLink;
  }

  public String getTaskAge() {
    return taskAge;
  }

  public void setTaskAge(String taskAge) {
    this.taskAge = taskAge;
  }

  public String getAbility() {
    return ability;
  }

  public void setAbility(String ability) {
    this.ability = ability;
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

  public void setTaskSettingId(Long value) {
    this.taskSettingId = value;
  }

  public Long getTaskSettingId() {
    return this.taskSettingId;
  }

  public void setTaskDate(Date value) {
    this.taskDate = value;
  }

  public Date getTaskDate() {
    return this.taskDate;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStartTime(Date value) {
    this.startTime = value;
  }

  public Date getStartTime() {
    return this.startTime;
  }

  public void setEndTime(Date value) {
    this.endTime = value;
  }

  public Date getEndTime() {
    return this.endTime;
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
}
