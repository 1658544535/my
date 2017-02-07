package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.OrderDao;
import com.tzmb2c.web.mapper.OrderMapper;
import com.tzmb2c.web.pojo.OrderPojo;

public class OrderDaoImpl implements OrderDao {

  @Autowired
  private OrderMapper orderMapper;

  @Override
  public List<OrderPojo> getOrderAll() throws SQLException {
    return orderMapper.getOrderAll();
  }

  @Override
  public List<OrderPojo> userIdOrder(Map<String, Object> map) throws SQLException {
    return orderMapper.userIdOrder(map);
  }

  @Override
  public Long insertOrder(OrderPojo orderPojo) throws SQLException {
    return orderMapper.insertOrder(orderPojo);
  }

  @Override
  public int updateOrder(OrderPojo orderPojo) throws SQLException {
    return orderMapper.updateOrder(orderPojo);
  }

  @Override
  public void updateOrderPush(OrderPojo orderPojo) throws SQLException {

    orderMapper.updateOrderPush(orderPojo);
  }

  @Override
  public OrderPojo orderStatusNum(Long id) throws SQLException {
    return orderMapper.orderStatusNum(id);

  }

  @Override
  public OrderPojo getfindByIdOrder(Long id) throws SQLException {
    return orderMapper.getfindByIdOrder(id);

  }

  @Override
  public void deleteOrder(Long id) throws SQLException {
    orderMapper.deleteOrder(id);
  }

  @Override
  public int orderAllCount(Map<String, Object> map) {

    return orderMapper.orderAllCount(map);
  }

  @Override
  public int getOrderAllCount(Map<String, Object> map) throws SQLException {

    return orderMapper.getOrderAllCount(map);
  }

  @Override
  public List<OrderPojo> getOrderAllList(Map<String, Object> map) throws SQLException {

    return orderMapper.getOrderAllList(map);
  }

  @Override
  public List<OrderPojo> orderAllList(Map<String, Object> map) {

    return orderMapper.orderAllList(map);
  }

  @Override
  public int orderAllCount2(Map<String, Object> map) {

    return orderMapper.orderAllCount2(map);
  }

  @Override
  public List<OrderPojo> orderAllList2(Map<String, Object> map) {

    return orderMapper.orderAllList2(map);
  }

  @Override
  public List<OrderPojo> getPindekeRanking(Map<String, Object> map) {

    return orderMapper.getPindekeRanking(map);
  }

  @Override
  public List<OrderPojo> getPindekeMonthSale(Map<String, Object> map) {

    return orderMapper.getPindekeMonthSale(map);
  }

  @Override
  public void delOrder(Long id) throws SQLException {

    orderMapper.delOrder(id);
  }

  @Override
  public OrderPojo findOrderById(Long id) throws SQLException {
    return orderMapper.getfindByIdOrder(id);
  }

  @Override
  public OrderPojo getfindByIdOrderToPush(Long id) throws SQLException {
    return orderMapper.getfindByIdOrderToPush(id);
  }

  @Override
  public void deleteOrdership(Map<String, Object> map) throws SQLException {
    orderMapper.deleteOrdership(map);
  }

  @Override
  public void updateOrderStatus(Long id) throws SQLException {
    orderMapper.updateOrderStatus(id);
  }

  @Override
  public List<OrderPojo> orderAllListSettle(Map<String, Object> map) {

    return orderMapper.orderAllListSettle(map);
  }

  @Override
  public OrderPojo orderAllListSettleOne(Map<String, Object> map) {

    return orderMapper.orderAllListSettleOne(map);
  }

  @Override
  public List<OrderPojo> orderAllListSettleMF(Map<String, Object> map) {

    return orderMapper.orderAllListSettleMF(map);
  }

  @Override
  public List<OrderPojo> supplyOrderListWeb(Map<String, Object> map) throws SQLException {

    return orderMapper.supplyOrderListWeb(map);
  }

