/*
 * 文 件 名: UserTaskServiceImpl.java 创 建 人: admin 创建时间: 2016-06-03
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserTaskDao;
import com.tzmb2c.web.pojo.UserTaskPojo;
import com.tzmb2c.web.service.UserTaskService;

/**
 * UserTask Service层
 */
public class UserTaskServiceImpl implements UserTaskService {

  @Autowired
  private UserTaskDao userTaskdao;

  @Override
  public int add(UserTaskPojo userTask) {
    if (null == userTask) {
      return 0;
    }
    int rows = userTaskdao.add(userTask);
    return rows;
  }

  @Override
  public int update(UserTaskPojo userTask) {
    if (null == userTask) {
      return 0;
    }
    int rows = userTaskdao.update(userTask);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userTaskdao.delete(id);
    return rows;
  }

  @Override
  public UserTaskPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    UserTaskPojo userTask = userTaskdao.getById(id);
    return userTask;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = userTaskdao.countBy(params);
    return rows;
  }

  @Override
  public List<UserTaskPojo> listPage(Map<String, Object> params) {
    List<UserTaskPojo> lists = userTaskdao.listPage(params);
    return lists;
  }

  @Override
  public List<UserTaskPojo> checkBrowsePostTask(Map<String, Object> params) {
    return userTaskdao.checkBrowsePostTask(params);
  }

  @Override
  public int isALLTaskDone(Map<String, Object> params) {
    return userTaskdao.isALLTaskDone(params);
  }
}
