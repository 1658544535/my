package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.EleorderDao;
import com.tzmb2c.web.pojo.EleorderPojo;
import com.tzmb2c.web.service.EleorderService;


public class EleorderServiceImpl implements EleorderService {
  @Autowired
  private EleorderDao eleorderDao;

  @Override
  public List<EleorderPojo> findEleorderAll(Map<String, Object> map) throws SQLException {
    return eleorderDao.findEleorderAll(map);
  }

  @Override
  public List<EleorderPojo> findEleorderByorderNo(Map<String, Object> map) throws SQLException {
    return eleorderDao.findEleorderByorderNo(map);
  }

  @Override
  public EleorderPojo findEleorderByorderNos(Map<String, Object> map) throws SQLException {
    return eleorderDao.findEleorderByorderNos(map);
  }

  @Override
  public int EleorderAllCount(Map<String, Object> map) throws SQLException {
    return eleorderDao.EleorderAllCount(map);
  }

  @Override
  public void insertEleorder(Map<String, Object> map) throws SQLException {
    eleorderDao.insertEleorder(map);
  }

  @Override
  public int sellSumNum(Long productId) throws SQLException {
    return eleorderDao.sellSumNum(productId);
  }
}
