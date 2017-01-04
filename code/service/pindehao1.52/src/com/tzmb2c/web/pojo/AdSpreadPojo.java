package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;

/**
 * 
 * 广告推广表 -- ad_apread
 * 
 */

public class AdSpreadPojo extends SuperPojo {
  /**
   * ID
   */
  private Long id;
  /**
   * 广告商1-youmi
   */
  private Integer adType;
  /**
   * 渠道：1ios 2android
   */
  private Integer channel;
  /**
   * mac
   */
  private String mac;
  /**
   * 设备标识
   */
  private String imei;
  /**
   * 回调地址
   */
  private String callbackUrl;
  /**
   * 激活状态 0-未激活 1-已激活
   */
  private Integer status;
  /**
   * 激活回调结果
   */
  private String result;
  /**
   * 注册用户ID
   */
  private Long userId;
  /**
   * 安卓设备id
   */
  private String androidId;
  /**
   * 安卓广告id
   */
  private String advertisingId;
  /**
   * 安卓动作追踪 1 用户安装并激活 App 成功 2 用户在 App 内注册帐户成功 3 用户在 App 内登录帐户成功 4 用户在游戏内创建角色 5 用户在游戏内玩到某个等级
   */
  private Integer tracking;
  /**
   * 安卓应用包名
   */
  private String packageId;
  private String adTypeName;
  private String channelName;
  private String statusName;
  private String createDateStr; // 创建时间STR
  private String createStartDateStr;// 检索开始时间STR
  private String createEndDateStr; // 检索结束时间STR

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getAdType() {
    return adType;
  }

  public void setAdType(Integer adType) {
    this.adType = adType;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getAndroidId() {
    return androidId;
  }

  public void setAndroidId(String androidId) {
    this.androidId = androidId;
  }

  public String getAdvertisingId() {
    return advertisingId;
  }

  public void setAdvertisingId(String advertisingId) {
    this.advertisingId = advertisingId;
  }

  public Integer getTracking() {
    return tracking;
  }

  public void setTracking(Integer tracking) {
    this.tracking = tracking;
  }

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public String getAdTypeName() {
    return adTypeName;
  }

  public void setAdTypeName(String adTypeName) {
    this.adTypeName = adTypeName;
  }

  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  @Override
  public void setCreateDate(Date createDate) {
    super.createDate = createDate;
    if (this.createDate != null) {
      this.createDateStr = StringUtil.dateString(this.createDate);
    }
  }

  public String getCreateStartDateStr() {
    return createStartDateStr;
  }

  public void setCreateStartDateStr(String createStartDateStr) {
    this.createStartDateStr = createStartDateStr;
  }

  public String getCreateEndDateStr() {
    return createEndDateStr;
  }

  public void setCreateEndDateStr(String createEndDateStr) {
    this.createEndDateStr = createEndDateStr;
  }
}