  @Override
  public List<OrderPojo> agentOrderListWeb(Map<String, Object> map) throws SQLException {

    return orderMapper.agentOrderListWeb(map);
  }

  @Override
  public void updateOrderStatus(OrderPojo order) {
    orderMapper.updateOrderStatusWeb(order);
  }

  @Override
  public int updateOrderStatus2(OrderPojo order) {
    return orderMapper.updateOrderStatus2(order);
  }

  @Override
  public OrderPojo findOrderByOrderNo(String orderNo) {
    return orderMapper.findOrderByOrderNo(orderNo);
  }

  @Override
  public OrderPojo findOrderByOrderNo2(Map<String, Object> map) {
    return orderMapper.findOrderByOrderNo2(map);
  }

  @Override
  public int shipOrderCountWeb(Map<String, Object> map) {

    return orderMapper.shipOrderCountWeb(map);
  }

  @Override
  public List<OrderPojo> getShipOrderAllListWeb(Map<String, Object> map) {

    return orderMapper.getShipOrderAllListWeb(map);
  }

  @Override
  public OrderPojo getfindByIdOrderWeb(Long id) throws SQLException {

    return orderMapper.getfindByIdOrderWeb(id);
  }

  @Override
  public void isDelete(OrderPojo orderPojo) throws SQLException {

    orderMapper.isDelete(orderPojo);
  }

  @Override
  public void deleteAllOrderById(String id) throws SQLException {

    orderMapper.deleteAllOrderById(id);
  }

  @Override
  public void checkAllOrderById(String id) throws SQLException {

    orderMapper.checkAllOrderById(id);
  }

  @Override
  public void isCancel(OrderPojo orderPojo) throws SQLException {

    orderMapper.isCancel(orderPojo);
  }

  @Override
  public int updateIsCancelOrder(OrderPojo orderPojo) throws SQLException {

    return orderMapper.updateIsCancelOrder(orderPojo);
  }

  @Override
  public List<OrderPojo> myWuLiuWeb(OrderPojo orderPojo) {

    return orderMapper.myWuLiuWeb(orderPojo);
  }

  @Override
  public List<OrderPojo> getOrderByoutTradeNo(String outTradeNo) {

    return orderMapper.getOrderByoutTradeNo(outTradeNo);
  }

  @Override
  public List<OrderPojo> consumerOrderListWeb(Map<String, Object> map) throws SQLException {

    return orderMapper.consumerOrderListWeb(map);
  }

  @Override
  public void orderPaySuccess(Map<String, Object> map) throws SQLException {
    orderMapper.orderPaySuccess(map);

  }

  @Override
  public List<OrderPojo> productMonthSaleList(Map<String, Object> map) throws SQLException {

    return orderMapper.productMonthSaleList(map);
  }

  @Override
  public int productMonthSaleCount(Map<String, Object> map) throws SQLException {
    return orderMapper.productMonthSaleCount(map);
  }

  @Override
  public int orderCount(Map<String, Object> map) throws SQLException {
    return orderMapper.orderCount(map);
  }

  @Override
  public int supplyOrderListWebCount(Map<String, Object> map) throws SQLException {
    return orderMapper.supplyOrderListWebCount(map);
  }

  @Override
  public void updateOrderPay(Map<String, Object> map) throws SQLException {
    orderMapper.updateOrderPay(map);

  }

  @Override
  public void updateOutTradeNo(Map<String, Object> map) throws SQLException {
    orderMapper.updateOutTradeNo(map);

  }

  @Override
  public List<OrderPojo> userIdOrderByPage(Map<String, Object> map) throws SQLException {
    return orderMapper.userIdOrderByPage(map);
  }

  @Override
  public List<OrderPojo> getPushByUidOrder(Map<String, Object> map) throws SQLException {
    return orderMapper.getPushByUidOrder(map);
  }

  @Override
  public int todayBargainNum() throws SQLException {

    return orderMapper.todayBargainNum();
  }

