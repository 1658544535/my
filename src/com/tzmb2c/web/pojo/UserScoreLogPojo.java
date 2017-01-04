package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 用户积分流水表 -- User_score_log
 * 
 */

public class UserScoreLogPojo extends SuperPojo {
  /**
   * 序列化id
   */
  private static final long serialVersionUID = 1L;
  /**
   * 编号id
   */
  private Long id;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 用户昵称
   */
  private String name;
  /**
   * 上一次积分
   */
  private Long curScore;
  /**
   * 交易积分
   */
  private Long tradeScore;
  /**
   * 交易来源
   */
  private Integer tradeSource;
  /**
   * 备注
   */
  private String remark;
  /**
   * 创建日期
   */
  private String createDateStr;
  /**
   * 更新日期
   */
  private String tradeSourceName;
  /**
   * 积分来源名
   */
  private String begandayStr;
  /**
   * 开始日期
   */
  private String enddayStr;
  /**
   * 结束日期
   */
  private String updateDateStr;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getCurScore() {
    return curScore;
  }

  public void setCurScore(Long curScore) {
    this.curScore = curScore;
  }

  public Long getTradeScore() {
    return tradeScore;
  }

  public void setTradeScore(Long tradeScore) {
    this.tradeScore = tradeScore;
  }

  public Integer getTradeSource() {
    return tradeSource;
  }

  public void setTradeSource(Integer tradeSource) {
    this.tradeSource = tradeSource;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    super.updateDate = updateDate;
    if (updateDate != null) {
      this.updateDateStr = StringUtil.dateString(updateDate);
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

  public String getTradeSourceName() {
    return tradeSourceName;
  }

  public void setTradeSourceName(String tradeSourceName) {
    this.tradeSourceName = tradeSourceName;
  }

  public String getBegandayStr() {
    return begandayStr;
  }

  public void setBegandayStr(String begandayStr) {
    this.begandayStr = begandayStr;
  }

  public String getEnddayStr() {
    return enddayStr;
  }

  public void setEnddayStr(String enddayStr) {
    this.enddayStr = enddayStr;
  }
}
