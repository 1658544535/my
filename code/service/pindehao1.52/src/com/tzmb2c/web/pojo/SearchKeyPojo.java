package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 搜索关键字记录-- search_key by Lie
 * 
 */

public class SearchKeyPojo extends SuperPojo {
  private Long id;// 编号
  private Integer type;// 类型(取业务字典：1.商品2.商家)
  private Integer displayOrder;// 排序
  private Integer hits;// 次数
  private String keyword;// 词
  private String typeName;
  private Integer os;

  public Integer getOs() {
    return os;
  }

  public void setOs(Integer os) {
    this.os = os;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder) {
    this.displayOrder = displayOrder;
  }

  public Integer getHits() {
    return hits;
  }

  public void setHits(Integer hits) {
    this.hits = hits;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

}
