package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgencyPushPojo;

public interface AgencyPushDao {

  public void insertAgencyPushRecord(AgencyPushPojo agencyPushPojo) throws SQLException;// 保存推送记录

  void updateAgencyPushStatus(AgencyPushPojo agencyPushPojo);

  void updateAgencyPushOtherStatus(AgencyPushPojo agencyPushPojo);

  AgencyPushPojo findAgencyPush(AgencyPushPojo agencyPushPojo);

  List<AgencyPushPojo> findAgencyPushOrder(Map<String, Object> map);

  public int findAgencyPushCount(Map<String, Object> map) throws SQLException;

}
