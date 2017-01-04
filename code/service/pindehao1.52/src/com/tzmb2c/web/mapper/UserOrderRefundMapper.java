package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserOrderRefundPojo;

public interface UserOrderRefundMapper {

  public int userOrderRefundAllCount(Map<String, Object> map);

  public List<UserOrderRefundPojo> userOrderRefundAllList(Map<String, Object> map);

  public void addUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public void delAllUserOrderRefundById(String id) throws SQLException;

  public void delUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public UserOrderRefundPojo findUserOrderRefundById(Long id);

  public void updateUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo);

  public void checkOrder(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public void checkAllUserOrderRefundById(UserOrderRefundPojo userOrderRefundPojo)
      throws SQLException;

  public void checkUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public List<UserOrderRefundPojo> findUserOrderRefundByUserId(Map<String, Object> map);

  public List<UserOrderRefundPojo> findUserOrderRefundByorderId(Map<String, Object> map);

  public List<UserOrderRefundPojo> findOrderRefundDetailByUserId(Map<String, Object> map);

  public void updateOrderRefundRemarks(UserOrderRefundPojo userOrderRefundPojo) throws SQLException;

  public UserOrderRefundPojo findUserOrderRefundByOid(Long oid) throws SQLException;

  public int userOrderRefundAllCount2(Map<String, Object> map);

  /** 根据orderid查询退货商品集合 */
  public List<UserOrderRefundPojo> findUserOrderRefundListByorderId(Map<String, Object> map);

  /** 商家中心-售后管理查询数据 */
  public List<UserOrderRefundPojo> findUserOrderRefundByUserId2(Map<String, Object> map);

  public void updateStatusOfUserOrderRefundById(UserOrderRefundPojo userOrderRefundPojo);

  UserOrderRefundPojo getByOutRefundNo(String outRefundNo) throws SQLException;


}
