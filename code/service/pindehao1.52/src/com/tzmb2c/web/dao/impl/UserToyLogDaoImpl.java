package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserToyLogDao;
import com.tzmb2c.web.mapper.UserToyLogMapper;
import com.tzmb2c.web.pojo.UserToyLogPojo;


public class UserToyLogDaoImpl implements UserToyLogDao {

  @Autowired
  private UserToyLogMapper UserToyLogMapper;

  @Override
  public List<UserToyLogPojo> findAll(Map<String, Object> map) throws SQLException {
    return UserToyLogMapper.findAll(map);
  }

  @Override
  public int findAllCount(Map<String, Object> map) throws SQLException {
    return UserToyLogMapper.findAllCount(map);
  }

  @Override
  public void delUserToyLog(UserToyLogPojo UserToyLogPojo) throws SQLException {
    UserToyLogMapper.delUserToyLog(UserToyLogPojo);
  }

  @Override
  public UserToyLogPojo findUserToyLogById(Long id) throws SQLException {
    return UserToyLogMapper.findUserToyLogById(id);
  }

  @Override
  public void updateUserToyLog(UserToyLogPojo UserToyLogPojo) throws SQLException {
    UserToyLogMapper.updateUserToyLog(UserToyLogPojo);
  }

  @Override
  public void addUserToyLog(UserToyLogPojo userToyLogPojo) throws SQLException {
    // TODO Auto-generated method stub
    UserToyLogMapper.addUserToyLog(userToyLogPojo);
  }
}
