package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserMakerDao;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.UserMakerService;

public class UserMakerServiceImpl implements UserMakerService {
  @Autowired
  private UserMakerDao userMakerDao;

  @Override
  public List<SysLoginPojo> findUserMakerList(Map<String, Object> map) throws SQLException {

    return userMakerDao.findUserMakerList(map);
  }
  @Override
  public int findUserMakerCount(Map<String, Object> map) throws SQLException {
    return userMakerDao.findUserMakerCount(map);
  }

  @Override
  public SysLoginPojo findUserMakerById(Long id) throws SQLException {
    return userMakerDao.findUserMakerById(id);
  }

  @Override
  public Long insertUserMaker(SysLoginPojo sysLoginPojo) throws SQLException {
    return userMakerDao.insertUserMaker(sysLoginPojo);
  }

  @Override
  public void delUserMaker(Long id) throws SQLException {
    userMakerDao.delUserMaker(id);
  }

  @Override
  public void checkUserMaker(Long id) throws SQLException {
    userMakerDao.checkUserMaker(id);
  }

  @Override
  public void uncheckUserMaker(Long id) throws SQLException {
    userMakerDao.uncheckUserMaker(id);
  }

  @Override
  public void updateUserMaker(SysLoginPojo sysLoginPojo) throws SQLException {
    userMakerDao.updateUserMaker(sysLoginPojo);
  }

}
