package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.VerificationPojo;

public interface VerificationMapper {

  /*
   * 获取所有的验证码条数
   */
  public int verificationAllListCount(Map<String, Object> map);

  /*
   * 获取验证码
   */
  public List<VerificationPojo> verificationAllList(Map<String, Object> map);



}
