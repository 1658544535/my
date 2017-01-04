package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 2014-12-06
 * 
 * @author creazylee 帮助中心
 */

public class HelpPojo extends SuperPojo {

  private Long id;// 编号ID
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态字符
  private Integer sorting;// 排序
  private Long typeId;// 分类
  private String helpTypeName;// 分类名称
  private String helpTypeNameEn;// 分类英文名称
  private String helpType;// 分类字符
  private String title;// 标题
  private String titleEn;// 标题英文
  private String content;// 内容
  private String contentEn;// 内容英文
  private String answer;// 编辑者
  private Date createDate;// 创建时间
  private String createDateStr;// 创建日期(字符串)
  private Integer isUsual;// 是否常见问题
  private Integer isHot;// 是否热门问题



  public Integer getIsUsual() {
    return isUsual;
  }

  public void setIsUsual(Integer isUsual) {
    this.isUsual = isUsual;
  }

  public Integer getIsHot() {
    return isHot;
  }

  public void setIsHot(Integer isHot) {
    this.isHot = isHot;
  }

  public String getHelpTypeNameEn() {
    return helpTypeNameEn;
  }

  public void setHelpTypeNameEn(String helpTypeNameEn) {
    this.helpTypeNameEn = helpTypeNameEn;
  }

  public String getHelpTypeName() {
    return helpTypeName;
  }

  public void setHelpTypeName(String helpTypeName) {
    this.helpTypeName = helpTypeName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public String getHelpType() {
    return helpType;
  }

  public void setHelpType(String helpType) {
    this.helpType = helpType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitleEn() {
    return titleEn;
  }

  public void setTitleEn(String titleEn) {
    this.titleEn = titleEn;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContentEn() {
    return contentEn;
  }

  public void setContentEn(String contentEn) {
    this.contentEn = contentEn;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
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
