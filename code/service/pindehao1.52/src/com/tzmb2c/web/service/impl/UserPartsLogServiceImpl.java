package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserPartsLogDao;
import com.tzmb2c.web.pojo.UserPartsLogPojo;
import com.tzmb2c.web.service.UserPartsLogService;

public class UserPartsLogServiceImpl implements UserPartsLogService {
  @Autowired
  private UserPartsLogDao userPartsLogDao;

  @Override
  public List<UserPartsLogPojo> findUserPartsLogList(Map<String, Object> map) throws SQLException {
    return userPartsLogDao.findUserPartsLogList(map);
  }

  @Override
  public int findUserPartsLogCount(Map<String, Object> map) throws SQLException {

    return userPartsLogDao.findUserPartsLogCount(map);
  }

  @Override
  public void insertUserPartsLog(UserPartsLogPojo userPartsLogPojo) throws SQLException {

    userPartsLogDao.insertUserPartsLog(userPartsLogPojo);
  }

  @Override
  public void delUserPartsLog(Long id) throws SQLException {

    userPartsLogDao.delUserPartsLog(id);
  }

  @Override
  public UserPartsLogPojo findUserPartsLogById(Long id) throws SQLException {

    return userPartsLogDao.findUserPartsLogById(id);
  }

  @Override
  public void updateUserPartsLog(UserPartsLogPojo userPartsLogPojo) throws SQLException {

    userPartsLogDao.updateUserPartsLog(userPartsLogPojo);
  }

  @Override
  public void checkUserPartsLog(Long id) throws SQLException {

    userPartsLogDao.checkUserPartsLog(id);
  }

  @Override
  public void uncheckUserPartsLog(Long id) throws SQLException {

    userPartsLogDao.uncheckUserPartsLog(id);
  }
}
