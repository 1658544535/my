package com.tzmb2c.web.pojo;


import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 批发商信息-- user_agency by Lie
 * 
 */
public class AgencyCollectPojo extends SuperPojo {

  private Long id;
  private Long uid;
  private Long agency_id;
  private Long agency_stock;
  private String is_export;// 是否导出
  private Long create_by;
  private Date create_date;
  private int update_by;
  private Date update_date;
  private String remarks;
  private Integer version;
  private Long product_id;
  private String image;
  private String product_name;
  private String productName;
  private Double sellingPrice;// 批发价价
  private Double originalPrice;// 产品原价
  private String pids;

  private String productNo;
  private String productNum;
  private String productImage;
  private Long puserId;
  private String manuName;// 品牌商名
  private String productTypeId;
  private String productTypeName;


  public Double getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(Double originalPrice) {
    this.originalPrice = originalPrice;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public Long getPuserId() {
    return puserId;
  }

  public void setPuserId(Long puserId) {
    this.puserId = puserId;
  }

  public String getManuName() {
    return manuName;
  }

  public void setManuName(String manuName) {
    this.manuName = manuName;
  }

  public String getProductTypeId() {
    return productTypeId;
  }

  public void setProductTypeId(String productTypeId) {
    this.productTypeId = productTypeId;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public Long getCreate_by() {
    return create_by;
  }

  public void setCreate_by(Long create_by) {
    this.create_by = create_by;
  }

  public Date getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Date create_date) {
    this.create_date = create_date;
  }

  public int getUpdate_by() {
    return update_by;
  }

  public void setUpdate_by(int update_by) {
    this.update_by = update_by;
  }

  public Date getUpdate_date() {
    return update_date;
  }

  public void setUpdate_date(Date update_date) {
    this.update_date = update_date;
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;

  }

  public String getIs_export() {
    return is_export;
  }

  public void setIs_export(String is_export) {
    this.is_export = is_export;
  }

  public Long getAgency_id() {
    return agency_id;
  }

  public void setAgency_id(Long agency_id) {
    this.agency_id = agency_id;
  }

  public Long getAgency_stock() {
    return agency_stock;
  }

  public void setAgency_stock(Long agency_stock) {
    this.agency_stock = agency_stock;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Long getProduct_id() {
    return product_id;
  }

  public void setProduct_id(Long product_id) {
    this.product_id = product_id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getPids() {
    return pids;
  }

  public void setPids(String pids) {
    this.pids = pids;
  }
}
