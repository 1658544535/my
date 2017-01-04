package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserVerifyDao;
import com.tzmb2c.web.mapper.UserVerifyMapper;
import com.tzmb2c.web.pojo.UserVerifyPojo;

public class UserVerifyDaoImpl implements UserVerifyDao {

  @Autowired
  private UserVerifyMapper userVerifyMapper;

  @Override
  public List<UserVerifyPojo> userVerifyFind(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return userVerifyMapper.userVerifyFind(map);
  }

  @Override
  public void userVerifyAdd(UserVerifyPojo userVerifyPojo) throws SQLException {
    userVerifyMapper.userVerifyAdd(userVerifyPojo);

  }

  @Override
  public UserVerifyPojo findNewestByPhone(UserVerifyPojo userVerifyPojo) {
    return userVerifyMapper.findNewestByPhone(userVerifyPojo);
  }
}
