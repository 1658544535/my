package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserOrderStatisticsDao;
import com.tzmb2c.web.mapper.UserOrderStatisticsMapper;
import com.tzmb2c.web.pojo.UserOrderStatisticsPojo;

public class UserOrderStatisticsDaoImpl implements UserOrderStatisticsDao {

  @Autowired
  private UserOrderStatisticsMapper userOrderStatisticsMapper;

  @Override
  public List<UserOrderStatisticsPojo> getUserOrderStatisticsAll() throws SQLException {
    return userOrderStatisticsMapper.getUserOrderStatisticsAll();
  }

  @Override
  public void insertUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException {
    try {
      userOrderStatisticsMapper.insertUserOrderStatistics(userOrderStatisticsPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException {

    userOrderStatisticsMapper.updateUserOrderStatistics(userOrderStatisticsPojo);
  }

  @Override
  public UserOrderStatisticsPojo getfindByIdUserOrderStatistics(Long id) throws SQLException {
    return userOrderStatisticsMapper.getfindByIdUserOrderStatistics(id);

  }

  @Override
  public void deleteUserOrderStatistics(Long id) throws SQLException {
    userOrderStatisticsMapper.deleteUserOrderStatistics(id);
  }

  @Override
  public int userOrderStatisticsAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userOrderStatisticsMapper.userOrderStatisticsAllCount(map);
  }

  @Override
  public List<UserOrderStatisticsPojo> userOrderStatisticsAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userOrderStatisticsMapper.userOrderStatisticsAllList(map);
  }

  @Override
  public void delUserOrderStatistics(Long id) throws SQLException {

    userOrderStatisticsMapper.delUserOrderStatistics(id);
  }

  @Override
  public UserOrderStatisticsPojo findUserOrderStatisticsById(Long id) throws SQLException {
    return userOrderStatisticsMapper.getfindByIdUserOrderStatistics(id);
  }

  @Override
  public List<UserOrderStatisticsPojo> findUserOrderStatisticsByUserId(Long id) throws SQLException {
    return userOrderStatisticsMapper.getfindByUserIdUserOrderStatistics(id);
  }
}
