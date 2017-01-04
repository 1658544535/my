package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 场景表 -- scene
 * 
 */
public class ScenePojo extends SuperPojo {
  /**
   * 序列化id
   */
  private Long id; // 场景/套餐表id
  private String name; // 场景/套餐名称
  private String image; // 场景/套餐图片名
  private String introduction; // 场景/套餐介绍
  private Integer sorting; // 排序
  private Integer status; // 状态
  private String statusName; // 审核状态名
  private String createDateStr; // 创建时间STR
  private String updateDateStr; // 更新时间STR
  protected Date beginTime; // 场景/套餐活动开始时间
  protected Date endTime; // 场景/套餐活动结束时间
  private String beginTimeStr; // 场景/套餐活动开始时间STR
  private String endTimeStr; // 场景/套餐活动结束时间STR
  private String currentTimeStr;// 当前时间
  private Integer preview; // 预览(0无效预览1有效预览)
  private Double packagePrice; // 套餐活动价
  private Double psellPrice; // 套餐原价
  private Integer type; // 类型(1场景2套餐)
  private Integer stockNum; // 套餐数量
  private Integer stock; // 套餐库存
  private String previewName; // 预览
  private Long activityId; // activity_time.id
  private String isdelete;// 判断是否删除
  private String video;// 场景web页面的视频id

  public Integer getPreview() {
    return preview;
  }

  public void setPreview(Integer preview) {
    this.preview = preview;
  }

  public Double getPackagePrice() {
    return packagePrice;
  }

  public void setPackagePrice(Double packagePrice) {
    this.packagePrice = packagePrice;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
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

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getCurrentTimeStr() {
    return currentTimeStr;
  }

  public void setCurrentTimeStr(String currentTimeStr) {
    this.currentTimeStr = currentTimeStr;
  }

  public Double getPsellPrice() {
    return psellPrice;
  }

  public void setPsellPrice(Double psellPrice) {
    this.psellPrice = psellPrice;
  }

  public Integer getStockNum() {
    return stockNum;
  }

  public void setStockNum(Integer stockNum) {
    this.stockNum = stockNum;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public String getPreviewName() {
    return previewName;
  }

  public void setPreviewName(String previewName) {
    this.previewName = previewName;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public String getIsdelete() {
    return isdelete;
  }

  public void setIsdelete(String isdelete) {
    this.isdelete = isdelete;
  }

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

}
