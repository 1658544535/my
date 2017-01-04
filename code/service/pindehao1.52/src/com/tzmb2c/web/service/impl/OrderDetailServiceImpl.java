package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.OrderDetailDao;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
  @Autowired
  private OrderDetailDao orderDetailDao;

  public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
    this.orderDetailDao = orderDetailDao;
  }


  @Override
  public List<OrderDetailPojo> orderDetailAllService() throws SQLException {
    return orderDetailDao.getOrderDetailAll();
  }

  @Override
  public List<OrderDetailPojo> getfindByUserIdOrderDetail(Long id) throws SQLException {
    return orderDetailDao.getfindByUserIdOrderDetail(id);
  }

  @Override
  public void addOrderDetailService(OrderDetailPojo orderDetailPojo) throws SQLException {

    /*
     * if("0".equals(orderDetailPojo.getOrderDetailLevel())){ OrderDetailPojo rsOrderDetailPojo =
     * orderDetailDao.getOrderDetailCountByOrderDetailLevel(orderDetailPojo.getLevel()); int
     * orderDetailId =0; if(rsOrderDetailPojo.getOrderDetailCount() > -1 &&
     * rsOrderDetailPojo.getOrderDetailCount() <= 9){ orderDetailId =
     * rsOrderDetailPojo.getOrderDetailCount()+1; orderDetailPojo.setOrderDetailId("A0" +
     * orderDetailId); }else if(rsOrderDetailPojo.getOrderDetailCount() > 9 &&
     * rsOrderDetailPojo.getOrderDetailCount() <= 99){ orderDetailId =
     * rsOrderDetailPojo.getOrderDetailCount()+1; orderDetailPojo.setOrderDetailId("A"
     * +orderDetailId ); } //orderDetailPojo.setOrderDetailPath("#"); }else{ OrderDetailPojo
     * rsOrderDetailPojo =
     * orderDetailDao.getOrderDetailCountByOrderDetailLevel(orderDetailPojo.getLevel());
     * if(rsOrderDetailPojo.getOrderDetailCount() > -1 && rsOrderDetailPojo.getOrderDetailCount() <=
     * 9){ orderDetailPojo.setOrderDetailId(orderDetailPojo.getLevel()+ "B0" +
     * (rsOrderDetailPojo.getOrderDetailCount() + 1)); }else
     * if(rsOrderDetailPojo.getOrderDetailCount() > 9 && rsOrderDetailPojo.getOrderDetailCount() <=
     * 99){ orderDetailPojo.setOrderDetailId(orderDetailPojo.getLevel()+ "B" +
     * (rsOrderDetailPojo.getOrderDetailCount() + 1)); } }
     */
    orderDetailDao.insertOrderDetail(orderDetailPojo);
  }


  @Override
  public void insertOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException {

    orderDetailDao.insertOrderDetail(orderDetailPojo);
  }

  @Override
  public void updateOrderDetail(OrderDetailPojo orderDetailPojo) throws SQLException {
    orderDetailDao.updateOrderDetail(orderDetailPojo);

  }

  @Override
  public OrderDetailPojo getfindByIdOrderDetail(Long id) throws SQLException {
    return orderDetailDao.getfindByIdOrderDetail(id);

  }

  @Override
  public List<OrderDetailPojo> getOrderDetail(Long id) {

    List<OrderDetailPojo> list = orderDetailDao.getOrderDetail(id);

    return list;

  }

  @Override
  public void deleteOrderDetail(Long id) throws SQLException {

    orderDetailDao.deleteOrderDetail(id);
  }

  @Override
  public int orderDetailAllCount(OrderDetailPojo orderDetailDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderDetailDaoPojo != null) {
      map.put("productId", orderDetailDaoPojo.getProductId());
      map.put("contentGuide", orderDetailDaoPojo.getContentGuide());
      map.put("productName", orderDetailDaoPojo.getProductName());
      map.put("source", orderDetailDaoPojo.getSource());
      map.put("types", orderDetailDaoPojo.getTypes());
      map.put("sysLoginName", orderDetailDaoPojo.getSysLoginName());
      map.put("beganday", orderDetailDaoPojo.getBeganday());
      map.put("endday", orderDetailDaoPojo.getEndday());
    }
    return orderDetailDao.orderDetailAllCount(map);
  }

  @Override
  public List<OrderDetailPojo> orderDetailAllList(OrderDetailPojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("productId", ticketRulePojo.getProductId());
      // map.put("ticketType", ticketRulePojo.getTicketType());
      map.put("orderId", ticketRulePojo.getOrderId());
      map.put("contentGuide", ticketRulePojo.getContentGuide());
      map.put("productName", ticketRulePojo.getProductName());
      map.put("source", ticketRulePojo.getSource());
      map.put("types", ticketRulePojo.getTypes());
      map.put("sysLoginName", ticketRulePojo.getSysLoginName());
      map.put("beganday", ticketRulePojo.getBeganday());
      map.put("endday", ticketRulePojo.getEndday());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<OrderDetailPojo> list = orderDetailDao.orderDetailAllList(map);

    return list;
  }

  @Override
  public void orderDetailDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        orderDetailDao.delOrderDetail(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delOrderDetail(Long id) throws SQLException {
    orderDetailDao.delOrderDetail(id);
  }

  @Override
  public OrderDetailPojo findOrderDetailById(Long id) throws SQLException {

    return orderDetailDao.findOrderDetailById(id);

  }


  @Override
  public List<OrderDetailPojo> getOrderDetailWeb(Long id) {
    // TODO Auto-generated method stub
    return orderDetailDao.getOrderDetailWeb(id);
  }


  @Override
  public OrderDetailPojo getfindByOrderIdOrderDetail(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return orderDetailDao.getfindByOrderIdOrderDetail(id);
  }


  @Override
  public OrderDetailPojo getfindByOrderIdOrderDetailWeb(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return orderDetailDao.getfindByOrderIdOrderDetailWeb(id);
  }


  @Override
  public void updateReStatus(long orderId, long reStatus) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", orderId);
    map.put("reStatus", reStatus);
    orderDetailDao.updateReStatus(map);
  }

  @Override
  public void updateReStatusmap(Map<String, Object> map) throws SQLException {

    orderDetailDao.updateReStatusmap(map);
  }

  @Override
  public List<OrderDetailPojo> getOrderDetailAllbyrestatus(Map<String, Object> map)
      throws SQLException {
    return orderDetailDao.getOrderDetailAllbyrestatus(map);
  }


  @Override
  public List<OrderDetailPojo> getOrderDetailByOutTradeNo(String outTradeNo) {
    return orderDetailDao.getOrderDetailByOutTradeNo(outTradeNo);
  }


  @Override
  public List<OrderDetailPojo> statisticalSell() {
    return orderDetailDao.statisticalSell();
  }


  @Override
  public List<OrderDetailPojo> getfindByUserIdOrderDetailExcel(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return orderDetailDao.getfindByUserIdOrderDetailExcel(id);
  }
}
