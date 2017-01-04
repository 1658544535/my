package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PushHomePagePojo;

public interface PushHomePageService {
  List<PushHomePagePojo> findAll(Map<String, Object> map) throws SQLException;

  int findAllCount(Map<String, Object> map) throws SQLException;

  void insertPushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException;

  void delPushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException;

  PushHomePagePojo findPushHomePageById(Long id) throws SQLException;

  void updatePushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException;

  void checkPushHomePage(Long id) throws SQLException;

  void uncheckPushHomePage(Long id) throws SQLException;

  void pushHomePageDeleteId(String[] tids) throws SQLException;

}
