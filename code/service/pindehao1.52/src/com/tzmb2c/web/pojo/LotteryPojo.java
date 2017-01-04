package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class LotteryPojo extends SuperPojo {

  private Long lotteryId;// 抽奖id
  private String subject;// 主题
  private Integer status;// 状态，0禁用，1启用
  private Long startTime;// 开始时间
  private Long endTime;// 结束时间

  private String statusName;// 状态名称
  private String startTimeDStr;// 开始时间
  private String endTimeDStr;// 结束时间

  public Long getLotteryId() {
    return lotteryId;
  }

  public void setLotteryId(Long lotteryId) {
    this.lotteryId = lotteryId;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getStartTime() {
    return startTime;
  }

  public void setStartTime(Long startTime) {
    this.startTime = startTime;
  }

  public Long getEndTime() {
    return endTime;
  }

  public void setEndTime(Long endTime) {
    this.endTime = endTime;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getStartTimeDStr() {
    return startTimeDStr;
  }

  public void setStartTimeDStr(String startTimeDStr) {
    this.startTimeDStr = startTimeDStr;
  }

  public String getEndTimeDStr() {
    return endTimeDStr;
  }

  public void setEndTimeDStr(String endTimeDStr) {
    this.endTimeDStr = endTimeDStr;
  }
}
