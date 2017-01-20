/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 拼得客-用户信息表; InnoDB free: 59392 kB
 * 
 * @version 1.0
 * @author
 */
public class UserPindekeInfoPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 真实姓名
   */
  private String name;
  /**
   * 手机号码
   */
  private String phone;
  /**
   * 身份证号码
   */
  private String cardNo;
  /**
   * 推广渠道
   */
  private String extendChannel;
  /**
   * 推广证明（图片1）
   */
  private String extendImg1;
  /**
   * 推广证明（图片2）
   */
  private String extendImg2;
  /**
   * 推广证明（图片3）
   */
  private String extendImg3;
  /**
   * 推广证明（图片4）
   */
  private String extendImg4;
  /**
   * 推广证明（图片5）
   */
  private String extendImg5;
  /**
   * 退回原因
   */
  private String returnMsg;
  /**
   * 返佣金额
   */
  private Double rebatePrice;
  /**
   * 提现金额
   */
  private Double withdrawPrice;
  /**
   * 剩余金额
   */
  private Double surpluPrice;
  /**
   * 删除标识（0-否1-是）
   */
  private Integer isDelete;
  /**
   * 审核状态（0-审核中1-审核通过2-审核不通过3-冻结）
   */
  private Integer status;

  private Date createDate;// 创建时间
  private String creatDateString;
  private Date updateDate; // 更新时间
  private String updateDateString;
  private String createByName; // 创建者
  private String updateByName; // 更新者
  private String pindekeDateStr; // 查询申请时间，开始时间
  private String pindekeDateEndStr; // 查询申请时间，结束时间
  private String loginname; // 用户账号
  /**
   * 返佣金额
   */
  private String rebatePriceStr;
  /**
   * 提现金额
   */
  private String withdrawPriceStr;
  /**
   * 剩余金额
   */
  private String surpluPriceStr;
  /**
   * 冻结金额
   */
  private Double freezingPrice;
  /**
   * 添加冻结金额
   */
  private Double freezingPriceAdd;
  /**
   * 减去冻结金额
   */
  private Double freezingPriceMinus;
  /**
   * 添加剩余金额
   */
  private Double SurpluPriceAdd;
  /**
   * 减去剩余金额
   */
  private Double SurpluPriceMinus;
  /**
   * 添加返佣总金额
   */
  private Double rebatePriceAdd;
  private String invitationCode;
  private String inviterName;
  private String pindekeNumber;// 拼得客编号
  private Integer grouponNum;
  private Integer grouponSuccessNum;

  public String getInviterName() {
    return inviterName;
  }

  public void setInviterName(String inviterName) {
    this.inviterName = inviterName;
  }

  public String getInvitationCode() {
    return invitationCode;
  }

  public void setInvitationCode(String invitationCode) {
    this.invitationCode = invitationCode;
  }

  public Double getRebatePriceAdd() {
    return rebatePriceAdd;
  }

  public void setRebatePriceAdd(Double rebatePriceAdd) {
    this.rebatePriceAdd = rebatePriceAdd;
  }

  public Double getFreezingPriceMinus() {
    return freezingPriceMinus;
  }

  public void setFreezingPriceMinus(Double freezingPriceMinus) {
    this.freezingPriceMinus = freezingPriceMinus;
  }

  public Double getSurpluPriceAdd() {
    return SurpluPriceAdd;
  }

  public void setSurpluPriceAdd(Double surpluPriceAdd) {
    SurpluPriceAdd = surpluPriceAdd;
  }

  public Double getSurpluPriceMinus() {
    return SurpluPriceMinus;
  }

  public void setSurpluPriceMinus(Double surpluPriceMinus) {
    SurpluPriceMinus = surpluPriceMinus;
  }

  public Double getFreezingPriceAdd() {
    return freezingPriceAdd;
  }

  public void setFreezingPriceAdd(Double freezingPriceAdd) {
    this.freezingPriceAdd = freezingPriceAdd;
  }

  public Double getFreezingPrice() {
    return freezingPrice;
  }

  public void setFreezingPrice(Double freezingPrice) {
    this.freezingPrice = freezingPrice;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getPindekeDateStr() {
    return pindekeDateStr;
  }

  public void setPindekeDateStr(String pindekeDateStr) {
    this.pindekeDateStr = pindekeDateStr;
  }

  public String getPindekeDateEndStr() {
    return pindekeDateEndStr;
  }

  public void setPindekeDateEndStr(String pindekeDateEndStr) {
    this.pindekeDateEndStr = pindekeDateEndStr;
  }

  public String getRebatePriceStr() {
    return rebatePriceStr;
  }

  public void setRebatePriceStr(String rebatePriceStr) {
    this.rebatePriceStr = rebatePriceStr;
  }

  public String getWithdrawPriceStr() {
    return withdrawPriceStr;
  }

  public void setWithdrawPriceStr(String withdrawPriceStr) {
    this.withdrawPriceStr = withdrawPriceStr;
  }

  public String getSurpluPriceStr() {
    return surpluPriceStr;
  }

  public void setSurpluPriceStr(String surpluPriceStr) {
    this.surpluPriceStr = surpluPriceStr;
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setUserId(Long value) {
    userId = value;
  }

  public Long getUserId() {
    return userId;
  }

  public void setName(String value) {
    name = value;
  }

  public String getName() {
    return name;
  }

  public void setPhone(String value) {
    phone = value;
  }

  public String getPhone() {
    return phone;
  }

  public void setCardNo(String value) {
    cardNo = value;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setExtendChannel(String value) {
    extendChannel = value;
  }

  public String getExtendChannel() {
    return extendChannel;
  }

  public void setExtendImg1(String value) {
    extendImg1 = value;
  }

  public String getExtendImg1() {
    return extendImg1;
  }

  public void setExtendImg2(String value) {
    extendImg2 = value;
  }

  public String getExtendImg2() {
    return extendImg2;
  }

  public void setExtendImg3(String value) {
    extendImg3 = value;
  }

  public String getExtendImg3() {
    return extendImg3;
  }

  public void setExtendImg4(String value) {
    extendImg4 = value;
  }

  public String getExtendImg4() {
    return extendImg4;
  }

  public void setExtendImg5(String value) {
    extendImg5 = value;
  }

  public String getExtendImg5() {
    return extendImg5;
  }

  public void setReturnMsg(String value) {
    returnMsg = value;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setRebatePrice(Double value) {
    rebatePrice = value;
  }

  public Double getRebatePrice() {
    return rebatePrice;
  }

  public void setWithdrawPrice(Double value) {
    withdrawPrice = value;
  }

  public Double getWithdrawPrice() {
    return withdrawPrice;
  }

  public void setSurpluPrice(Double value) {
    surpluPrice = value;
  }

  public Double getSurpluPrice() {
    return surpluPrice;
  }

  public void setIsDelete(Integer value) {
    isDelete = value;
  }

  public Integer getIsDelete() {
    return isDelete;
  }

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    creatDateString = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    setUpdateDateString(StringUtil.dateString(this.updateDate));
  }

  public String getCreatDateString() {
    return creatDateString;
  }

  public void setCreatDateString(String creatDateString) {
    this.creatDateString = creatDateString;
  }

  public String getUpdateDateString() {
    return updateDateString;
  }

  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }

  public String getCreateByName() {
    return createByName;
  }

  public void setCreateByName(String createByName) {
    this.createByName = createByName;
  }

  public String getUpdateByName() {
    return updateByName;
  }

  public void setUpdateByName(String updateByName) {
    this.updateByName = updateByName;
  }

  public String getPindekeNumber() {
    return pindekeNumber;
  }

  public void setPindekeNumber(String pindekeNumber) {
    this.pindekeNumber = pindekeNumber;
  }

  public Integer getGrouponNum() {
    return grouponNum;
  }

  public void setGrouponNum(Integer grouponNum) {
    this.grouponNum = grouponNum;
  }

  public Integer getGrouponSuccessNum() {
    return grouponSuccessNum;
  }

  public void setGrouponSuccessNum(Integer grouponSuccessNum) {
    this.grouponSuccessNum = grouponSuccessNum;
  }
}
