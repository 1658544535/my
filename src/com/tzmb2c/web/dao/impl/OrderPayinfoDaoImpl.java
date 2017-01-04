package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.OrderPayinfoDao;
import com.tzmb2c.web.mapper.OrderPayinfoMapper;
import com.tzmb2c.web.pojo.OrderPayinfoPojo;

public class OrderPayinfoDaoImpl implements OrderPayinfoDao {

  @Autowired
  private OrderPayinfoMapper orderPayinfoMapper;

  @Override
  public void addOrderPayInfo(OrderPayinfoPojo orderPayinfo) throws SQLException {
    orderPayinfoMapper.addOrderPayInfo(orderPayinfo);
  }

  @Override
  public void delOrderPayInfoById(String id) throws SQLException {
    orderPayinfoMapper.delOrderPayInfoById(id);
  }

  @Override
  public void updateOrderPayInfo(OrderPayinfoPojo orderPayinfoPojo) throws SQLException {
    orderPayinfoMapper.updateOrderPayInfo(orderPayinfoPojo);
  }

  @Override
  public List<OrderPayinfoPojo> orderPayInfoAllList(Map<String, Object> map) {
    return orderPayinfoMapper.orderPayInfoAllList(map);
  }

  @Override
  public int orderPayInfoAllCount(Map<String, Object> map) {
    return orderPayinfoMapper.orderPayInfoAllCount(map);
  }

  @Override
  public OrderPayinfoPojo findOrderPayInfoById(Long id) {
    return orderPayinfoMapper.findOrderPayInfoById(id);
  }

  @Override
  public OrderPayinfoPojo findOrderPayInfoByOrderNo(String outTradeNo) {
    return orderPayinfoMapper.findOrderPayInfoByOrderNo(outTradeNo);
  }

  @Override
  public OrderPayinfoPojo findOrderPayInfoByCmbTradeNo(String cmbTradeNo) {
    return orderPayinfoMapper.findOrderPayInfoByCmbTradeNo(cmbTradeNo);
  }



}
