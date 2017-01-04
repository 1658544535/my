package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class LotteryForwardPojo extends SuperPojo {

  private Long id;// 转发记录id
  private Long userId;// 会员id
  private Integer time;// 转发时间

  private String timeDStr;// 转发时间

  private String userName;// 会员名称

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

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public String getTimeDStr() {
    return timeDStr;
  }

  public void setTimeDStr(String timeDStr) {
    this.timeDStr = timeDStr;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
