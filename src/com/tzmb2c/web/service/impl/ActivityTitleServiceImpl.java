package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityTitleDao;
import com.tzmb2c.web.pojo.ActivityTitlePojo;
import com.tzmb2c.web.service.ActivityTitleService;

public class ActivityTitleServiceImpl implements ActivityTitleService {
  @Autowired
  private ActivityTitleDao activityTitleDao;

  @Override
  public List<ActivityTitlePojo> findActivityTitleList(Map<String, Object> map) throws SQLException {
    return activityTitleDao.findActivityTitleList(map);
  }

  @Override
  public int findActivityTitleCount(Map<String, Object> map) throws SQLException {
    return activityTitleDao.findActivityTitleCount(map);
  }

  @Override
  public ActivityTitlePojo findActivityTitleById(Long id) throws SQLException {
    return activityTitleDao.findActivityTitleById(id);
  }

  @Override
  public ActivityTitlePojo getActivityTitleById(Map<String, Object> map) throws SQLException {
    return activityTitleDao.getActivityTitleById(map);
  }

  @Override
  public void insertActivityTitle(ActivityTitlePojo activityTitle) throws SQLException {
    activityTitleDao.insertActivityTitle(activityTitle);
  }

  @Override
  public void delActivityTitle(Long id) throws SQLException {
    activityTitleDao.delActivityTitle(id);
  }

  @Override
  public void updateActivityTitle(ActivityTitlePojo activityTitle) throws SQLException {
    activityTitleDao.updateActivityTitle(activityTitle);
  }

  @Override
  public void checkActivityTitle(Long id) throws SQLException {
    activityTitleDao.checkActivityTitle(id);
  }

  @Override
  public void uncheckActivityTitle(Long id) throws SQLException {
    activityTitleDao.uncheckActivityTitle(id);
  }


}
