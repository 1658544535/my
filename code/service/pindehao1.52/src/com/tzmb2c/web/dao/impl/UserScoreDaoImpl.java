package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserScoreDao;
import com.tzmb2c.web.mapper.UserScoreMapper;
import com.tzmb2c.web.pojo.UserScorePojo;

public class UserScoreDaoImpl implements UserScoreDao {
  @Autowired
  private UserScoreMapper userScoreMapper;

  @Override
  public List<UserScorePojo> findUserScoreList(Map<String, Object> map) throws SQLException {
    return userScoreMapper.findUserScoreList(map);
  }

  @Override
  public int findUserScoreCount(Map<String, Object> map) throws SQLException {
    return userScoreMapper.findUserScoreCount(map);
  }

  @Override
  public UserScorePojo findUserScoreById(Long id) throws SQLException {
    return userScoreMapper.findUserScoreById(id);
  }

  @Override
  public UserScorePojo findUserScoreByUid(Long uid) throws SQLException {
    return userScoreMapper.findUserScoreByUid(uid);
  }

  @Override
  public void insertUserScore(UserScorePojo userScore) throws SQLException {
    userScoreMapper.insertUserScore(userScore);
  }

  @Override
  public void delUserScoreById(Long id) throws SQLException {
    userScoreMapper.delUserScoreById(id);
  }

  @Override
  public void delUserScoreByUid(Long uid) throws SQLException {
    userScoreMapper.delUserScoreByUid(uid);
  }

  @Override
  public void updateUserScoreByUid(UserScorePojo userScore) throws SQLException {
    userScoreMapper.updateUserScoreByUid(userScore);
  }

}
