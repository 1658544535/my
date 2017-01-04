package com.tzmb2c.web.service;

import com.tzmb2c.web.pojo.LoginRecPojo;

public interface LoginRecService {
  void addLoginRec(LoginRecPojo loginRecPojo) throws Exception;

  LoginRecPojo queryLoginRecBeforeItem(Long id) throws Exception;

  Long queryTDLoinRecCount(String loginIp);
}
