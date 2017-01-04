package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserScorePojo;

public interface UserScoreService {
  List<UserScorePojo> findUserScoreList(Map<String, Object> map) throws SQLException;

  int findUserScoreCount(Map<String, Object> map) throws SQLException;

  UserScorePojo findUserScoreById(Long id) throws SQLException;

  UserScorePojo findUserScoreByUid(Long uid) throws SQLException;

  void insertUserScore(UserScorePojo userScore) throws SQLException;

  void delUserScoreById(Long id) throws SQLException;

  void delUserScoreByUid(Long uid) throws SQLException;

  void updateUserScoreByUid(UserScorePojo userScore) throws SQLException;
}
