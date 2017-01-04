package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.OrderShipDao;
import com.tzmb2c.web.mapper.OrderShipMapper;
import com.tzmb2c.web.pojo.OrderShipPojo;

public class OrderShipDaoImpl implements OrderShipDao {

  @Autowired
  private OrderShipMapper orderShipMapper;

  @Override
  public List<OrderShipPojo> getOrderShipAll() throws SQLException {
    return orderShipMapper.getOrderShipAll();
  }

  @Override
  public int insertOrderShip(OrderShipPojo orderShipPojo) throws SQLException {
    try {
      return orderShipMapper.insertOrderShip(orderShipPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }
    return 0;
  }

  @Override
  public int updateOrderShip(OrderShipPojo orderShipPojo) throws SQLException {

    return orderShipMapper.updateOrderShip(orderShipPojo);
  }

  @Override
  public OrderShipPojo getfindByIdOrderShip(Long id) throws SQLException {
    return orderShipMapper.getfindByIdOrderShip(id);

  }

  @Override
  public OrderShipPojo findByIdOrderShip(Long id) throws SQLException {
    return orderShipMapper.findByIdOrderShip(id);

  }

  @Override
  public void deleteOrderShip(Long id) throws SQLException {
    orderShipMapper.deleteOrderShip(id);
  }

  @Override
  public int orderShipAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return orderShipMapper.orderShipAllCount(map);
  }

  @Override
  public List<OrderShipPojo> orderShipAllList(Map<String, Object> map) {
    return orderShipMapper.orderShipAllList(map);
  }

  @Override
  public void delOrderShip(Long id) throws SQLException {

    orderShipMapper.delOrderShip(id);
  }

  @Override
  public OrderShipPojo findOrderShipById(Long id) throws SQLException {
    return orderShipMapper.getfindByIdOrderShip(id);
  }

  @Override
  public int checkOrderShipExpired(Long orderId) throws SQLException {
    return orderShipMapper.checkOrderShipExpired(orderId);
  }
}
