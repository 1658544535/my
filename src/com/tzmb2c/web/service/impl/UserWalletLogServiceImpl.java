package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserWalletLogDao;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.service.UserWalletLogService;

public class UserWalletLogServiceImpl implements UserWalletLogService {
  @Autowired
  private UserWalletLogDao userWalletLogDao;

  @Override
  public List<UserWalletLogPojo> findUserWalletLogList(Map<String, Object> map) throws SQLException {

    return userWalletLogDao.findUserWalletLogList(map);
  }

  @Override
  public int findUserWalletLogCount(Map<String, Object> map) throws SQLException {

    return userWalletLogDao.findUserWalletLogCount(map);
  }

  @Override
  public void insertUserWalletLog(UserWalletLogPojo userWalletLogPojo) throws SQLException {

    userWalletLogDao.insertUserWalletLog(userWalletLogPojo);
  }

  @Override
  public void delUserWalletLog(Long id) throws SQLException {

    userWalletLogDao.delUserWalletLog(id);
  }

  @Override
  public UserWalletLogPojo findUserWalletLogById(Long id) throws SQLException {

    return userWalletLogDao.findUserWalletLogById(id);
  }

  @Override
  public void updateUserWalletLog(UserWalletLogPojo userWalletLogPojo) throws SQLException {

    userWalletLogDao.updateUserWalletLog(userWalletLogPojo);
  }

  @Override
  public List<UserWalletLogPojo> findUserWalletLogByUserId(Long userId) throws SQLException {

    return userWalletLogDao.findUserWalletLogByUserId(userId);
  }

  @Override
  public List<UserWalletLogPojo> getUserWalletLogByUserId(Long userId) throws SQLException {

    return userWalletLogDao.getUserWalletLogByUserId(userId);
  }

  @Override
  public int getUserIncomeCountToday(Long uid) throws SQLException {
    return userWalletLogDao.getUserIncomeCountToday(uid);
  }
}
