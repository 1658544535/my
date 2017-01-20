package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.CartPojo;
import com.tzmb2c.web.pojo.OrderPojo;

public interface OrderMapper {


  public List<OrderPojo> getOrderAll() throws SQLException;

  public Long insertOrder(OrderPojo orderPojo) throws SQLException;

  public int updateOrder(OrderPojo orderPojo) throws SQLException;

  public void updateOrderPush(OrderPojo orderPojo) throws SQLException;

  public int updateIsCancelOrder(OrderPojo orderPojo) throws SQLException;

  public void isDelete(OrderPojo orderPojo) throws SQLException;

  public void checkAllOrderById(String id) throws SQLException;

  public void isCancel(OrderPojo orderPojo) throws SQLException;

  public void deleteAllOrderById(String id) throws SQLException;

  public void updateOrderStatus(Long id) throws SQLException;

  public OrderPojo orderStatusNum(Long id) throws SQLException;

  public OrderPojo getfindByIdOrder(Long id) throws SQLException;

  public OrderPojo getfindByIdOrderToPush(Long id) throws SQLException;

  public void deleteOrdership(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> userIdOrder(Map<String, Object> map) throws SQLException;

  public void deleteOrder(Long id) throws SQLException;

  public int orderAllCount(Map<String, Object> map);

  public int getOrderAllCount(Map<String, Object> map);

  public List<OrderPojo> getOrderAllList(Map<String, Object> map);

  public List<OrderPojo> orderAllList(Map<String, Object> map);

  public int orderAllCount2(Map<String, Object> map);

  public List<OrderPojo> orderAllList2(Map<String, Object> map);

  public List<OrderPojo> getPindekeRanking(Map<String, Object> map);

  public List<OrderPojo> getPindekeMonthSale(Map<String, Object> map);
  
  public List<OrderPojo> orderAllListExcel(Map<String, Object> map);

  public List<OrderPojo> exportOrderExcel(Map<String, Object> map) throws SQLException;

  void delOrder(Long id) throws SQLException;

  public List<OrderPojo> orderAllListSettle(Map<String, Object> map);

  public List<OrderPojo> orderAllListSettleMF(Map<String, Object> map);

  public OrderPojo orderAllListSettleOne(Map<String, Object> map);

  // ---前端调用---//
  public List<OrderPojo> supplyOrderListWeb(Map<String, Object> map);

  // ---批发商调用---//
  public List<OrderPojo> agentOrderListWeb(Map<String, Object> map);

  public void updateOrderStatusWeb(OrderPojo order);

  public int updateOrderStatus2(OrderPojo order);

  public List<CartPojo> getCartsByIds(String ids) throws SQLException;

  public OrderPojo findOrderByOrderNo(String orderNo);

  public int shipOrderCountWeb(Map<String, Object> map);

  public List<OrderPojo> getShipOrderAllListWeb(Map<String, Object> map);

  public OrderPojo getfindByIdOrderWeb(Long id) throws SQLException;

  public List<OrderPojo> myWuLiuWeb(OrderPojo orderPojo);

  public List<OrderPojo> getOrderByoutTradeNo(String outTradeNo);

  public List<OrderPojo> consumerOrderListWeb(Map<String, Object> map);

  public void orderPaySuccess(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> productMonthSaleList(Map<String, Object> map) throws SQLException;

  public int productMonthSaleCount(Map<String, Object> map) throws SQLException;

  public int orderCount(Map<String, Object> map) throws SQLException;

  public int supplyOrderListWebCount(Map<String, Object> map) throws SQLException;

  public void updateOrderPay(Map<String, Object> map) throws SQLException;

  public void updateOutTradeNo(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> userIdOrderByPage(Map<String, Object> map);

  public List<OrderPojo> getPushByUidOrder(Map<String, Object> map);

  public int todayBargainNum() throws SQLException;

  public List<OrderPojo> orderEleAllList(Map<String, Object> map) throws SQLException;

  public int orderEleAllListCount(Map<String, Object> map) throws SQLException;

  public double transactionPriceSum() throws SQLException;

  public void updateorders(Map<String, Object> map) throws SQLException;

  public int checkUserFirstOrder(Long userId) throws SQLException;

  public int isUserFirstOrder(Long orderId) throws SQLException;

  public void updateFirstOrder(String outTradeNo) throws SQLException;

  public void updateOrderPrice(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> queryOrderSettleInfo(Map<String, Object> map) throws SQLException;

  public List<OrderPojo> groupOrderSettleBySuserId(Map<String, Object> map) throws SQLException;

  public int settleOrderByUserId(OrderPojo orderPojo) throws SQLException;

  public int settleOrderById(OrderPojo orderPojo) throws SQLException;

  public int updateOrderStatusDelivery(OrderPojo orderPojo) throws SQLException;

  public int updateOrderType(Map<String, Object> map) throws SQLException;

  public int countBy(Map<String, Object> params);

  public List<OrderPojo> listPage(Map<String, Object> params);

  public int groupRefundCountBy(Map<String, Object> params);

  public List<OrderPojo> groupRefundListPage(Map<String, Object> params);

  public List<OrderPojo> onlyOrderTabList(Map<String, Object> params) throws SQLException;

  /**
   * 订单退款更新.
   * 
   * @param params
   * @return
   * @throws SQLException
   * @throw
   * @return int
   */
  public int updateOrderRefund(Map<String, Object> params) throws SQLException;

  public OrderPojo findOrderByBatchNo(Map<String, Object> params) throws SQLException;

  /**
   * 当日订单量
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return int
   */
  public int orderCnt(Map<String, Object> params) throws SQLException;

  /**
   * 当日订单总销售额
   * 
   * @return
   * @throws SQLException
   * @throw
   * @return String
   */
  public String totalSale(Map<String, Object> params) throws SQLException;
}
