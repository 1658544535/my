package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 工厂玩具表 -- game_factory_toy
 * 
 */

public class VideoPojo extends SuperPojo {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private Long id;// 编号id
  private String label;// 视频标题
  private String url;// 视频链接
  private Integer score; // 评分
  private String source;// 来源
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态字符
  private Date createDate;// 创建时间
  private Date updateDate;// 更新时间
  private String createDateStr;
  private String updateDateStr;
  private Integer age;// 年龄字典ID
  private Integer skill;// 能力字典ID
  private String ageName;// 年龄
  private String abilityName;// 能力
  private String type;// 类型
  private String remark;//备注
  private Long isDelete;//是否删除（0：未删除；1：删除）

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    createDateStr = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    updateDateStr = StringUtil.dateToString(this.updateDate);
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

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }



  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getAgeName() {
    return ageName;
  }

  public void setAgeName(String ageName) {
    this.ageName = ageName;
  }

  public String getAbilityName() {
    return abilityName;
  }

  public void setAbilityName(String abilityName) {
    this.abilityName = abilityName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getSkill() {
    return skill;
  }

  public void setSkill(Integer skill) {
    this.skill = skill;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Long isDelete) {
    this.isDelete = isDelete;
  }
  


}
