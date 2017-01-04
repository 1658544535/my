package com.tzmb2c.web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.LoginRecDao;
import com.tzmb2c.web.mapper.LoginRecMapper;
import com.tzmb2c.web.pojo.LoginRecPojo;

public class LoginRecDaoImpl implements LoginRecDao {
  @Autowired
  private LoginRecMapper loginRecMapper;

  @Override
  public void addLoginRec(LoginRecPojo loginRecPojo) throws Exception {
    loginRecMapper.addLoginRec(loginRecPojo);
  }

  @Override
  public LoginRecPojo queryLoginRecBeforeItem(Long id) throws Exception {
    return loginRecMapper.queryLoginRecBeforeItem(id);
  }

  @Override
  public Long queryTDLoinRecCount(String loginIp) {
    return loginRecMapper.queryTDLoinRecCount(loginIp);
  }
}
