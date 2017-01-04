package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.OrderRefundPojo;

public interface OrderRefundMapper {


  public List<OrderRefundPojo> getOrderRefundAll() throws SQLException;

  public void insertOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException;

  public void updateOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException;

  public OrderRefundPojo getfindByIdOrderRefund(Long id) throws SQLException;

  public void deleteOrderRefund(Long id) throws SQLException;

  public int orderRefundAllCount(Map<String, Object> map);

  public List<OrderRefundPojo> orderRefundAllList(Map<String, Object> map);

  void delOrderRefund(Long id) throws SQLException;

  // 根据订单id跟产品id查询相对应的订单退货记录
  public OrderRefundPojo getorderRefundDetail(OrderRefundPojo orderRefundPojo) throws SQLException;

  public void updateOrderRefundbyDetailId(OrderRefundPojo orderRefundPojo) throws SQLException;

  public int getNOrefundDetail(Map<String, Object> map);
}
