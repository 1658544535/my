package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.OrderDao;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.UserOrderDetailPojo;
import com.tzmb2c.web.service.OrderService;

public class OrderServiceImpl implements OrderService {
  @Autowired
  private OrderDao orderDao;

  public void setOrderDao(OrderDao orderDao) {
    this.orderDao = orderDao;
  }

  @Override
  public List<OrderPojo> orderAllService() throws SQLException {
    return orderDao.getOrderAll();
  }

  @Override
  public List<OrderPojo> userIdOrder(OrderPojo orderDaoPojo) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderDaoPojo != null) {
      map.put("userId", orderDaoPojo.getUserId());
      map.put("orderStatus", orderDaoPojo.getOrderStatus());
    }
    return orderDao.userIdOrder(map);
  }

  @Override
  public List<OrderPojo> userIdOrderByPage(OrderPojo orderDaoPojo, Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderDaoPojo != null) {
      map.put("userId", orderDaoPojo.getUserId());
      map.put("orderStatus", orderDaoPojo.getOrderStatus());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return orderDao.userIdOrderByPage(map);
  }


  @Override
  public List<OrderPojo> getPushByUidOrder(OrderPojo orderDaoPojo, Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderDaoPojo != null) {
      map.put("pushAgencyUid", orderDaoPojo.getPushAgencyUid());

    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return orderDao.getPushByUidOrder(map);
  }

  @Override
  public void addOrderService(OrderPojo orderPojo) throws SQLException {

    /*
     * if("0".equals(orderPojo.getOrderLevel())){ OrderPojo rsOrderPojo =
     * orderDao.getOrderCountByOrderLevel(orderPojo.getLevel()); int orderId =0;
     * if(rsOrderPojo.getOrderCount() > -1 && rsOrderPojo.getOrderCount() <= 9){ orderId =
     * rsOrderPojo.getOrderCount()+1; orderPojo.setOrderId("A0" + orderId); }else
     * if(rsOrderPojo.getOrderCount() > 9 && rsOrderPojo.getOrderCount() <= 99){ orderId =
     * rsOrderPojo.getOrderCount()+1; orderPojo.setOrderId("A" +orderId ); }
     * //orderPojo.setOrderPath("#"); }else{ OrderPojo rsOrderPojo =
     * orderDao.getOrderCountByOrderLevel(orderPojo.getLevel()); if(rsOrderPojo.getOrderCount() > -1
     * && rsOrderPojo.getOrderCount() <= 9){ orderPojo.setOrderId(orderPojo.getLevel()+ "B0" +
     * (rsOrderPojo.getOrderCount() + 1)); }else if(rsOrderPojo.getOrderCount() > 9 &&
     * rsOrderPojo.getOrderCount() <= 99){ orderPojo.setOrderId(orderPojo.getLevel()+ "B" +
     * (rsOrderPojo.getOrderCount() + 1)); } }
     */
    orderDao.insertOrder(orderPojo);
  }

  @Override
  public Long insertOrder(OrderPojo orderPojo) throws SQLException {

    return orderDao.insertOrder(orderPojo);
  }

  @Override
  public void updateOrderStatus(Long id) throws SQLException {
    orderDao.updateOrderStatus(id);

  }

  @Override
  public int updateOrder(OrderPojo orderPojo) throws SQLException {
    return orderDao.updateOrder(orderPojo);

  }

  @Override
  public void updateOrderPush(OrderPojo orderPojo) throws SQLException {
    orderDao.updateOrderPush(orderPojo);

  }

  @Override
  public OrderPojo orderStatusNum(Long id) throws SQLException {
    return orderDao.orderStatusNum(id);

  }

  @Override
  public OrderPojo getfindByIdOrder(Long id) throws SQLException {
    return orderDao.getfindByIdOrder(id);

  }

  @Override
  public void deleteOrder(Long id) throws SQLException {

    orderDao.deleteOrder(id);
  }

  @Override
  public int orderAllCount(OrderPojo orderDaoPojo, String os) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderDaoPojo != null) {
      map.put("orderNo", orderDaoPojo.getOrderNo());
      map.put("orderStatus", orderDaoPojo.getOrderStatus());
      map.put("consignee", orderDaoPojo.getConsignee());
      map.put("consigneePhone", orderDaoPojo.getConsigneePhone());
      map.put("consigneeAddress", orderDaoPojo.getConsigneeAddress());
      map.put("payStatus", orderDaoPojo.getPayStatus());
      map.put("overdue", orderDaoPojo.getOverdue());
      map.put("createDate", orderDaoPojo.getCreateDateString());
    }
    if (os != null & !os.equals("")) {
      map.put("orderStatus", Integer.parseInt(os));
    }

    return orderDao.orderAllCount(map);
  }

  @Override
  public int getOrderAllCount(Map<String, Object> map) throws SQLException {
    return orderDao.getOrderAllCount(map);
  }

  @Override
  public List<OrderPojo> getOrderAllList(Map<String, Object> map) throws SQLException {
    return orderDao.getOrderAllList(map);
  }

  @Override
  public List<OrderPojo> orderAllList(OrderPojo orderDaoPojo, Pager page, String os) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (orderDaoPojo != null) {
      map.put("orderNo", orderDaoPojo.getOrderNo());
      map.put("orderStatus", orderDaoPojo.getOrderStatus());
      map.put("consignee", orderDaoPojo.getConsignee());
      map.put("consigneePhone", orderDaoPojo.getConsigneePhone());
      map.put("consigneeAddress", orderDaoPojo.getConsigneeAddress());
      map.put("payStatus", orderDaoPojo.getPayStatus());
      map.put("overdue", orderDaoPojo.getOverdue());
      map.put("createDate", orderDaoPojo.getCreateDateString());
      map.put("tids", orderDaoPojo.getTids());

    }
    if (os != null & !os.equals("")) {
      map.put("orderStatus", Integer.parseInt(os));
    }

    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<OrderPojo> list = orderDao.orderAllList(map);

    return list;
  }

  @Override
  public int orderAllCount2(Map<String, Object> map) {
    return orderDao.orderAllCount2(map);
  }

  @Override
  public List<OrderPojo> orderAllList2(Map<String, Object> map) {
    return orderDao.orderAllList2(map);
  }

  @Override
  public List<OrderPojo> getPindekeRanking(Map<String, Object> map) {
    return orderDao.getPindekeRanking(map);
  }

  @Override
  public List<OrderPojo> getPindekeMonthSale(Map<String, Object> map) {
    return orderDao.getPindekeMonthSale(map);
  }
  
  @Override
  public void orderDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        orderDao.delOrder(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delOrder(Long id) throws SQLException {
    orderDao.delOrder(id);
  }

  @Override
  public OrderPojo findOrderById(Long id) throws SQLException {

    return orderDao.findOrderById(id);

  }

  @Override
  public OrderPojo getfindByIdOrderToPush(Long id) throws SQLException {

    return orderDao.getfindByIdOrderToPush(id);

  }

  @Override
  public void deleteOrdership(Map<String, Object> map) throws SQLException {

    orderDao.deleteOrdership(map);

  }

  // //////////////////////////////////分割线////////////////////////
  // 订单结算
  @Override
  public List<OrderPojo> orderAllListSettle(OrderPojo orderDaoPojo, Pager page, String os,
      String beganday, String endday, String userName) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (os != null && !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (userName != null && !userName.equals("")) {
      map.put("userName", userName);
    }

    List<OrderPojo> list = orderDao.orderAllListSettle(map);

    return list;
  }

  @Override
  public OrderPojo orderAllListSettleOne(String beganday, String endday, String userName) {

    Map<String, Object> map = new HashMap<String, Object>();

    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (userName != null && !userName.equals("")) {
      map.put("userName", userName);
    }

    return orderDao.orderAllListSettleOne(map);
  }

  // 供应商订单结算
  @Override
  public List<OrderPojo> orderAllListSettleMF(OrderPojo orderDaoPojo, Pager page, String beganday,
      String endday, String userName) {

    Map<String, Object> map = new HashMap<String, Object>();

    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (userName != null && !userName.equals("")) {
      map.put("userName", userName);
    }

    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<OrderPojo> list = orderDao.orderAllListSettleMF(map);

    return list;
  }

  @Override
  public List<OrderPojo> supplyOrderListWeb(Map<String, Object> map) throws SQLException {

    map.get("orderPojo");
    List<OrderPojo> list = orderDao.supplyOrderListWeb(map);
    return list;
  }

  @Override
  public List<OrderPojo> agentOrderListWeb(Map<String, Object> map) throws SQLException {

    map.get("orderPojo");
    List<OrderPojo> list = orderDao.agentOrderListWeb(map);
    return list;
  }

  @Override
  public void updateOrderStatus(OrderPojo order) {
    orderDao.updateOrderStatus(order);
  }

  @Override
  public int updateOrderStatus2(OrderPojo order) {
    return orderDao.updateOrderStatus2(order);
  }

  @Override
  public OrderPojo findOrderByOrderNo(String orderNo) {
    return orderDao.findOrderByOrderNo(orderNo);
  }

  @Override
  public int shipOrderCountWeb(OrderPojo orderDaoPojo) {

    Map<String, Object> map = new HashMap<String, Object>();
    // if(orderDaoPojo!=null){
    // map.put("orderNo", orderDaoPojo.getOrderNo());
    // map.put("orderStatus", orderDaoPojo.getOrderStatus());
    // map.put("consignee", orderDaoPojo.getConsignee());
    // map.put("payStatus", orderDaoPojo.getPayStatus());
    // }
    // if(os!=null&!os.equals(""))
    // map.put("orderStatus", Integer.parseInt(os));

    return orderDao.shipOrderCountWeb(map);
  }

  @Override
  public List<OrderPojo> getShipOrderAllListWeb(OrderPojo orderDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    // if(orderDaoPojo!=null){
    // map.put("orderNo", orderDaoPojo.getOrderNo());
    // map.put("orderStatus", orderDaoPojo.getOrderStatus());
    // map.put("consignee", orderDaoPojo.getConsignee());
    // map.put("payStatus", orderDaoPojo.getPayStatus());
    // }
    // if(os!=null&!os.equals(""))
    // map.put("orderStatus", Integer.parseInt(os));

    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<OrderPojo> list = orderDao.getShipOrderAllListWeb(map);
    return list;
  }

  @Override
  public OrderPojo getfindByIdOrderWeb(Long id) throws SQLException {

    return orderDao.getfindByIdOrderWeb(id);
  }

  @Override
  public void isDelete(OrderPojo orderPojo) throws SQLException {

    orderDao.isDelete(orderPojo);
  }

  @Override
  public void deleteAllOrderById(String[] tids) {

    for (String tid : tids) {
      try {
        orderDao.deleteAllOrderById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkAllOrderById(String[] tids) {

    for (String tid : tids) {
      try {
        orderDao.checkAllOrderById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void isCancel(OrderPojo orderPojo) throws SQLException {

    orderDao.isCancel(orderPojo);
  }

  @Override
  public int updateIsCancelOrder(OrderPojo orderPojo) throws SQLException {

    return orderDao.updateIsCancelOrder(orderPojo);
  }

  @Override
  public List<OrderPojo> myWuLiuWeb(OrderPojo orderPojo) {

    return orderDao.myWuLiuWeb(orderPojo);
  }

  @Override
  public List<OrderPojo> getOrderByoutTradeNo(String outTradeNo) {

    return orderDao.getOrderByoutTradeNo(outTradeNo);
  }

  @Override
  public List<OrderPojo> consumerOrderListWeb(Map<String, Object> map) throws SQLException {

    map.get("orderPojo");
    List<OrderPojo> list = orderDao.consumerOrderListWeb(map);
    return list;
  }

  @Override
  public void orderPaySuccess(Map<String, Object> map) throws SQLException {
    orderDao.orderPaySuccess(map);
    // 自提方式直接改为已发货
    // orderDao.updateOrderPay(map);

  }

  @Override
  public List<OrderPojo> productMonthSaleList(UserOrderDetailPojo userOrderDetailPojo, Pager page,
      String beganday, String endday) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderDetailPojo != null) {
      map.put("shopName", userOrderDetailPojo.getShopName());
    }
    if (beganday != null) {
      map.put("beganday", beganday);
    }
    if (endday != null) {
      map.put("endday", endday);
    }
    // if(orderDaoPojo!=null){
    // map.put("orderNo", orderDaoPojo.getOrderNo());
    // map.put("orderStatus", orderDaoPojo.getOrderStatus());
    // map.put("consignee", orderDaoPojo.getConsignee());
    // map.put("payStatus", orderDaoPojo.getPayStatus());
    // }
    // if(os!=null&!os.equals(""))
    // map.put("orderStatus", Integer.parseInt(os));

    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return orderDao.productMonthSaleList(map);
  }

  @Override
  public int productMonthSaleCount(UserOrderDetailPojo orderDetailPojo, String beganday,
      String endday) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderDetailPojo != null) {
      map.put("shopName", orderDetailPojo.getShopName());
    }
    // if(os!=null&!os.equals(""))
    // map.put("orderStatus", Integer.parseInt(os));
    if (beganday != null) {
      map.put("beganday", beganday);
    }
    if (endday != null) {
      map.put("endday", endday);
    }
    return orderDao.productMonthSaleCount(map);
  }

  @Override
  public int orderCount(Map<String, Object> map) throws SQLException {
    return orderDao.orderCount(map);
  }

  @Override
  public int supplyOrderListWebCount(Map<String, Object> map) throws SQLException {
    return orderDao.supplyOrderListWebCount(map);
  }

  @Override
  public void updateOutTradeNo(Map<String, Object> map) throws SQLException {
    orderDao.updateOutTradeNo(map);
  }

  @Override
  public int todayBargainNum() throws SQLException {

    return orderDao.todayBargainNum();
  }

  @Override
  public List<OrderPojo> orderEleAllList(Map<String, Object> map) throws SQLException {

    return orderDao.orderEleAllList(map);
  }

  @Override
  public int orderEleAllListCount(Map<String, Object> map) throws SQLException {

    return orderDao.orderEleAllListCount(map);
  }

  @Override
  public double transactionPriceSum() throws SQLException {
    return orderDao.transactionPriceSum();
  }

  @Override
  public void updateorders(Map<String, Object> map) throws SQLException {
    orderDao.updateorders(map);
  }

  @Override
  public int checkUserFirstOrder(Long userId) throws SQLException {
    return orderDao.checkUserFirstOrder(userId);
  }

  @Override
  public int isUserFirstOrder(Long orderId) throws SQLException {
    return orderDao.isUserFirstOrder(orderId);
  }

  @Override
  public void updateFirstOrder(String outTradeNo) throws SQLException {
    orderDao.updateFirstOrder(outTradeNo);
  }

  @Override
  public void updateOrderPrice(Map<String, Object> map) throws SQLException {
    orderDao.updateOrderPrice(map);
  }

  @Override
  public List<OrderPojo> queryOrderSettleInfo(Map<String, Object> map) throws SQLException {
    return orderDao.queryOrderSettleInfo(map);
  }

  @Override
  public int settleOrderById(OrderPojo orderPojo) throws SQLException {
    return orderDao.settleOrderById(orderPojo);
  }

  @Override
  public int settleOrderByUserId(OrderPojo orderPojo) throws SQLException {
    return orderDao.settleOrderByUserId(orderPojo);
  }

  @Override
  public List<OrderPojo> groupOrderSettleBySuserId(Map<String, Object> map) throws SQLException {
    return orderDao.groupOrderSettleBySuserId(map);
  }

  @Override
  public int updateOrderStatusDelivery(OrderPojo orderPojo) throws SQLException {
    return orderDao.updateOrderStatusDelivery(orderPojo);
  }

  @Override
  public List<OrderPojo> orderAllListExcel(Map<String, Object> map) {
    return orderDao.orderAllListExcel(map);
  }

  @Override
  public int updateOrderType(Map<String, Object> map) throws SQLException {
    return orderDao.updateOrderType(map);
  }

  @Override
  public int countBy(Map<String, Object> params) {
    return orderDao.countBy(params);
  }

  @Override
  public int groupRefundCountBy(Map<String, Object> params) {
    return orderDao.groupRefundCountBy(params);
  }

  @Override
  public List<OrderPojo> listPage(Map<String, Object> params) {
    return orderDao.listPage(params);
  }

  @Override
  public List<OrderPojo> groupRefundListPage(Map<String, Object> params) {
    return orderDao.groupRefundListPage(params);
  }

  @Override
  public int updateOrderRefund(Map<String, Object> params) throws SQLException {
    return orderDao.updateOrderRefund(params);
  }

  @Override
  public OrderPojo findOrderByBatchNo(Map<String, Object> params) throws SQLException {
    return orderDao.findOrderByBatchNo(params);
  }

  @Override
  public int orderCnt(Map<String, Object> params) throws SQLException {
    return orderDao.orderCnt(params);
  }

  @Override
  public String totalSale(Map<String, Object> params) throws SQLException {
    return orderDao.totalSale(params);
  }

  @Override
  public List<OrderPojo> exportOrderExcel(Map<String, Object> map) throws SQLException {
    return orderDao.exportOrderExcel(map);
  }

  @Override
  public List<OrderPojo> onlyOrderTabList(Map<String, Object> params) throws SQLException {
    return orderDao.onlyOrderTabList(params);
  }

}
