package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.OrderRefundPojo;

public interface OrderRefundDao {

  List<OrderRefundPojo> getOrderRefundAll() throws SQLException;

  void insertOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException;

  void updateOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException;

  OrderRefundPojo getfindByIdOrderRefund(Long id) throws SQLException;

  void deleteOrderRefund(Long id) throws SQLException;

  int orderRefundAllCount(Map<String, Object> map);

  List<OrderRefundPojo> orderRefundAllList(Map<String, Object> map);

  void delOrderRefund(Long id) throws SQLException;

  OrderRefundPojo findOrderRefundById(Long id) throws SQLException;

  /**
   * 根据订单id跟产品id查询相对应的订单退货记录
   * 
   * @param orderRefundPojo 将两个id放在对象中
   * @return
   * @throws SQLException
   */
  OrderRefundPojo getorderRefundDetail(OrderRefundPojo orderRefundPojo) throws SQLException;

  void updateOrderRefundbyDetailId(OrderRefundPojo orderRefundPojo) throws SQLException;

  int getNOrefundDetail(Map<String, Object> map) throws SQLException;
}
