package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.OrderShipPojo;

public interface OrderShipMapper {


  public List<OrderShipPojo> getOrderShipAll() throws SQLException;

  public int insertOrderShip(OrderShipPojo orderShipPojo) throws SQLException;

  public int updateOrderShip(OrderShipPojo orderShipPojo) throws SQLException;

  public OrderShipPojo getfindByIdOrderShip(Long id) throws SQLException;

  public OrderShipPojo findByIdOrderShip(Long id) throws SQLException;

  public void deleteOrderShip(Long id) throws SQLException;

  public int orderShipAllCount(Map<String, Object> map);

  public List<OrderShipPojo> orderShipAllList(Map<String, Object> map);

  void delOrderShip(Long id) throws SQLException;

  public int checkOrderShipExpired(Long orderId) throws SQLException;

}
