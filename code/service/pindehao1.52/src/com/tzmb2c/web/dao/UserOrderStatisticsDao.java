package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserOrderStatisticsPojo;

public interface UserOrderStatisticsDao {

  List<UserOrderStatisticsPojo> getUserOrderStatisticsAll() throws SQLException;

  void insertUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException;

  void updateUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException;

  UserOrderStatisticsPojo getfindByIdUserOrderStatistics(Long id) throws SQLException;

  void deleteUserOrderStatistics(Long id) throws SQLException;

  int userOrderStatisticsAllCount(Map<String, Object> map);

  List<UserOrderStatisticsPojo> userOrderStatisticsAllList(Map<String, Object> map);

  void delUserOrderStatistics(Long id) throws SQLException;

  UserOrderStatisticsPojo findUserOrderStatisticsById(Long id) throws SQLException;

  List<UserOrderStatisticsPojo> findUserOrderStatisticsByUserId(Long id) throws SQLException;

}
