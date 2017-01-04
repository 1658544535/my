package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.VerificationDao;
import com.tzmb2c.web.mapper.VerificationMapper;
import com.tzmb2c.web.pojo.VerificationPojo;



public class VerificationDaoImpl implements VerificationDao {

  @Autowired
  private VerificationMapper verificationMapper;


  @Override
  public int VerificationListCount(Map<String, Object> map) {

    return verificationMapper.verificationAllListCount(map);
  }


  @Override
  public List<VerificationPojo> getVerificationAll(Map<String, Object> map) {

    return verificationMapper.verificationAllList(map);
  }



}
