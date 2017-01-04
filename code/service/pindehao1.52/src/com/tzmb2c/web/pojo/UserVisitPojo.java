package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class UserVisitPojo extends SuperPojo {

  private Integer id;
  private Integer visitTime;
  private String ip;
  private String ipDesc;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getVisitTime() {
    return visitTime;
  }

  public void setVisitTime(Integer visitTime) {
    this.visitTime = visitTime;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getIpDesc() {
    return ipDesc;
  }

  public void setIpDesc(String ipDesc) {
    this.ipDesc = ipDesc;
  }

}
