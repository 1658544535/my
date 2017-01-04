package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.OrderPayinfoPojo;

public interface OrderPayinfoService {

  public void addOrderPayInfo(OrderPayinfoPojo orderPayinfoPojo) throws SQLException;

  public void delOrderPayInfoById(String id) throws SQLException;

  public void updateOrderPayInfo(OrderPayinfoPojo orderPayinfoPojo) throws SQLException;

  public List<OrderPayinfoPojo> orderPayInfoAllList(Map<String, Object> map);

  public int orderPayInfoAllCount(Map<String, Object> map);

  public OrderPayinfoPojo findOrderPayInfoById(Long id);
  /**
   * 
   * 根据outTradeNo查找一条数据
   * @param outTradeNo
   * @return
   * @throw
   * @return OrderPayinfoPojo
   */
  public OrderPayinfoPojo findOrderPayInfoByOrderNo(String outTradeNo);
  /**
   * 
   * 根据CmbTradeNo查找一条数据
   * @param outTradeNo
   * @return
   * @throw
   * @return OrderPayinfoPojo
   */
  public OrderPayinfoPojo findOrderPayInfoByCmbTradeNo(String cmbTradeNo);

}
