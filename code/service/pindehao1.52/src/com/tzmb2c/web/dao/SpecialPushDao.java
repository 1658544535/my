package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SpecialPushPojo;

public interface SpecialPushDao {
  List<SpecialPushPojo> findSpecialPushList(Map<String, Object> map) throws SQLException;

  int findSpecialPushCount(Map<String, Object> map) throws SQLException;

  void insertSpecialPush(SpecialPushPojo specialPush) throws SQLException;

  void delSpecialPush(Long id) throws SQLException;

  SpecialPushPojo findSpecialPushById(Long id) throws SQLException;

  void updateSpecialPush(SpecialPushPojo specialPushPojo) throws SQLException;

  void checkSpecialPush(Long id) throws SQLException;

  void uncheckSpecialPush(Long id) throws SQLException;
}
