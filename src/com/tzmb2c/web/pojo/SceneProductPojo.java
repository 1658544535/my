package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 场景产品表 -- scene_product
 * 
 */

public class SceneProductPojo extends SuperPojo {
  /**
   * 序列化id
   */
  private static final long serialVersionUID = 1L;
  private Long id; // 场景/套餐产品表Id
  private Long sceneId; // 场景/套餐表id
  private String sceneName; // 场景/套餐名称
  private Long productId; // 产品表id
  private String productName; // 产品名称
  private String title; // 产品标题
  private String image; // 产品图片
  private String introduction; // 产品推荐介绍
  private Integer sorting; // 排序
  private Integer status; // 状态
  private String statusName; // 状态名称
  private String createDateStr; // 创建时间STR
  private String updateDateStr; // 更新时间STR
  private Double sellingPrice; // 产品表的建议零售价
  private Double distributionPrice;// 产品表的分销价
  private Double sellPrice; // 平台产品原价/（建议零售价）
  private Double scenePrice; // 场景/套餐活动产品价格/（分销价）
  private Long sceneStock; // 场景/套餐活动产品库存
  private Long sceneNum; // 场景/套餐活动产品总量
  private Integer skuLinkId; // product_sku_link.id
  private Integer isIntroduce; // 是否主推（取字典：0否1是）
  private Integer previewPro; // 预览(0无效预览1有效预览)
  private Long goodId; // activity_goods编号id
  private String isIntroduceName; // 是否主推
  private String previewProName; // 预览

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (createDate != null) {
      this.createDateStr = StringUtil.dateString(createDate);
    }
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    super.updateDate = updateDate;
    if (updateDate != null) {
      this.updateDateStr = StringUtil.dateString(updateDate);
    }
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getUpdateDateStr() {
    return updateDateStr;
  }

  public void setUpdateDateStr(String updateDateStr) {
    this.updateDateStr = updateDateStr;
  }

  public Long getSceneId() {
    return sceneId;
  }

  public void setSceneId(Long sceneId) {
    this.sceneId = sceneId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
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

  public Double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Double getScenePrice() {
    return scenePrice;
  }

  public void setScenePrice(Double scenePrice) {
    this.scenePrice = scenePrice;
  }

  public Long getSceneStock() {
    return sceneStock;
  }

  public void setSceneStock(Long sceneStock) {
    this.sceneStock = sceneStock;
  }

  public Long getSceneNum() {
    return sceneNum;
  }

  public void setSceneNum(Long sceneNum) {
    this.sceneNum = sceneNum;
  }

  public Integer getSkuLinkId() {
    return skuLinkId;
  }

  public void setSkuLinkId(Integer skuLinkId) {
    this.skuLinkId = skuLinkId;
  }

  public String getSceneName() {
    return sceneName;
  }

  public void setSceneName(String sceneName) {
    this.sceneName = sceneName;
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

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public Integer getIsIntroduce() {
    return isIntroduce;
  }

  public void setIsIntroduce(Integer isIntroduce) {
    this.isIntroduce = isIntroduce;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public Integer getPreviewPro() {
    return previewPro;
  }

  public void setPreviewPro(Integer previewPro) {
    this.previewPro = previewPro;
  }

  public Long getGoodId() {
    return goodId;
  }

  public void setGoodId(Long goodId) {
    this.goodId = goodId;
  }

  public String getPreviewProName() {
    return previewProName;
  }

  public void setPreviewProName(String previewProName) {
    this.previewProName = previewProName;
  }

  public String getIsIntroduceName() {
    return isIntroduceName;
  }

  public void setIsIntroduceName(String isIntroduceName) {
    this.isIntroduceName = isIntroduceName;
  }
}
