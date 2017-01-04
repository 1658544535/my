package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.TaskSettingPojo;

public interface TaskSettingMapper {
  List<TaskSettingPojo> findTaskSettingList1(Map<String, Object> map) throws SQLException;

  int findTaskSettingCount1(Map<String, Object> map) throws SQLException;

  List<TaskSettingPojo> findTaskSettingList2(Map<String, Object> map) throws SQLException;

  int findTaskSettingCount2(Map<String, Object> map) throws SQLException;

  void insertTaskSetting(TaskSettingPojo taskSettingPojo) throws SQLException;

  void delTaskSetting(Long id) throws SQLException;

  int findTaskSettingCountDate(Map<String, Object> map) throws SQLException;

  List<TaskSettingPojo> findTaskSettingToday(Map<String, Object> map) throws SQLException;

  TaskSettingPojo getTaskSettingById(Long id) throws SQLException;
}
