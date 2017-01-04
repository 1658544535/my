package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserOrderStatisticsPojo;

public interface UserOrderStatisticsMapper {


  public List<UserOrderStatisticsPojo> getUserOrderStatisticsAll() throws SQLException;

  public void insertUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException;

  public void updateUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException;

  public UserOrderStatisticsPojo getfindByIdUserOrderStatistics(Long id) throws SQLException;

  public List<UserOrderStatisticsPojo> getfindByUserIdUserOrderStatistics(Long id)
      throws SQLException;

  public void deleteUserOrderStatistics(Long id) throws SQLException;

  public int userOrderStatisticsAllCount(Map<String, Object> map);

  public List<UserOrderStatisticsPojo> userOrderStatisticsAllList(Map<String, Object> map);

  void delUserOrderStatistics(Long id) throws SQLException;

}
