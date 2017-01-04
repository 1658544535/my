package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserWalletLogDao;
import com.tzmb2c.web.mapper.UserWalletLogMapper;
import com.tzmb2c.web.pojo.UserWalletLogPojo;

public class UserWalletLogDaoImpl implements UserWalletLogDao {
  @Autowired
  private UserWalletLogMapper userWalletLogMapper;

  @Override
  public List<UserWalletLogPojo> findUserWalletLogList(Map<String, Object> map) throws SQLException {

    return userWalletLogMapper.findUserWalletLogList(map);
  }

  @Override
  public int findUserWalletLogCount(Map<String, Object> map) throws SQLException {

    return userWalletLogMapper.findUserWalletLogCount(map);
  }

  @Override
  public void insertUserWalletLog(UserWalletLogPojo userWalletLogPojo) throws SQLException {

    userWalletLogMapper.insertUserWalletLog(userWalletLogPojo);
  }

  @Override
  public void delUserWalletLog(Long id) throws SQLException {

    userWalletLogMapper.delUserWalletLog(id);
  }

  @Override
  public UserWalletLogPojo findUserWalletLogById(Long id) throws SQLException {

    return userWalletLogMapper.findUserWalletLogById(id);
  }

  @Override
  public void updateUserWalletLog(UserWalletLogPojo userWalletLogPojo) throws SQLException {

    userWalletLogMapper.updateUserWalletLog(userWalletLogPojo);
  }

  @Override
  public List<UserWalletLogPojo> findUserWalletLogByUserId(Long userId) throws SQLException {

    return userWalletLogMapper.findUserWalletLogByUserId(userId);
  }

  @Override
  public List<UserWalletLogPojo> getUserWalletLogByUserId(Long userId) throws SQLException {

    return userWalletLogMapper.getUserWalletLogByUserId(userId);
  }

  @Override
  public int getUserIncomeCountToday(Long uid) throws SQLException {
    return userWalletLogMapper.getUserIncomeCountToday(uid);
  }
}
