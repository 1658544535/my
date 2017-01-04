package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.BaiduLoginPojo;

public interface BaiduLoginMapper {
  List<BaiduLoginPojo> findBaiduLoginList(Map<String, Object> map) throws SQLException;

  int findBaiduLoginCount(Map<String, Object> map) throws SQLException;

  void insertBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException;

  void delBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException;

  BaiduLoginPojo findBaiduLoginById(Long id) throws SQLException;

  void updateBaiduLogin(BaiduLoginPojo baiduLoginPojo) throws SQLException;

  int checkMachineCodeRepeat(Long uid) throws SQLException;

  int checkMachineCodeRepeatByCode(String code) throws SQLException;
}
