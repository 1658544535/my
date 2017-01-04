package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.TaskSettingDao;
import com.tzmb2c.web.pojo.TaskSettingPojo;
import com.tzmb2c.web.service.TaskSettingService;

public class TaskSettingServiceImpl implements TaskSettingService {
  @Autowired
  private TaskSettingDao taskSettingDao;

  @Override
  public List<TaskSettingPojo> findTaskSettingList1(Map<String, Object> map) throws SQLException {
    return taskSettingDao.findTaskSettingList1(map);
  }

  @Override
  public int findTaskSettingCount1(Map<String, Object> map) throws SQLException {
    return taskSettingDao.findTaskSettingCount1(map);
  }

  @Override
  public List<TaskSettingPojo> findTaskSettingList2(Map<String, Object> map) throws SQLException {
    return taskSettingDao.findTaskSettingList2(map);
  }

  @Override
  public int findTaskSettingCount2(Map<String, Object> map) throws SQLException {
    return taskSettingDao.findTaskSettingCount2(map);
  }

  @Override
  public void insertTaskSetting(TaskSettingPojo taskSettingPojo) throws SQLException {
    taskSettingDao.insertTaskSetting(taskSettingPojo);
  }

  @Override
  public void delTaskSetting(Long id) throws SQLException {
    taskSettingDao.delTaskSetting(id);
  }

  @Override
  public List<TaskSettingPojo> findTaskSettingToday(Map<String, Object> map) throws SQLException {
    return taskSettingDao.findTaskSettingToday(map);
  }

  @Override
  public TaskSettingPojo getTaskSettingById(Long id) throws SQLException {
    return taskSettingDao.getTaskSettingById(id);
  }

}
