package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserOrderDetailPojo;

public interface UserOrderDetailService {

  public int userOrderDetailAllCount(UserOrderDetailPojo userOrderDetailPojo);

  public List<UserOrderDetailPojo> userOrderDetailAllList(UserOrderDetailPojo userOrderDetailPojo,
      Pager page);

  public int productSaleCountAllCount(UserOrderDetailPojo userOrderDetailPojo);

  public List<UserOrderDetailPojo> productSaleCountAllList(UserOrderDetailPojo userOrderDetailPojo,
      Pager page);

  public void addUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException;

  public void delUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException;

  public void delAllUserOrderDetailById(String[] tids);

  public UserOrderDetailPojo findUserOrderDetailById(Long id) throws SQLException;

  public void updateUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException;

  public void checkUserOrderDetail(UserOrderDetailPojo userOrderDetailPojo) throws SQLException;

  public void checkAllUserOrderDetailById(String[] tids);

  public int shopSaleListCount(UserOrderDetailPojo orderDetailPojo, String beganday, String endday);

  public List<UserOrderDetailPojo> shopSaleList(UserOrderDetailPojo userOrderDetailPojo,
      Pager page, String beganday, String endday);

  public int sellSumNum(Long productId) throws SQLException;
}
