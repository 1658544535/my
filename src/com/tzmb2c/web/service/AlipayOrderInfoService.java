package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.Map;

import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;

public interface AlipayOrderInfoService {

  public void insertOne(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException;

  public void updateOrder(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException;

  public void updatePayType(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException;

  public AlipayOrderInfoPojo findPayInfoByTradeNo(String outTradeNo) throws SQLException;

  public int updatePayRefund(Map<String, Object> param) throws SQLException;

  /**
   * 根据退款批次号查询支付宝订单
   * 
   * @param params
   * @return
   */
  public AlipayOrderInfoPojo findOrderInfoByBatchNo(Map<String, Object> params) throws SQLException;
}
