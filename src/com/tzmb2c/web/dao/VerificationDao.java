package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.VerificationPojo;

public interface VerificationDao {
  /*
   * 获取验证码
   */
  public List<VerificationPojo> getVerificationAll(Map<String, Object> map);

  /*
   * 获取验证码条数
   */
  public int VerificationListCount(Map<String, Object> map);
}
