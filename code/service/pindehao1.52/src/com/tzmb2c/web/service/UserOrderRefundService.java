package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;

public interface UserOrderRefundService {

  public int userOrderRefundAllCount(UserOrderRefundPojo userOrderRefundPojo, String refundStatus);

  public List<UserOrderRefundPojo> userOrderRefundAllList(UserOrderRefundPojo userOrderRefundPojo,
      Pager page, String refundStatus);

  public void addUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public void delUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public void delAllUserOrderRefundById(String[] tids);

  public UserOrderRefundPojo findUserOrderRefundById(Long id) throws SQLException;

  public void updateUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public void checkOrder(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public void checkUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public void checkAllUserOrderRefundById(String[] tids, UserOrderRefundPojo userOrderRefundPojo);

  public List<UserOrderRefundPojo> findUserOrderRefundByUserId(
      UserOrderRefundPojo userOrderRefundPojo, Pager page);

  List<UserOrderRefundPojo> findUserOrderRefundByorderId(UserOrderRefundPojo userOrderRefundPojo);

  public List<UserOrderRefundPojo> findOrderRefundDetailByUserId(
      UserOrderRefundPojo userOrderRefundPojo, Pager page);

  public void updateOrderRefundRemarks(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public UserOrderRefundPojo findUserOrderRefundByOid(Long oid) throws SQLException;

  public int userOrderRefundAllCount2(Map<String, Object> map) throws SQLException;

  public List<UserOrderRefundPojo> findUserOrderRefundListByorderId(
      UserOrderRefundPojo userOrderRefundPojo);

  /** 商家中心-售后管理查询数据 */
  public List<UserOrderRefundPojo> findUserOrderRefundByUserId2(
      UserOrderRefundPojo userOrderRefundPojo, Pager page);

  public void updateStatusOfUserOrderRefundById(UserOrderRefundPojo userOrderRefundPojo);

  UserOrderRefundPojo getByOutRefundNo(String outRefundNo) throws SQLException;


}
