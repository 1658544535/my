package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserOrderRefundDao;
import com.tzmb2c.web.mapper.UserOrderRefundMapper;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;

public class UserOrderRefundDaoImpl implements UserOrderRefundDao {

  @Autowired
  private UserOrderRefundMapper userOrderRefundMapper;

  @Override
  public int userOrderRefundAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userOrderRefundMapper.userOrderRefundAllCount(map);
  }

  @Override
  public List<UserOrderRefundPojo> userOrderRefundAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userOrderRefundMapper.userOrderRefundAllList(map);
  }

  @Override
  public void addUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundMapper.addUserOrderRefund(userOrderRefundPojo);
  }

  @Override
  public void delUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundMapper.delUserOrderRefund(userOrderRefundPojo);
  }

  @Override
  public void delAllUserOrderRefundById(String id) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundMapper.delAllUserOrderRefundById(id);
  }

  @Override
  public UserOrderRefundPojo findUserOrderRefundById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return userOrderRefundMapper.findUserOrderRefundById(id);
  }

  @Override
  public UserOrderRefundPojo findUserOrderRefundByOid(Long oid) throws SQLException {
    // TODO Auto-generated method stub
    return userOrderRefundMapper.findUserOrderRefundByOid(oid);
  }

  @Override
  public void updateUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundMapper.updateUserOrderRefund(userOrderRefundPojo);
  }

  @Override
  public void checkUserOrderRefund(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundMapper.checkUserOrderRefund(userOrderRefundPojo);
  }

  @Override
  public void checkAllUserOrderRefundById(UserOrderRefundPojo userOrderRefundPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundMapper.checkAllUserOrderRefundById(userOrderRefundPojo);
  }

  @Override
  public void checkOrder(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderRefundMapper.checkOrder(userOrderRefundPojo);
  }

  @Override
  public List<UserOrderRefundPojo> findUserOrderRefundByUserId(Map<String, Object> map) {
    return userOrderRefundMapper.findUserOrderRefundByUserId(map);
  }

  @Override
  public List<UserOrderRefundPojo> findUserOrderRefundByorderId(Map<String, Object> map) {
    return userOrderRefundMapper.findUserOrderRefundByorderId(map);
  }

  @Override
  public List<UserOrderRefundPojo> findOrderRefundDetailByUserId(Map<String, Object> map) {
    return userOrderRefundMapper.findOrderRefundDetailByUserId(map);
  }

  @Override
  public void updateOrderRefundRemarks(UserOrderRefundPojo userOrderRefundPojo) throws SQLException {
    userOrderRefundMapper.updateOrderRefundRemarks(userOrderRefundPojo);
  }

  @Override
  public int userOrderRefundAllCount2(Map<String, Object> map) {
    return userOrderRefundMapper.userOrderRefundAllCount2(map);
  }

  @Override
  public List<UserOrderRefundPojo> findUserOrderRefundListByorderId(Map<String, Object> map) {
    return userOrderRefundMapper.findUserOrderRefundListByorderId(map);
  }

  @Override
  public List<UserOrderRefundPojo> findUserOrderRefundByUserId2(Map<String, Object> map) {
    return userOrderRefundMapper.findUserOrderRefundByUserId2(map);
  }

  @Override
  public void updateStatusOfUserOrderRefundById(UserOrderRefundPojo userOrderRefundPojo) {
    userOrderRefundMapper.updateStatusOfUserOrderRefundById(userOrderRefundPojo);

  }

  @Override
  public UserOrderRefundPojo getByOutRefundNo(String outRefundNo) throws SQLException {
    // TODO Auto-generated method stub
    return userOrderRefundMapper.getByOutRefundNo(outRefundNo);
  }
}
