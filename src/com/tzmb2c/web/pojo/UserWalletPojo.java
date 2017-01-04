package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 用户钱包-- user_wallet by zzh
 * 
 */
public class UserWalletPojo extends SuperPojo {
  private Long id; // 序号id
  private Long userId; // 会员id
  private String loginName; // 用户账号
  private Double balance; // 用户余额
  private Double totalBalance; // 钱包总额
  private String invitationCode;// 分享邀请码
  private Long paixu; // 排序
  private Long blackFlag; // 嫌疑对象
  private String blackFlagName; // 嫌疑对象名
  private String userName; // 用户昵称
  private Long versions;// 用于并发同步

  public Long getVersions() {
    return versions;
  }

  public void setVersions(Long versions) {
    this.versions = versions;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

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

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Double getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(Double totalBalance) {
    this.totalBalance = totalBalance;
  }

  public String getInvitationCode() {
    return invitationCode;
  }

  public void setInvitationCode(String invitationCode) {
    this.invitationCode = invitationCode;
  }

  public Long getPaixu() {
    return paixu;
  }

  public void setPaixu(Long paixu) {
    this.paixu = paixu;
  }

  public Long getBlackFlag() {
    return blackFlag;
  }

  public void setBlackFlag(Long blackFlag) {
    this.blackFlag = blackFlag;
  }

  public String getBlackFlagName() {
    return blackFlagName;
  }

  public void setBlackFlagName(String blackFlagName) {
    this.blackFlagName = blackFlagName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
