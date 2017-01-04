package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserVisitDao;
import com.tzmb2c.web.mapper.UserVisitMapper;
import com.tzmb2c.web.pojo.UserVisitPojo;

public class UserVisitDaoImpl implements UserVisitDao {

  @Autowired
  private UserVisitMapper userVisitMapper;

  @Override
  public List<UserVisitPojo> getTodayVisitPojos() throws SQLException {

    return userVisitMapper.getTodayVisitPojos();

  }

  @Override
  public int getTodayVisitNum() throws SQLException {

    return userVisitMapper.getTodayVisitNum();

  }

}
