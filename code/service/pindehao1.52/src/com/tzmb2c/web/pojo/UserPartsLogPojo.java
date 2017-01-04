package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * brand_dic表
 */
public class UserPartsLogPojo extends SuperPojo {
  private Long id;// id
  private Long userId;// 用户ID
  private Long toyId;// 玩具ID
  private Long partsId;// 部件ID
  private Date createBeginDate;// 生成开始时间
  private Date createEndDate;// 生成结束时间
  private Long status;// 状态（0-未生成、1-生成中、2-已生成、3-已合成）
  private String createDateStr;// 抽取日期STR
  private String updateDateStr;// 更新日期STR
  private String statusName;// 审核状态名
  private String createBeginDateStr;// 生成开始时间STR
  private String createEndDateStr;// 生成结束时间STR
  private String loginname;// 用户账号
  private String toyName;// 玩具名称
  private String partsName;// 部件名称
  private Long createTime;// 生成所需时间
  private String partsImages;// 部件图片
  private String toyImages;// 玩具图片

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (createDate != null) {
      this.setCreateDateStr(StringUtil.dateString(createDate));
    }
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    super.updateDate = updateDate;
    if (updateDate != null) {
      this.setUpdateDateStr(StringUtil.dateString(updateDate));
    }
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

  public Long getToyId() {
    return toyId;
  }

  public void setToyId(Long toyId) {
    this.toyId = toyId;
  }

  public Long getPartsId() {
    return partsId;
  }

  public void setPartsId(Long partsId) {
    this.partsId = partsId;
  }

  public Date getCreateBeginDate() {
    return createBeginDate;
  }

  public void setCreateBeginDate(Date createBeginDate) {
    this.createBeginDate = createBeginDate;
    if (createBeginDate != null) {
      this.setCreateBeginDateStr(StringUtil.dateString(createBeginDate));
    }
  }

  public Date getCreateEndDate() {
    return createEndDate;
  }

  public void setCreateEndDate(Date createEndDate) {
    this.createEndDate = createEndDate;
    if (createEndDate != null) {
      this.setCreateEndDateStr(StringUtil.dateString(createEndDate));
    }
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
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

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getCreateBeginDateStr() {
    return createBeginDateStr;
  }

  public void setCreateBeginDateStr(String createBeginDateStr) {
    this.createBeginDateStr = createBeginDateStr;
  }

  public String getCreateEndDateStr() {
    return createEndDateStr;
  }

  public void setCreateEndDateStr(String createEndDateStr) {
    this.createEndDateStr = createEndDateStr;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getToyName() {
    return toyName;
  }

  public void setToyName(String toyName) {
    this.toyName = toyName;
  }

  public String getPartsName() {
    return partsName;
  }

  public void setPartsName(String partsName) {
    this.partsName = partsName;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public String getPartsImages() {
    return partsImages;
  }

  public void setPartsImages(String partsImages) {
    this.partsImages = partsImages;
  }

  public String getToyImages() {
    return toyImages;
  }

  public void setToyImages(String toyImages) {
    this.toyImages = toyImages;
  }
}
