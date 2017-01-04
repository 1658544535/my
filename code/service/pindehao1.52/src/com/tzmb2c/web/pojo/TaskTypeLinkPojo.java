package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 任务类型连接表 -- task_type_link
 */
public class TaskTypeLinkPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id; // id
  private Long taskTypeId; // 年龄id
  private Long taskTypeLinkId; // 能力id
  private String taskTypeLinkName; // 相应的能力名称
  private Long taskTypeLinkValue; // 相应能力的字典值

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTaskTypeId() {
    return taskTypeId;
  }

  public void setTaskTypeId(Long taskTypeId) {
    this.taskTypeId = taskTypeId;
  }

  public Long getTaskTypeLinkId() {
    return taskTypeLinkId;
  }

  public void setTaskTypeLinkId(Long taskTypeLinkId) {
    this.taskTypeLinkId = taskTypeLinkId;
  }

  public String getTaskTypeLinkName() {
    return taskTypeLinkName;
  }

  public void setTaskTypeLinkName(String taskTypeLinkName) {
    this.taskTypeLinkName = taskTypeLinkName;
  }

  public Long getTaskTypeLinkValue() {
    return taskTypeLinkValue;
  }

  public void setTaskTypeLinkValue(Long taskTypeLinkValue) {
    this.taskTypeLinkValue = taskTypeLinkValue;
  }



}
