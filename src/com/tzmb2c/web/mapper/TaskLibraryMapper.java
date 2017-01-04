package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.TaskPojo;
/**
 * 任务库
 * @author LinJianhong
 *
 */
public interface TaskLibraryMapper {
  
  /**
   * 查詢任务数目
   * @param map	带参数的pojo
   * @return	有参数时根据参数来查询符合相关条件的任务数目，无参数时查询所有任务的数目
   * @throws SQLException
   */
  int findTaskLibraryCount(Map<String, Object> map) throws SQLException;
  
  /**
   * 查询任务列表
   * @param map	带参数的pojo
   * @return	有参数时根据参数来查询符合相关条件的任务，无参数时查询所有任务
   * @throws SQLException
   */
  List<TaskPojo> findTaskLibraryList(Map<String, Object> map) throws SQLException;

  /**
   * 通过ID查询单条任务
   * @param id	任务ID
   * @return	符合条件的任务记录
   * @throws SQLException
   */
  TaskPojo findTaskLibraryById(Long id) throws SQLException;

  /**
   * 插入单条任务记录
   * @param taskPojo	封装任务信息的pojo
   * @throws SQLException
   */
  void insertTaskLibrary(TaskPojo taskPojo) throws SQLException;

  /**
   * 删除单条任务记录
   * @param id 任务ID
   * @throws SQLException
   */
  void delTaskLibrary(Long id) throws SQLException;

  /**
   * 更新单条任务记录
   * @param taskPojo	封装任务信息pojo
   * @throws SQLException
   */
  void updateTaskLibrary(TaskPojo taskPojo) throws SQLException;


  
}
