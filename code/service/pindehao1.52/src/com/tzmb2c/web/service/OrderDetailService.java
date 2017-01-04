package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.OrderDetailPojo;

public interface OrderDetailService {

  public List<OrderDetailPojo> orderDetailAllService() throws SQLException;

  public void addOrderDetailService(OrderDetailPojo orderDetailPojo) throws SQLException;

  public void insertOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException;

  public void updateOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException;

  public void updateReStatus(long orderId, long reStatus) throws SQLException;

  public OrderDetailPojo getfindByIdOrderDetail(Long id) throws SQLException;

  public void deleteOrderDetail(Long orderDetailId) throws SQLException;

  public int orderDetailAllCount(OrderDetailPojo orderDetailDaoPojo);

  List<OrderDetailPojo> orderDetailAllList(OrderDetailPojo ticketRulePojo, Pager page);

  void orderDetailDeleteId(String[] tids);

  void delOrderDetail(Long id) throws SQLException;

  OrderDetailPojo findOrderDetailById(Long merId) throws SQLException;

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
