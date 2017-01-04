package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 
 * 电商订单--ele_order by czp
 * 
 */

public class EleorderPojo extends SuperPojo {
  private int id;
  private String orderNo;
  private String productId;
  private int num;
  private String productName;
  private double pay;
  private String consigneeAddress;
  private String consignee;
  private String consigneePhone;
  private String createDateStr;
  private String espressPrice;
  private int weight;
  private String espress;
  private String espressNo;
  private String allnum;
  private String allpay;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getPay() {
    return pay;
  }

  public void setPay(double pay) {
    this.pay = pay;
  }

  public String getConsigneeAddress() {
    return consigneeAddress;
  }

  public void setConsigneeAddress(String consigneeAddress) {
    this.consigneeAddress = consigneeAddress;
  }

  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  public String getConsigneePhone() {
    return consigneePhone;
  }

  public void setConsigneePhone(String consigneePhone) {
    this.consigneePhone = consigneePhone;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  public String getEspressPrice() {
    return espressPrice;
  }

  public void setEspressPrice(String espressPrice) {
    this.espressPrice = espressPrice;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public String getEspress() {
    return espress;
  }

  public void setEspress(String espress) {
    this.espress = espress;
  }

  public String getEspressNo() {
    return espressNo;
  }

  public void setEspressNo(String espressNo) {
    this.espressNo = espressNo;
  }

  public String getAllnum() {
    return allnum;
  }

  public void setAllnum(String allnum) {
    this.allnum = allnum;
  }

  public String getAllpay() {
    return allpay;
  }

  public void setAllpay(String allpay) {
    this.allpay = allpay;
  }


}
