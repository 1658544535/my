package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.utils.StringUtil;

public class CouponPojo extends SuperPojo {
  private Long couponId;// 优惠券id
  private String name;// 优惠券名称
  private Integer type;// 类型，0兑换产品，1满m减n金额，2直减金额
  private String typeName;// 类型名称
  private Integer status;// 状态，0禁用，1启用
  private String statusName;// 状态名称
  private Long validStime;// 有效期开始时间
  private Long validEtime;// 有效期结束时间
  private Long createTime;// 创建时间
  private String content;// 序列化数据，内容根据type字段值而定，1为{om:订单金额,m:扣减金额}，2{m:扣减金额}
  private String pid;// 产品id
  private String n;// 数量
  private String om;// 订单金额
  private String m;// 扣减金额
  private String m2;// 扣减金
  private Date validStimeD;// 有效期开始时间
  private Date validEtimeD;// 有效期结束时间
  private Date createTimeD;// 创建时间
  private String validStimeDStr;// 有效期开始时间
  private String validEtimeDStr;// 有效期结束时间
  private String createTimeDStr;// 创建时间
  private Integer sheetNum;// 优惠券数
  private Integer surplusNum;// 可领取数
  private Integer isProduct;// 是否指定商品，0-否1-是
  private Long productId;// 商品ID
  private String productName;// 商品名称
  private Integer isDelete;// 删除状态，0-否1-是
  private String caption;// 优惠券说明
  private Integer channel;// 渠道
  private Integer surplusNumM;// 减少优惠券可领取量标记


  public Integer getSurplusNumM() {
    return surplusNumM;
  }

  public void setSurplusNumM(Integer surplusNumM) {
    this.surplusNumM = surplusNumM;
  }

  public String getUrl() {
    return ConstParam.WX_URL + "/coupon.php?id=" + StringUtil.checkVal(couponId);
  }

  public void setUrl(String url) {}

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Integer getSheetNum() {
    return sheetNum;
  }

  public void setSheetNum(Integer sheetNum) {
    this.sheetNum = sheetNum;
  }

  public Integer getSurplusNum() {
    return surplusNum;
  }

  public void setSurplusNum(Integer surplusNum) {
    this.surplusNum = surplusNum;
  }

  public Integer getIsProduct() {
    return isProduct;
  }

  public void setIsProduct(Integer isProduct) {
    this.isProduct = isProduct;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getValidStimeDStr() {
    return validStimeDStr;
  }

  public void setValidStimeDStr(String validStimeDStr) {
    this.validStimeDStr = validStimeDStr;
  }

  public String getValidEtimeDStr() {
    return validEtimeDStr;
  }

  public void setValidEtimeDStr(String validEtimeDStr) {
    this.validEtimeDStr = validEtimeDStr;
  }

  public String getCreateTimeDStr() {
    return createTimeDStr;
  }

  public void setCreateTimeDStr(String createTimeDStr) {
    this.createTimeDStr = createTimeDStr;
  }

  public Date getValidStimeD() {
    return validStimeD;
  }

  public void setValidStimeD(Date validStimeD) {
    this.validStimeD = validStimeD;
  }

  public Date getValidEtimeD() {
    return validEtimeD;
  }

  public void setValidEtimeD(Date validEtimeD) {
    this.validEtimeD = validEtimeD;
  }

  public Date getCreateTimeD() {
    return createTimeD;
  }

  public void setCreateTimeD(Date createTimeD) {
    this.createTimeD = createTimeD;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  public String getOm() {
    return om;
  }

  public void setOm(String om) {
    this.om = om;
  }

  public String getM() {
    return m;
  }

  public void setM(String m) {
    this.m = m;
  }

  public Long getCouponId() {
    return couponId;
  }

  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Long getValidStime() {
    return validStime;
  }

  public void setValidStime(Long validStime) {
    this.validStime = validStime;
  }

  public Long getValidEtime() {
    return validEtime;
  }

  public void setValidEtime(Long validEtime) {
    this.validEtime = validEtime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getM2() {
    return m2;
  }

  public void setM2(String m2) {
    this.m2 = m2;
  }

}
