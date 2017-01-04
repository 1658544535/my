package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

public class SpecialPushPojo extends SuperPojo {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private Long id; // ID
  private Long specialId; // 专场ID
  private Integer sorting; // 排序
  private Integer status; // 状态(取业务字典：1-待审核，2-审核通过)

  private String specialName; // 专场名称
  private String specialTitle; // 专场标题
  private Long userBrandId; // 用户品牌id
  private String banner; // 首页海报
  private Integer discountType; // 优惠类型:1满减2满折
  private String discountContext;// 优惠内容，JSON格式[{om:xx,m:xx}]
  private String ageRange; // 适用年龄
  private String introduction; // 专场介绍
  private Date beginTime; // 专场开始时间
  private Date endTime; // 专场结束时间
  private String beginTimeStr; // 专场开始时间STR
  private String endTimeStr; // 专场结束时间STR
  private Long activityId; // activity_time.id
  private String mainCategory1; // 一级类目
  private String mainCategory2; // 二级类目
  private String isdelete; // 判断是否删除

  private String statusName; // 状态名称
  private String createDateStr; // 创建时间
  private String updateDateStr; // 更新时间

  private Double discount;// 折起



  public Double getDiscount() {
    return discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }


  public String getSpecialTitle() {
    return specialTitle;
  }

  public void setSpecialTitle(String specialTitle) {
    this.specialTitle = specialTitle;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Long specialId) {
    this.specialId = specialId;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
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

  public String getSpecialName() {
    return specialName;
  }

  public void setSpecialName(String specialName) {
    this.specialName = specialName;
  }

  public Long getUserBrandId() {
    return userBrandId;
  }

  public void setUserBrandId(Long userBrandId) {
    this.userBrandId = userBrandId;
  }

  public String getBanner() {
    return banner;
  }

  public void setBanner(String banner) {
    this.banner = banner;
  }

  public Integer getDiscountType() {
    return discountType;
  }

  public void setDiscountType(Integer discountType) {
    this.discountType = discountType;
  }

  public String getDiscountContext() {
    return discountContext;
  }

  public void setDiscountContext(String discountContext) {
    this.discountContext = discountContext;
  }

  public String getAgeRange() {
    return ageRange;
  }

  public void setAgeRange(String ageRange) {
    this.ageRange = ageRange;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    if (beginTime != null) {
      this.beginTimeStr = StringUtil.dateString(beginTime);
    }
    this.beginTime = beginTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    if (endTime != null) {
      this.endTimeStr = StringUtil.dateString(endTime);
    }
    this.endTime = endTime;
  }

  public String getBeginTimeStr() {
    return beginTimeStr;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public String getEndTimeStr() {
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public String getMainCategory1() {
    return mainCategory1;
  }

  public void setMainCategory1(String mainCategory1) {
    this.mainCategory1 = mainCategory1;
  }

  public String getMainCategory2() {
    return mainCategory2;
  }

  public void setMainCategory2(String mainCategory2) {
    this.mainCategory2 = mainCategory2;
  }

  public String getIsdelete() {
    return isdelete;
  }

  public void setIsdelete(String isdelete) {
    this.isdelete = isdelete;
  }

  /*
   * (non-Javadoc)
   * 
   * @see maowu.framework.utils.pojo.SuperPojo#setCreateDate(java.util.Date)
   */
  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (this.createDate != null) {
      this.createDateStr = StringUtil.dateString(this.createDate);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see maowu.framework.utils.pojo.SuperPojo#setUpdateDate(java.util.Date)
   */
  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    if (this.updateDate != null) {
      this.updateDateStr = StringUtil.dateString(this.updateDate);
    }
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
}
