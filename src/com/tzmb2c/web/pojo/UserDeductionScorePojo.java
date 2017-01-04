package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

public class UserDeductionScorePojo extends SuperPojo {

  private Long id;// ID
  private Long suserId;// 用户ID
  private Double deductScore;// 扣减分数
  private String remark;// 备注（扣减原因）
  private Integer status;// 状态
  private String statusName;
  private String createDateStr;
  private String updateDateStr;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSuserId() {
    return suserId;
  }

  public void setSuserId(Long suserId) {
    this.suserId = suserId;
  }

  public Double getDeductScore() {
    return deductScore;
  }

  public void setDeductScore(Double deductScore) {
    this.deductScore = deductScore;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
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
}
