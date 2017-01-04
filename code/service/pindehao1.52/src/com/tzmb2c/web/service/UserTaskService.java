/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserTaskPojo;

/**
 * @version 1.0
 * @author
 */
public interface UserTaskService {

  public int add(UserTaskPojo userTask);

  public int update(UserTaskPojo userTask);

  public int delete(Long id);

  public UserTaskPojo getById(Long id);

  public Integer countBy(Map<String, Object> params);

  public List<UserTaskPojo> listPage(Map<String, Object> params);

  /**
   * 判断用户是否有领取浏览帖子的任务
   * 
   * @param params
   * @return
   */
  List<UserTaskPojo> checkBrowsePostTask(Map<String, Object> params);

  public int isALLTaskDone(Map<String, Object> params);
}
