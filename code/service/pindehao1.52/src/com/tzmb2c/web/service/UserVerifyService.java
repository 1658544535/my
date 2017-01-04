package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserVerifyPojo;

public interface UserVerifyService {

  public List<UserVerifyPojo> userVerifyFind(Map<String, Object> map) throws SQLException;

  public void userVerifyAdd(UserVerifyPojo userVerifyPojo) throws SQLException;

  public UserVerifyPojo findNewestByPhone(UserVerifyPojo userVerifyPojo);
}
