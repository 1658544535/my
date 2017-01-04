package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserAttestationPojo;

public interface UserAttestationService {

  void addAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  UserAttestationPojo findAttestationInfoById(Long id) throws SQLException;

  void updateAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  void delAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  void checkAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  public List<UserAttestationPojo> attestationInfoAllList(UserAttestationPojo userAttestationPojo,
      Pager page, String type);

  public int attestationInfoAllCount(UserAttestationPojo userAttestationPojo, String type);

  public void delAllAttestationInfoById(String[] tids);

  public void checkAllAttestationInfoById(String[] tids);

}
