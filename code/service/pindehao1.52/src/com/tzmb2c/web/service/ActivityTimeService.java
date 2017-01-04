package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ActivityTimePojo;

public interface ActivityTimeService {
  List<ActivityTimePojo> findActivityTimeList(Map<String, Object> map) throws SQLException;

  int findActivityTimeCount(Map<String, Object> map) throws SQLException;

  ActivityTimePojo findActivityTimeById(Long id) throws SQLException;

  Long insertActivityTime(ActivityTimePojo activityTime) throws SQLException;

  void delActivityTime(Long id) throws SQLException;

  void updateActivityTime(ActivityTimePojo activityTime) throws SQLException;

  List<ActivityTimePojo> getActivityTimeList(ActivityTimePojo activityTimePojo) throws SQLException;
}
