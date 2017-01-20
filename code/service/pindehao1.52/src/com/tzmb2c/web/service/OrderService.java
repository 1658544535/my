package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.UserOrderDetailPojo;

public interface OrderService {

  public List<OrderPojo> orderAllService() throws SQLException;

  public void addOrderService(OrderPojo orderPojo) throws SQLException;

  public Long insertOrder(OrderPojo orderPojo) throws SQLException;

  public int updateOrder(OrderPojo orderPojo) throws SQLException;

  public void updateOrderPush(OrderPojo orderPojo) throws SQLException;

  public int updateIsCancelOrder(OrderPojo orderPojo) throws SQLException;

  public void isDelete(OrderPojo orderPojo) throws SQLException;

  public void checkAllOrderById(String[] tids);

  public void isCancel(OrderPojo orderPojo) throws SQLException;

  public void deleteAllOrderById(String[] tids);

  public OrderPojo getfindByIdOrder(Long id) throws SQLException;

  public void deleteOrdership(Map<String, Object> map) throws SQLException;

  public void deleteOrder(Long orderId) throws SQLException;

  void orderDeleteId(String[] tids);

  void delOrder(Long id) throws SQLException;

  OrderPojo findOrderById(Long merId) throws SQLException;

  OrderPojo getfindByIdOrderToPush(Long merId) throws SQLException;

  void updateOrderStatus(Long id) throws SQLException;

  List<OrderPojo> orderAllList(OrderPojo orderDaoPojo, Pager page, String os);

  int orderAllCount(OrderPojo orderDaoPojo, String os);

  List<OrderPojo> orderAllListSettle(OrderPojo orderDaoPojo, Pager page, String os,
      String beganday, String endday, String userName);

  public Object orderAllListSettleMF(OrderPojo order, Pager page, String beganday, String endday,
      String userName);

  List<OrderPojo> userIdOrder(OrderPojo orderDaoPojo) throws SQLException;

  // ---前端调用---//
  public List<OrderPojo> supplyOrderListWeb(Map<String, Object> map) throws SQLException;

  // ---批发商调用---//
  public List<OrderPojo> agentOrderListWeb(Map<String, Object> map) throws SQLException;

  OrderPojo orderAllListSettleOne(String beganday, String endday, String userName);

  void updateOrderStatus(OrderPojo order);

  OrderPojo findOrderByOrderNo(String orderNo);

  OrderPojo orderStatusNum(Long id) throws SQLException;

  public int shipOrderCountWeb(OrderPojo orderDaoPojo);

  public List<OrderPojo> getShipOrderAllListWeb(OrderPojo orderDaoPojo, Pager page);

  public OrderPojo getfindByIdOrderWeb(Long id) throws SQLException;

  public List<OrderPojo> myWuLiuWeb(OrderPojo orderPojo);

  public List<OrderPojo> getOrderByoutTradeNo(String outTradeNo);

  public List<OrderPojo> consumerOrderListWeb(Map<String, Object> map) throws SQLException;

  public void orderPaySuccess(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> productMonthSaleList(UserOrderDetailPojo userOrderDetailPojo, Pager page,
      String beganday, String endday) throws SQLException;

  public int productMonthSaleCount(UserOrderDetailPojo orderDetailPojo, String beganday,
      String endday) throws SQLException;

  public int orderCount(Map<String, Object> map) throws SQLException;

  public int supplyOrderListWebCount(Map<String, Object> map) throws SQLException;

  int updateOrderStatus2(OrderPojo order);

  public void updateOutTradeNo(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> userIdOrderByPage(OrderPojo orderDaoPojo, Pager page) throws SQLException;

  public List<OrderPojo> getPushByUidOrder(OrderPojo orderDaoPojo, Pager page) throws SQLException;

  public int todayBargainNum() throws SQLException;

  List<OrderPojo> orderEleAllList(Map<String, Object> map) throws SQLException;

  int orderEleAllListCount(Map<String, Object> map) throws SQLException;

  public double transactionPriceSum() throws SQLException;

  List<OrderPojo> orderAllList2(Map<String, Object> map);

  List<OrderPojo> getPindekeRanking(Map<String, Object> map);
  
  List<OrderPojo> getPindekeMonthSale(Map<String, Object> map);
  
  public List<OrderPojo> orderAllListExcel(Map<String, Object> map);

  int orderAllCount2(Map<String, Object> map);

  void updateorders(Map<String, Object> map) throws SQLException;

  // 首单处理
  public int checkUserFirstOrder(Long userId) throws SQLException;

  public int isUserFirstOrder(Long orderId) throws SQLException;

  public void updateFirstOrder(String outTradeNo) throws SQLException;

  public void updateOrderPrice(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> queryOrderSettleInfo(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> groupOrderSettleBySuserId(Map<String, Object> map) throws SQLException;

  public int settleOrderByUserId(OrderPojo orderPojo) throws SQLException;

  public int settleOrderById(OrderPojo orderPojo) throws SQLException;

  public int updateOrderStatusDelivery(OrderPojo orderPojo) throws SQLException;

  public List<OrderPojo> getOrderAllList(Map<String, Object> map) throws SQLException;

  public int getOrderAllCount(Map<String, Object> map) throws SQLException;

  public int updateOrderType(Map<String, Object> map) throws SQLException;

  public int countBy(Map<String, Object> params);

  public int groupRefundCountBy(Map<String, Object> params);

  public List<OrderPojo> listPage(Map<String, Object> params);

  public List<OrderPojo> groupRefundListPage(Map<String, Object> params);

  public int updateOrderRefund(Map<String, Object> params) throws SQLException;

  /**
   * 根据退款批次号查询订单
   * 
   * @param params
   * @return
   */
  public OrderPojo findOrderByBatchNo(Map<String, Object> params) throws SQLException;

  public int orderCnt(Map<String, Object> params) throws SQLException;

  public String totalSale(Map<String, Object> params) throws SQLException;

  public List<OrderPojo> exportOrderExcel(Map<String, Object> map) throws SQLException;

  /**
   * 只查订单表
   * 
   * @param params
   * @return
   * @throws SQLException
   */
  public List<OrderPojo> onlyOrderTabList(Map<String, Object> params) throws SQLException;
}
