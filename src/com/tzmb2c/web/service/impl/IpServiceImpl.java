package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.IpDao;
import com.tzmb2c.web.service.IpService;


public class IpServiceImpl implements IpService {
  @Autowired
  private IpDao ipDao;

  @Override
  public void insertIP(Map<String, Object> map) throws SQLException {
    ipDao.insertIp(map);
  }
}
