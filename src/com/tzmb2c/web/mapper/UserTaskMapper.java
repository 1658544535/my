/*
 * 文 件 名: UserTaskMapper.java 创 建 人: admin 创建时间: 2016-06-03
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserTaskPojo;

public interface UserTaskMapper {

  UserTaskPojo getById(Long id);

  int countBy(Map<String, Object> params);

  List<UserTaskPojo> listPage(Map<String, Object> params);

  int insert(UserTaskPojo userTask);

  int update(UserTaskPojo userTask);

  int deleteById(Long id);

  int isALLTaskDone(Map<String, Object> params);

  List<UserTaskPojo> checkBrowsePostTask(Map<String, Object> params);
}
