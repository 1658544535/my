package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 活动表 -- Activity_Title
 * 
 */

public class ActivityTitlePojo extends SuperPojo {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private Long id;// 编号id
  private String title;// 活动标题
  private String banner;// 活动图片
  private String titlePic;// 标题图片1
  private String titlePicture;// 标题图片2
  private String titlePhoto;// 标题图片3
  private String type;// 活动类型(4首页列表5web活动页)
  private Integer status;// 活动审核状态
  private String statusName;// 审核状态中文
  private Date createDate;
  private Date updateDate;
  private String createDateStr;
  private String updateDateStr;


  public String getBanner() {
    return banner;
  }

  public void setBanner(String banner) {
    this.banner = banner;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public String getTitlePic() {
    return titlePic;
  }

  public void setTitlePic(String titlePic) {
    this.titlePic = titlePic;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTitlePicture() {
    return titlePicture;
  }

  public void setTitlePicture(String titlePicture) {
    this.titlePicture = titlePicture;
  }

  public String getTitlePhoto() {
    return titlePhoto;
  }

  public void setTitlePhoto(String titlePhoto) {
    this.titlePhoto = titlePhoto;
  }
}
