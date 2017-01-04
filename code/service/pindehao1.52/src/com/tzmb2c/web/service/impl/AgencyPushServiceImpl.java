package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AgencyPushDao;
import com.tzmb2c.web.pojo.AgencyPushPojo;
import com.tzmb2c.web.service.AgencyPushService;

public class AgencyPushServiceImpl implements AgencyPushService {

  @Autowired
  private AgencyPushDao agencyPushDao;

  @Override
  public void insertAgencyPushRecord(AgencyPushPojo agencyPushPojo) throws SQLException {
    // TODO Auto-generated method stub
    agencyPushDao.insertAgencyPushRecord(agencyPushPojo);
  }

  @Override
  public void updateAgencyPushStatus(AgencyPushPojo agencyPushPojo) {
    agencyPushDao.updateAgencyPushStatus(agencyPushPojo);
  }

  @Override
  public void updateAgencyPushOtherStatus(AgencyPushPojo agencyPushPojo) {
    agencyPushDao.updateAgencyPushOtherStatus(agencyPushPojo);
  }

  @Override
  public AgencyPushPojo findAgencyPush(AgencyPushPojo agencyPushPojo) {
    return agencyPushDao.findAgencyPush(agencyPushPojo);
  }

  @Override
  public List<AgencyPushPojo> findAgencyPushOrder(Map<String, Object> map) {
    return agencyPushDao.findAgencyPushOrder(map);
  }

  @Override
  public int findAgencyPushCount(Map<String, Object> map) throws SQLException {

    return agencyPushDao.findAgencyPushCount(map);
  }
}
