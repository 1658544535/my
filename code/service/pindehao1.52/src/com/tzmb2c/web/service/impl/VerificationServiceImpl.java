package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.VerificationDao;
import com.tzmb2c.web.pojo.VerificationPojo;
import com.tzmb2c.web.service.VerificationService;

public class VerificationServiceImpl implements VerificationService {
  @Autowired
  private VerificationDao VerificatioDao;

  public void setVerificationDao(VerificationDao verificationDao) {
    this.VerificatioDao = verificationDao;
  }

  @Override
  public List<VerificationPojo> VerificationAll(Map<String, Object> map) throws SQLException {

    return VerificatioDao.getVerificationAll(map);
  }

  @Override
  public int VerificationAllCount(Map<String, Object> map) {

    return VerificatioDao.VerificationListCount(map);
  }


}
