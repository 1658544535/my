package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PushHomePagePojo;

public interface PushHomePageMapper {
  List<PushHomePagePojo> findAll(Map<String, Object> map) throws SQLException;

  PushHomePagePojo findPushHomePageById(Long id) throws SQLException;

  int findAllCount(Map<String, Object> map) throws SQLException;

  void insertPushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException;

  void delPushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException;

  void updatePushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException;

  void checkPushHomePage(Long id) throws SQLException;

  void uncheckPushHomePage(Long id) throws SQLException;

  void pushHomePageDeleteId(Long id) throws SQLException;
}
