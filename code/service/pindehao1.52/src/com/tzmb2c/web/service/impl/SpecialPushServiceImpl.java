package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialPushDao;
import com.tzmb2c.web.pojo.SpecialPushPojo;
import com.tzmb2c.web.service.SpecialPushService;

public class SpecialPushServiceImpl implements SpecialPushService {
  @Autowired
  private SpecialPushDao specialPushDao;

  @Override
  public List<SpecialPushPojo> findSpecialPushList(Map<String, Object> map) throws SQLException {
    return specialPushDao.findSpecialPushList(map);
  }

  @Override
  public int findSpecialPushCount(Map<String, Object> map) throws SQLException {
    return specialPushDao.findSpecialPushCount(map);
  }

  @Override
  public void insertSpecialPush(SpecialPushPojo specialPush) throws SQLException {
    specialPushDao.insertSpecialPush(specialPush);
  }

  @Override
  public void delSpecialPush(Long id) throws SQLException {
    // TODO Auto-generated method stub
    specialPushDao.delSpecialPush(id);
  }

  @Override
  public SpecialPushPojo findSpecialPushById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return specialPushDao.findSpecialPushById(id);
  }

  @Override
  public void updateSpecialPush(SpecialPushPojo specialPushPojo) throws SQLException {
    // TODO Auto-generated method stub
    specialPushDao.updateSpecialPush(specialPushPojo);
  }

  @Override
  public void checkSpecialPush(Long id) throws SQLException {
    // TODO Auto-generated method stub
    specialPushDao.checkSpecialPush(id);
  }

  @Override
  public void uncheckSpecialPush(Long id) throws SQLException {
    // TODO Auto-generated method stub
    specialPushDao.uncheckSpecialPush(id);
  }
}
