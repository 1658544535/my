package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserToyLogPojo;

public interface UserToyLogService {
  List<UserToyLogPojo> findAll(Map<String, Object> map) throws SQLException;

  int findAllCount(Map<String, Object> map) throws SQLException;

  void delUserToyLog(UserToyLogPojo UserToyLogPojo) throws SQLException;

  UserToyLogPojo findUserToyLogById(Long id) throws SQLException;

  void updateUserToyLog(UserToyLogPojo UserToyLogPojo) throws SQLException;

  void addUserToyLog(UserToyLogPojo userToyLogPojo) throws SQLException;
}
