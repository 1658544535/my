package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class TempDataPojo extends SuperPojo {
  private int id;
  private String realName;
  private String address;
  private String phone;


  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
