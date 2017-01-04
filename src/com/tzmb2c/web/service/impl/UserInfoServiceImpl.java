package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.UserInfoDao;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {
  @Autowired
  private UserInfoDao userInfoDao;

  public void setUserInfoDao(UserInfoDao userInfoDao) {
    this.userInfoDao = userInfoDao;
  }

  @Override
  public void insertUserInfo(UserInfoPojo userInfoPojo) throws SQLException {
    userInfoDao.insertUserInfo(userInfoPojo);

  }

  @Override
  public List<UserInfoPojo> userInfoAllService() throws SQLException {
    return userInfoDao.getUserInfoAll();
  }

  @Override
  public void updateUserInfo(UserInfoPojo userInfoPojo) throws SQLException {
    userInfoDao.updateUserInfo(userInfoPojo);

  }

  @Override
  public UserInfoPojo getfindByIdUserInfo(Long id) throws SQLException {
    return userInfoDao.getfindByIdUserInfo(id);

  }

  @Override
  public int userInfoAllCount(UserInfoPojo userInfoDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userInfoDaoPojo != null) {
      map.put("address", userInfoDaoPojo.getAddress());
      map.put("phone", userInfoDaoPojo.getPhone());
    }
    return userInfoDao.userInfoAllCount(map);
  }

  @Override
  public List<UserInfoPojo> userInfoAllList(UserInfoPojo userInfoDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (userInfoDaoPojo != null) {
      map.put("address", userInfoDaoPojo.getAddress());
      map.put("phone", userInfoDaoPojo.getPhone());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<UserInfoPojo> list = userInfoDao.userInfoAllList(map);

    return list;
  }

  @Override
  public void userInfoChecks(String[] tids) {
    for (String tid : tids) {
      try {
        userInfoDao.checkUserInfo(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkUserInfo(Long id) throws SQLException {
    userInfoDao.checkUserInfo(id);
  }

  @Override
  public void delUserInfo(Long id) throws SQLException {
    userInfoDao.delUserInfo(id);
  }

  @Override
  public void delUserInfobyUserId(Long id) throws SQLException {
    userInfoDao.delUserInfobyUserId(id);
  }

  @Override
  public UserInfoPojo findUserInfoById(Long id) throws SQLException {

    return userInfoDao.findUserInfoById(id);

  }

  @Override
  public UserInfoPojo findUserInfoByUserId(Long id) throws SQLException {

    return userInfoDao.findUserInfoByUserId(id);

  }

  @Override
  public void uncheckUserInfo(Long id) throws SQLException {
    userInfoDao.uncheckUserInfo(id);
  }

  @Override
  public int userInfoAllCount2(Map<String, Object> map) throws SQLException {
    return userInfoDao.userInfoAllCount2(map);
  }

  @Override
  public List<UserInfoPojo> userInfoAllList2(Map<String, Object> map) {
    return userInfoDao.userInfoAllList2(map);
  }

  @Override
  public void updateUserInfo2(UserInfoPojo userInfoPojo) throws SQLException {
    userInfoDao.updateUserInfo2(userInfoPojo);
  }

  @Override
  public UserInfoPojo getUserInfoById(Long id) throws SQLException {
    return userInfoDao.getUserInfoById(id);
  }

  @Override
  public int jinwanhaoAllCount(UserInfoPojo userInfoDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userInfoDaoPojo != null) {
      map.put("address", userInfoDaoPojo.getAddress());
      map.put("phone", userInfoDaoPojo.getPhone());
      map.put("QQ", userInfoDaoPojo.getQQ());
      map.put("userId", userInfoDaoPojo.getUserId());
      map.put("loginName", userInfoDaoPojo.getLoginName());
      map.put("createDateStr", userInfoDaoPojo.getCreateDateStr());
    }
    return userInfoDao.jinwanhaoAllCount(map);
  }

  @Override
  public List<UserInfoPojo> jinwanhaoAllList(UserInfoPojo userInfoDaoPojo, Pager page) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (userInfoDaoPojo != null) {
      map.put("address", userInfoDaoPojo.getAddress());
      map.put("phone", userInfoDaoPojo.getPhone());
      map.put("QQ", userInfoDaoPojo.getQQ());
      map.put("userId", userInfoDaoPojo.getUserId());
      map.put("loginName", userInfoDaoPojo.getLoginName());
      map.put("createDateStr", userInfoDaoPojo.getCreateDateStr());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<UserInfoPojo> list = userInfoDao.jinwanhaoAllList(map);

    return list;
  }
}
