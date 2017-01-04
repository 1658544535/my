package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserScoreDao;
import com.tzmb2c.web.pojo.UserScorePojo;
import com.tzmb2c.web.service.UserScoreService;

public class UserScoreServiceImpl implements UserScoreService {
  @Autowired
  private UserScoreDao userScoreDao;

  @Override
  public List<UserScorePojo> findUserScoreList(Map<String, Object> map) throws SQLException {
    return userScoreDao.findUserScoreList(map);
  }

  @Override
  public int findUserScoreCount(Map<String, Object> map) throws SQLException {
    return userScoreDao.findUserScoreCount(map);
  }

  @Override
  public UserScorePojo findUserScoreById(Long id) throws SQLException {
    return userScoreDao.findUserScoreById(id);
  }

  @Override
  public UserScorePojo findUserScoreByUid(Long uid) throws SQLException {
    return userScoreDao.findUserScoreByUid(uid);
  }

  @Override
  public void insertUserScore(UserScorePojo userScore) throws SQLException {
    userScoreDao.insertUserScore(userScore);
  }

  @Override
  public void delUserScoreById(Long id) throws SQLException {
    userScoreDao.delUserScoreById(id);
  }

  @Override
  public void delUserScoreByUid(Long uid) throws SQLException {
    userScoreDao.delUserScoreByUid(uid);
  }

  @Override
  public void updateUserScoreByUid(UserScorePojo userScore) throws SQLException {
    userScoreDao.updateUserScoreByUid(userScore);
  }


}
