package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserMakerThemeDao;
import com.tzmb2c.web.mapper.UserMakerThemeMapper;
import com.tzmb2c.web.pojo.UserMakerThemePojo;

// @Service
public class UserMakerThemeDaoImpl implements UserMakerThemeDao {

  @Autowired
  private UserMakerThemeMapper userMakerThemeMapper;

  @Override
  @Transactional
  public int addUserMakerTheme(UserMakerThemePojo userMakerTheme) {
    if (null == userMakerTheme) {
      return 0;
    }
    int rows = userMakerThemeMapper.addUserMakerTheme(userMakerTheme);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserMakerTheme(UserMakerThemePojo userMakerTheme) {
    if (null == userMakerTheme) {
      return 0;
    }
    int rows = userMakerThemeMapper.updateUserMakerTheme(userMakerTheme);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserMakerThemeById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userMakerThemeMapper.deleteUserMakerThemeById(id);
    return rows;
  }


  @Override
  public UserMakerThemePojo getUserMakerThemeById(Long id) {
    if (null == id) {
      return null;
    }
    UserMakerThemePojo userMakerTheme = userMakerThemeMapper.getUserMakerThemeById(id);
    //
    return userMakerTheme;
  }

  @Override
  public Integer userMakerThemeCount(Map<String, Object> params) {
    Integer rows = userMakerThemeMapper.userMakerThemeCount(params);
    return rows;
  }

  @Override
  public List<UserMakerThemePojo> userMakerThemeList(Map<String, Object> params) {
    List<UserMakerThemePojo> lists = userMakerThemeMapper.userMakerThemeList(params);

    return lists;
  }

  @Override
  public int updateUserMakerThemeById(UserMakerThemePojo userMakerTheme) {
    if (null == userMakerTheme) {
      return 0;
    }
    int rows = userMakerThemeMapper.updateUserMakerThemeById(userMakerTheme);
    return rows;
  }

  @Override
  public int increaseUserMakerThemeNumById(UserMakerThemePojo userMakerTheme) {
    // TODO Auto-generated method stub
    return userMakerThemeMapper.increaseUserMakerThemeNumById(userMakerTheme);
  }

  @Override
  public int decreaseUserMakerThemeNumById(UserMakerThemePojo userMakerTheme) {
    // TODO Auto-generated method stub
    return userMakerThemeMapper.decreaseUserMakerThemeNumById(userMakerTheme);
  }
}
