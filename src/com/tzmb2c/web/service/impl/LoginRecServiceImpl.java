package com.tzmb2c.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.LoginRecDao;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.service.LoginRecService;

public class LoginRecServiceImpl implements LoginRecService {
  @Autowired
  private LoginRecDao loginRecDao;

  @Override
  public void addLoginRec(LoginRecPojo loginRecPojo) throws Exception {
    loginRecDao.addLoginRec(loginRecPojo);
  }

  @Override
  public LoginRecPojo queryLoginRecBeforeItem(Long id) throws Exception {
    return loginRecDao.queryLoginRecBeforeItem(id);
  }

  @Override
  public Long queryTDLoinRecCount(String loginIp) {
    return loginRecDao.queryTDLoinRecCount(loginIp);
  }
}
