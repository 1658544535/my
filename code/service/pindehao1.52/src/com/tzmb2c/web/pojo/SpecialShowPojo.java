package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 专场表 -- special_show
 */
public class SpecialShowPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id; // 专场id
  private String name; // 专场名称
  private Long userBrandId; // 用户品牌id
  private String banner; // 首页海报
  private Integer discountType; // 优惠类型:1满减2满折
  private String discountTypeName; // 优惠类型:1满减2满折
  private String discountContext; // 优惠内容，JSON格式[{om:xx,m:xx}]
  private Long ageRange; // 适用年龄（参数）
  private String ageRangeName; // 适用年龄名
  private String introduction; // 专场介绍
  private Date beginTime; // 专场开始时间
  private Date endTime; // 专场结束时间
  private String beginTimeStr; // 专场开始时间STR
  private String endTimeStr; // 专场结束时间STR
  private Long sorting; // 排序
  private Long status; // 状态(取业务字典：1-待审核，2-待排期，3-退回修改，4-排期完成)
  private String statusName; // 状态名
  private Long activityId; // activity_time.id
  private String mainCategory1; // 一级类目
  private String mainCategory2; // 二级类目
  private String createDateStr; // 创建时间STR
  private String updateDateStr; // 更新时间STR
  private String currentTimeStr; // 当前时间STR
  private Long t; // 编辑/排期判断字
  private String isdelete; // 判断是否删除
  private String title; // 专场标题
  private String mainCategory1Name;// 一级类目名称
  private String mainCategory2Name;// 二级类目名称
  private String userBrandIdName; // 用户品牌名称
  private Long userId; // 用户ID
  private String specificDiscount; // 具体优惠内容
  private Long om1;
  private Long m1;
  private Long om2;
  private Long m2;
  private Long om3;
  private Long m3;
  private Long om4;
  private Long m4;
  private Integer discountTypec; // 改变的优惠类型
  private Long refundAddrId; // 专场退货地址ID
  private String refundAddress; // 专场退货地址
  private String consigneePhone; // 专场联系电话
  private String QQ; // 专场联系QQ
  private String houtai; // 区分后台与接口
  private Double discount; // APP专用折扣
  private String discountStr; // APP专用折扣显示
  private Integer option;
  private String channelId; // 频道ID
  private String channelName; // 频道名称

  public Long getActivityId() {
    return activityId;
  }

  public Long getAgeRange() {
    return ageRange;
  }

  public String getAgeRangeName() {
    return ageRangeName;
  }

  public String getBanner() {
    return banner;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public String getBeginTimeStr() {
    return beginTimeStr;
  }

  public String getChannelId() {
    return channelId;
  }

  public String getChannelName() {
    return channelName;
  }

  public String getConsigneePhone() {
    return consigneePhone;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public String getCurrentTimeStr() {
    return currentTimeStr;
  }

  public Double getDiscount() {
    return discount;
  }

  public String getDiscountContext() {
    return discountContext;
  }

  public String getDiscountStr() {
    return discountStr;
  }

  public Integer getDiscountType() {
    return discountType;
  }

  public Integer getDiscountTypec() {
    return discountTypec;
  }

  public String getDiscountTypeName() {
    return discountTypeName;
  }

  public Date getEndTime() {
    return endTime;
  }

  public String getEndTimeStr() {
    return endTimeStr;
  }

  public String getHoutai() {
    return houtai;
  }

  public Long getId() {
    return id;
  }

  public String getIntroduction() {
    return introduction;
  }

  public String getIsdelete() {
    return isdelete;
  }

  public Long getM1() {
    return m1;
  }

  public Long getM2() {
    return m2;
  }

  public Long getM3() {
    return m3;
  }

  public Long getM4() {
    return m4;
  }

  public String getMainCategory1() {
    return mainCategory1;
  }

  public String getMainCategory1Name() {
    return mainCategory1Name;
  }

  public String getMainCategory2() {
    return mainCategory2;
  }

  public String getMainCategory2Name() {
    return mainCategory2Name;
  }

  public String getName() {
    return name;
  }

  public Long getOm1() {
    return om1;
  }

  public Long getOm2() {
    return om2;
  }

  public Long getOm3() {
    return om3;
  }

  public Long getOm4() {
    return om4;
  }

  public Integer getOption() {
    return option;
  }

  public String getQQ() {
    return QQ;
  }

  public String getRefundAddress() {
    return refundAddress;
  }

  public Long getRefundAddrId() {
    return refundAddrId;
  }

  public Long getSorting() {
    return sorting;
  }

  public String getSpecificDiscount() {
    return specificDiscount;
  }

  public Long getStatus() {
    return status;
  }

  public String getStatusName() {
    return statusName;
  }

  public Long getT() {
    return t;
  }

  public String getTitle() {
    return title;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public Long getUserBrandId() {
    return userBrandId;
  }

  public String getUserBrandIdName() {
    return userBrandIdName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public void setAgeRange(Long ageRange) {
    this.ageRange = ageRange;
  }

  public void setAgeRangeName(String ageRangeName) {
    this.ageRangeName = ageRangeName;
  }

  public void setBanner(String banner) {
    this.banner = banner;
  }

  public void setBeginTime(Date beginTime) {
    if (beginTime != null) {
      this.beginTimeStr = StringUtil.dateString(beginTime);
    }
    this.beginTime = beginTime;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public void setConsigneePhone(String consigneePhone) {
    this.consigneePhone = consigneePhone;
  }

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public void setCurrentTimeStr(String currentTimeStr) {
    this.currentTimeStr = currentTimeStr;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public void setDiscountContext(String discountContext) {
    this.discountContext = discountContext;
  }

  public void setDiscountStr(String discountStr) {
    this.discountStr = discountStr;
  }

  public void setDiscountType(Integer discountType) {
    this.discountType = discountType;
  }

  public void setDiscountTypec(Integer discountTypec) {
    this.discountTypec = discountTypec;
  }

  public void setDiscountTypeName(String discountTypeName) {
    this.discountTypeName = discountTypeName;
  }

  public void setEndTime(Date endTime) {
    if (endTime != null) {
      this.endTimeStr = StringUtil.dateString(endTime);
    }
    this.endTime = endTime;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public void setHoutai(String houtai) {
    this.houtai = houtai;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public void setIsdelete(String isdelete) {
    this.isdelete = isdelete;
  }

  public void setM1(Long m1) {
    this.m1 = m1;
  }

  public void setM2(Long m2) {
    this.m2 = m2;
  }

  public void setM3(Long m3) {
    this.m3 = m3;
  }

  public void setM4(Long m4) {
    this.m4 = m4;
  }

  public void setMainCategory1(String mainCategory1) {
    this.mainCategory1 = mainCategory1;
  }

  public void setMainCategory1Name(String mainCategory1Name) {
    this.mainCategory1Name = mainCategory1Name;
  }

  public void setMainCategory2(String mainCategory2) {
    this.mainCategory2 = mainCategory2;
  }

  public void setMainCategory2Name(String mainCategory2Name) {
    this.mainCategory2Name = mainCategory2Name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOm1(Long om1) {
    this.om1 = om1;
  }

  public void setOm2(Long om2) {
    this.om2 = om2;
  }

  public void setOm3(Long om3) {
    this.om3 = om3;
  }

  public void setOm4(Long om4) {
    this.om4 = om4;
  }

  public void setOption(Integer option) {
    this.option = option;
  }

  public void setQQ(String qQ) {
    QQ = qQ;
  }

  public void setRefundAddress(String refundAddress) {
    this.refundAddress = refundAddress;
  }

  public void setRefundAddrId(Long refundAddrId) {
    this.refundAddrId = refundAddrId;
  }

  public void setSorting(Long sorting) {
    this.sorting = sorting;
  }

  public void setSpecificDiscount(String specificDiscount) {
    this.specificDiscount = specificDiscount;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public void setT(Long t) {
    this.t = t;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    super.updateDate = updateDate;
    if (updateDate != null) {
      this.updateDateStr = StringUtil.dateString(updateDate);
    }
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public void setUserBrandId(Long userBrandId) {
    this.userBrandId = userBrandId;
  }

  public void setUserBrandIdName(String userBrandIdName) {
    this.userBrandIdName = userBrandIdName;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

}
