package com.tzmb2c.web.dao;

import com.tzmb2c.web.pojo.LoginRecPojo;

public interface LoginRecDao {

  void addLoginRec(LoginRecPojo loginRecPojo) throws Exception;

  LoginRecPojo queryLoginRecBeforeItem(Long id) throws Exception;

  Long queryTDLoinRecCount(String loginIp);
}
