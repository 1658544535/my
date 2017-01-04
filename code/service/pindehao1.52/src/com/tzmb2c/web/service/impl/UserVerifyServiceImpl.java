package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserVerifyDao;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.service.UserVerifyService;

public class UserVerifyServiceImpl implements UserVerifyService {
  @Autowired
  private UserVerifyDao userVerifyDao;


  @Override
  public List<UserVerifyPojo> userVerifyFind(Map<String, Object> map) throws SQLException {
    return userVerifyDao.userVerifyFind(map);
  }

  @Override
  public void userVerifyAdd(UserVerifyPojo userVerifyPojo) throws SQLException {
    userVerifyDao.userVerifyAdd(userVerifyPojo);

  }

  @Override
  public UserVerifyPojo findNewestByPhone(UserVerifyPojo userVerifyPojo) {
    return userVerifyDao.findNewestByPhone(userVerifyPojo);
  }
}
