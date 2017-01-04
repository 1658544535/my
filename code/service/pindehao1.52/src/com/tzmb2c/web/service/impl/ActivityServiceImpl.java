package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityDao;
import com.tzmb2c.web.pojo.ActivityPojo;
import com.tzmb2c.web.service.ActivityService;


public class ActivityServiceImpl implements ActivityService {
  @Autowired
  private ActivityDao activityDao;
  private ActivityPojo activityPojo;

  @Override
  public List<ActivityPojo> findAll(Map<String, Object> map) throws SQLException {
    if (map.containsKey(activityPojo) != false) {
      map.put("title", activityPojo.getTitle());// 查询的标题
      map.put("type", activityPojo.getType());// 查询的类型
    }
    return activityDao.findAll(map);
  }

  @Override
  public int findAllCount(Map<String, Object> map) throws SQLException {
    if (activityPojo != null) {
      map.put("title", activityPojo.getTitle());// 查询的标题
      map.put("type", activityPojo.getType());// 查询的类型
    }
    return activityDao.findAllCount(map);
  }

  @Override
  public void insertActivity(Map<String, Object> map) throws SQLException {
    activityDao.insertActivity(map);
  }

  @Override
  public void delActivity(ActivityPojo activityPojo) throws SQLException {

    activityDao.delActivity(activityPojo);
  }

  @Override
  public ActivityPojo findActivityById(Long id) throws SQLException {

    return activityDao.findActivityById(id);
  }

  @Override
  public void updateActivity(ActivityPojo activityPojo) throws SQLException {

    activityDao.updateActivity(activityPojo);
  }



}
