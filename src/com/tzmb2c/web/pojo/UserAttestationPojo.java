package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 2014-12-24
 * 
 * @author creazylee 用户认证
 */

public class UserAttestationPojo extends SuperPojo {

  private Long userId;// 用户ID
  private String userName;// 用户昵称
  private Integer type;// 认证类型(取业务字典：1 采购商 2 供应商)
  private String attestationImage;// 认证营业执照
  private String user3cImage;// 3c认证执照
  private Integer status;// 显示状态(取业务字典：0未审核1已审核)
  private String statusName;// 显示状态字符
  private Date createDate;// 创建日期
  private String createDateStr;// 创建日期(字符串)

  public String getUser3cImage() {
    return user3cImage;
  }

  public void setUser3cImage(String user3cImage) {
    this.user3cImage = user3cImage;
  }

  public Long getUser_id(Long userId) {
    return userId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getAttestationImage() {
    return attestationImage;
  }

  public void setAttestationImage(String attestationImage) {
    this.attestationImage = attestationImage;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    this.createDateStr = StringUtil.dateToString(this.createDate);
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

}
