package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

public class ProductSkuLinkPojo extends SuperPojo {
  private Long id;// 编号
  private Long productId;// 产品编号
  private Long skuColorId;// 颜色sku
  private SkuAttributePojo colorAttr;
  private SkuAttributePojo formatAttr;
  private Long skuFormatId;// 规格sku
  private int stock;// 剩余库存
  private int stockNum;// 库存总量
  private Double price;// 阶梯价格
  private Double sellingPrice;// 阶梯价格
  private int sorting;// 排序
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private String statusName;// 状态
  private Long createBy;// 创建者
  private Date createDate;// 创建时间
  private Long updateBy;// 更新者
  private Date updateDate;// 更新时间
  private String remarks; // 备注信息
  private Integer version;// 版本号
  private String productName;// 产品名称
  private String colorValue; // 颜色名称
  private String formatValue; // 规格名称
  private Integer type; // 0-普通;1-活动
  private String typeName; // 0-普通;1-活动
  private Long activityId; // 活动id
  private String businessCode;
  private String ladderSku;// SKU阶梯
  private String image; // 属性前段显示图标
  private Integer minusStock;// 减少库存的数量
  private String skuColor;// 颜色
  private String skuFormat;// 规格
  private long[] cids;
  private Integer isDelete;// 是否删除



  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public long[] getCids() {
    return cids;
  }

  public void setCids(long[] cids) {
    this.cids = cids;
  }

  public String getSkuColor() {
    return skuColor;
  }

  public void setSkuColor(String skuColor) {
    this.skuColor = skuColor;
  }

  public String getSkuFormat() {
    return skuFormat;
  }

  public void setSkuFormat(String skuFormat) {
    this.skuFormat = skuFormat;
  }

  public Integer getMinusStock() {
    return minusStock;
  }

  public void setMinusStock(Integer minusStock) {
    this.minusStock = minusStock;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public int getStockNum() {
    return stockNum;
  }

  public void setStockNum(int stockNum) {
    this.stockNum = stockNum;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
    if (this.type != null && this.type == 1) {
      this.typeName = "是";
    } else {
      this.typeName = "否";
    }
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
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

  public Long getSkuColorId() {
    return skuColorId;
  }

  public void setSkuColorId(Long skuColorId) {
    this.skuColorId = skuColorId;
  }

  public Long getSkuFormatId() {
    return skuFormatId;
  }

  public void setSkuFormatId(Long skuFormatId) {
    this.skuFormatId = skuFormatId;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getSorting() {
    return sorting;
  }

  public void setSorting(int sorting) {
    this.sorting = sorting;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public Long getCreateBy() {
    return createBy;
  }

  @Override
  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public Long getUpdateBy() {
    return updateBy;
  }

  @Override
  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
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

  public String getColorValue() {
    return colorValue;
  }

  public void setColorValue(String colorValue) {
    this.colorValue = colorValue;
  }

  public String getFormatValue() {
    return formatValue;
  }

  public void setFormatValue(String formatValue) {
    this.formatValue = formatValue;
  }

  public SkuAttributePojo getColorAttr() {
    return colorAttr;
  }

  public void setColorAttr(SkuAttributePojo colorAttr) {
    this.colorAttr = colorAttr;
  }

  public SkuAttributePojo getFormatAttr() {
    return formatAttr;
  }

  public void setFormatAttr(SkuAttributePojo formatAttr) {
    this.formatAttr = formatAttr;
  }

  public String getBusinessCode() {
    return businessCode;
  }

  public void setBusinessCode(String businessCode) {
    this.businessCode = businessCode;
  }

  public String getLadderSku() {
    return ladderSku;
  }

  public void setLadderSku(String ladderSku) {
    this.ladderSku = ladderSku;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }



}
