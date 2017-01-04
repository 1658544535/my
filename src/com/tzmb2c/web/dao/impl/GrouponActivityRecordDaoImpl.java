/*
 * 文 件 名:  GrouponActivityRecordDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-22
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponActivityRecordDao;
import com.tzmb2c.web.mapper.GrouponActivityRecordMapper;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;

/**
 * GrouponActivityRecord Dao层
 */
public class GrouponActivityRecordDaoImpl implements GrouponActivityRecordDao {

  @Autowired
  private GrouponActivityRecordMapper grouponActivityRecordMapper;

  @Override
  public int add(GrouponActivityRecordPojo grouponActivityRecord) throws SQLException {
    if (null == grouponActivityRecord) {
      return 0;
    }
    int rows = grouponActivityRecordMapper.insert(grouponActivityRecord);
    return rows;
  }

  @Override
  public int update(GrouponActivityRecordPojo grouponActivityRecord) throws SQLException {
    if (null == grouponActivityRecord) {
      return 0;
    }
    int rows = grouponActivityRecordMapper.update(grouponActivityRecord);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = grouponActivityRecordMapper.deleteById(id);
    return rows;
  }

  @Override
  public GrouponActivityRecordPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GrouponActivityRecordPojo grouponActivityRecord = grouponActivityRecordMapper.getById(id);
    return grouponActivityRecord;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = grouponActivityRecordMapper.countBy(params);
    return rows;
  }

  @Override
  public List<GrouponActivityRecordPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GrouponActivityRecordPojo> lists = grouponActivityRecordMapper.listPage(params);
    return lists;
  }
}
