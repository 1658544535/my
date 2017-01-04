package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityTimeDao;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.service.ActivityTimeService;

public class ActivityTimeServiceImpl implements ActivityTimeService {
  @Autowired
  private ActivityTimeDao activityTimeDao;

  @Override
  public List<ActivityTimePojo> findActivityTimeList(Map<String, Object> map) throws SQLException {
    return activityTimeDao.findActivityTimeList(map);
  }

  @Override
  public int findActivityTimeCount(Map<String, Object> map) throws SQLException {
    return activityTimeDao.findActivityTimeCount(map);
  }

  @Override
  public ActivityTimePojo findActivityTimeById(Long id) throws SQLException {
    return activityTimeDao.findActivityTimeById(id);
  }

  @Override
  public Long insertActivityTime(ActivityTimePojo activityTime) throws SQLException {
    return activityTimeDao.insertActivityTime(activityTime);

  }

  @Override
  public void delActivityTime(Long id) throws SQLException {
    activityTimeDao.delActivityTime(id);
  }

  @Override
  public void updateActivityTime(ActivityTimePojo activityTime) throws SQLException {
    activityTimeDao.updateActivityTime(activityTime);
  }

  @Override
  public List<ActivityTimePojo> getActivityTimeList(ActivityTimePojo activityTimePojo)
      throws SQLException {
    return activityTimeDao.getActivityTimeList(activityTimePojo);
  }

}
