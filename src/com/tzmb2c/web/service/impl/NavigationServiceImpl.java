package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.NavigationDao;
import com.tzmb2c.web.pojo.NavigationPojo;
import com.tzmb2c.web.service.NavigationService;

public class NavigationServiceImpl implements NavigationService {
  @Autowired
  private NavigationDao navigationDao;

  @Override
  public List<NavigationPojo> findNavigationList(Map<String, Object> map) throws SQLException {
    return navigationDao.findNavigationList(map);
  }

  @Override
  public int findNavigationCount(Map<String, Object> map) throws SQLException {
    return navigationDao.findNavigationCount(map);
  }

  @Override
  public void insertNavigation(NavigationPojo navigationPojo) throws SQLException {
    navigationDao.insertNavigation(navigationPojo);

  }

  @Override
  public NavigationPojo findNavigationById(Long id) throws SQLException {
    return navigationDao.findNavigationById(id);
  }

  @Override
  public void updateNavigation(NavigationPojo navigationPojo) throws SQLException {
    navigationDao.updateNavigation(navigationPojo);
  }

  @Override
  public void checkNavigation(Long id) throws SQLException {
    navigationDao.checkNavigation(id);
  }

  @Override
  public void uncheckNavigation(Long id) throws SQLException {
    navigationDao.uncheckNavigation(id);
  }

  @Override
  public void delNavigation(Long id) throws SQLException {
    navigationDao.delNavigation(id);
  }
}
