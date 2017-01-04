package com.tzmb2c.SAPService.Entity;

public class DirectSendDTO {
  private int userId;
  private String account;
  private String passwrod;
  private String phones;
  private String content;
  private String sendTime;
  private int sendType;
  private String postFixNumber;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPasswrod() {
    return passwrod;
  }

  public void setPasswrod(String passwrod) {
    this.passwrod = passwrod;
  }

  public String getPhones() {
    return phones;
  }

  public void setPhones(String phones) {
    this.phones = phones;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSendTime() {
    return sendTime;
  }

  public void setSendTime(String sendTime) {
    this.sendTime = sendTime;
  }

  public int getSendType() {
    return sendType;
  }

  public void setSendType(int sendType) {
    this.sendType = sendType;
  }

  public String getPostFixNumber() {
    return postFixNumber;
  }

  public void setPostFixNumber(String postFixNumber) {
    this.postFixNumber = postFixNumber;
  }

}
