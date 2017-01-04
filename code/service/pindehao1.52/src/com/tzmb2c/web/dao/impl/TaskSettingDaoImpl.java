package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.TaskSettingDao;
import com.tzmb2c.web.mapper.TaskSettingMapper;
import com.tzmb2c.web.pojo.TaskSettingPojo;

public class TaskSettingDaoImpl implements TaskSettingDao {
  @Autowired
  private TaskSettingMapper taskSettingMapper;

  @Override
  public List<TaskSettingPojo> findTaskSettingList1(Map<String, Object> map) throws SQLException {
    return taskSettingMapper.findTaskSettingList1(map);
  }

  @Override
  public int findTaskSettingCount1(Map<String, Object> map) throws SQLException {
    return taskSettingMapper.findTaskSettingCount1(map);
  }

  @Override
  public List<TaskSettingPojo> findTaskSettingList2(Map<String, Object> map) throws SQLException {
    return taskSettingMapper.findTaskSettingList2(map);
  }

  @Override
  public int findTaskSettingCount2(Map<String, Object> map) throws SQLException {
    return taskSettingMapper.findTaskSettingCount2(map);
  }

  @Override
  public void insertTaskSetting(TaskSettingPojo taskSettingPojo) throws SQLException {
    taskSettingMapper.insertTaskSetting(taskSettingPojo);
  }

  @Override
  public void delTaskSetting(Long id) throws SQLException {
    taskSettingMapper.delTaskSetting(id);
  }

  @Override
  public List<TaskSettingPojo> findTaskSettingToday(Map<String, Object> map) throws SQLException {
    return taskSettingMapper.findTaskSettingToday(map);
  }

  @Override
  public TaskSettingPojo getTaskSettingById(Long id) throws SQLException {
    return taskSettingMapper.getTaskSettingById(id);
  }
}
