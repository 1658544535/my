package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.OrderDetailPojo;

public interface OrderDetailMapper {


  public List<OrderDetailPojo> getOrderDetailAll() throws SQLException;

  public void insertOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException;

  public void updateOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException;

  public void updateReStatus(Map<String, Object> map) throws SQLException;

  public OrderDetailPojo getfindByIdOrderDetail(Long id) throws SQLException;

  public List<OrderDetailPojo> getfindByUserIdOrderDetail(Long id) throws SQLException;

  public List<OrderDetailPojo> getfindByUserIdOrderDetailExcel(Long id) throws SQLException;

  public List<OrderDetailPojo> getOrderDetail(Long id);

  public void deleteOrderDetail(Long id) throws SQLException;

  public int orderDetailAllCount(Map<String, Object> map);

  public List<OrderDetailPojo> orderDetailAllList(Map<String, Object> map);

  void delOrderDetail(Long id) throws SQLException;

  public OrderDetailPojo getfindByOrderIdOrderDetail(Long id) throws SQLException;

  public OrderDetailPojo getfindByOrderIdOrderDetailWeb(Long id) throws SQLException;

  public void updateReStatusmap(Map<String, Object> map) throws SQLException;


  public List<OrderDetailPojo> getOrderDetailAllbyrestatus(Map<String, Object> map)
      throws SQLException;

  // 前台调用
  public List<OrderDetailPojo> getOrderDetailWeb(Long id);

  // 根据支付流水号找到所有订单
  public List<OrderDetailPojo> getOrderDetailByOutTradeNo(String outTradeNo);

  public List<OrderDetailPojo> statisticalSell();

}
