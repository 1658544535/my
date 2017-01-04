package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserScoreLogDao;
import com.tzmb2c.web.mapper.UserScoreLogMapper;
import com.tzmb2c.web.pojo.UserScoreLogPojo;

public class UserScoreLogDaoImpl implements UserScoreLogDao {
  @Autowired
  private UserScoreLogMapper userScoreLogMapper;

  @Override
  public List<UserScoreLogPojo> findUserScoreLogList(Map<String, Object> map) throws SQLException {
    return userScoreLogMapper.findUserScoreLogList(map);
  }

  @Override
  public int findUserScoreLogCount(Map<String, Object> map) throws SQLException {
    return userScoreLogMapper.findUserScoreLogCount(map);
  }

  @Override
  public UserScoreLogPojo findUserScoreLogById(Long id) throws SQLException {
    return userScoreLogMapper.findUserScoreLogById(id);
  }

  @Override
  public List<UserScoreLogPojo> findUserScoreLogByUid(Long uid) throws SQLException {
    return userScoreLogMapper.findUserScoreLogByUid(uid);
  }

  @Override
  public void insertUserScoreLog(UserScoreLogPojo userScoreLog) throws SQLException {
    userScoreLogMapper.insertUserScoreLog(userScoreLog);
  }

  @Override
  public void delUserScoreLogById(Long id) throws SQLException {
    userScoreLogMapper.delUserScoreLogById(id);
  }

  @Override
  public void delUserScoreLogByUid(Long uid) throws SQLException {
    userScoreLogMapper.delUserScoreLogByUid(uid);
  }

  @Override
  public void updateUserScoreLogById(UserScoreLogPojo userScoreLog) throws SQLException {
    userScoreLogMapper.updateUserScoreLogById(userScoreLog);
  }


}
