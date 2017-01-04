/*
 * 文 件 名:  GrouponRecommendDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-26
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponRecommendDao;
import com.tzmb2c.web.mapper.GrouponRecommendMapper;
import com.tzmb2c.web.pojo.GrouponRecommendPojo;

/**
 * GrouponRecommend Dao层
 */
public class GrouponRecommendDaoImpl implements GrouponRecommendDao {

  @Autowired
  private GrouponRecommendMapper grouponRecommendMapper;

  @Override
  public int add(GrouponRecommendPojo grouponRecommend) throws SQLException {
    if (null == grouponRecommend) {
      return 0;
    }
    int rows = grouponRecommendMapper.insert(grouponRecommend);
    return rows;
  }

  @Override
  public int update(GrouponRecommendPojo grouponRecommend) throws SQLException {
    if (null == grouponRecommend) {
      return 0;
    }
    int rows = grouponRecommendMapper.update(grouponRecommend);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = grouponRecommendMapper.deleteById(id);
    return rows;
  }

  @Override
  public GrouponRecommendPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GrouponRecommendPojo grouponRecommend = grouponRecommendMapper.getById(id);
    return grouponRecommend;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = grouponRecommendMapper.countBy(params);
    return rows;
  }

  @Override
  public List<GrouponRecommendPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GrouponRecommendPojo> lists = grouponRecommendMapper.listPage(params);
    return lists;
  }
}
