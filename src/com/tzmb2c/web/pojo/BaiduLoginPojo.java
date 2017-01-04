package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * baidu登陆记录表--baidu_login
 * 
 */
public class BaiduLoginPojo extends SuperPojo {
  /**
   * 序列化id
   */
  private Long id;// id
  private Long userId;// userid
  private Date loginTime;// 登陆时间
  private String loginName;// 登陆昵称
  private String baiduId;// 百度ID
  private String loginTimeStr;// 登陆时间STR
  private String updateDateStr;// 更新日期
  private String loginTimeStartStr;
  private String loginTimeEndStr;
  private String loginDate;// 登陆日期
  private Integer type;// 来源：1-注册2-登录
  private String typeName;// 来源名称

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
    if (loginTime != null) {
      this.loginTimeStr = StringUtil.dateString(this.loginTime);
    }
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getBaiduId() {
    return baiduId;
  }

  public void setBaiduId(String baiduId) {
    this.baiduId = baiduId;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    super.updateDate = updateDate;
    if (updateDate != null) {
      this.updateDateStr = StringUtil.dateString(updateDate);
    }
  }

  public String getLoginTimeStr() {
    return loginTimeStr;
  }

  public void setLoginTimeStr(String loginTimeStr) {
    this.loginTimeStr = loginTimeStr;
  }

  public String getLoginTimeStartStr() {
    return loginTimeStartStr;
  }

  public void setLoginTimeStartStr(String loginTimeStartStr) {
    this.loginTimeStartStr = loginTimeStartStr;
  }

  public String getLoginTimeEndStr() {
    return loginTimeEndStr;
  }

  public void setLoginTimeEndStr(String loginTimeEndStr) {
    this.loginTimeEndStr = loginTimeEndStr;
  }

  public String getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(String loginDate) {
    this.loginDate = loginDate;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
}
