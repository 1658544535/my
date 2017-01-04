package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserToyLogPojo;

public interface UserToyLogDao {
  List<UserToyLogPojo> findAll(Map<String, Object> map) throws SQLException;

  int findAllCount(Map<String, Object> map) throws SQLException;

  UserToyLogPojo findUserToyLogById(Long id) throws SQLException;

  void updateUserToyLog(UserToyLogPojo userToyLogPojo) throws SQLException;

  void delUserToyLog(UserToyLogPojo userToyLogPojo) throws SQLException;

  void addUserToyLog(UserToyLogPojo userToyLogPojo) throws SQLException;
}
