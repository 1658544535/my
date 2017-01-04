package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.EleorderDao;
import com.tzmb2c.web.mapper.EleorderMapper;
import com.tzmb2c.web.pojo.EleorderPojo;

public class EleorderDaoImpl implements EleorderDao {
  @Autowired
  private EleorderMapper eleorderMapper;

  @Override
  public List<EleorderPojo> findEleorderAll(Map<String, Object> map) throws SQLException {
    return eleorderMapper.findEleorderAll(map);
  }

  @Override
  public EleorderPojo findEleorderByorderNos(Map<String, Object> map) throws SQLException {
    return eleorderMapper.findEleorderByorderNos(map);
  }

  @Override
  public List<EleorderPojo> findEleorderByorderNo(Map<String, Object> map) throws SQLException {
    return eleorderMapper.findEleorderByorderNo(map);
  }

  @Override
  public int EleorderAllCount(Map<String, Object> map) throws SQLException {
    return eleorderMapper.EleorderAllCount(map);
  }

  @Override
  public void insertEleorder(Map<String, Object> map) throws SQLException {
    eleorderMapper.insertEleorder(map);
  }

  @Override
  public int sellSumNum(Long productId) throws SQLException {
    return eleorderMapper.sellSumNum(productId);
  }

}
