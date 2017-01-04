package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HomePageIconDao;
import com.tzmb2c.web.pojo.HomePageIconPojo;
import com.tzmb2c.web.service.HomePageIconService;

public class HomePageIconServiceImpl implements HomePageIconService {
  @Autowired
  private HomePageIconDao homePageIconDao;

  @Override
  public List<HomePageIconPojo> findHomePageIconList(Map<String, Object> map) throws SQLException {
    return homePageIconDao.findHomePageIconList(map);
  }

  @Override
  public int findHomePageIconCount(Map<String, Object> map) throws SQLException {
    return homePageIconDao.findHomePageIconCount(map);
  }

  @Override
  public void insertHomePageIcon(HomePageIconPojo homePageIconPojo) throws SQLException {
    homePageIconDao.insertHomePageIcon(homePageIconPojo);
  }

  @Override
  public void delHomePageIcon(Long id) throws SQLException {
    homePageIconDao.delHomePageIcon(id);
  }

  @Override
  public HomePageIconPojo findHomePageIconById(Long id) throws SQLException {
    return homePageIconDao.findHomePageIconById(id);
  }

  @Override
  public void updateHomePageIcon(HomePageIconPojo homePageIconPojo) throws SQLException {
    homePageIconDao.updateHomePageIcon(homePageIconPojo);
  }

  @Override
  public void checkHomePageIcon(Long id) throws SQLException {
    homePageIconDao.checkHomePageIcon(id);
  }

  @Override
  public void uncheckHomePageIcon(Long id) throws SQLException {
    homePageIconDao.uncheckHomePageIcon(id);
  }
}
