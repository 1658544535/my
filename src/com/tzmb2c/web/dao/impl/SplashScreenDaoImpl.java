package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SplashScreenDao;
import com.tzmb2c.web.mapper.SplashScreenMapper;
import com.tzmb2c.web.pojo.SplashScreenPojo;

public class SplashScreenDaoImpl implements SplashScreenDao {
  @Autowired
  private SplashScreenMapper splashScreenMapper;

  @Override
  public List<SplashScreenPojo> findSplashScreenList(Map<String, Object> map) throws SQLException {
    return splashScreenMapper.findSplashScreenList(map);
  }

  @Override
  public int findSplashScreenCount(Map<String, Object> map) throws SQLException {
    return splashScreenMapper.findSplashScreenCount(map);
  }

  @Override
  public void insertSplashScreen(SplashScreenPojo splashScreenPojo) throws SQLException {
    splashScreenMapper.insertSplashScreen(splashScreenPojo);
  }

  @Override
  public void delSplashScreen(Long id) throws SQLException {
    splashScreenMapper.delSplashScreen(id);
  }

  @Override
  public SplashScreenPojo findSplashScreenById(Long id) throws SQLException {
    return splashScreenMapper.findSplashScreenById(id);
  }

  @Override
  public void updateSplashScreen(SplashScreenPojo splashScreenPojo) throws SQLException {
    splashScreenMapper.updateSplashScreen(splashScreenPojo);
  }

  @Override
  public void checkSplashScreen(Long id) throws SQLException {
    splashScreenMapper.checkSplashScreen(id);
  }

  @Override
  public void uncheckSplashScreen(Long id) throws SQLException {
    splashScreenMapper.uncheckSplashScreen(id);
  }

}
