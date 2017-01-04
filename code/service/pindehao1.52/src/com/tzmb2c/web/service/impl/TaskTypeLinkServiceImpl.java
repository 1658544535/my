package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.TaskTypeLinkDao;
import com.tzmb2c.web.pojo.TaskTypeLinkPojo;
import com.tzmb2c.web.service.TaskTypeLinkService;

public class TaskTypeLinkServiceImpl implements TaskTypeLinkService {
  @Autowired
  private TaskTypeLinkDao taskTypeLinkDao;


  @Override
  public List<TaskTypeLinkPojo> findTaskTypeLinkByTaskType(Long ageId) throws SQLException {
    return taskTypeLinkDao.findTaskTypeLinkByTaskType(ageId);
  }



}
