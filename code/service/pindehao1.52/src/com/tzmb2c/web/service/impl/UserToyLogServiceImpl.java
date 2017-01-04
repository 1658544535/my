package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserToyLogDao;
import com.tzmb2c.web.pojo.UserToyLogPojo;
import com.tzmb2c.web.service.UserToyLogService;


public class UserToyLogServiceImpl implements UserToyLogService {
  @Autowired
  private UserToyLogDao userToyLogDao;

  @Override
  public List<UserToyLogPojo> findAll(Map<String, Object> map) throws SQLException {
    return userToyLogDao.findAll(map);
  }

  @Override
  public int findAllCount(Map<String, Object> map) throws SQLException {
    return userToyLogDao.findAllCount(map);
  }

  @Override
  public void delUserToyLog(UserToyLogPojo UserToyLogPojo) throws SQLException {

    userToyLogDao.delUserToyLog(UserToyLogPojo);
  }

  @Override
  public UserToyLogPojo findUserToyLogById(Long id) throws SQLException {

    return userToyLogDao.findUserToyLogById(id);
  }

  @Override
  public void updateUserToyLog(UserToyLogPojo UserToyLogPojo) throws SQLException {

    userToyLogDao.updateUserToyLog(UserToyLogPojo);
  }

  @Override
  public void addUserToyLog(UserToyLogPojo userToyLogPojo) throws SQLException {
    // TODO Auto-generated method stub
    userToyLogDao.addUserToyLog(userToyLogPojo);
  }



}
