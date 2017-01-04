package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.BaiduLoginPojo;

public interface BaiduLoginDao {
  List<BaiduLoginPojo> findBaiduLoginList(Map<String, Object> map) throws SQLException;

  int findBaiduLoginCount(Map<String, Object> map) throws SQLException;

  void insertBaiduLogin(BaiduLoginPojo UserScore) throws SQLException;

  void delBaiduLogin(BaiduLoginPojo BaiduLoginPojo) throws SQLException;

  BaiduLoginPojo findBaiduLoginById(Long id) throws SQLException;

  void updateBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException;

  int checkMachineCodeRepeat(Long uid) throws SQLException;

  int checkMachineCodeRepeatByCode(String code) throws SQLException;
}
