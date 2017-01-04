package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserAttestationPojo;

public interface UserAttestationMapper {

  public int attestationInfoAllCount(Map<String, Object> map);

  public List<UserAttestationPojo> attestationInfoAllList(Map<String, Object> map);

  public void addAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  public UserAttestationPojo findAttestationInfoById(Long id);

  public void updateAttestationInfo(UserAttestationPojo userAttestationPojo);

  public void delAllAttestationInfoById(String id) throws SQLException;

  public void delAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  public void checkAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException;

  public void checkAllAttestationInfoById(String id) throws SQLException;

}
