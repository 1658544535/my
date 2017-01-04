package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.NavigationPojo;

public interface NavigationService {
  List<NavigationPojo> findNavigationList(Map<String, Object> map) throws SQLException;

  int findNavigationCount(Map<String, Object> map) throws SQLException;

  void insertNavigation(NavigationPojo navigationPojo) throws SQLException;

  void delNavigation(Long id) throws SQLException;

  NavigationPojo findNavigationById(Long id) throws SQLException;

  void updateNavigation(NavigationPojo navigationPojo) throws SQLException;

  void checkNavigation(Long id) throws SQLException;

  void uncheckNavigation(Long id) throws SQLException;
}
