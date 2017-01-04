package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 帖子表
 */
public class UserCirclePostPojo extends SuperPojo {

  // ID
  private Long id;
  // 用户ID
  private Long userId;
  // 圈子ID
  private Long circleId;
  // 标题
  private String title;
  // 图片
  private String banner;
  // 点赞数
  private Long likeNum;
  // 评论数
  private Long commentNum;
  // 收藏数
  private Long collectNum;
  // 浏览数
  private Long lookNum;
  // 评分
  private Integer score;
  // 图文介绍
  private String content;
  // 年龄标签字典ID
  private Long ageType;
  // 能力标签字典ID
  private Long skillType;
  // 标签4/备选ID
  private Long optionType;
  // 标签3/商品
  private Long productType;
  // 状态（0未审核，1审核通过,2审核不通过）
  private Integer status;
  // 状态字符
  private String statusName;
  // 创建时间
  private Date createDate;
  // 创建人
  private Long createBy;
  // 修改时间
  private Date updateDate;
  // 修改人
  private Long updateBy;
  // 用户昵称
  private String userName;
  // 用户logo
  private String userImage;
  private String createDateStr;
  private String updateDateStr;
  private String ageTypeName;// 年龄
  private String skillTypeName;// 能力
  private String optionTypeName;
  private String circleName;// 社圈名称
  private String createDateStartStr;
  private String createDateEndStr;
  // 排序条件
  private String orderBy;
  // 次能力
  private Long secSkillType;
  private String secSkillTypeName;
  private String productTypeName;
  // 用户性别
  private Integer userSex;
  // 视频URL
  private String videoUrl;
  // 图片
  private String image;
  // 删除标识（0-正常，1-移除）
  private Integer isDelete;
  // 排序
  private Integer sorting;
  // 分享数
  private Long shareNum;
  // 商品销售数
  private Long saleNum;
  // 有你喜欢Detail ID
  private Long favId;
  /**
   * sketch:简要
   */
  private String sketch;
  private Integer height;
  private Integer width;
  private Long yourFavouritesId;
  // 用户类型 11达人 12创客
  private String userType;

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public Long getYourFavouritesId() {
    return yourFavouritesId;
  }

  public void setYourFavouritesId(Long yourFavouritesId) {
    this.yourFavouritesId = yourFavouritesId;
  }

  public String getSketch() {
    return sketch;
  }

  public void setSketch(String sketch) {
    this.sketch = sketch;
  }

  public Long getFavId() {
    return favId;
  }

  public void setFavId(Long favId) {
    this.favId = favId;
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setUserId(Long value) {
    userId = value;
  }

  public Long getUserId() {
    return userId;
  }

  public void setCircleId(Long value) {
    circleId = value;
  }

  public Long getCircleId() {
    return circleId;
  }

  public void setTitle(String value) {
    title = value;
  }

  public String getTitle() {
    return title;
  }

  public void setBanner(String value) {
    banner = value;
  }

  public String getBanner() {
    return banner;
  }

  public void setLikeNum(Long value) {
    likeNum = value;
  }

  public Long getLikeNum() {
    return likeNum;
  }

  public void setCommentNum(Long value) {
    commentNum = value;
  }

  public Long getCommentNum() {
    return commentNum;
  }

  public void setCollectNum(Long value) {
    collectNum = value;
  }

  public Long getCollectNum() {
    return collectNum;
  }

  public void setLookNum(Long value) {
    lookNum = value;
  }

  public Long getLookNum() {
    return lookNum;
  }

  public void setContent(String value) {
    content = value;
  }

  public String getContent() {
    return content;
  }

  public void setAgeType(Long value) {
    ageType = value;
  }

  public Long getAgeType() {
    return ageType;
  }

  public void setSkillType(Long value) {
    skillType = value;
  }

  public Long getSkillType() {
    return skillType;
  }

  public void setOptionType(Long value) {
    optionType = value;
  }

  public Long getOptionType() {
    return optionType;
  }

  public void setProductType(Long value) {
    productType = value;
  }

  public Long getProductType() {
    return productType;
  }

  public void setStatus(Integer value) {
    status = value;
  }

  public Integer getStatus() {
    return status;
  }

  @Override
  public void setCreateDate(Date value) {
    createDate = value;
    setCreateDateStr(StringUtil.dateToString(createDate));
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateBy(Long value) {
    createBy = value;
  }

  @Override
  public Long getCreateBy() {
    return createBy;
  }

  @Override
  public void setUpdateDate(Date value) {
    updateDate = value;
    setUpdateDateStr(StringUtil.dateToString(updateDate));
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateBy(Long value) {
    updateBy = value;
  }

  @Override
  public Long getUpdateBy() {
    return updateBy;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getCreateDateStr() {
    if (getCreateDate() != null) {
      createDateStr = StringUtil.dateString(createDate);
    }
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getUpdateDateStr() {
    if (getUpdateDate() != null) {
      updateDateStr = StringUtil.dateString(updateDate);
    }
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public String getAgeTypeName() {
    return ageTypeName;
  }

  public void setAgeTypeName(String ageTypeName) {
    this.ageTypeName = ageTypeName;
  }

  public String getSkillTypeName() {
    return skillTypeName;
  }

  public void setSkillTypeName(String skillTypeName) {
    this.skillTypeName = skillTypeName;
  }

  public String getOptionTypeName() {
    return optionTypeName;
  }

  public void setOptionTypeName(String optionTypeName) {
    this.optionTypeName = optionTypeName;
  }

  public String getCircleName() {
    return circleName;
  }

  public void setCircleName(String circleName) {
    this.circleName = circleName;
  }

  public String getCreateDateStartStr() {
    return createDateStartStr;
  }

  public void setCreateDateStartStr(String createDateStartStr) {
    this.createDateStartStr = createDateStartStr;
  }

  public String getCreateDateEndStr() {
    return createDateEndStr;
  }

  public void setCreateDateEndStr(String createDateEndStr) {
    this.createDateEndStr = createDateEndStr;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public Long getSecSkillType() {
    return secSkillType;
  }

  public void setSecSkillType(Long secSkillType) {
    this.secSkillType = secSkillType;
  }

  public String getSecSkillTypeName() {
    return secSkillTypeName;
  }

  public void setSecSkillTypeName(String secSkillTypeName) {
    this.secSkillTypeName = secSkillTypeName;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public Integer getUserSex() {
    return userSex;
  }

  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public Integer getSorting() {
    return sorting;
  }

  public void setSorting(Integer sorting) {
    this.sorting = sorting;
  }

  public Long getShareNum() {
    return shareNum;
  }

  public void setShareNum(Long shareNum) {
    this.shareNum = shareNum;
  }

  public Long getSaleNum() {
    return saleNum;
  }

  public void setSaleNum(Long saleNum) {
    this.saleNum = saleNum;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

}
