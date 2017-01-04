package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.TaskTypeLinkDao;
import com.tzmb2c.web.mapper.TaskTypeLinkMapper;
import com.tzmb2c.web.pojo.TaskTypeLinkPojo;

public class TaskTypeLinkDaoImpl implements TaskTypeLinkDao {
  @Autowired
  private TaskTypeLinkMapper TaskTypeLinkMapper;



  @Override
  public List<TaskTypeLinkPojo> findTaskTypeLinkByTaskType(Long ageId) throws SQLException {
    return TaskTypeLinkMapper.findTaskTypeLinkByTaskType(ageId);
  }



}
