package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserAttestationPojo;

public interface UserAttestationDao {

  void addAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  UserAttestationPojo findAttestationInfoById(Long id) throws SQLException;

  void updateAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  void delAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  void checkAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  void delAllAttestationInfoById(String id) throws SQLException;

  void checkAllAttestationInfoById(String id) throws SQLException;

  public List<UserAttestationPojo> attestationInfoAllList(Map<String, Object> map);

  public int attestationInfoAllCount(Map<String, Object> map);

}
