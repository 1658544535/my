package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityDao;
import com.tzmb2c.web.mapper.ActivityMapper;
import com.tzmb2c.web.pojo.ActivityPojo;


public class ActivityDaoImpl implements ActivityDao {

  @Autowired
  private ActivityMapper activityMapper;

  @Override
  public List<ActivityPojo> findAll(Map<String, Object> map) throws SQLException {
    return activityMapper.findAll(map);
  }

  @Override
  public int findAllCount(Map<String, Object> map) throws SQLException {
    return activityMapper.findAllCount(map);
  }

  @Override
  public void insertActivity(Map<String, Object> map) throws SQLException {
    activityMapper.insertActivity(map);
  }

  @Override
  public void delActivity(ActivityPojo activityPojo) throws SQLException {
    activityMapper.delActivity(activityPojo);
  }

  @Override
  public ActivityPojo findActivityById(Long id) throws SQLException {
    return activityMapper.findActivityById(id);
  }

  @Override
  public void updateActivity(ActivityPojo activityPojo) throws SQLException {
    activityMapper.updateActivity(activityPojo);
  }
}
