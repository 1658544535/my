package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 2014-12-15
 * 
 * @author creazylee 产品评价管理
 */

public class ProductCommentPojo extends SuperPojo {

  private Long id;// 编号ID
  private Long orderId;// 订单ID
  private String orderNo;// 订单号
  private Long productId;// 产品ID
  private Long stockId;// 库存ID
  private String productName;// 产品名称
  private String productImage;// 产品图片
  private Integer score;// 状态(取业务字典：1差评2中评3好评)
  private String scoreName;// 状态字符
  private Long userId;// 用户id
  private String userName;// 用户昵称
  private String loginName;// 用户账号
  private String companyName;// 用户公司名称
  private String comment;// 评价内容
  private Long createBy;// 创建者
  private Date createDate;// 创建时间
  private String createDateStr;// 创建日期(字符串)
  private Integer scoreTotal;// 总分数
  private Integer scoreProduct;// 宝贝与描述相符分数
  private Integer scoreService;// 卖家服务态度分数
  private Integer scoreSspeed;// 卖家发货速度分数
  private Integer scoreEspeed;// 物流发货速度分数
  private Integer scoreEservice;// 送件人态度分数
  private String size, color;
  private Integer status;// 审核状态
  private String statusName;// 审核状态名称
  private String beginTimeStr;// 评价开始时间
  private String endTimeStr;// 评价结束时间
  // sku关联id
  private Integer skuLinkId;

  public Integer getSkuLinkId() {
    return skuLinkId;
  }

  public void setSkuLinkId(Integer skuLinkId) {
    this.skuLinkId = skuLinkId;
  }

  public String getEndTimeStr() {
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public String getBeginTimeStr() {
    return beginTimeStr;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
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

  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Long getStockId() {
    return stockId;
  }

  public void setStockId(Long stockId) {
    this.stockId = stockId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getScoreName() {
    return scoreName;
  }

  public void setScoreName(String scoreName) {
    this.scoreName = scoreName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
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
    this.createDateStr = StringUtil.dateToString(this.createDate);
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
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

  public Integer getScoreTotal() {
    return scoreTotal;
  }

  public void setScoreTotal(Integer scoreTotal) {
    this.scoreTotal = scoreTotal;
  }

  public Integer getScoreProduct() {
    return scoreProduct;
  }

  public void setScoreProduct(Integer scoreProduct) {
    this.scoreProduct = scoreProduct;
  }

  public Integer getScoreService() {
    return scoreService;
  }

  public void setScoreService(Integer scoreService) {
    this.scoreService = scoreService;
  }

  public Integer getScoreSspeed() {
    return scoreSspeed;
  }

  public void setScoreSspeed(Integer scoreSspeed) {
    this.scoreSspeed = scoreSspeed;
  }

  public Integer getScoreEspeed() {
    return scoreEspeed;
  }

  public void setScoreEspeed(Integer scoreEspeed) {
    this.scoreEspeed = scoreEspeed;
  }

  public Integer getScoreEservice() {
    return scoreEservice;
  }

  public void setScoreEservice(Integer scoreEservice) {
    this.scoreEservice = scoreEservice;
  }

}
