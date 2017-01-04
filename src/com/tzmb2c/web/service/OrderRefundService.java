package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.OrderRefundPojo;

public interface OrderRefundService {

  public List<OrderRefundPojo> orderRefundAllService() throws SQLException;

  public void addOrderRefundService(OrderRefundPojo orderRefundPojo) throws SQLException;

  public void insertOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException;

  public void updateOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException;

  public OrderRefundPojo getfindByIdOrderRefund(Long id) throws SQLException;

  public void deleteOrderRefund(Long orderRefundId) throws SQLException;

  public int orderRefundAllCount(OrderRefundPojo orderRefundDaoPojo);

  List<OrderRefundPojo> orderRefundAllList(OrderRefundPojo ticketRulePojo, Pager page);

  void orderRefundDeleteId(String[] tids);

  void delOrderRefund(Long id) throws SQLException;

  OrderRefundPojo findOrderRefundById(Long merId) throws SQLException;

  /**
   * 根据订单id跟产品id查询相对应的订单退货记录
   * 
   * @param orderRefundDaoPojo
   * @return
   * @throws SQLException
   */
  public OrderRefundPojo getorderRefundDetail(OrderRefundPojo orderRefundDaoPojo)
      throws SQLException;

  void updateOrderRefundbyDetailId(OrderRefundPojo orderRefundPojo) throws SQLException;

  int getNOrefundDetail(Map<String, Object> map) throws SQLException;
}
