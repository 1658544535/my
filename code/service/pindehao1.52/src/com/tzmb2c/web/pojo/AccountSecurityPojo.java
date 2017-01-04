package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 2014-12-15
 * 
 * @author creazylee 账户安全
 */

public class AccountSecurityPojo extends SuperPojo {

  private Long userId;// 用户ID
  private Integer type;// 认证类型(取业务字典：1 采购商 2 供应商)
  private String quesion;// 问题
  private String answer;// 答案
  private Integer status;// 显示状态(取业务字典：0未审核1已审核)
  private String statusName;// 显示状态字符
  private Date createDate;// 创建时间
  private String createDateStr;// 创建时间(字符串)
  private String userName;// 用户昵称

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getQuesion() {
    return quesion;
  }

  public void setQuesion(String quesion) {
    this.quesion = quesion;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}
