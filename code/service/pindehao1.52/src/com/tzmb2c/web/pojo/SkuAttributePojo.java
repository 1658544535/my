package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class SkuAttributePojo extends SuperPojo {

  private Long id;// 编号id
  private String attribute;// 属性
  private String value;// 属性值
  private String image; // 属性前段显示图标
  private int sorting;// 排序
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private Long productId;// 产品id
  private Integer dictValue;// 字典value



  public Integer getDictValue() {
    return dictValue;
  }

  public void setDictValue(Integer dictValue) {
    this.dictValue = dictValue;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
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

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }



}
