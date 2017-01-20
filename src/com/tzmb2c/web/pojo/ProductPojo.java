package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.export.excel.ExcelConf;

/**
 * @author EricChen
 */
public class ProductPojo extends SuperPojo {
  private static final long serialVersionUID = 1L;
  private Long id;// 类型编号
  private Long userId;// 用户编号
  private String productNo;// 产品序号
  private String productNum;// 产品货号
  private String productTypeId;// 产品类型ID
  private String productTypeIds;// 所有产品类型ID
  private String productName;// 产品名称
  private String ProductStatus;// 产品名称
  private String title;
  private String keywords;
  private String drawkeywords;
  private String description;
  private String productNameOrId;
  private String activityType;// 活动类型，0-秒杀，1-专题，2-场景，3-专场
  private Long activityId;// 活动Id
  private Double activePrice;// 活动价格
  private String productNameEn;// 产品名称英文
  private String productSketch;// 产品简述
  private Double distributionPrice;// 分销价
  private Double sellingPrice;// 建议零售价
  private Double lowestPrice;// 最低零售价
  private String unit, unitName;// 单位
  private Integer minimum;// 起订量
  private String ladderPrice;// 价格阶梯
  private String image;// 产品主图名称
  private String qrcode;// 二维码图片地址
  private String hits;// 点击数
  private String isIntroduce;// 是否推荐(取业务字典：0否1是)
  private String content;// 产品介绍
  private Integer sellNumber;// 销售数量
  private Integer baseNumber;// 销售数量基数
  private String isNew;// 是否新品
  private Integer sorting;// 排序
  private Integer status;// 状态
  private String discount;// 折扣
  private Integer recommend;// 推荐值（0-1000首页新品推荐，1001-2000首页产品推荐，2001-3000热卖产品）
  private String brand;// 品牌（取综合字典）
  private String texture;// 材质（取综合字典）
  private String age;// 适用年龄（取综合字典）
  private String productFunction;// 功能（取综合字典）
  private String location;// 产地
  private String isPower;// 是否电动（取综合字典）
  private String pack;// 包装方式（取综合字典）
  private String isHotsale;// 掌柜热卖(取业务字典：0否1是)
  private String packName, isPowerName, productFunctionName, ageName, textureName, brandName,
      brandNames, postageTypeName;
  private String userName;// 用户名称
  private String statusName;// 审核状态名称
  private String isIntroduceName;// 是否推荐名称
  private String productTypeName;// 产品类型名称
  private String userAddress;// 用户地址
  private String shopName;// 店铺名称
  private String isNewName;// 是否新品名称
  private String sortingType;// 排序方式
  private String isHotsaleName;
  private Double BeginPrice, EndPrice;
  private Integer postageType;// 是否包邮
  private String weight;// 重量
  private String productTypeIdsName;// 大类名称
  private Long shopId;// 店铺id
  private Date createDate;// 创建时间
  private String creatDateString;
  private Date updateDate; // 更新时间
  private String updateDateString;
  private String updateByName;
  private String time;
  private Double proxyPrice; // 代理价
  private String ageType; // 年龄段
  private Long titleId;// 首页列表活动标题id
  private String ageDesc;// 年龄描述
  private String tag;// 标签描述
  private Double sellPrice;//
  private Integer tongji;
  private String name;
  private String timeIdIsN;// 判断time_id是否为空
  private String videoUrl;// 视频URL
  private String tvideoUrl;// 视频展示URL
  private String tvideoUrlImage;// 视频展示图片
  private Integer houtai;// 用来区分是否为后台编辑商品
  private Long favId;
  private Double price; // 拼团价格,团购价
  private Integer stock; // 库存
  private Integer activityStatus; // 活动状态
  private Integer num; // 拼团人数
  private Integer type; // 当前活动类型
  private Integer status2; // 上架下架
  private Long grouponId; // 活动ID
  private Long productSkuId; // 商品库存ID
  private String skuFormat; // 商品款式，套餐
  private String skuColor; // 商品颜色
  private Integer numNow1;
  private Integer orderBy; // 排序条件



  /**
   * 用户品牌ID
   */
  private Long userBrandId;
  /**
   * 一级分类
   */
  private String productType1;
  private String productType1Name;// 一级分类名称
  private String productCommt;// 商品满意度
  private String[] filter;// 用于判断是否（!null：是；null：否）过滤掉添加过的商品
  private Boolean isAdd;// 用于判断商品是否添加过


  private Integer imageCount;
  private Integer focusImageCount;
  private Long geekId;// 创客id
  private String imageSmall;
  private String imageMain;

