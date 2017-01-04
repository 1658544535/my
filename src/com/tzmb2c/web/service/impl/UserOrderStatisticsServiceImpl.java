package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.UserOrderStatisticsDao;
import com.tzmb2c.web.pojo.UserOrderStatisticsPojo;
import com.tzmb2c.web.service.UserOrderStatisticsService;

public class UserOrderStatisticsServiceImpl implements UserOrderStatisticsService {
  @Autowired
  private UserOrderStatisticsDao userOrderStatisticsDao;

  public void setUserOrderStatisticsDao(UserOrderStatisticsDao userOrderStatisticsDao) {
    this.userOrderStatisticsDao = userOrderStatisticsDao;
  }


  @Override
  public List<UserOrderStatisticsPojo> userOrderStatisticsAllService() throws SQLException {
    return userOrderStatisticsDao.getUserOrderStatisticsAll();
  }



  @Override
  public void addUserOrderStatisticsService(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException {

    /*
     * if("0".equals(userOrderStatisticsPojo.getUserOrderStatisticsLevel())){
     * UserOrderStatisticsPojo rsUserOrderStatisticsPojo =
     * userOrderStatisticsDao.getUserOrderStatisticsCountByUserOrderStatisticsLevel
     * (userOrderStatisticsPojo.getLevel()); int userOrderStatisticsId =0;
     * if(rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() > -1 &&
     * rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() <= 9){ userOrderStatisticsId =
     * rsUserOrderStatisticsPojo.getUserOrderStatisticsCount()+1;
     * userOrderStatisticsPojo.setUserOrderStatisticsId("A0" + userOrderStatisticsId); }else
     * if(rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() > 9 &&
     * rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() <= 99){ userOrderStatisticsId =
     * rsUserOrderStatisticsPojo.getUserOrderStatisticsCount()+1;
     * userOrderStatisticsPojo.setUserOrderStatisticsId("A" +userOrderStatisticsId ); }
     * //userOrderStatisticsPojo.setUserOrderStatisticsPath("#"); }else{ UserOrderStatisticsPojo
     * rsUserOrderStatisticsPojo =
     * userOrderStatisticsDao.getUserOrderStatisticsCountByUserOrderStatisticsLevel
     * (userOrderStatisticsPojo.getLevel());
     * if(rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() > -1 &&
     * rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() <= 9){
     * userOrderStatisticsPojo.setUserOrderStatisticsId(userOrderStatisticsPojo.getLevel()+ "B0" +
     * (rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() + 1)); }else
     * if(rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() > 9 &&
     * rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() <= 99){
     * userOrderStatisticsPojo.setUserOrderStatisticsId(userOrderStatisticsPojo.getLevel()+ "B" +
     * (rsUserOrderStatisticsPojo.getUserOrderStatisticsCount() + 1)); } }
     */
    userOrderStatisticsDao.insertUserOrderStatistics(userOrderStatisticsPojo);
  }


  @Override
  public void insertUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException {

    userOrderStatisticsDao.insertUserOrderStatistics(userOrderStatisticsPojo);
  }

  @Override
  public void updateUserOrderStatistics(UserOrderStatisticsPojo userOrderStatisticsPojo)
      throws SQLException {
    userOrderStatisticsDao.updateUserOrderStatistics(userOrderStatisticsPojo);

  }

  @Override
  public UserOrderStatisticsPojo getfindByIdUserOrderStatistics(Long id) throws SQLException {
    return userOrderStatisticsDao.getfindByIdUserOrderStatistics(id);

  }

  @Override
  public void deleteUserOrderStatistics(Long id) throws SQLException {

    userOrderStatisticsDao.deleteUserOrderStatistics(id);
  }

  @Override
  public int userOrderStatisticsAllCount(UserOrderStatisticsPojo userOrderStatisticsDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userOrderStatisticsDaoPojo != null) {
      map.put("remarks", userOrderStatisticsDaoPojo.getRemarks());
      map.put("year", userOrderStatisticsDaoPojo.getYear());
      map.put("month", userOrderStatisticsDaoPojo.getMonth());
    }
    return userOrderStatisticsDao.userOrderStatisticsAllCount(map);
  }

  @Override
  public List<UserOrderStatisticsPojo> userOrderStatisticsAllList(
      UserOrderStatisticsPojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("remarks", ticketRulePojo.getRemarks());
      map.put("year", ticketRulePojo.getYear());
      map.put("month", ticketRulePojo.getMonth());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<UserOrderStatisticsPojo> list = userOrderStatisticsDao.userOrderStatisticsAllList(map);

    return list;
  }

  @Override
  public void userOrderStatisticsDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        userOrderStatisticsDao.delUserOrderStatistics(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delUserOrderStatistics(Long id) throws SQLException {
    userOrderStatisticsDao.delUserOrderStatistics(id);
  }

  @Override
  public UserOrderStatisticsPojo findUserOrderStatisticsById(Long id) throws SQLException {

    return userOrderStatisticsDao.findUserOrderStatisticsById(id);

  }

  @Override
  public List<UserOrderStatisticsPojo> findUserOrderStatisticsByUserId(Long id) throws SQLException {

    return userOrderStatisticsDao.findUserOrderStatisticsByUserId(id);

  }
}
