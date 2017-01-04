package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.export.excel.ExcelConf;

/**
 * @author EricChen
 */
public class ProductExcelPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id;// 类型编号
  private Long grouponId; // 活动ID
  private String productName;// 产品名称
  private Double distributionPrice;// 单购价
  private Double price; // 拼团价格,团购价
  private Integer stock; // 库存
  private String statusStr; // 上架下架 销售状态
  private Integer num; // 拼团人数
  private Integer productStatus;// 产品状态
  private String productNum;// 产品货号
  private String productType1;
  private String productTypeIds;// 所有产品类型ID
  private String productTypeId;// 产品类型ID
  private String productNameOrId;
  private Integer activityStatus; // 活动状态
  private String orderBy;// 排序

  @ExcelConf(headName = "商品ID", order = "1")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ExcelConf(headName = "活动ID", order = "2")
  public Long getGrouponId() {
    return grouponId;
  }

  public void setGrouponId(Long grouponId) {
    this.grouponId = grouponId;
  }

  @ExcelConf(headName = "货号", order = "3")
  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  @ExcelConf(headName = "商品名称", order = "4")
  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  @ExcelConf(headName = "单购价", order = "5")
  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  @ExcelConf(headName = "团购价", order = "6")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @ExcelConf(headName = "库存", order = "7")
  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  @ExcelConf(headName = "状态", order = "8")
  public String getStatusStr() {
    return statusStr;
  }

  public void setStatusStr(String statusStr) {
    this.statusStr = statusStr;
  }

  @ExcelConf(headName = "组团人数", order = "9")
  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Integer getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(Integer productStatus) {
    this.productStatus = productStatus;
  }

  public String getProductType1() {
    return productType1;
  }

  public void setProductType1(String productType1) {
    this.productType1 = productType1;
  }

  public String getProductTypeIds() {
    return productTypeIds;
  }

  public void setProductTypeIds(String productTypeIds) {
    this.productTypeIds = productTypeIds;
  }

  public String getProductTypeId() {
    return productTypeId;
  }

  public void setProductTypeId(String productTypeId) {
    this.productTypeId = productTypeId;
  }

  public String getProductNameOrId() {
    return productNameOrId;
  }

  public void setProductNameOrId(String productNameOrId) {
    this.productNameOrId = productNameOrId;
  }

  public Integer getActivityStatus() {
    return activityStatus;
  }

  public void setActivityStatus(Integer activityStatus) {
    this.activityStatus = activityStatus;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

}
