/*
 * 文 件 名:  GrouponActivityRecordServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-22
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponActivityRecordDao;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;

/**
 * GrouponActivityRecord Service层
 */
public class GrouponActivityRecordServiceImpl implements GrouponActivityRecordService {

  @Autowired
  private GrouponActivityRecordDao grouponActivityRecorddao;

  @Override
  public int add(GrouponActivityRecordPojo grouponActivityRecord) throws SQLException {
    if (null == grouponActivityRecord) {
      return 0;
    }
    int rows = grouponActivityRecorddao.add(grouponActivityRecord);
    return rows;
  }

  @Override
  public int update(GrouponActivityRecordPojo grouponActivityRecord) throws SQLException {
    if (null == grouponActivityRecord) {
      return 0;
    }
    int rows = grouponActivityRecorddao.update(grouponActivityRecord);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = grouponActivityRecorddao.delete(id);
    return rows;
  }

  @Override
  public GrouponActivityRecordPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GrouponActivityRecordPojo grouponActivityRecord = grouponActivityRecorddao.getById(id);
    return grouponActivityRecord;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = grouponActivityRecorddao.countBy(params);
    return rows;
  }

  @Override
  public List<GrouponActivityRecordPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GrouponActivityRecordPojo> lists = grouponActivityRecorddao.listPage(params);
    return lists;
  }
}
