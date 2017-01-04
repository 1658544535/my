package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.OrderRefundDao;
import com.tzmb2c.web.mapper.OrderRefundMapper;
import com.tzmb2c.web.pojo.OrderRefundPojo;

public class OrderRefundDaoImpl implements OrderRefundDao {

  @Autowired
  private OrderRefundMapper orderRefundMapper;

  @Override
  public List<OrderRefundPojo> getOrderRefundAll() throws SQLException {
    return orderRefundMapper.getOrderRefundAll();
  }

  @Override
  public void insertOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException {
    try {
      orderRefundMapper.insertOrderRefund(orderRefundPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException {

    orderRefundMapper.updateOrderRefund(orderRefundPojo);
  }

  @Override
  public OrderRefundPojo getfindByIdOrderRefund(Long id) throws SQLException {
    return orderRefundMapper.getfindByIdOrderRefund(id);

  }

  @Override
  public void deleteOrderRefund(Long id) throws SQLException {
    orderRefundMapper.deleteOrderRefund(id);
  }

  @Override
  public int orderRefundAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return orderRefundMapper.orderRefundAllCount(map);
  }

  @Override
  public List<OrderRefundPojo> orderRefundAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return orderRefundMapper.orderRefundAllList(map);
  }

  @Override
  public void delOrderRefund(Long id) throws SQLException {

    orderRefundMapper.delOrderRefund(id);
  }

  @Override
  public OrderRefundPojo findOrderRefundById(Long id) throws SQLException {
    return orderRefundMapper.getfindByIdOrderRefund(id);
  }

  @Override
  public OrderRefundPojo getorderRefundDetail(OrderRefundPojo orderRefundPojo) throws SQLException {

    return orderRefundMapper.getorderRefundDetail(orderRefundPojo);
  }

  @Override
  public void updateOrderRefundbyDetailId(OrderRefundPojo orderRefundPojo) throws SQLException {

    orderRefundMapper.updateOrderRefundbyDetailId(orderRefundPojo);
  }

  @Override
  public int getNOrefundDetail(Map<String, Object> map) throws SQLException {
    return orderRefundMapper.getNOrefundDetail(map);
  }
}
