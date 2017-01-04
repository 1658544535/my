package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 2014-12-19
 * 
 * @author creazylee 采购商:我的关注
 */

public class PurchaserAttentionPojo extends SuperPojo {

  private Long id;// 关注id
  private Long userId;// 用户编号
  private String userName;// 用户昵称
  private Long productTypeId;// 关注产品类型ID
  private String productType;// 关注产品类型
  private String productTypeName;// 产品类型名称
  private Integer version; // 版本
  private String remarks; // 备注

  public Long getProductTypeId() {
    return productTypeId;
  }

  public void setProductTypeId(Long productTypeId) {
    this.productTypeId = productTypeId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

}
