package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HomePageIconDao;
import com.tzmb2c.web.mapper.HomePageIconMapper;
import com.tzmb2c.web.pojo.HomePageIconPojo;

public class HomePageIconDaoImpl implements HomePageIconDao {
  @Autowired
  private HomePageIconMapper homePageIconMapper;

  @Override
  public List<HomePageIconPojo> findHomePageIconList(Map<String, Object> map) throws SQLException {
    return homePageIconMapper.findHomePageIconList(map);
  }

  @Override
  public int findHomePageIconCount(Map<String, Object> map) throws SQLException {
    return homePageIconMapper.findHomePageIconCount(map);
  }

  @Override
  public void insertHomePageIcon(HomePageIconPojo homePageIconPojo) throws SQLException {
    homePageIconMapper.insertHomePageIcon(homePageIconPojo);
  }

  @Override
  public void delHomePageIcon(Long id) throws SQLException {
    homePageIconMapper.delHomePageIcon(id);
  }

  @Override
  public HomePageIconPojo findHomePageIconById(Long id) throws SQLException {
    return homePageIconMapper.findHomePageIconById(id);
  }

  @Override
  public void updateHomePageIcon(HomePageIconPojo homePageIconPojo) throws SQLException {
    homePageIconMapper.updateHomePageIcon(homePageIconPojo);
  }

  @Override
  public void checkHomePageIcon(Long id) throws SQLException {
    homePageIconMapper.checkHomePageIcon(id);
  }

  @Override
  public void uncheckHomePageIcon(Long id) throws SQLException {
    homePageIconMapper.uncheckHomePageIcon(id);
  }
}
