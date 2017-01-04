package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.UserShopCollectDao;
import com.tzmb2c.web.pojo.UserShopCollectPojo;
import com.tzmb2c.web.service.UserShopCollectService;

public class UserShopCollectServiceImpl implements UserShopCollectService {
  @Autowired
  private UserShopCollectDao userShopCollectDao;

  public void setUserShopCollectDao(UserShopCollectDao userShopCollectDao) {
    this.userShopCollectDao = userShopCollectDao;
  }


  @Override
  public List<UserShopCollectPojo> userShopCollectAllService() throws SQLException {
    return userShopCollectDao.getUserShopCollectAll();
  }



  @Override
  public void addUserShopCollectService(UserShopCollectPojo userShopCollectPojo)
      throws SQLException {

    /*
     * if("0".equals(userShopCollectPojo.getUserShopCollectLevel())){ UserShopCollectPojo
     * rsUserShopCollectPojo =
     * userShopCollectDao.getUserShopCollectCountByUserShopCollectLevel(userShopCollectPojo
     * .getLevel()); int userShopCollectId =0; if(rsUserShopCollectPojo.getUserShopCollectCount() >
     * -1 && rsUserShopCollectPojo.getUserShopCollectCount() <= 9){ userShopCollectId =
     * rsUserShopCollectPojo.getUserShopCollectCount()+1;
     * userShopCollectPojo.setUserShopCollectId("A0" + userShopCollectId); }else
     * if(rsUserShopCollectPojo.getUserShopCollectCount() > 9 &&
     * rsUserShopCollectPojo.getUserShopCollectCount() <= 99){ userShopCollectId =
     * rsUserShopCollectPojo.getUserShopCollectCount()+1;
     * userShopCollectPojo.setUserShopCollectId("A" +userShopCollectId ); }
     * //userShopCollectPojo.setUserShopCollectPath("#"); }else{ UserShopCollectPojo
     * rsUserShopCollectPojo =
     * userShopCollectDao.getUserShopCollectCountByUserShopCollectLevel(userShopCollectPojo
     * .getLevel()); if(rsUserShopCollectPojo.getUserShopCollectCount() > -1 &&
     * rsUserShopCollectPojo.getUserShopCollectCount() <= 9){
     * userShopCollectPojo.setUserShopCollectId(userShopCollectPojo.getLevel()+ "B0" +
     * (rsUserShopCollectPojo.getUserShopCollectCount() + 1)); }else
     * if(rsUserShopCollectPojo.getUserShopCollectCount() > 9 &&
     * rsUserShopCollectPojo.getUserShopCollectCount() <= 99){
     * userShopCollectPojo.setUserShopCollectId(userShopCollectPojo.getLevel()+ "B" +
     * (rsUserShopCollectPojo.getUserShopCollectCount() + 1)); } }
     */
    userShopCollectDao.insertUserShopCollect(userShopCollectPojo);
  }


  @Override
  public void insertUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException {

    userShopCollectDao.insertUserShopCollect(userShopCollectPojo);
  }

  @Override
  public void updateUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException {
    userShopCollectDao.updateUserShopCollect(userShopCollectPojo);

  }

  @Override
  public UserShopCollectPojo getfindByIdUserShopCollect(Long id) throws SQLException {
    return userShopCollectDao.getfindByIdUserShopCollect(id);

  }

  @Override
  public void deleteUserShopCollect(Map<String, Object> map) throws SQLException {

    userShopCollectDao.deleteUserShopCollect(map);
  }

  @Override
  public int userShopCollectAllCount(UserShopCollectPojo userShopCollectDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userShopCollectDaoPojo != null) {
      map.put("suserId", userShopCollectDaoPojo.getSuserId());
      map.put("shopId", userShopCollectDaoPojo.getShopId());
      map.put("userId", userShopCollectDaoPojo.getUserId());
    }
    return userShopCollectDao.userShopCollectAllCount(map);
  }


  @Override
  public int myShopCollectAllCount(long uid) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid != 0) {
      map.put("userId", uid);
    }
    return userShopCollectDao.userShopCollectAllCount(map);
  }

  @Override
  public List<UserShopCollectPojo> userShopCollectAllList(UserShopCollectPojo ticketRulePojo,
      Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    // if(ticketRulePojo!=null){
    // map.put("productName", ticketRulePojo.getProductName());
    // map.put("channel", ticketRulePojo.getChannel());
    // map.put("productModel", ticketRulePojo.getProductModel());
    // }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<UserShopCollectPojo> list = userShopCollectDao.userShopCollectAllList(map);

    return list;
  }

  @Override
  public void userShopCollectDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        userShopCollectDao.delUserShopCollect(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delUserShopCollect(Long id) throws SQLException {
    userShopCollectDao.delUserShopCollect(id);
  }

  @Override
  public UserShopCollectPojo findUserShopCollectById(Long id) throws SQLException {

    return userShopCollectDao.findUserShopCollectById(id);

  }

  @Override
  public List<UserShopCollectPojo> findUserShopCollectByUserId(Long id) throws SQLException {

    return userShopCollectDao.findUserShopCollectByUserId(id);

  }


  @Override
  public UserShopCollectPojo findUserShopCollectByShopId(UserShopCollectPojo userShopCollectPojo)
      throws SQLException {
    return userShopCollectDao.findUserShopCollectByShopId(userShopCollectPojo);
  }


  @Override
  public List<UserShopCollectPojo> userShopCollectByPage(UserShopCollectPojo userShopCollectPojo,
      Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userShopCollectPojo != null) {
      map.put("userId", userShopCollectPojo.getUserId());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return userShopCollectDao.userShopCollectByPage(map);
  }
}
