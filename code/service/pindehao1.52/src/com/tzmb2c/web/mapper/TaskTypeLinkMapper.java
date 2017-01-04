package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.TaskTypeLinkPojo;

/**
 * 任务类型连接Mapper
 * 
 * @author LinJianhong
 * 
 */
public interface TaskTypeLinkMapper {

  /**
   * 任务类型
   * 
   * @param
   * @return
   * @throws SQLException
   */
  List<TaskTypeLinkPojo> findTaskTypeLinkByTaskType(Long ageId) throws SQLException;

}
