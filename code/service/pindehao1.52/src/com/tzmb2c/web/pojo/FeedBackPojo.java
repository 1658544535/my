package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 2014-12-06
 * 
 * @author fu 意见反馈
 */

public class FeedBackPojo extends SuperPojo {

  private Long id;// 编号ID
  private Integer type;// 类型(取业务字典：1投诉2建议)
  private Integer status;// 状态(取业务字典：0未审核1已审核)
  private Long userId;// 用户ID
  private String content;// 内容
  private String contentEn;// 内容英文
  private String name;// 用户名
  private String email;// 邮箱地址
  private String area;// 所在地区
  private String telephone;// 联系电话
  private Integer province;// 所在地区
  private Integer city;// 所在地区
  private Integer area2;// 所在地区
  private String createDateStr;// 活动日期

  public Integer getProvince() {
    return province;
  }

  public void setProvince(Integer province) {
    this.province = province;
  }

  public Integer getCity() {
    return city;
  }

  public void setCity(Integer city) {
    this.city = city;
  }

  public Integer getArea2() {
    return area2;
  }

  public void setArea2(Integer area2) {
    this.area2 = area2;
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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContentEn() {
    return contentEn;
  }

  public void setContentEn(String contentEn) {
    this.contentEn = contentEn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }


}
