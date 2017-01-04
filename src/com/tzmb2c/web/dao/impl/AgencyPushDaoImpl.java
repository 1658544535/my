package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgencyPushDao;
import com.tzmb2c.web.mapper.AgencyPushMapper;
import com.tzmb2c.web.pojo.AgencyPushPojo;

public class AgencyPushDaoImpl implements AgencyPushDao {
  @Autowired
  private AgencyPushMapper agencyPushMapper;

  @Override
  public void insertAgencyPushRecord(AgencyPushPojo agencyPushPojo) throws SQLException {
    // TODO Auto-generated method stub
    agencyPushMapper.insertAgencyPushRecord(agencyPushPojo);
  }

  @Override
  public void updateAgencyPushStatus(AgencyPushPojo agencyPushPojo) {
    agencyPushMapper.updateAgencyPushStatus(agencyPushPojo);
  }

  @Override
  public void updateAgencyPushOtherStatus(AgencyPushPojo agencyPushPojo) {
    agencyPushMapper.updateAgencyPushOtherStatus(agencyPushPojo);
  }

  @Override
  public AgencyPushPojo findAgencyPush(AgencyPushPojo agencyPushPojo) {
    return agencyPushMapper.findAgencyPush(agencyPushPojo);
  }

  @Override
  public List<AgencyPushPojo> findAgencyPushOrder(Map<String, Object> map) {
    return agencyPushMapper.findAgencyPushOrder(map);
  }

  @Override
  public int findAgencyPushCount(Map<String, Object> map) throws SQLException {
    return agencyPushMapper.findAgencyPushCount(map);
  }
}
