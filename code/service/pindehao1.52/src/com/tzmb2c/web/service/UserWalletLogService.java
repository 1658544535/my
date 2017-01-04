package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserWalletLogPojo;

public interface UserWalletLogService {
  List<UserWalletLogPojo> findUserWalletLogList(Map<String, Object> map) throws SQLException;

  int findUserWalletLogCount(Map<String, Object> map) throws SQLException;

  void insertUserWalletLog(UserWalletLogPojo userWalletLogPojo) throws SQLException;

  void delUserWalletLog(Long id) throws SQLException;

  UserWalletLogPojo findUserWalletLogById(Long id) throws SQLException;

  void updateUserWalletLog(UserWalletLogPojo userWalletLogPojo) throws SQLException;

  List<UserWalletLogPojo> findUserWalletLogByUserId(Long userId) throws SQLException;

  List<UserWalletLogPojo> getUserWalletLogByUserId(Long userId) throws SQLException;

  int getUserIncomeCountToday(Long uid) throws SQLException;
}
