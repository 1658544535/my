package com.tzmb2c.web.pojo;

import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 登录信息记录-- sys_login_log by Lie
 * 
 */

public class ProductAccessLogPojo extends SuperPojo {
  private Long id;// 编号
  private Long productId;// 产品id
  private Long userId;// 访问者用户id
  private String path;// 访问路径
  private Date pathDate;// 访问时间
  private String userName, productName, nums;
  private String path_datestr;// 访问时间字符串

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Date getPathDate() {
    return pathDate;
  }

  public void setPathDate(Date pathDate) {
    this.pathDate = pathDate;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getNums() {
    return nums;
  }

  public void setNums(String nums) {
    this.nums = nums;
  }

  public String getPath_datestr() {
    return path_datestr;
  }

  public void setPath_datestr(String path_datestr) {
    this.path_datestr = path_datestr;
  }

}
