package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * @author EricChen
 */
public class ProductStockPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id;// 类型编号
  private Long userId;// 用户ID
  private Long productId;// 产品ID
  private String sku;// SKU
  private String stockNo;// 库存编号
  private String weight;// 重量
  private String size;// 尺寸
  private String color;// 颜色
  private Integer inventory;// 库存
  private Integer warehouseId;// 仓库
  private Integer sorting;// 排序
  private Integer status;// 状态

  private String productName;// 产品名称
  private String statusName;// 审核状态名称

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

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getStockNo() {
    return stockNo;
  }

  public void setStockNo(String stockNo) {
    this.stockNo = stockNo;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Integer getInventory() {
    return inventory;
  }

  public void setInventory(Integer inventory) {
    this.inventory = inventory;
  }

  public Integer getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(Integer warehouseId) {
    this.warehouseId = warehouseId;
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

}
