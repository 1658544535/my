package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserVisitDao;
import com.tzmb2c.web.pojo.UserVisitPojo;
import com.tzmb2c.web.service.UserVisitService;

public class UserVisitServiceImpl implements UserVisitService {
  @Autowired
  private UserVisitDao userVisitDao;

  @Override
  public List<UserVisitPojo> getTodayVisitPojos() throws SQLException {

    return userVisitDao.getTodayVisitPojos();
  }

  @Override
  public int getTodayVisitNum() throws SQLException {

    return userVisitDao.getTodayVisitNum();
  }


}
