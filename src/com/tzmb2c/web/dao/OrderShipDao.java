package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.OrderShipPojo;

public interface OrderShipDao {

  List<OrderShipPojo> getOrderShipAll() throws SQLException;

  int insertOrderShip(OrderShipPojo orderShipPojo) throws SQLException;

  int updateOrderShip(OrderShipPojo orderShipPojo) throws SQLException;

  OrderShipPojo getfindByIdOrderShip(Long id) throws SQLException;

  void deleteOrderShip(Long id) throws SQLException;

  int orderShipAllCount(Map<String, Object> map);

  List<OrderShipPojo> orderShipAllList(Map<String, Object> map);

  void delOrderShip(Long id) throws SQLException;

  OrderShipPojo findOrderShipById(Long id) throws SQLException;

  OrderShipPojo findByIdOrderShip(Long id) throws SQLException;

  int checkOrderShipExpired(Long orderId) throws SQLException;

}
