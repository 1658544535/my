package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.OrderPayinfoDao;
import com.tzmb2c.web.pojo.OrderPayinfoPojo;
import com.tzmb2c.web.service.OrderPayinfoService;

public class OrderPayinfoServiceImpl implements OrderPayinfoService {

  @Autowired
  private OrderPayinfoDao orderPayinfoDao;

  @Override
  public void addOrderPayInfo(OrderPayinfoPojo orderPayinfo) throws SQLException {
    orderPayinfoDao.addOrderPayInfo(orderPayinfo);
  }

  @Override
  public void delOrderPayInfoById(String id) throws SQLException {
    orderPayinfoDao.delOrderPayInfoById(id);
  }

  @Override
  public List<OrderPayinfoPojo> orderPayInfoAllList(Map<String, Object> map) {
    return orderPayinfoDao.orderPayInfoAllList(map);
  }

  @Override
  public int orderPayInfoAllCount(Map<String, Object> map) {
    return orderPayinfoDao.orderPayInfoAllCount(map);
  }

  @Override
  public OrderPayinfoPojo findOrderPayInfoById(Long id) {
    return orderPayinfoDao.findOrderPayInfoById(id);
  }

  @Override
  public OrderPayinfoPojo findOrderPayInfoByOrderNo(String outTradeNo) {
    return orderPayinfoDao.findOrderPayInfoByOrderNo(outTradeNo);
  }

  @Override
  public void updateOrderPayInfo(OrderPayinfoPojo orderPayinfoPojo) throws SQLException {
    orderPayinfoDao.updateOrderPayInfo(orderPayinfoPojo);
  }

  @Override
  public OrderPayinfoPojo findOrderPayInfoByCmbTradeNo(String cmbTradeNo) {
    return orderPayinfoDao.findOrderPayInfoByCmbTradeNo(cmbTradeNo);
  }


}
