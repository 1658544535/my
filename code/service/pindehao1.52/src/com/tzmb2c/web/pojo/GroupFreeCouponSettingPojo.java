/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.utils.StringUtil;

/**
 * 团免券领取设置; InnoDB free: 149504 kB
 * 
 * @version 1.0
 * @author
 */
public class GroupFreeCouponSettingPojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 失效时间
   */
  private Date invalidTime;
  /**
   * 可领取人数
   */
  private Integer num;
  /**
   * 剩余人数
   */
  private Integer surplusNum;
  /**
   * 状态，0-未审核1-审核通过
   */
  private Integer status;
  private String invalidTimeStr;
  private Integer SurplusNumM;
  private Integer surplusToys;

  public Integer getSurplusToys() {
    return surplusToys;
  }

  public void setSurplusToys(Integer surplusToys) {
    this.surplusToys = surplusToys;
  }

  public String getUrl() {
    return ConstParam.WX_URL + "/nbshare.php?id=" + StringUtil.checkVal(id) + "&n="
        + StringUtil.checkVal(surplusToys);
  }

  public void setUrl(String url) {}

  public Integer getSurplusNumM() {
    return SurplusNumM;
  }

  public void setSurplusNumM(Integer surplusNumM) {
    SurplusNumM = surplusNumM;
  }

  public String getInvalidTimeStr() {
    if (this.invalidTime != null) {
      return StringUtil.dateString(this.invalidTime);
    }
    return invalidTimeStr;
  }

  public void setInvalidTimeStr(String invalidTimeStr) {
    this.invalidTimeStr = invalidTimeStr;
  }

  public void setId(Long value) {
    this.id = value;
  }

  public Long getId() {
    return this.id;
  }

  public void setInvalidTime(Date value) {
    this.invalidTime = value;
  }

  public Date getInvalidTime() {
    return this.invalidTime;
  }

  public void setNum(Integer value) {
    this.num = value;
  }

  public Integer getNum() {
    return this.num;
  }

  public void setSurplusNum(Integer value) {
    this.surplusNum = value;
  }

  public Integer getSurplusNum() {
    return this.surplusNum;
  }

  public void setStatus(Integer value) {
    this.status = value;
  }

  public Integer getStatus() {
    return this.status;
  }
}
