package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class UserCertificatesPhotoPojo extends SuperPojo {

  private Long id;// ID
  private Long suserId;// 用户ID
  private String image1;// 营业执照
  private String image2;// 组织机构代码证照
  private String image3;// 税务登记证照
  private String image4;// 法人身份证正面照
  private String image5;// 法人身份证反面照
  private String image6;// 合同照
  private Integer status;// 状态
  private String image1BeginDate;// 营业执照有效开始时间
  private String image1EndDate;// 营业执照有效结束时间
  private String image7;// 其他资质照（可不填）

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSuserId() {
    return suserId;
  }

  public void setSuserId(Long suserId) {
    this.suserId = suserId;
  }

  public String getImage1() {
    return image1;
  }

  public void setImage1(String image1) {
    this.image1 = image1;
  }

  public String getImage2() {
    return image2;
  }

  public void setImage2(String image2) {
    this.image2 = image2;
  }

  public String getImage3() {
    return image3;
  }

  public void setImage3(String image3) {
    this.image3 = image3;
  }

  public String getImage4() {
    return image4;
  }

  public void setImage4(String image4) {
    this.image4 = image4;
  }

  public String getImage5() {
    return image5;
  }

  public void setImage5(String image5) {
    this.image5 = image5;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getImage6() {
    return image6;
  }

  public void setImage6(String image6) {
    this.image6 = image6;
  }

  public String getImage1BeginDate() {
    return image1BeginDate;
  }

  public void setImage1BeginDate(String image1BeginDate) {
    this.image1BeginDate = image1BeginDate;
  }

  public String getImage1EndDate() {
    return image1EndDate;
  }

  public void setImage1EndDate(String image1EndDate) {
    this.image1EndDate = image1EndDate;
  }

  public String getImage7() {
    return image7;
  }

  public void setImage7(String image7) {
    this.image7 = image7;
  }
}
