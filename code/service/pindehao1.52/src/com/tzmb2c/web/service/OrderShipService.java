package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.OrderShipPojo;

public interface OrderShipService {

  public List<OrderShipPojo> orderShipAllService() throws SQLException;

  public void addOrderShipService(OrderShipPojo orderShipPojo) throws SQLException;

  public int insertOrderShip(OrderShipPojo orderShipPojo) throws SQLException;

  public int updateOrderShip(OrderShipPojo orderShipPojo) throws SQLException;

  public OrderShipPojo getfindByIdOrderShip(Long id) throws SQLException;

  public void deleteOrderShip(Long orderShipId) throws SQLException;

  public int orderShipAllCount(OrderShipPojo orderShipDaoPojo);

  List<OrderShipPojo> orderShipAllList(OrderShipPojo ticketRulePojo, Pager page);

  void orderShipDeleteId(String[] tids);

  void delOrderShip(Long id) throws SQLException;

  OrderShipPojo findOrderShipById(Long merId) throws SQLException;

  OrderShipPojo findByIdOrderShip(Long id) throws SQLException;

  public int isOrderShipExpiredByOrderId(Long orderId) throws SQLException;
}
