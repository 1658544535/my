package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.VerificationPojo;

public interface VerificationService {
  /*
   * 获取验证码
   */
  public List<VerificationPojo> VerificationAll(Map<String, Object> map) throws SQLException;

  /**
   * 获取验证码条数
   * 
   * @param map
   * @return
   */
  public int VerificationAllCount(Map<String, Object> map);
}
