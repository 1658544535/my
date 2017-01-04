package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.IpDao;
import com.tzmb2c.web.mapper.IpMapper;


public class IpDaoImpl implements IpDao {
  @Autowired
  private IpMapper ipMapper;

  @Override
  public void insertIp(Map<String, Object> map) throws SQLException {
    ipMapper.insertIp(map);
  }
}
