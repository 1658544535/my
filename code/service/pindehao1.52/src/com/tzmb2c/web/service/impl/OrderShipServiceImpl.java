package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.OrderShipDao;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.service.OrderShipService;

public class OrderShipServiceImpl implements OrderShipService {
  @Autowired
  private OrderShipDao orderShipDao;

  public void setOrderShipDao(OrderShipDao orderShipDao) {
    this.orderShipDao = orderShipDao;
  }


  @Override
  public List<OrderShipPojo> orderShipAllService() throws SQLException {
    return orderShipDao.getOrderShipAll();
  }



  @Override
  public void addOrderShipService(OrderShipPojo orderShipPojo) throws SQLException {

    /*
     * if("0".equals(orderShipPojo.getOrderShipLevel())){ OrderShipPojo rsOrderShipPojo =
     * orderShipDao.getOrderShipCountByOrderShipLevel(orderShipPojo.getLevel()); int orderShipId =0;
     * if(rsOrderShipPojo.getOrderShipCount() > -1 && rsOrderShipPojo.getOrderShipCount() <= 9){
     * orderShipId = rsOrderShipPojo.getOrderShipCount()+1; orderShipPojo.setOrderShipId("A0" +
     * orderShipId); }else if(rsOrderShipPojo.getOrderShipCount() > 9 &&
     * rsOrderShipPojo.getOrderShipCount() <= 99){ orderShipId =
     * rsOrderShipPojo.getOrderShipCount()+1; orderShipPojo.setOrderShipId("A" +orderShipId ); }
     * //orderShipPojo.setOrderShipPath("#"); }else{ OrderShipPojo rsOrderShipPojo =
     * orderShipDao.getOrderShipCountByOrderShipLevel(orderShipPojo.getLevel());
     * if(rsOrderShipPojo.getOrderShipCount() > -1 && rsOrderShipPojo.getOrderShipCount() <= 9){
     * orderShipPojo.setOrderShipId(orderShipPojo.getLevel()+ "B0" +
     * (rsOrderShipPojo.getOrderShipCount() + 1)); }else if(rsOrderShipPojo.getOrderShipCount() > 9
     * && rsOrderShipPojo.getOrderShipCount() <= 99){
     * orderShipPojo.setOrderShipId(orderShipPojo.getLevel()+ "B" +
     * (rsOrderShipPojo.getOrderShipCount() + 1)); } }
     */
    orderShipDao.insertOrderShip(orderShipPojo);
  }


  @Override
  public int insertOrderShip(OrderShipPojo orderShipPojo) throws SQLException {

    return orderShipDao.insertOrderShip(orderShipPojo);
  }

  @Override
  public int updateOrderShip(OrderShipPojo orderShipPojo) throws SQLException {
    return orderShipDao.updateOrderShip(orderShipPojo);

  }

  @Override
  public OrderShipPojo getfindByIdOrderShip(Long id) throws SQLException {
    return orderShipDao.getfindByIdOrderShip(id);

  }

  @Override
  public OrderShipPojo findByIdOrderShip(Long id) throws SQLException {
    return orderShipDao.findByIdOrderShip(id);

  }

  @Override
  public void deleteOrderShip(Long id) throws SQLException {

    orderShipDao.deleteOrderShip(id);
  }

  @Override
  public int orderShipAllCount(OrderShipPojo orderShipDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderShipDaoPojo != null) {
      map.put("logisticsNo", orderShipDaoPojo.getLogisticsNo());
      map.put("orderNo", orderShipDaoPojo.getOrderNo());
      map.put("orderStatus", orderShipDaoPojo.getOrderStatus());
      map.put("consigneeType", orderShipDaoPojo.getConsigneeType());
    }
    return orderShipDao.orderShipAllCount(map);
  }

  @Override
  public List<OrderShipPojo> orderShipAllList(OrderShipPojo orderShipDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (orderShipDaoPojo != null) {
      map.put("logisticsNo", orderShipDaoPojo.getLogisticsNo());
      map.put("orderNo", orderShipDaoPojo.getOrderNo());
      map.put("orderId", orderShipDaoPojo.getOrderId());
      map.put("orderStatus", orderShipDaoPojo.getOrderStatus());
      map.put("consigneeType", orderShipDaoPojo.getConsigneeType());
      map.put("panduan", orderShipDaoPojo.getPanduan());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<OrderShipPojo> list = orderShipDao.orderShipAllList(map);

    return list;
  }

  @Override
  public void orderShipDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        orderShipDao.delOrderShip(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delOrderShip(Long id) throws SQLException {
    orderShipDao.delOrderShip(id);
  }

  @Override
  public OrderShipPojo findOrderShipById(Long id) throws SQLException {

    return orderShipDao.findOrderShipById(id);

  }


  @Override
  public int isOrderShipExpiredByOrderId(Long orderId) throws SQLException {
    return orderShipDao.checkOrderShipExpired(orderId);
  }


}
