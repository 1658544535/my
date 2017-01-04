package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialPushDao;
import com.tzmb2c.web.mapper.SpecialPushMapper;
import com.tzmb2c.web.pojo.SpecialPushPojo;

public class SpecialPushDaoImpl implements SpecialPushDao {
  @Autowired
  private SpecialPushMapper specialPushMapper;

  @Override
  public List<SpecialPushPojo> findSpecialPushList(Map<String, Object> map) throws SQLException {
    return specialPushMapper.findSpecialPushList(map);
  }

  @Override
  public int findSpecialPushCount(Map<String, Object> map) throws SQLException {
    return specialPushMapper.findSpecialPushCount(map);
  }

  @Override
  public void insertSpecialPush(SpecialPushPojo SpecialPush) throws SQLException {
    specialPushMapper.insertSpecialPush(SpecialPush);
  }

  @Override
  public void delSpecialPush(Long id) throws SQLException {
    // TODO Auto-generated method stub
    specialPushMapper.delSpecialPush(id);
  }

  @Override
  public SpecialPushPojo findSpecialPushById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return specialPushMapper.findSpecialPushById(id);
  }

  @Override
  public void updateSpecialPush(SpecialPushPojo specialPushPojo) throws SQLException {
    // TODO Auto-generated method stub
    specialPushMapper.updateSpecialPush(specialPushPojo);
  }

  @Override
  public void checkSpecialPush(Long id) throws SQLException {
    // TODO Auto-generated method stub
    specialPushMapper.checkSpecialPush(id);
  }

  @Override
  public void uncheckSpecialPush(Long id) throws SQLException {
    // TODO Auto-generated method stub
    specialPushMapper.uncheckSpecialPush(id);
  }
}
