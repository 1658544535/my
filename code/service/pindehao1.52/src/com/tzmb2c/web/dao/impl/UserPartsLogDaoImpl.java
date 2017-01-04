package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserPartsLogDao;
import com.tzmb2c.web.mapper.UserPartsLogMapper;
import com.tzmb2c.web.pojo.UserPartsLogPojo;

public class UserPartsLogDaoImpl implements UserPartsLogDao {
  @Autowired
  private UserPartsLogMapper userPartsLogMapper;

  @Override
  public List<UserPartsLogPojo> findUserPartsLogList(Map<String, Object> map) throws SQLException {
    return userPartsLogMapper.findUserPartsLogList(map);
  }

  @Override
  public int findUserPartsLogCount(Map<String, Object> map) throws SQLException {

    return userPartsLogMapper.findUserPartsLogCount(map);
  }

  @Override
  public void insertUserPartsLog(UserPartsLogPojo userPartsLogPojo) throws SQLException {

    userPartsLogMapper.insertUserPartsLog(userPartsLogPojo);
  }

  @Override
  public void delUserPartsLog(Long id) throws SQLException {

    userPartsLogMapper.delUserPartsLog(id);
  }

  @Override
  public UserPartsLogPojo findUserPartsLogById(Long id) throws SQLException {

    return userPartsLogMapper.findUserPartsLogById(id);
  }

  @Override
  public void updateUserPartsLog(UserPartsLogPojo userPartsLogPojo) throws SQLException {

    userPartsLogMapper.updateUserPartsLog(userPartsLogPojo);
  }

  @Override
  public void checkUserPartsLog(Long id) throws SQLException {

    userPartsLogMapper.checkUserPartsLog(id);
  }

  @Override
  public void uncheckUserPartsLog(Long id) throws SQLException {

    userPartsLogMapper.uncheckUserPartsLog(id);
  }
}
