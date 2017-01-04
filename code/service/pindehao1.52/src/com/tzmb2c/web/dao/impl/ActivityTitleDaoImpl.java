package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityTitleDao;
import com.tzmb2c.web.mapper.ActivityTitleMapper;
import com.tzmb2c.web.pojo.ActivityTitlePojo;

public class ActivityTitleDaoImpl implements ActivityTitleDao {
  @Autowired
  private ActivityTitleMapper activityTitleMapper;

  @Override
  public List<ActivityTitlePojo> findActivityTitleList(Map<String, Object> map) throws SQLException {
    return activityTitleMapper.findActivityTitleList(map);
  }

  @Override
  public int findActivityTitleCount(Map<String, Object> map) throws SQLException {
    return activityTitleMapper.findActivityTitleCount(map);
  }

  @Override
  public ActivityTitlePojo findActivityTitleById(Long id) throws SQLException {
    return activityTitleMapper.findActivityTitleById(id);
  }

  @Override
  public ActivityTitlePojo getActivityTitleById(Map<String, Object> map) throws SQLException {
    return activityTitleMapper.getActivityTitleById(map);
  }

  @Override
  public void insertActivityTitle(ActivityTitlePojo activityTitle) throws SQLException {
    activityTitleMapper.insertActivityTitle(activityTitle);
  }

  @Override
  public void delActivityTitle(Long id) throws SQLException {
    activityTitleMapper.delActivityTitle(id);
  }

  @Override
  public void updateActivityTitle(ActivityTitlePojo activityTitle) throws SQLException {
    activityTitleMapper.updateActivityTitle(activityTitle);
  }

  @Override
  public void checkActivityTitle(Long id) throws SQLException {
    activityTitleMapper.checkActivityTitle(id);
  }

  @Override
  public void uncheckActivityTitle(Long id) throws SQLException {
    activityTitleMapper.uncheckActivityTitle(id);
  }

}
