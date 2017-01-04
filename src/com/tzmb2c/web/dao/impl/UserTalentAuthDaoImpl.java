package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserTalentAuthDao;
import com.tzmb2c.web.mapper.UserTalentAuthMapper;
import com.tzmb2c.web.pojo.UserTalentAuthPojo;

public class UserTalentAuthDaoImpl implements UserTalentAuthDao {
  @Autowired
  private UserTalentAuthMapper userTalentAuthMapper;

  @Override
  public List<UserTalentAuthPojo> findUserTalentAuthList(Map<String, Object> map)
      throws SQLException {
    return userTalentAuthMapper.findUserTalentAuthList(map);
  }

  @Override
  public int findUserTalentAuthCount(Map<String, Object> map) throws SQLException {
    return userTalentAuthMapper.findUserTalentAuthCount(map);
  }

  @Override
  public void insertUserTalentAuth(UserTalentAuthPojo userTalentAuthPojo) throws SQLException {
    userTalentAuthMapper.insertUserTalentAuth(userTalentAuthPojo);

  }

  @Override
  public UserTalentAuthPojo findUserTalentAuthById(Long id) throws SQLException {
    return userTalentAuthMapper.findUserTalentAuthById(id);
  }

  @Override
  public void checkUserTalentAuth(Long id) throws SQLException {
    userTalentAuthMapper.checkUserTalentAuth(id);
  }

  @Override
  public void uncheckUserTalentAuth(Long id) throws SQLException {
    userTalentAuthMapper.uncheckUserTalentAuth(id);
  }

  @Override
  public UserTalentAuthPojo findUserTalentAuthByUid(Long uid) throws SQLException {
    return userTalentAuthMapper.findUserTalentAuthByUid(uid);
  }

  @Override
  public int updateUserTalentAuth(UserTalentAuthPojo userTalentAuthPojo) throws SQLException {
    return userTalentAuthMapper.updateUserTalentAuth(userTalentAuthPojo);
  }

  @Override
  public List<UserTalentAuthPojo> searchUserTalentAuth(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return userTalentAuthMapper.searchUserTalentAuth(map);
  }
}
