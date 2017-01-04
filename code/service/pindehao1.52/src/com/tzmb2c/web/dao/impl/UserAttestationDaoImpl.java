package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserAttestationDao;
import com.tzmb2c.web.mapper.UserAttestationMapper;
import com.tzmb2c.web.pojo.UserAttestationPojo;

public class UserAttestationDaoImpl implements UserAttestationDao {

  @Autowired
  private UserAttestationMapper userAttestationMapper;

  @Override
  public void addAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationMapper.addAttestationInfo(userAttestationPojo);
  }

  @Override
  public UserAttestationPojo findAttestationInfoById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return userAttestationMapper.findAttestationInfoById(id);
  }

  @Override
  public void updateAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationMapper.updateAttestationInfo(userAttestationPojo);
  }

  @Override
  public void delAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationMapper.delAttestationInfo(userAttestationPojo);
  }

  @Override
  public void checkAttestationInfo(UserAttestationPojo userAttestationPojo) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationMapper.checkAttestationInfo(userAttestationPojo);
  }

  @Override
  public void delAllAttestationInfoById(String id) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationMapper.delAllAttestationInfoById(id);
  }

  @Override
  public void checkAllAttestationInfoById(String id) throws SQLException {
    // TODO Auto-generated method stub
    userAttestationMapper.checkAllAttestationInfoById(id);
  }

  @Override
  public List<UserAttestationPojo> attestationInfoAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userAttestationMapper.attestationInfoAllList(map);
  }

  @Override
  public int attestationInfoAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userAttestationMapper.attestationInfoAllCount(map);
  }

}