  @Override
  public List<OrderPojo> orderEleAllList(Map<String, Object> map) throws SQLException {

    return orderMapper.orderEleAllList(map);
  }

  @Override
  public int orderEleAllListCount(Map<String, Object> map) throws SQLException {

    return orderMapper.orderEleAllListCount(map);
  }

  @Override
  public double transactionPriceSum() throws SQLException {
    return orderMapper.transactionPriceSum();
  }

  @Override
  public void updateorders(Map<String, Object> map) throws SQLException {
    orderMapper.updateorders(map);
  }

  @Override
  public int checkUserFirstOrder(Long userId) throws SQLException {

    return orderMapper.checkUserFirstOrder(userId);
  }

  @Override
  public int isUserFirstOrder(Long orderId) throws SQLException {

    return orderMapper.isUserFirstOrder(orderId);
  }

  @Override
  public void updateFirstOrder(String outTradeNo) throws SQLException {
    orderMapper.updateFirstOrder(outTradeNo);

  }

  @Override
  public void updateOrderPrice(Map<String, Object> map) throws SQLException {
    orderMapper.updateOrderPrice(map);
  }

  @Override
  public List<OrderPojo> queryOrderSettleInfo(Map<String, Object> map) throws SQLException {
    return orderMapper.queryOrderSettleInfo(map);
  }

  @Override
  public int settleOrderById(OrderPojo orderPojo) throws SQLException {
    return orderMapper.settleOrderById(orderPojo);
  }

  @Override
  public List<OrderPojo> groupOrderSettleBySuserId(Map<String, Object> map) throws SQLException {
    return orderMapper.groupOrderSettleBySuserId(map);
  }

  @Override
  public int settleOrderByUserId(OrderPojo orderPojo) throws SQLException {
    return orderMapper.settleOrderByUserId(orderPojo);
  }

  @Override
  public int updateOrderStatusDelivery(OrderPojo orderPojo) throws SQLException {
    return orderMapper.updateOrderStatusDelivery(orderPojo);
  }

  @Override
  public List<OrderPojo> orderAllListExcel(Map<String, Object> map) {
    return orderMapper.orderAllListExcel(map);
  }

  @Override
  public int updateOrderType(Map<String, Object> map) throws SQLException {
    return orderMapper.updateOrderType(map);
  }

  @Override
  public int countBy(Map<String, Object> params) {
    return orderMapper.countBy(params);
  }

  @Override
  public List<OrderPojo> listPage(Map<String, Object> params) {
    return orderMapper.listPage(params);
  }

  @Override
  public int groupRefundCountBy(Map<String, Object> params) {
    return orderMapper.groupRefundCountBy(params);
  }

  @Override
  public List<OrderPojo> groupRefundListPage(Map<String, Object> params) {
    return orderMapper.groupRefundListPage(params);
  }

  @Override
  public int updateOrderRefund(Map<String, Object> params) throws SQLException {
    return orderMapper.updateOrderRefund(params);
  }

  @Override
  public OrderPojo findOrderByBatchNo(Map<String, Object> params) throws SQLException {
    return orderMapper.findOrderByBatchNo(params);
  }

  @Override
  public int orderCnt(Map<String, Object> params) throws SQLException {
    return orderMapper.orderCnt(params);
  }

  @Override
  public String totalSale(Map<String, Object> params) throws SQLException {
    return orderMapper.totalSale(params);
  }

  @Override
  public List<OrderPojo> exportOrderExcel(Map<String, Object> map) throws SQLException {
    return orderMapper.exportOrderExcel(map);
  }

  @Override
  public List<OrderPojo> onlyOrderTabList(Map<String, Object> params) throws SQLException {
    return orderMapper.onlyOrderTabList(params);
  }

  @Override
  public int updateSellerMessage(OrderPojo orderPojo) throws SQLException {
    return orderMapper.updateSellerMessage(orderPojo);
  }

}
