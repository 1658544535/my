package com.tzmb2c.web.mapper;

import com.tzmb2c.web.pojo.LoginRecPojo;

public interface LoginRecMapper {

  public void addLoginRec(LoginRecPojo loginRecPojo);

  public LoginRecPojo queryLoginRecBeforeItem(Long id);

  public Long queryTDLoinRecCount(String loginIp);

}
