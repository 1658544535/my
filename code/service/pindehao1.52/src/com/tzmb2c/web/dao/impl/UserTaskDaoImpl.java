/*
 * 文 件 名: UserTaskDaoImpl.java 创 建 人: admin 创建时间: 2016-06-03
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserTaskDao;
import com.tzmb2c.web.mapper.UserTaskMapper;
import com.tzmb2c.web.pojo.UserTaskPojo;

/**
 * UserTask Dao层
 */
public class UserTaskDaoImpl implements UserTaskDao {

  @Autowired
  private UserTaskMapper userTaskMapper;

  @Override
  public int add(UserTaskPojo userTask) {
    if (null == userTask) {
      return 0;
    }
    int rows = userTaskMapper.insert(userTask);
    return rows;
  }

  @Override
  public int update(UserTaskPojo userTask) {
    if (null == userTask) {
      return 0;
    }
    int rows = userTaskMapper.update(userTask);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userTaskMapper.deleteById(id);
    return rows;
  }

  @Override
  public UserTaskPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    UserTaskPojo userTask = userTaskMapper.getById(id);
    return userTask;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = userTaskMapper.countBy(params);
    return rows;
  }

  @Override
  public List<UserTaskPojo> listPage(Map<String, Object> params) {
    List<UserTaskPojo> lists = userTaskMapper.listPage(params);
    return lists;
  }

  @Override
  public List<UserTaskPojo> checkBrowsePostTask(Map<String, Object> params) {
    return userTaskMapper.checkBrowsePostTask(params);
  }

  @Override
  public int isALLTaskDone(Map<String, Object> params) {
    return userTaskMapper.isALLTaskDone(params);
  }
}
