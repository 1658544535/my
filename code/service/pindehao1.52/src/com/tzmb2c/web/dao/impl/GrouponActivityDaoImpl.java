/*
 * 文 件 名: GrouponActivityDaoImpl.java 创 建 人: admin 创建时间: 2016-09-21
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponActivityDao;
import com.tzmb2c.web.mapper.GrouponActivityMapper;
import com.tzmb2c.web.pojo.GrouponActivityPojo;

/**
 * GrouponActivity Dao层
 */
public class GrouponActivityDaoImpl implements GrouponActivityDao {

  @Autowired
  private GrouponActivityMapper grouponActivityMapper;

  @Override
  public int add(GrouponActivityPojo grouponActivity) throws SQLException {
    if (null == grouponActivity) {
      return 0;
    }
    int rows = grouponActivityMapper.insert(grouponActivity);
    return rows;
  }

  @Override
  public int update(GrouponActivityPojo grouponActivity) throws SQLException {
    if (null == grouponActivity) {
      return 0;
    }
    int rows = grouponActivityMapper.update(grouponActivity);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = grouponActivityMapper.deleteById(id);
    return rows;
  }

  @Override
  public GrouponActivityPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GrouponActivityPojo grouponActivity = grouponActivityMapper.getById(id);
    return grouponActivity;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = grouponActivityMapper.countBy(params);
    return rows;
  }

  @Override
  public List<GrouponActivityPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GrouponActivityPojo> lists = grouponActivityMapper.listPage(params);
    return lists;
  }

  @Override
  public List<GrouponActivityPojo> listPage2(Map<String, Object> params) throws SQLException {
    List<GrouponActivityPojo> lists = grouponActivityMapper.listPage(params);
    return lists;
  }
}
