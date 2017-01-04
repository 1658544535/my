package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.UserCollectDao;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.service.UserCollectService;

public class UserCollectServiceImpl implements UserCollectService {
  @Autowired
  private UserCollectDao userCollectDao;

  public void setUserCollectDao(UserCollectDao userCollectDao) {
    this.userCollectDao = userCollectDao;
  }


  @Override
  public List<UserCollectPojo> userCollectAllService() throws SQLException {
    return userCollectDao.getUserCollectAll();
  }



  @Override
  public void addUserCollectService(UserCollectPojo userCollectPojo) throws SQLException {

    /*
     * if("0".equals(userCollectPojo.getUserCollectLevel())){ UserCollectPojo rsUserCollectPojo =
     * userCollectDao.getUserCollectCountByUserCollectLevel(userCollectPojo.getLevel()); int
     * userCollectId =0; if(rsUserCollectPojo.getUserCollectCount() > -1 &&
     * rsUserCollectPojo.getUserCollectCount() <= 9){ userCollectId =
     * rsUserCollectPojo.getUserCollectCount()+1; userCollectPojo.setUserCollectId("A0" +
     * userCollectId); }else if(rsUserCollectPojo.getUserCollectCount() > 9 &&
     * rsUserCollectPojo.getUserCollectCount() <= 99){ userCollectId =
     * rsUserCollectPojo.getUserCollectCount()+1; userCollectPojo.setUserCollectId("A"
     * +userCollectId ); } //userCollectPojo.setUserCollectPath("#"); }else{ UserCollectPojo
     * rsUserCollectPojo =
     * userCollectDao.getUserCollectCountByUserCollectLevel(userCollectPojo.getLevel());
     * if(rsUserCollectPojo.getUserCollectCount() > -1 && rsUserCollectPojo.getUserCollectCount() <=
     * 9){ userCollectPojo.setUserCollectId(userCollectPojo.getLevel()+ "B0" +
     * (rsUserCollectPojo.getUserCollectCount() + 1)); }else
     * if(rsUserCollectPojo.getUserCollectCount() > 9 && rsUserCollectPojo.getUserCollectCount() <=
     * 99){ userCollectPojo.setUserCollectId(userCollectPojo.getLevel()+ "B" +
     * (rsUserCollectPojo.getUserCollectCount() + 1)); } }
     */
    // if (userCollectPojo.getType() == null) {
    // userCollectPojo.setType(0L);
    // }
    userCollectDao.insertUserCollect(userCollectPojo);
  }


  @Override
  public void insertUserCollect(UserCollectPojo userCollectPojo) throws SQLException {

    userCollectDao.insertUserCollect(userCollectPojo);
  }

  @Override
  public void updateUserCollect(UserCollectPojo userCollectPojo) throws SQLException {
    userCollectDao.updateUserCollect(userCollectPojo);

  }

  @Override
  public UserCollectPojo getfindByIdUserCollect(Long id) throws SQLException {
    return userCollectDao.getfindByIdUserCollect(id);

  }

  @Override
  public void deleteUserCollect(Map<String, Object> map) throws SQLException {

    userCollectDao.deleteUserCollect(map);
  }

  @Override
  public int userCollectAllCount(UserCollectPojo userCollectDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userCollectDaoPojo != null) {
      if (userCollectDaoPojo.getActivityId() != null) {
        map.put("activityId", userCollectDaoPojo.getActivityId());
      }
      if (userCollectDaoPojo.getUserId() != null) {
        map.put("userId", userCollectDaoPojo.getUserId());
      }
      if (userCollectDaoPojo.getType() != null) {
        map.put("type", userCollectDaoPojo.getType());
      }
      if (userCollectDaoPojo.getProductId() != null) {
        map.put("productId", userCollectDaoPojo.getProductId());
      }
      if (userCollectDaoPojo.getIsDelete() != null) {
        map.put("isDelete", userCollectDaoPojo.getIsDelete());
      }
    }
    return userCollectDao.userCollectAllCount(map);
  }


  @Override
  public int productCollectAllCount(UserCollectPojo userCollectDaoPojo) {
    return userCollectDao.productCollectAllCount(userCollectDaoPojo);
  }

  @Override
  public int myProductCollectAllCount(long uid) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (uid != 0) {
      map.put("userId", uid);
    }
    return userCollectDao.userCollectAllCount(map);
  }

  @Override
  public List<UserCollectPojo> userCollectAllList(UserCollectPojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      if (ticketRulePojo.getActivityId() != null) {
        map.put("activityId", ticketRulePojo.getActivityId());
      }
      if (ticketRulePojo.getUserId() != null) {
        map.put("userId", ticketRulePojo.getUserId());
      }
      if (ticketRulePojo.getType() != null) {
        map.put("type", ticketRulePojo.getType());
      }
      if (ticketRulePojo.getProductId() != null) {
        map.put("productId", ticketRulePojo.getProductId());
      }
      if (ticketRulePojo.getIsDelete() != null) {
        map.put("isDelete", ticketRulePojo.getIsDelete());
      }
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<UserCollectPojo> list = userCollectDao.userCollectAllList(map);

    return list;
  }

  @Override
  public void userCollectDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        userCollectDao.delUserCollect(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delUserCollect(Long id) throws SQLException {
    userCollectDao.delUserCollect(id);
  }

  @Override
  public UserCollectPojo findUserCollectById(Long id) throws SQLException {

    return userCollectDao.findUserCollectById(id);

  }

  @Override
  public UserCollectPojo findUserCollectByProductId(UserCollectPojo userCollectPojo)
      throws SQLException {

    return userCollectDao.findUserCollectByProductId(userCollectPojo);

  }

  @Override
  public List<UserCollectPojo> findUserCollectByUserId(Long id) throws SQLException {

    return userCollectDao.findUserCollectByUserId(id);

  }


  @Override
  public List<UserCollectPojo> UserCollectByUserId(UserCollectPojo userCollectPojo, Pager page)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (userCollectPojo != null) {
      map.put("userId", userCollectPojo.getUserId());
      map.put("type", userCollectPojo.getType() != null ? 1 : 0);
    }
    List<UserCollectPojo> list = userCollectDao.UserCollectByUserId(map);

    return list;
  }


  @Override
  public List<UserCollectPojo> findUserCollectByUserIdPage(UserCollectPojo userCollectPojo,
      Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (userCollectPojo != null) {
      map.put("userId", userCollectPojo.getUserId());
    }
    return userCollectDao.findUserCollectByUserIdPage(map);
  }
}
