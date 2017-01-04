package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserScoreLogDao;
import com.tzmb2c.web.pojo.UserScoreLogPojo;
import com.tzmb2c.web.service.UserScoreLogService;

public class UserScoreLogServiceImpl implements UserScoreLogService {
  @Autowired
  private UserScoreLogDao userScoreLogDao;

  @Override
  public List<UserScoreLogPojo> findUserScoreLogList(Map<String, Object> map) throws SQLException {
    return userScoreLogDao.findUserScoreLogList(map);
  }

  @Override
  public int findUserScoreLogCount(Map<String, Object> map) throws SQLException {
    return userScoreLogDao.findUserScoreLogCount(map);
  }

  @Override
  public UserScoreLogPojo findUserScoreLogById(Long id) throws SQLException {
    return userScoreLogDao.findUserScoreLogById(id);
  }

  @Override
  public List<UserScoreLogPojo> findUserScoreLogByUid(Long uid) throws SQLException {
    return userScoreLogDao.findUserScoreLogByUid(uid);
  }

  @Override
  public void insertUserScoreLog(UserScoreLogPojo userScoreLog) throws SQLException {
    userScoreLogDao.insertUserScoreLog(userScoreLog);
  }

  @Override
  public void delUserScoreLogById(Long id) throws SQLException {
    userScoreLogDao.delUserScoreLogById(id);
  }

  @Override
  public void delUserScoreLogByUid(Long uid) throws SQLException {
    userScoreLogDao.delUserScoreLogByUid(uid);
  }

  @Override
  public void updateUserScoreLogById(UserScoreLogPojo userScoreLog) throws SQLException {
    userScoreLogDao.updateUserScoreLogById(userScoreLog);
  }

}
