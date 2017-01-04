package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserMakerThemeDao;
import com.tzmb2c.web.pojo.UserMakerThemePojo;
import com.tzmb2c.web.service.UserMakerThemeService;

// @Service
public class UserMakerThemeServiceImpl implements UserMakerThemeService {

  @Autowired
  private UserMakerThemeDao userMakerThemeDao;

  @Override
  @Transactional
  public int addUserMakerTheme(UserMakerThemePojo userMakerTheme) {
    if (null == userMakerTheme) {
      return 0;
    }
    int rows = userMakerThemeDao.addUserMakerTheme(userMakerTheme);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserMakerTheme(UserMakerThemePojo userMakerTheme) {
    if (null == userMakerTheme) {
      return 0;
    }
    int rows = userMakerThemeDao.updateUserMakerTheme(userMakerTheme);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserMakerThemeById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userMakerThemeDao.deleteUserMakerThemeById(id);
    return rows;
  }


  @Override
  public UserMakerThemePojo getUserMakerThemeById(Long id) {
    if (null == id) {
      return null;
    }
    UserMakerThemePojo userMakerTheme = userMakerThemeDao.getUserMakerThemeById(id);
    //
    return userMakerTheme;
  }

  @Override
  public Integer userMakerThemeCount(Map<String, Object> params) {
    Integer rows = userMakerThemeDao.userMakerThemeCount(params);
    return rows;
  }

  @Override
  public List<UserMakerThemePojo> userMakerThemeList(Map<String, Object> params) {
    List<UserMakerThemePojo> lists = userMakerThemeDao.userMakerThemeList(params);

    return lists;
  }

  @Override
  public int updateUserMakerThemeById(UserMakerThemePojo userMakerTheme) {
    if (null == userMakerTheme) {
      return 0;
    }
    int rows = userMakerThemeDao.updateUserMakerThemeById(userMakerTheme);
    return rows;
  }

  @Override
  public int increaseUserMakerThemeNumById(UserMakerThemePojo userMakerTheme) {
    // TODO Auto-generated method stub
    return userMakerThemeDao.increaseUserMakerThemeNumById(userMakerTheme);
  }

  @Override
  public int decreaseUserMakerThemeNumById(UserMakerThemePojo userMakerTheme) {
    // TODO Auto-generated method stub
    return userMakerThemeDao.decreaseUserMakerThemeNumById(userMakerTheme);
  }
}
