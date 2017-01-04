package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AgencyPushPojo;

public interface AgencyPushMapper {

  public void insertAgencyPushRecord(AgencyPushPojo agencyPushPojo) throws SQLException;// 保存推送记录

  public void updateAgencyPushStatus(AgencyPushPojo agencyPushPojo);

  public void updateAgencyPushOtherStatus(AgencyPushPojo agencyPushPojo);

  public AgencyPushPojo findAgencyPush(AgencyPushPojo agencyPushPojo);

  public List<AgencyPushPojo> findAgencyPushOrder(Map<String, Object> map);

  public int findAgencyPushCount(Map<String, Object> map);

}
