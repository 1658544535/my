package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ActivityPojo;

public interface ActivityDao {
  List<ActivityPojo> findAll(Map<String, Object> map) throws SQLException;

  int findAllCount(Map<String, Object> map) throws SQLException;

  void insertActivity(Map<String, Object> map) throws SQLException;

  void delActivity(ActivityPojo activityPojo) throws SQLException;

  ActivityPojo findActivityById(Long id) throws SQLException;

  void updateActivity(ActivityPojo activityPojo) throws SQLException;
}
