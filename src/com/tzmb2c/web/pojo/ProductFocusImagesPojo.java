package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class ProductFocusImagesPojo extends SuperPojo {
  private Long id;
  private Long productId;// 产品ID
  private Long userId;// 用户编号
  private String images;
  private Integer sorting, status;// 排序 ，状态
  private String delang;
  private String statusName;
  private String productName;
  private String productNum;
  private String alts;
  private long[] cids;

  public long[] getCids() {
    return cids;
  }

  public void setCids(long[] cids) {
    this.cids = cids;
  }

  public String getDelang() {
    return delang;
  }

  public void setDelang(String delang) {
    this.delang = delang;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
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
    if (status != null && status == 0) {
      this.statusName = "未审核";
    }
    if (status != null && status == 1) {
      this.statusName = "已审核";
    }
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public String getAlts() {
    return alts;
  }

  public void setAlts(String alts) {
    this.alts = alts;
  }



}
