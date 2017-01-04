package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.OrderPayinfoPojo;

public interface OrderPayinfoDao {

  public void addOrderPayInfo(OrderPayinfoPojo orderPayinfoPojo) throws SQLException;

  public void delOrderPayInfoById(String id) throws SQLException;

  public void updateOrderPayInfo(OrderPayinfoPojo orderPayinfoPojo) throws SQLException;

  public List<OrderPayinfoPojo> orderPayInfoAllList(Map<String, Object> map);

  public int orderPayInfoAllCount(Map<String, Object> map);

  public OrderPayinfoPojo findOrderPayInfoById(Long id);

  public OrderPayinfoPojo findOrderPayInfoByOrderNo(String outTradeNo);
  
  public OrderPayinfoPojo findOrderPayInfoByCmbTradeNo(String outTradeNo);

}
