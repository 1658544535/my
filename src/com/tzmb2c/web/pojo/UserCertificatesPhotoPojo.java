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
  /**
   * 营业执照注册码
   */
  private String image1No;
  /**
   * 法人身份证号码
   */
  private String image4No;
  /**
   * 紧急联系人姓名
   */
  private String emergencyContactName;
  /**
   * 紧急联系人电话
   */
  private String emergencyContactPhone;
  /**
   * 开户行
   */
  private String bankName;
  /**
   * 开户行所在地
   */
  private String bankAddress;
  /**
   * 支行信息
   */
  private String bankInfo;
  /**
   * 开户名
   */
  private String bankUsername;
  /**
   * 开户行卡号
   */
  private String bankNo;
  /**
   * 组织机构代码号
   */
  private String image2No;
  /**
   * 纳税人识别号
   */
  private String image3No;
  /**
   * 统一社会信用代码
   */
  private String image5No;
  /**
   * 组织机构证有效开始时间
   */
  private String image2BeginDate;
  /**
   * 组织机构证有效结束时间
   */
  private String image2EndDate;

  /**
   * 质检报告图片1
   */
  private String qcImage1;
  /**
   * 质检报告图片2
   */
  private String qcImage2;
  /**
   * 质检报告图片3
   */
  private String qcImage3;
  /**
   * 质检报告图片4
   */
  private String qcImage4;
  /**
   * 质检报告图片5
   */
  private String qcImage5;
  /**
   * 质检报告图片6
   */
  private String qcImage6;
  /**
   * 品牌授权证明图片1
   */
  private String blImage1;
  /**
   * 品牌授权证明图片2
   */
  private String blImage2;
  /**
   * 品牌授权证明图片3
   */
  private String blImage3;
  /**
   * 品牌授权证明图片4
   */
  private String blImage4;
  /**
   * 品牌授权证明图片5
   */
  private String blImage5;
  /**
   * 品牌授权证明图片6
   */
  private String blImage6;
  /**
   * 品牌授权证明图片7
   */
  private String blImage7;
  /**
   * 品牌授权证明图片8
   */
  private String blImage8;
  /**
   * 品牌授权证明图片9
   */
  private String blImage9;
  /**
   * 品牌授权证明图片10
   */
  private String blImage10;
  /**
   * 身份证有效开始时间
   */
  private String image4BeginDate;
  /**
   * 身份证有效结束时间
   */
  private String image4EndDate;
  /**
   * 开户许可证
   */
  private String image8;

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

  public String getImage1No() {
    return image1No;
  }

  public void setImage1No(String image1No) {
    this.image1No = image1No;
  }

  public String getImage4No() {
    return image4No;
  }

  public void setImage4No(String image4No) {
    this.image4No = image4No;
  }

  public String getEmergencyContactName() {
    return emergencyContactName;
  }

  public void setEmergencyContactName(String emergencyContactName) {
    this.emergencyContactName = emergencyContactName;
  }

  public String getEmergencyContactPhone() {
    return emergencyContactPhone;
  }

  public void setEmergencyContactPhone(String emergencyContactPhone) {
    this.emergencyContactPhone = emergencyContactPhone;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankAddress() {
    return bankAddress;
  }

  public void setBankAddress(String bankAddress) {
    this.bankAddress = bankAddress;
  }

  public String getBankInfo() {
    return bankInfo;
  }

  public void setBankInfo(String bankInfo) {
    this.bankInfo = bankInfo;
  }

  public String getBankUsername() {
    return bankUsername;
  }

  public void setBankUsername(String bankUsername) {
    this.bankUsername = bankUsername;
  }

  public String getBankNo() {
    return bankNo;
  }

  public void setBankNo(String bankNo) {
    this.bankNo = bankNo;
  }

  public String getImage2No() {
    return image2No;
  }

  public void setImage2No(String image2No) {
    this.image2No = image2No;
  }

  public String getImage3No() {
    return image3No;
  }

  public void setImage3No(String image3No) {
    this.image3No = image3No;
  }

  public String getImage5No() {
    return image5No;
  }

  public void setImage5No(String image5No) {
    this.image5No = image5No;
  }

  public String getImage2BeginDate() {
    return image2BeginDate;
  }

  public void setImage2BeginDate(String image2BeginDate) {
    this.image2BeginDate = image2BeginDate;
  }

  public String getImage2EndDate() {
    return image2EndDate;
  }

  public void setImage2EndDate(String image2EndDate) {
    this.image2EndDate = image2EndDate;
  }

  public String getQcImage1() {
    return qcImage1;
  }

  public void setQcImage1(String qcImage1) {
    this.qcImage1 = qcImage1;
  }

  public String getQcImage2() {
    return qcImage2;
  }

  public void setQcImage2(String qcImage2) {
    this.qcImage2 = qcImage2;
  }

  public String getQcImage3() {
    return qcImage3;
  }

  public void setQcImage3(String qcImage3) {
    this.qcImage3 = qcImage3;
  }

  public String getQcImage4() {
    return qcImage4;
  }

  public void setQcImage4(String qcImage4) {
    this.qcImage4 = qcImage4;
  }

  public String getQcImage5() {
    return qcImage5;
  }

  public void setQcImage5(String qcImage5) {
    this.qcImage5 = qcImage5;
  }

  public String getQcImage6() {
    return qcImage6;
  }

  public void setQcImage6(String qcImage6) {
    this.qcImage6 = qcImage6;
  }

  public String getBlImage1() {
    return blImage1;
  }

  public void setBlImage1(String blImage1) {
    this.blImage1 = blImage1;
  }

  public String getBlImage2() {
    return blImage2;
  }

  public void setBlImage2(String blImage2) {
    this.blImage2 = blImage2;
  }

  public String getBlImage3() {
    return blImage3;
  }

  public void setBlImage3(String blImage3) {
    this.blImage3 = blImage3;
  }

  public String getBlImage4() {
    return blImage4;
  }

  public void setBlImage4(String blImage4) {
    this.blImage4 = blImage4;
  }

  public String getBlImage5() {
    return blImage5;
  }

  public void setBlImage5(String blImage5) {
    this.blImage5 = blImage5;
  }

  public String getBlImage6() {
    return blImage6;
  }

  public void setBlImage6(String blImage6) {
    this.blImage6 = blImage6;
  }

  public String getBlImage7() {
    return blImage7;
  }

  public void setBlImage7(String blImage7) {
    this.blImage7 = blImage7;
  }

  public String getBlImage8() {
    return blImage8;
  }

  public void setBlImage8(String blImage8) {
    this.blImage8 = blImage8;
  }

  public String getBlImage9() {
    return blImage9;
  }

  public void setBlImage9(String blImage9) {
    this.blImage9 = blImage9;
  }

  public String getBlImage10() {
    return blImage10;
  }

  public void setBlImage10(String blImage10) {
    this.blImage10 = blImage10;
  }

  public String getImage4BeginDate() {
    return image4BeginDate;
  }

  public void setImage4BeginDate(String image4BeginDate) {
    this.image4BeginDate = image4BeginDate;
  }

  public String getImage4EndDate() {
    return image4EndDate;
  }

  public void setImage4EndDate(String image4EndDate) {
    this.image4EndDate = image4EndDate;
  }

  public String getImage8() {
    return image8;
  }

  public void setImage8(String image8) {
    this.image8 = image8;
  }

}
