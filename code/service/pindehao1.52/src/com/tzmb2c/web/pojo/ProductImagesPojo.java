package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * @author EricChen
 */
public class ProductImagesPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id;// 类型编号
  private Long productId;// 产品ID
  private Long userId;// 用户ID
  private String images;// 图片路径
  private Integer sorting;// 排序
  private Integer status;// 状态
  private String productNum;// 产品货号
  private String productName;// 产品名称
  private String statusName;// 审核状态名称
  private String alts;// 图片属性
  private long[] cids;

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
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
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getAlts() {
    return alts;
  }

  public void setAlts(String alts) {
    this.alts = alts;
  }

  public long[] getCids() {
    return cids;
  }

  public void setCids(long[] cids) {
    this.cids = cids;
  }

}
