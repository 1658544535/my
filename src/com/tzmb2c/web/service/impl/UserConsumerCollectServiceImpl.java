package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.UserConsumerCollectDao;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.service.UserConsumerCollectService;

/**
 * 
 * @author EricChen
 * 
 */
public class UserConsumerCollectServiceImpl implements UserConsumerCollectService {
  @Autowired
  private UserConsumerCollectDao userConsumerCollectDao;

  @Override
  public void delUserConsumerCollect(Long id) throws SQLException {
    userConsumerCollectDao.delUserConsumerCollect(id);
  }

  @Override
  public List<UserConsumerCollectPojo> getAllList(UserConsumerCollectPojo userConsumerCollectPojo) {
    return userConsumerCollectDao.getAllList(userConsumerCollectPojo);
  }

  @Override
  public UserConsumerCollectPojo findCollect(UserConsumerCollectPojo userConsumerCollectPojo) {
    return userConsumerCollectDao.findCollect(userConsumerCollectPojo);
  }

  @Override
  public void insertUserConsumerCollect(UserConsumerCollectPojo userConsumerCollectPojo)
      throws SQLException {
    userConsumerCollectDao.insertUserConsumerCollect(userConsumerCollectPojo);
  }

  @Override
  public List<UserConsumerCollectPojo> findCollect2(
      UserConsumerCollectPojo userConsumerCollectPojo, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (userConsumerCollectPojo != null) {
      map.put("userId", userConsumerCollectPojo.getUserId());
    }
    return userConsumerCollectDao.findCollect2(map);
  }

  @Override
  public int findCountByUserId(Long userId) throws SQLException {
    return userConsumerCollectDao.findCountByUserId(userId);
  }

  @Override
  public void deleteUserConsumerCollect(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    userConsumerCollectDao.deleteUserConsumerCollect(map);
  }
}
