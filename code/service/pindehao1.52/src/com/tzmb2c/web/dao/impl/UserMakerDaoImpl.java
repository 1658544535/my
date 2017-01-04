package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserMakerDao;
import com.tzmb2c.web.mapper.UserMakerMapper;
import com.tzmb2c.web.pojo.SysLoginPojo;

public class UserMakerDaoImpl implements UserMakerDao {
  @Autowired
  private UserMakerMapper userMakerMapper;

  @Override
  public List<SysLoginPojo> findUserMakerList(Map<String, Object> map) throws SQLException {
    return userMakerMapper.findUserMakerList(map);
  }

  @Override
  public int findUserMakerCount(Map<String, Object> map) throws SQLException {
    return userMakerMapper.findUserMakerCount(map);
  }

  @Override
  public SysLoginPojo findUserMakerById(Long id) throws SQLException {
    return userMakerMapper.findUserMakerById(id);
  }

  @Override
  public Long insertUserMaker(SysLoginPojo sysLoginPojo) throws SQLException {
    return userMakerMapper.insertUserMaker(sysLoginPojo);
  }

  @Override
  public void delUserMaker(Long id) throws SQLException {
    userMakerMapper.delUserMaker(id);
  }

  @Override
  public void checkUserMaker(Long id) throws SQLException {
    userMakerMapper.checkUserMaker(id);
  }

  @Override
  public void uncheckUserMaker(Long id) throws SQLException {
    userMakerMapper.uncheckUserMaker(id);
  }

  @Override
  public void updateUserMaker(SysLoginPojo sysLoginPojo) throws SQLException {
    userMakerMapper.updateUserMaker(sysLoginPojo);
  }
}
