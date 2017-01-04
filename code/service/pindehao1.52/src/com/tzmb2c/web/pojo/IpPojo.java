package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 用户ip-- user_visit by chenzhipeng
 * 
 */

public class IpPojo extends SuperPojo {
  private int id;
  private String ip;
  private String visitTime;
  private String ipDesc;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getVisitTime() {
    return visitTime;
  }

  public void setVisitTime(String visitTime) {
    this.visitTime = visitTime;
  }

  public String getIpDesc() {
    return ipDesc;
  }

  public void setIpDesc(String ipDesc) {
    this.ipDesc = ipDesc;
  }

}
