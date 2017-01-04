package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SplashScreenDao;
import com.tzmb2c.web.pojo.SplashScreenPojo;
import com.tzmb2c.web.service.SplashScreenService;

public class SplashScreenServiceImpl implements SplashScreenService {
  @Autowired
  private SplashScreenDao splashScreenDao;

  @Override
  public List<SplashScreenPojo> findSplashScreenList(Map<String, Object> map) throws SQLException {
    return splashScreenDao.findSplashScreenList(map);
  }

  @Override
  public int findSplashScreenCount(Map<String, Object> map) throws SQLException {
    return splashScreenDao.findSplashScreenCount(map);
  }

  @Override
  public void insertSplashScreen(SplashScreenPojo splashScreenPojo) throws SQLException {
    splashScreenDao.insertSplashScreen(splashScreenPojo);
  }

  @Override
  public void delSplashScreen(Long id) throws SQLException {
    splashScreenDao.delSplashScreen(id);
  }

  @Override
  public SplashScreenPojo findSplashScreenById(Long id) throws SQLException {
    return splashScreenDao.findSplashScreenById(id);
  }

  @Override
  public void updateSplashScreen(SplashScreenPojo splashScreenPojo) throws SQLException {
    splashScreenDao.updateSplashScreen(splashScreenPojo);
  }

  @Override
  public void checkSplashScreen(Long id) throws SQLException {
    splashScreenDao.checkSplashScreen(id);
  }

  @Override
  public void uncheckSplashScreen(Long id) throws SQLException {
    splashScreenDao.uncheckSplashScreen(id);
  }
}
