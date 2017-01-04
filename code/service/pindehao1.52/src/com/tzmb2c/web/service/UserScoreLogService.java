package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserScoreLogPojo;

public interface UserScoreLogService {
  List<UserScoreLogPojo> findUserScoreLogList(Map<String, Object> map) throws SQLException;

  int findUserScoreLogCount(Map<String, Object> map) throws SQLException;

  UserScoreLogPojo findUserScoreLogById(Long id) throws SQLException;

  List<UserScoreLogPojo> findUserScoreLogByUid(Long uid) throws SQLException;

  void insertUserScoreLog(UserScoreLogPojo userScoreLog) throws SQLException;

  void delUserScoreLogById(Long id) throws SQLException;

  void delUserScoreLogByUid(Long uid) throws SQLException;

  void updateUserScoreLogById(UserScoreLogPojo userScoreLog) throws SQLException;
}
