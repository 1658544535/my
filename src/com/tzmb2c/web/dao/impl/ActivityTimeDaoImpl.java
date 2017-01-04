package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityTimeDao;
import com.tzmb2c.web.mapper.ActivityTimeMapper;
import com.tzmb2c.web.pojo.ActivityTimePojo;

public class ActivityTimeDaoImpl implements ActivityTimeDao {
  @Autowired
  private ActivityTimeMapper activityTimeMapper;

  @Override
  public List<ActivityTimePojo> findActivityTimeList(Map<String, Object> map) throws SQLException {
    return activityTimeMapper.findActivityTimeList(map);
  }

  @Override
  public int findActivityTimeCount(Map<String, Object> map) throws SQLException {
    return activityTimeMapper.findActivityTimeCount(map);
  }

  @Override
  public ActivityTimePojo findActivityTimeById(Long id) throws SQLException {
    return activityTimeMapper.findActivityTimeById(id);
  }

  @Override
  public Long insertActivityTime(ActivityTimePojo activityTime) throws SQLException {
    return activityTimeMapper.insertActivityTime(activityTime);
  }

  @Override
  public void delActivityTime(Long id) throws SQLException {
    activityTimeMapper.delActivityTime(id);
  }

  @Override
  public void updateActivityTime(ActivityTimePojo activityTime) throws SQLException {
    activityTimeMapper.updateActivityTime(activityTime);
  }

  @Override
  public List<ActivityTimePojo> getActivityTimeList(ActivityTimePojo activityTimePojo)
      throws SQLException {
    return activityTimeMapper.getActivityTimeList(activityTimePojo);
  }

}
