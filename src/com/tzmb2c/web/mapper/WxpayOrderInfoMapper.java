package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.WxpayOrderInfoPojo;

public interface WxpayOrderInfoMapper {

  public void insertPay(WxpayOrderInfoPojo wxpayOrderInfoPojo) throws SQLException;

  public void updatePay(WxpayOrderInfoPojo wxpayOrderInfoPojo) throws SQLException;

  public WxpayOrderInfoPojo findPayInfoByTradeNo(String outTradeNo) throws SQLException;

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

  int countBy(Map<String, Object> params) throws SQLException;

  List<WxpayOrderInfoPojo> listPage(Map<String, Object> params) throws SQLException;
}
