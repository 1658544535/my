package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPartsLogPojo;

public interface UserPartsLogDao {
  List<UserPartsLogPojo> findUserPartsLogList(Map<String, Object> map) throws SQLException;

  int findUserPartsLogCount(Map<String, Object> map) throws SQLException;

  void insertUserPartsLog(UserPartsLogPojo userPartsLogPojo) throws SQLException;

  void delUserPartsLog(Long id) throws SQLException;

  UserPartsLogPojo findUserPartsLogById(Long id) throws SQLException;

  void updateUserPartsLog(UserPartsLogPojo userPartsLogPojo) throws SQLException;

  void checkUserPartsLog(Long id) throws SQLException;

  void uncheckUserPartsLog(Long id) throws SQLException;
}
