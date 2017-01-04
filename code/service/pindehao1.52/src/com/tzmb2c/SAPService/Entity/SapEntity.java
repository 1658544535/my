package com.tzmb2c.SAPService.Entity;

public class SapEntity {

  private String bizCode = "";
  private String bizName = "";
  private String timeTamp = "";


  private String fromCode = "";
  private String content = "";
  private String scount = "";
  private String userPort = "";
  private String[] mobile = null;


  public String getBizCode() {
    return bizCode;
  }

  public void setBizCode(String bizCode) {
    this.bizCode = bizCode;
  }

  public String getBizName() {
    return bizName;
  }

  public void setBizName(String bizName) {
    this.bizName = bizName;
  }

  public String getTimeTamp() {
    return timeTamp;
  }

  public void setTimeTamp(String timeTamp) {
    this.timeTamp = timeTamp;
  }

  public String getFromCode() {
    return fromCode;
  }

  public void setFromCode(String fromCode) {
    this.fromCode = fromCode;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getScount() {
    return scount;
  }

  public void setScount(String scount) {
    this.scount = scount;
  }

  public String getUserPort() {
    return userPort;
  }

  public void setUserPort(String userPort) {
    this.userPort = userPort;
  }

  public String[] getMobile() {
    return mobile;
  }

  public void setMobile(String[] mobile) {
    this.mobile = mobile;
  }


}
