package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.tzmb2c.web.pojo.AlipayOrderInfoPojo;

public interface AlipayOrderInfoMapper {

  public void insertOne(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException;

  public void updateOrder(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException;

  public void updatePayType(AlipayOrderInfoPojo alipayOrderInfoPojo) throws SQLException;

  public AlipayOrderInfoPojo findPayInfoByTradeNo(String outTradeNo) throws SQLException;

  /**
   * 更新退款信息.
   * 
   * @param param
   * @return
   * @throws SQLException
   * @throw
   * @return int
   */
  public int updatePayRefund(Map<String, Object> param) throws SQLException;

  public AlipayOrderInfoPojo findOrderInfoByBatchNo(Map<String, Object> params) throws SQLException;

}
