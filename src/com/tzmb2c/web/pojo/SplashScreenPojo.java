package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 闪屏设置管理表--splash_screen by zzh
 */
public class SplashScreenPojo extends SuperPojo {
  private Long id; // 序号id
  private String image1; // 图片1
  private String image2; // 图片2
  private String image3; // 图片3
  private String title; // 标题
  private String url; // 链接
  private Long sorting; // 排序
  private Long status; // 审核状态
  private String statusName;// 审核状态名

  public String getImage2() {
    return image2;
  }

  public void setImage2(String image2) {
    this.image2 = image2;
  }

  public String getImage1() {
    return image1;
  }

  public void setImage1(String image1) {
    this.image1 = image1;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImage3() {
    return image3;
  }

  public void setImage3(String image3) {
    this.image3 = image3;
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
