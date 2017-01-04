package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.OrderRefundDao;
import com.tzmb2c.web.pojo.OrderRefundPojo;
import com.tzmb2c.web.service.OrderRefundService;

public class OrderRefundServiceImpl implements OrderRefundService {
  @Autowired
  private OrderRefundDao orderRefundDao;

  public void setOrderRefundDao(OrderRefundDao orderRefundDao) {
    this.orderRefundDao = orderRefundDao;
  }


  @Override
  public List<OrderRefundPojo> orderRefundAllService() throws SQLException {
    return orderRefundDao.getOrderRefundAll();
  }



  @Override
  public void addOrderRefundService(OrderRefundPojo orderRefundPojo) throws SQLException {

    /*
     * if("0".equals(orderRefundPojo.getOrderRefundLevel())){ OrderRefundPojo rsOrderRefundPojo =
     * orderRefundDao.getOrderRefundCountByOrderRefundLevel(orderRefundPojo.getLevel()); int
     * orderRefundId =0; if(rsOrderRefundPojo.getOrderRefundCount() > -1 &&
     * rsOrderRefundPojo.getOrderRefundCount() <= 9){ orderRefundId =
     * rsOrderRefundPojo.getOrderRefundCount()+1; orderRefundPojo.setOrderRefundId("A0" +
     * orderRefundId); }else if(rsOrderRefundPojo.getOrderRefundCount() > 9 &&
     * rsOrderRefundPojo.getOrderRefundCount() <= 99){ orderRefundId =
     * rsOrderRefundPojo.getOrderRefundCount()+1; orderRefundPojo.setOrderRefundId("A"
     * +orderRefundId ); } //orderRefundPojo.setOrderRefundPath("#"); }else{ OrderRefundPojo
     * rsOrderRefundPojo =
     * orderRefundDao.getOrderRefundCountByOrderRefundLevel(orderRefundPojo.getLevel());
     * if(rsOrderRefundPojo.getOrderRefundCount() > -1 && rsOrderRefundPojo.getOrderRefundCount() <=
     * 9){ orderRefundPojo.setOrderRefundId(orderRefundPojo.getLevel()+ "B0" +
     * (rsOrderRefundPojo.getOrderRefundCount() + 1)); }else
     * if(rsOrderRefundPojo.getOrderRefundCount() > 9 && rsOrderRefundPojo.getOrderRefundCount() <=
     * 99){ orderRefundPojo.setOrderRefundId(orderRefundPojo.getLevel()+ "B" +
     * (rsOrderRefundPojo.getOrderRefundCount() + 1)); } }
     */
    orderRefundDao.insertOrderRefund(orderRefundPojo);
  }


  @Override
  public void insertOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException {

    orderRefundDao.insertOrderRefund(orderRefundPojo);
  }

  @Override
  public void updateOrderRefund(OrderRefundPojo orderRefundPojo) throws SQLException {
    orderRefundDao.updateOrderRefund(orderRefundPojo);

  }

  @Override
  public OrderRefundPojo getfindByIdOrderRefund(Long id) throws SQLException {
    return orderRefundDao.getfindByIdOrderRefund(id);

  }

  @Override
  public void deleteOrderRefund(Long id) throws SQLException {

    orderRefundDao.deleteOrderRefund(id);
  }

  @Override
  public int orderRefundAllCount(OrderRefundPojo orderRefundDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderRefundDaoPojo != null) {
      map.put("productName", orderRefundDaoPojo.getProductName());
      map.put("status", orderRefundDaoPojo.getStatus());
      map.put("beginTime", orderRefundDaoPojo.getBeginTime());
      map.put("endTime", orderRefundDaoPojo.getEndTime());
    }
    return orderRefundDao.orderRefundAllCount(map);
  }

  @Override
  public List<OrderRefundPojo> orderRefundAllList(OrderRefundPojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("productName", ticketRulePojo.getProductName());
      map.put("status", ticketRulePojo.getStatus());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<OrderRefundPojo> list = orderRefundDao.orderRefundAllList(map);

    return list;
  }

  @Override
  public void orderRefundDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        orderRefundDao.delOrderRefund(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delOrderRefund(Long id) throws SQLException {
    orderRefundDao.delOrderRefund(id);
  }

  @Override
  public OrderRefundPojo findOrderRefundById(Long id) throws SQLException {

    return orderRefundDao.findOrderRefundById(id);

  }


  @Override
  public OrderRefundPojo getorderRefundDetail(OrderRefundPojo orderRefundDaoPojo)
      throws SQLException {

    return orderRefundDao.getorderRefundDetail(orderRefundDaoPojo);
  }

  @Override
  public void updateOrderRefundbyDetailId(OrderRefundPojo orderRefundPojo) throws SQLException {
    orderRefundDao.updateOrderRefundbyDetailId(orderRefundPojo);

  }

  @Override
  public int getNOrefundDetail(Map<String, Object> map) throws SQLException {
    return orderRefundDao.getNOrefundDetail(map);
  }
}
