package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * brand_dic表
 */
public class UserTalentAuthPojo extends SuperPojo {
  private Long id; // 序号id
  private Long userId;// 用户ID
  private String loginname;// 达人用户账号
  private String userName;// 达人用户昵称
  private String platform;// 已认证平台(新浪微博,微信,qq空间,今日头条,豆瓣,知乎,自定义)
  private String identity;// 职业身份/身份信息
  private Integer fansNum;// 粉丝数:1-2000≤粉丝数<10000,2-1W≤粉丝数＜5W,3-5W≤粉丝数＜10W,4-10W≤粉丝数＜50W,5-50W≤粉丝数＜100W,6-100W≤粉丝数＜200W,7-粉丝数≥200W
  private String fansNumName;// 粉丝数名称
  private String platformUserName;// 平台用户名
  private String platformFansNum;// 平台粉丝数
  private String crossPlatform;// 跨平台能力:新浪微博,微信,qq空间,自定义
  private Integer contentOutPer;// 日内容产量:1,2,3,4,5,6,7,8,9,10,11:11-20,12:21-30,13:31-40,14:40以上
  private String contentOutPerName;// 日内容产量名
  private Integer origContentOutPer;// 日原创内容产量:1,2,3,4,5,6,7,8,9,10,11:11-20,12:21-30,13:31-40,14:40以上
  private String origContentOutPerName;// 日原创内容产量名
  private String coopSimilarPlat;// 合作同类平台名称
  private String coopBrand;// 合作商品品牌名称
  private String commercialType;// 其他平台商业化合作模式:CPS佣金模式,固定报价模式,CPM模式,CPC模式,自定义
  private String commercialPrice;// 其他平台合作报价
  private String sampleTitle1;// 样稿标题1
  private String sampleUrl1;// 样稿链接1
  private String sampleTitle2;// 样稿标题2
  private String sampleUrl2;// 样稿链接2
  private String sampleTitle3;// 样稿标题3
  private String sampleUrl3;// 样稿链接3
  private String createDateStr;// 创建时间STR
  private Integer status;// 审核状态
  private String statusName;// 审核状态名
  private Long all;// 全查标记
  private String userImage;// 用户头像
  private Long followNum;// 关注人数
  private String orderBy;// 排序标记
  private String realName;// 真实姓名
  private String idCardImage;// 手持身份证图片
  private Long postNum;// 笔记数量



  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public Long getFollowNum() {
    return followNum;
  }

  public void setFollowNum(Long followNum) {
    this.followNum = followNum;
  }

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public Integer getFansNum() {
    return fansNum;
  }

  public void setFansNum(Integer fansNum) {
    this.fansNum = fansNum;
  }

  public String getFansNumName() {
    return fansNumName;
  }

  public void setFansNumName(String fansNumName) {
    this.fansNumName = fansNumName;
  }

  public String getCrossPlatform() {
    return crossPlatform;
  }

  public void setCrossPlatform(String crossPlatform) {
    this.crossPlatform = crossPlatform;
  }

  public Integer getContentOutPer() {
    return contentOutPer;
  }

  public void setContentOutPer(Integer contentOutPer) {
    this.contentOutPer = contentOutPer;
  }

  public Integer getOrigContentOutPer() {
    return origContentOutPer;
  }

  public void setOrigContentOutPer(Integer origContentOutPer) {
    this.origContentOutPer = origContentOutPer;
  }

  public String getCoopSimilarPlat() {
    return coopSimilarPlat;
  }

  public void setCoopSimilarPlat(String coopSimilarPlat) {
    this.coopSimilarPlat = coopSimilarPlat;
  }

  public String getCoopBrand() {
    return coopBrand;
  }

  public void setCoopBrand(String coopBrand) {
    this.coopBrand = coopBrand;
  }

  public String getCommercialType() {
    return commercialType;
  }

  public void setCommercialType(String commercialType) {
    this.commercialType = commercialType;
  }

  public String getCommercialPrice() {
    return commercialPrice;
  }

  public void setCommercialPrice(String commercialPrice) {
    this.commercialPrice = commercialPrice;
  }

  public String getSampleTitle1() {
    return sampleTitle1;
  }

  public void setSampleTitle1(String sampleTitle1) {
    this.sampleTitle1 = sampleTitle1;
  }

  public String getSampleUrl1() {
    return sampleUrl1;
  }

  public void setSampleUrl1(String sampleUrl1) {
    this.sampleUrl1 = sampleUrl1;
  }

  public String getSampleTitle2() {
    return sampleTitle2;
  }

  public void setSampleTitle2(String sampleTitle2) {
    this.sampleTitle2 = sampleTitle2;
  }

  public String getSampleUrl2() {
    return sampleUrl2;
  }

  public void setSampleUrl2(String sampleUrl2) {
    this.sampleUrl2 = sampleUrl2;
  }

  public String getSampleTitle3() {
    return sampleTitle3;
  }

  public void setSampleTitle3(String sampleTitle3) {
    this.sampleTitle3 = sampleTitle3;
  }

  public String getSampleUrl3() {
    return sampleUrl3;
  }

  public void setSampleUrl3(String sampleUrl3) {
    this.sampleUrl3 = sampleUrl3;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
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

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getContentOutPerName() {
    return contentOutPerName;
  }

  public void setContentOutPerName(String contentOutPerName) {
    this.contentOutPerName = contentOutPerName;
  }

  public String getOrigContentOutPerName() {
    return origContentOutPerName;
  }

  public void setOrigContentOutPerName(String origContentOutPerName) {
    this.origContentOutPerName = origContentOutPerName;
  }

  public Long getAll() {
    return all;
  }

  public void setAll(Long all) {
    this.all = all;
  }

  public String getPlatformFansNum() {
    return platformFansNum;
  }

  public void setPlatformFansNum(String platformFansNum) {
    this.platformFansNum = platformFansNum;
  }

  public String getPlatformUserName() {
    return platformUserName;
  }

  public void setPlatformUserName(String platformUserName) {
    this.platformUserName = platformUserName;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getIdCardImage() {
    return idCardImage;
  }

  public void setIdCardImage(String idCardImage) {
    this.idCardImage = idCardImage;
  }

  public Long getPostNum() {
    return postNum;
  }

  public void setPostNum(Long postNum) {
    this.postNum = postNum;
  }
}
