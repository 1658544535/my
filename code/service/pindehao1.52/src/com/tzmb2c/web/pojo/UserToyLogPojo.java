package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * TODO<游戏工厂玩具生成记录>
 * 
 * @author linyuting
 */

public class UserToyLogPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Long userId;// 用户id
  private String username;// 收件人
  private String toyName;// 玩具名
  private Long toyId;// 玩具id
  private String address;// 送货地址
  private String telphone;// 联系方式
  private String expressNo;// 物流号码
  private String expressName;// 物流名称
  private Date createDate;// 合成时间
  private Long createBy;// 创建人
  private Long updateBy;// 修改人
  private Date updateDate;// 修改时间
  private String createDateStr;
  private String updateDateStr;
  private String beginday;// 开始时间
  private String endday;// 结束时间
  private String toyImages;// 玩具图片
  private String loginName;// 用户名
  private String loginType;// 登录用户



  public String getLoginType() {
    return loginType;
  }

  public void setLoginType(String loginType) {
    this.loginType = loginType;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getBeginday() {
    return beginday;
  }

  public void setBeginday(String beginday) {
    this.beginday = beginday;
  }

  public String getEndday() {
    return endday;
  }

  public void setEndday(String endday) {
    this.endday = endday;
  }

  public String getToyName() {
    return toyName;
  }

  public void setToyName(String toyName) {
    this.toyName = toyName;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getToyId() {
    return toyId;
  }

  public void setToyId(Long toyId) {
    this.toyId = toyId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTelphone() {
    return telphone;
  }

  public void setTelphone(String telphone) {
    this.telphone = telphone;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (createDate != null) {
      this.setCreateDateStr(StringUtil.dateString(createDate));
    }
  }

  @Override
  public Long getCreateBy() {
    return createBy;
  }

  @Override
  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  @Override
  public Long getUpdateBy() {
    return updateBy;
  }

  @Override
  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    if (createDate != null) {
      this.setUpdateDateStr(StringUtil.dateString(updateDate));
    }
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public String getToyImages() {
    return toyImages;
  }

  public void setToyImages(String toyImages) {
    this.toyImages = toyImages;
  }

}
