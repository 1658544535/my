package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserOrderDetailDao;
import com.tzmb2c.web.mapper.UserOrderDetailMapper;
import com.tzmb2c.web.pojo.UserOrderDetailPojo;

public class UserOrderDetailDaoImpl implements UserOrderDetailDao {

  @Autowired
  private UserOrderDetailMapper userOrderDetailMapper;

  @Override
  public int userOrderDetailAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userOrderDetailMapper.userOrderDetailAllCount(map);
  }

  @Override
  public List<UserOrderDetailPojo> userOrderDetailAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userOrderDetailMapper.userOrderDetailAllList(map);
  }

  @Override
  public void addUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailMapper.addUserOrderDetail(userOrderDetailPojo);
  }

  @Override
  public void delUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailMapper.delUserOrderDetail(userOrderDetailPojo);
  }

  @Override
  public void delAllUserOrderDetailById(String id) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailMapper.delAllUserOrderDetailById(id);
  }

  @Override
  public UserOrderDetailPojo findUserOrderDetailById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return userOrderDetailMapper.findUserOrderDetailById(id);
  }

  @Override
  public void updateUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailMapper.updateUserOrderDetail(userOrderDetailPojo);
  }

  @Override
  public void checkUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailMapper.checkUserOrderDetail(userOrderDetailPojo);
  }

  @Override
  public void checkAllUserOrderDetailById(String id) throws SQLException {
    // TODO Auto-generated method stub
    userOrderDetailMapper.checkAllUserOrderDetailById(id);
  }

  @Override
  public int productSaleCountAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userOrderDetailMapper.productSaleCountAllCount(map);
  }

  @Override
  public List<UserOrderDetailPojo> productSaleCountAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userOrderDetailMapper.productSaleCountAllList(map);
  }

  @Override
  public int shopSaleListCount(Map<String, Object> map) {
    return userOrderDetailMapper.shopSaleListCount(map);
  }

  @Override
  public List<UserOrderDetailPojo> shopSaleList(Map<String, Object> map) {
    return userOrderDetailMapper.shopSaleList(map);
  }

  @Override
  public int sellSumNum(Long productId) throws SQLException {
    return userOrderDetailMapper.sellSumNum(productId);
  }

}
