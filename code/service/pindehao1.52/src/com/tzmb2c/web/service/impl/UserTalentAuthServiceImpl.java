package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserTalentAuthDao;
import com.tzmb2c.web.pojo.UserTalentAuthPojo;
import com.tzmb2c.web.service.UserTalentAuthService;

public class UserTalentAuthServiceImpl implements UserTalentAuthService {
  @Autowired
  private UserTalentAuthDao userTalentAuthDao;

  @Override
  public List<UserTalentAuthPojo> findUserTalentAuthList(Map<String, Object> map)
      throws SQLException {
    return userTalentAuthDao.findUserTalentAuthList(map);
  }

  @Override
  public int findUserTalentAuthCount(Map<String, Object> map) throws SQLException {
    return userTalentAuthDao.findUserTalentAuthCount(map);
  }

  @Override
  public void insertUserTalentAuth(UserTalentAuthPojo userTalentAuthPojo) throws SQLException {
    userTalentAuthDao.insertUserTalentAuth(userTalentAuthPojo);

  }

  @Override
  public UserTalentAuthPojo findUserTalentAuthById(Long id) throws SQLException {
    return userTalentAuthDao.findUserTalentAuthById(id);
  }

  @Override
  public void checkUserTalentAuth(Long id) throws SQLException {
    userTalentAuthDao.checkUserTalentAuth(id);
  }

  @Override
  public void uncheckUserTalentAuth(Long id) throws SQLException {
    userTalentAuthDao.uncheckUserTalentAuth(id);
  }

  @Override
  public UserTalentAuthPojo findUserTalentAuthByUid(Long uid) throws SQLException {
    return userTalentAuthDao.findUserTalentAuthByUid(uid);
  }

  @Override
  public int updateUserTalentAuth(UserTalentAuthPojo userTalentAuthPojo) throws SQLException {
    return userTalentAuthDao.updateUserTalentAuth(userTalentAuthPojo);
  }

  @Override
  public List<UserTalentAuthPojo> searchUserTalentAuth(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return userTalentAuthDao.searchUserTalentAuth(map);
  }
}
