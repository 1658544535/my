package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 首页图标表--home_page_icon BY zzh
 */
public class HomePageIconPojo extends SuperPojo {
  private Long id; // 序号id
  private String image; // 图标图片
  private String title; // 图标标题
  private String url; // 链接内容
  private Long sorting; // 排序
  private Long status; // 审核状态
  private String statusName;// 状态名称
  private Integer type;// 类型
  private String typeName;// 类型名 1-猜价2-专区3-限时秒杀4-01.抽奖

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Long getSorting() {
    return sorting;
  }

  public void setSorting(Long sorting) {
    this.sorting = sorting;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }
}
