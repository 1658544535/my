package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.OrderDetailPojo;

public interface OrderDetailDao {

  List<OrderDetailPojo> getOrderDetailAll() throws SQLException;

  void insertOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException;

  void updateOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException;

  void updateReStatus(Map<String, Object> map) throws SQLException;

  OrderDetailPojo getfindByIdOrderDetail(Long id) throws SQLException;

  void deleteOrderDetail(Long id) throws SQLException;

  int orderDetailAllCount(Map<String, Object> map);

  List<OrderDetailPojo> orderDetailAllList(Map<String, Object> map);

  void delOrderDetail(Long id) throws SQLException;

  OrderDetailPojo findOrderDetailById(Long id) throws SQLException;

  List<OrderDetailPojo> getOrderDetail(Long id);

  List<OrderDetailPojo> getfindByUserIdOrderDetail(Long id) throws SQLException;

  List<OrderDetailPojo> getfindByUserIdOrderDetailExcel(Long id) throws SQLException;

  public OrderDetailPojo getfindByOrderIdOrderDetail(Long id) throws SQLException;

  public OrderDetailPojo getfindByOrderIdOrderDetailWeb(Long id) throws SQLException;

  // 前台调用
  public List<OrderDetailPojo> getOrderDetailWeb(Long id);

  void updateReStatusmap(Map<String, Object> map) throws SQLException;

  List<OrderDetailPojo> getOrderDetailAllbyrestatus(Map<String, Object> map) throws SQLException;

  public List<OrderDetailPojo> getOrderDetailByOutTradeNo(String outTradeNo);

  public List<OrderDetailPojo> statisticalSell();

}