  private Integer countNum;
  private Integer maxNum;// 限购数量
  private Integer formatValue;// 格式(字典)
  private Integer colorValue;// 颜色(字典)
  private Integer normType;// 规格(1一种2两种)
  private Integer limitNum; // 商品数量
  private Integer surplusNum; // 剩余数量
  private Integer surplusNumMinus; // 减剩余数量
  private String beginTimeStr;
  private String endTimeStr;
  private String faraway;// 偏远地区
  private Integer gaLimitNum; // 活动剩余数量
  private Integer gaSurplusNum; // 活动减剩余数量


  public String getBeginTimeStr() {
    return beginTimeStr;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public String getEndTimeStr() {
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public Integer getGaLimitNum() {
    return gaLimitNum;
  }

  public void setGaLimitNum(Integer gaLimitNum) {
    this.gaLimitNum = gaLimitNum;
  }

  public Integer getGaSurplusNum() {
    return gaSurplusNum;
  }

  public void setGaSurplusNum(Integer gaSurplusNum) {
    this.gaSurplusNum = gaSurplusNum;
  }

  public String getFaraway() {
    return faraway;
  }

  public void setFaraway(String faraway) {
    this.faraway = faraway;
  }

  public Integer getSurplusNumMinus() {
    return surplusNumMinus;
  }

  public void setSurplusNumMinus(Integer surplusNumMinus) {
    this.surplusNumMinus = surplusNumMinus;
  }

  public Integer getLimitNum() {
    return limitNum;
  }

  public void setLimitNum(Integer limitNum) {
    this.limitNum = limitNum;
  }

  public Integer getSurplusNum() {
    return surplusNum;
  }

  public void setSurplusNum(Integer surplusNum) {
    this.surplusNum = surplusNum;
  }

  public Integer getNormType() {
    return normType;
  }

  public void setNormType(Integer normType) {
    this.normType = normType;
  }

  public Integer getFormatValue() {
    return formatValue;
  }

  public void setFormatValue(Integer formatValue) {
    this.formatValue = formatValue;
  }

  public Integer getColorValue() {
    return colorValue;
  }

  public void setColorValue(Integer colorValue) {
    this.colorValue = colorValue;
  }

  public Integer getMaxNum() {
    return maxNum;
  }

  public void setMaxNum(Integer maxNum) {
    this.maxNum = maxNum;
  }

  public Integer getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(Integer orderBy) {
    this.orderBy = orderBy;
  }

  public Integer getCountNum() {
    return countNum;
  }

  public void setCountNum(Integer countNum) {
    this.countNum = countNum;
  }

  public Integer getActivityStatus() {
    return activityStatus;
  }

  public void setActivityStatus(Integer activityStatus) {
    this.activityStatus = activityStatus;
  }

  public String getSkuFormat() {
    return skuFormat;
  }

  public void setSkuFormat(String skuFormat) {
    this.skuFormat = skuFormat;
  }

  public String getSkuColor() {
    return skuColor;
  }

  public void setSkuColor(String skuColor) {
    this.skuColor = skuColor;
  }

  public String getImageMain() {
    return imageMain;
  }

  public void setImageMain(String imageMain) {
    this.imageMain = imageMain;
  }

  public String getImageSmall() {
    return imageSmall;
  }

  public void setImageSmall(String imageSmall) {
    this.imageSmall = imageSmall;
  }

  public Integer getNumNow1() {
    return numNow1;
  }

  public void setNumNow1(Integer numNow1) {
    this.numNow1 = numNow1;
  }

  public Long getGeekId() {
    return geekId;
  }

  public void setGeekId(Long geekId) {
    this.geekId = geekId;
  }

  public String getProductCommt() {
    return productCommt;
  }

  public void setProductCommt(String productCommt) {
    this.productCommt = productCommt;
  }

  public Long getProductSkuId() {
    return productSkuId;
  }

  public void setProductSkuId(Long productSkuId) {
    this.productSkuId = productSkuId;
  }

  public Long getUserBrandId() {
    return userBrandId;
  }

  public void setUserBrandId(Long userBrandId) {
    this.userBrandId = userBrandId;
  }

  public String getProductType1() {
    return productType1;
  }

  public void setProductType1(String productType1) {
    this.productType1 = productType1;
  }

  public Integer getBaseNumber() {
    return baseNumber;
  }

  public void setBaseNumber(Integer baseNumber) {
    this.baseNumber = baseNumber;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getUpdateByName() {
    return updateByName;
  }

  public void setUpdateByName(String updateByName) {
    this.updateByName = updateByName;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    creatDateString = StringUtil.dateToString(this.createDate);
  }

  @Override
  public Date getUpdateDate() {
    return updateDate;
  }

  @Override
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    setUpdateDateString(StringUtil.dateString(this.updateDate));
  }

  public String getCreatDateString() {
    return creatDateString;
  }

  public void setCreatDateString(String creatDateString) {
    this.creatDateString = creatDateString;
  }

  @ExcelConf(headName = "产品名称", order = "1")
  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  @ExcelConf(headName = "是否电动", order = "2")
  public String getIsPowerName() {
    return isPowerName;
  }

  public void setIsPowerName(String isPowerName) {
    this.isPowerName = isPowerName;
  }

  @ExcelConf(headName = "包装方式", order = "3")
  public String getPackName() {
    return packName;
  }

  public void setPackName(String packName) {
    this.packName = packName;
  }

  @ExcelConf(headName = "产品货号", order = "4")
  public String getProductNum() {
    return productNum;
  }

  public void setProductNum(String productNum) {
    this.productNum = productNum;
  }

  @ExcelConf(headName = "品牌", order = "5")
  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  @ExcelConf(headName = "产地", order = "6")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPostageTypeName() {
    return postageTypeName;
  }

  public void setPostageTypeName(String postageTypeName) {
    this.postageTypeName = postageTypeName;
  }

  @ExcelConf(headName = "材质", order = "7")
  public String getTextureName() {
    return textureName;
  }

  public void setTextureName(String textureName) {
    this.textureName = textureName;
  }

  @ExcelConf(headName = "适用年龄", order = "8")
  public String getAgeName() {
    return ageName;
  }

  public void setAgeName(String ageName) {
    this.ageName = ageName;
  }

  @ExcelConf(headName = "建议零售价", order = "9")
  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  @ExcelConf(headName = "最低零售价", order = "10")
  public Double getLowestPrice() {
    return lowestPrice;
  }

  public void setLowestPrice(Double lowestPrice) {
    this.lowestPrice = lowestPrice;
  }

  @ExcelConf(headName = "重量", order = "11")
  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }



  public Integer getStatus2() {
    return status2;
  }

  public void setStatus2(Integer status2) {
    this.status2 = status2;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public String getProductTypeIdsName() {
    return productTypeIdsName;
  }

  public void setProductTypeIdsName(String productTypeIdsName) {
    this.productTypeIdsName = productTypeIdsName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public String getProductTypeName() {
    return productTypeName;
  }

  public void setProductTypeName(String productTypeName) {
    this.productTypeName = productTypeName;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getProductSketch() {
    return productSketch;
  }

  public void setProductSketch(String productSketch) {
    this.productSketch = productSketch;
  }

  public Double getBeginPrice() {
    return BeginPrice;
  }

  public void setBeginPrice(Double beginPrice) {
    BeginPrice = beginPrice;
  }

  public Double getEndPrice() {
    return EndPrice;
  }

  public void setEndPrice(Double endPrice) {
    EndPrice = endPrice;
  }

  public String getProductFunctionName() {
    return productFunctionName;
  }

  public void setProductFunctionName(String productFunctionName) {
    this.productFunctionName = productFunctionName;
  }


  public String getIsHotsaleName() {
    return isHotsaleName;
  }

  public void setIsHotsaleName(String isHotsaleName) {
    this.isHotsaleName = isHotsaleName;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }

  public Integer getRecommend() {
    return recommend;
  }

  public void setRecommend(Integer recommend) {
    this.recommend = recommend;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getTexture() {
    return texture;
  }

  public void setTexture(String texture) {
    this.texture = texture;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getProductFunction() {
    return productFunction;
  }

  public void setProductFunction(String productFunction) {
    this.productFunction = productFunction;
  }

  public String getIsPower() {
    return isPower;
  }

  public void setIsPower(String isPower) {
    this.isPower = isPower;
  }

  public String getPack() {
    return pack;
  }

  public void setPack(String pack) {
    this.pack = pack;
  }

  public String getIsHotsale() {
    return isHotsale;
  }

  public void setIsHotsale(String isHotsale) {
    this.isHotsale = isHotsale;
  }

  public String getSortingType() {
    return sortingType;
  }

  public void setSortingType(String sortingType) {
    this.sortingType = sortingType;
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
    if (userId != null && userId == 0) {
      userName = "官方";
    }
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public String getProductTypeId() {
    return productTypeId;
  }

  public void setProductTypeId(String productTypeId) {
    this.productTypeId = productTypeId;
  }

  public String getProductTypeIds() {
    return productTypeIds;
  }

  public void setProductTypeIds(String productTypeIds) {
    this.productTypeIds = productTypeIds;
  }

  public String getProductNameEn() {
    return productNameEn;
  }

  public void setProductNameEn(String productNameEn) {
    this.productNameEn = productNameEn;
  }

  public Integer getMinimum() {
    return minimum;
  }

  public void setMinimum(Integer minimum) {
    this.minimum = minimum;
  }

  public String getLadderPrice() {
    return ladderPrice;
  }

  public void setLadderPrice(String ladderPrice) {
    this.ladderPrice = ladderPrice;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getQrcode() {
    return qrcode;
  }

  public void setQrcode(String qrcode) {
    this.qrcode = qrcode;
  }

  public String getHits() {
    return hits;
  }

  public String getIsIntroduce() {
    return isIntroduce;
  }

  public void setIsIntroduce(String isIntroduce) {
    this.isIntroduce = isIntroduce;
  }

  public void setHits(String hits) {
    this.hits = hits;
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


  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getIsIntroduceName() {
    return isIntroduceName;
  }

  public void setIsIntroduceName(String isIntroduceName) {
    this.isIntroduceName = isIntroduceName;
  }

  public Integer getSellNumber() {
    return sellNumber;
  }

  public void setSellNumber(Integer sellNumber) {
    this.sellNumber = sellNumber;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public String getIsNew() {
    return isNew;
  }

  public void setIsNew(String isNew) {
    this.isNew = isNew;
  }

  public String getIsNewName() {
    return isNewName;
  }

  public void setIsNewName(String isNewName) {
    this.isNewName = isNewName;
  }

  public Integer getPostageType() {
    return postageType;
  }

  public void setPostageType(Integer postageType) {
    this.postageType = postageType;
  }

  public Double getProxyPrice() {
    return proxyPrice;
  }

  public void setProxyPrice(Double proxyPrice) {
    this.proxyPrice = proxyPrice;
  }

  public String getUpdateDateString() {
    return updateDateString;
  }

  public void setUpdateDateString(String updateDateString) {
    this.updateDateString = updateDateString;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getDrawkeywords() {
    return drawkeywords;
  }

  public void setDrawkeywords(String drawkeywords) {
    this.drawkeywords = drawkeywords;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getProductNameOrId() {
    return productNameOrId;
  }

  public void setProductNameOrId(String productNameOrId) {
    this.productNameOrId = productNameOrId;
  }

  public String getAgeType() {
    return ageType;
  }

  public void setAgeType(String ageType) {
    this.ageType = ageType;
  }

  public Long getTitleId() {
    return titleId;
  }

  public void setTitleId(Long titleId) {
    this.titleId = titleId;
  }

  public Double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(Double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Integer getTongji() {
    return tongji;
  }

  public void setTongji(Integer tongji) {
    this.tongji = tongji;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAgeDesc() {
    return ageDesc;
  }

  public void setAgeDesc(String ageDesc) {
    this.ageDesc = ageDesc;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getActivityType() {
    return activityType;
  }

  public void setActivityType(String activityType) {
    this.activityType = activityType;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Double getActivePrice() {
    return activePrice;
  }

  public void setActivePrice(Double activePrice) {
    this.activePrice = activePrice;
  }

  public String getProductStatus() {
    return ProductStatus;
  }

  public void setProductStatus(String productStatus) {
    ProductStatus = productStatus;
  }

  public String getTimeIdIsN() {
    return timeIdIsN;
  }

  public void setTimeIdIsN(String timeIdIsN) {
    this.timeIdIsN = timeIdIsN;
  }

  public String getBrandNames() {
    return brandNames;
  }

  public void setBrandNames(String brandNames) {
    this.brandNames = brandNames;
  }

  public String getProductType1Name() {
    return productType1Name;
  }

  public void setProductType1Name(String productType1Name) {
    this.productType1Name = productType1Name;
  }

  public String[] getFilter() {
    return filter;
  }

  public void setFilter(String[] filter) {
    this.filter = filter;
  }

  public Boolean getIsAdd() {
    return isAdd;
  }

  public void setIsAdd(Boolean isAdd) {
    this.isAdd = isAdd;
  }

  public Integer getImageCount() {
    return imageCount;
  }

  public void setImageCount(Integer imageCount) {
    this.imageCount = imageCount;
  }

  public Integer getFocusImageCount() {
    return focusImageCount;
  }

  public void setFocusImageCount(Integer focusImageCount) {
    this.focusImageCount = focusImageCount;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  public String getTvideoUrl() {
    return tvideoUrl;
  }

  public void setTvideoUrl(String tvideoUrl) {
    this.tvideoUrl = tvideoUrl;
  }

  public String getTvideoUrlImage() {
    return tvideoUrlImage;
  }

  public void setTvideoUrlImage(String tvideoUrlImage) {
    this.tvideoUrlImage = tvideoUrlImage;
  }

  public Integer getHoutai() {
    return houtai;
  }

  public void setHoutai(Integer houtai) {
    this.houtai = houtai;
  }

  public Long getFavId() {
    return favId;
  }

  public void setFavId(Long favId) {
    this.favId = favId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Long getGrouponId() {
    return grouponId;
  }

  public void setGrouponId(Long grouponId) {
    this.grouponId = grouponId;
  }
}
