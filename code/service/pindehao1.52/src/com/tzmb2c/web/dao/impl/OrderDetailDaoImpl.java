package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.OrderDetailDao;
import com.tzmb2c.web.mapper.OrderDetailMapper;
import com.tzmb2c.web.pojo.OrderDetailPojo;

public class OrderDetailDaoImpl implements OrderDetailDao {

  @Autowired
  private OrderDetailMapper orderDetailMapper;

  @Override
  public List<OrderDetailPojo> getOrderDetailAll() throws SQLException {
    return orderDetailMapper.getOrderDetailAll();
  }

  @Override
  public void insertOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException {
    try {
      orderDetailMapper.insertOrderDetail(orderDetailPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException {

    orderDetailMapper.updateOrderDetail(orderDetailPojo);
  }

  @Override
  public OrderDetailPojo getfindByIdOrderDetail(Long id) throws SQLException {
    return orderDetailMapper.getfindByIdOrderDetail(id);

  }

  @Override
  public List<OrderDetailPojo> getOrderDetail(Long id) {
    return orderDetailMapper.getOrderDetail(id);

  }

  @Override
  public void deleteOrderDetail(Long id) throws SQLException {
    orderDetailMapper.deleteOrderDetail(id);
  }

  @Override
  public int orderDetailAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return orderDetailMapper.orderDetailAllCount(map);
  }

  @Override
  public List<OrderDetailPojo> orderDetailAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return orderDetailMapper.orderDetailAllList(map);
  }

  @Override
  public void delOrderDetail(Long id) throws SQLException {

    orderDetailMapper.delOrderDetail(id);
  }

  @Override
  public OrderDetailPojo findOrderDetailById(Long id) throws SQLException {
    return orderDetailMapper.getfindByIdOrderDetail(id);
  }

  @Override
  public List<OrderDetailPojo> getfindByUserIdOrderDetail(Long id) throws SQLException {
    return orderDetailMapper.getfindByUserIdOrderDetail(id);
  }

  @Override
  public List<OrderDetailPojo> getOrderDetailWeb(Long id) {
    // TODO Auto-generated method stub
    return orderDetailMapper.getOrderDetailWeb(id);
  }

  @Override
  public OrderDetailPojo getfindByOrderIdOrderDetail(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return orderDetailMapper.getfindByOrderIdOrderDetail(id);
  }

  @Override
  public OrderDetailPojo getfindByOrderIdOrderDetailWeb(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return orderDetailMapper.getfindByOrderIdOrderDetailWeb(id);
  }

  @Override
  public void updateReStatus(Map<String, Object> map) throws SQLException {
    orderDetailMapper.updateReStatus(map);
  }

  @Override
  public void updateReStatusmap(Map<String, Object> map) throws SQLException {
    orderDetailMapper.updateReStatusmap(map);
  }

  @Override
  public List<OrderDetailPojo> getOrderDetailAllbyrestatus(Map<String, Object> map)
      throws SQLException {
    return orderDetailMapper.getOrderDetailAllbyrestatus(map);
  }

  @Override
  public List<OrderDetailPojo> getOrderDetailByOutTradeNo(String outTradeNo) {
    return orderDetailMapper.getOrderDetailByOutTradeNo(outTradeNo);
  }

  @Override
  public List<OrderDetailPojo> statisticalSell() {
    return orderDetailMapper.statisticalSell();
  }

  @Override
  public List<OrderDetailPojo> getfindByUserIdOrderDetailExcel(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return orderDetailMapper.getfindByUserIdOrderDetailExcel(id);
  }
}
