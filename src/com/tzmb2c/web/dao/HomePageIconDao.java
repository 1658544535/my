package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HomePageIconPojo;

public interface HomePageIconDao {
  List<HomePageIconPojo> findHomePageIconList(Map<String, Object> map) throws SQLException;

  int findHomePageIconCount(Map<String, Object> map) throws SQLException;

  void insertHomePageIcon(HomePageIconPojo homePageIconPojo) throws SQLException;

  void delHomePageIcon(Long id) throws SQLException;

  HomePageIconPojo findHomePageIconById(Long id) throws SQLException;

  void updateHomePageIcon(HomePageIconPojo homePageIconPojo) throws SQLException;

  void checkHomePageIcon(Long id) throws SQLException;

  void uncheckHomePageIcon(Long id) throws SQLException;
}
