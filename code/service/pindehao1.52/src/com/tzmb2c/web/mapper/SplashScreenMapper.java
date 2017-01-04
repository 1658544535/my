package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SplashScreenPojo;

public interface SplashScreenMapper {
  List<SplashScreenPojo> findSplashScreenList(Map<String, Object> map) throws SQLException;

  int findSplashScreenCount(Map<String, Object> map) throws SQLException;

  void insertSplashScreen(SplashScreenPojo splashScreenPojo) throws SQLException;

  void delSplashScreen(Long id) throws SQLException;

  SplashScreenPojo findSplashScreenById(Long id) throws SQLException;

  void updateSplashScreen(SplashScreenPojo splashScreenPojo) throws SQLException;

  void checkSplashScreen(Long id) throws SQLException;

  void uncheckSplashScreen(Long id) throws SQLException;
}
