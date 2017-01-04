package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysLoginPojo;

public interface UserMakerMapper {
  List<SysLoginPojo> findUserMakerList(Map<String, Object> map) throws SQLException;

  int findUserMakerCount(Map<String, Object> map) throws SQLException;

  SysLoginPojo findUserMakerById(Long id) throws SQLException;

  Long insertUserMaker(SysLoginPojo sysLoginPojo) throws SQLException;

  void delUserMaker(Long id) throws SQLException;

  void checkUserMaker(Long id) throws SQLException;

  void uncheckUserMaker(Long id) throws SQLException;

  void updateUserMaker(SysLoginPojo sysLoginPojo) throws SQLException;
}
