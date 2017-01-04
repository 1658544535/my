package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;

public interface WxpayOrderInfoService {

  public void insertPay(WxpayOrderInfoPojo wxpayOrderInfoPojo) throws SQLException;

  public void updatePay(WxpayOrderInfoPojo wxpayOrderInfoPojo) throws SQLException;

  public WxpayOrderInfoPojo findPayInfoByTradeNo(String outTradeNo) throws SQLException;

  public int updatePayRefund(Map<String, Object> param) throws SQLException;

  public int countBy(Map<String, Object> params) throws SQLException;

  public List<WxpayOrderInfoPojo> listPage(Map<String, Object> params) throws SQLException;
}
