package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class UserCouponPojo extends SuperPojo {

  private String couponNo;// 优惠券码
  private Long userId;// 会员id
  private Long couponId;// 优惠券id
  private Integer status;// 状态，0禁用，1启用
  private String statusName;// 状态名称
  private Long genTime;// 生成时间
  private Integer used;// 是否已使用，0否1是
  private String usedName;// 使用状态
  private Long useTime;// 使用时间
  private Long validstime;// 有效开始时间
  private Long validetime;// 有效结束时间

  private String couponName;// 优惠券名称
  private Integer type;// 类型

  private String genTimeDStr;// 生成时间
  private String useTimeDStr;// 使用时间

  private String userName;// 会员名称
  private String typeName;// 状态名称

  private String validstimeStr;// 有效开始时间
  private String validetimeStr;// 有效结束时间
  private Integer source;// 优惠券来源
  private String content;// 优惠券内容
  private Integer versions;// 用于修改判断
  private Long productId;// 商品ID
  private Integer isProduct;// 是否指定商品
  private Long sourceId;// 优惠券来源ID
  private Integer num;  // 批量生成优惠券数量
  private String loginname; // 用户账户


  public Long getSourceId() {
    return sourceId;
  }

  public void setSourceId(Long sourceId) {
    this.sourceId = sourceId;
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public String getValidstimeStr() {
    return validstimeStr;
  }

  public void setValidstimeStr(String validstimeStr) {
    this.validstimeStr = validstimeStr;
  }

  public String getValidetimeStr() {
    return validetimeStr;
  }

  public void setValidetimeStr(String validetimeStr) {
    this.validetimeStr = validetimeStr;
  }


  public Long getValidstime() {
    return validstime;
  }

  public void setValidstime(Long validstime) {
    this.validstime = validstime;
  }

  public Long getValidetime() {
    return validetime;
  }

  public void setValidetime(Long validetime) {
    this.validetime = validetime;
  }

  public String getGenTimeDStr() {
    return genTimeDStr;
  }

  public void setGenTimeDStr(String genTimeDStr) {
    this.genTimeDStr = genTimeDStr;
  }

  public String getUseTimeDStr() {
    return useTimeDStr;
  }

  public void setUseTimeDStr(String useTimeDStr) {
    this.useTimeDStr = useTimeDStr;
  }

  public String getCouponNo() {
    return couponNo;
  }

  public void setCouponNo(String couponNo) {
    this.couponNo = couponNo;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getCouponId() {
    return couponId;
  }

  public void setCouponId(Long couponId) {
    this.couponId = couponId;
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

  public Long getGenTime() {
    return genTime;
  }

  public void setGenTime(Long genTime) {
    this.genTime = genTime;
  }

  public Integer getUsed() {
    return used;
  }

  public void setUsed(Integer used) {
    this.used = used;
  }

  public String getUsedName() {
    return usedName;
  }

  public void setUsedName(String usedName) {
    this.usedName = usedName;
  }

  public Long getUseTime() {
    return useTime;
  }

  public void setUseTime(Long useTime) {
    this.useTime = useTime;
  }

  public String getCouponName() {
    return couponName;
  }

  public void setCouponName(String couponName) {
    this.couponName = couponName;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Integer getVersions() {
    return versions;
  }

  public void setVersions(Integer versions) {
    this.versions = versions;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }
}
