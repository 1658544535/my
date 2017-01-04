package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserOrderStatisticsPojo;

public interface UserOrderStatisticsService {

  public List<UserOrderStatisticsPojo> userOrderStatisticsAllService() throws SQLException;

  public void addUserOrderStatisticsService(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException;

  public void insertUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException;

  public void updateUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException;

  public UserOrderStatisticsPojo getfindByIdUserOrderStatistics(Long id) throws SQLException;

  public void deleteUserOrderStatistics(Long userOrderStatisticsId) throws SQLException;

  public int userOrderStatisticsAllCount(UserOrderStatisticsPojo userOrderStatisticsDaoPojo);

  List<UserOrderStatisticsPojo> userOrderStatisticsAllList(UserOrderStatisticsPojo ticketRulePojo,
      Pager page);

  void userOrderStatisticsDeleteId(String[] tids);

  void delUserOrderStatistics(Long id) throws SQLException;

  UserOrderStatisticsPojo findUserOrderStatisticsById(Long merId) throws SQLException;

  List<UserOrderStatisticsPojo> findUserOrderStatisticsByUserId(Long id) throws SQLException;
}
