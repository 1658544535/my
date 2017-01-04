/*
 * 文 件 名:  GrouponRecommendServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-26
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponRecommendDao;
import com.tzmb2c.web.pojo.GrouponRecommendPojo;
import com.tzmb2c.web.service.GrouponRecommendService;

/**
 * GrouponRecommend Service层
 */
public class GrouponRecommendServiceImpl implements GrouponRecommendService {

  @Autowired
  private GrouponRecommendDao grouponRecommenddao;

  @Override
  public int add(GrouponRecommendPojo grouponRecommend) throws SQLException {
    if (null == grouponRecommend) {
      return 0;
    }
    int rows = grouponRecommenddao.add(grouponRecommend);
    return rows;
  }

  @Override
  public int update(GrouponRecommendPojo grouponRecommend) throws SQLException {
    if (null == grouponRecommend) {
      return 0;
    }
    int rows = grouponRecommenddao.update(grouponRecommend);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = grouponRecommenddao.delete(id);
    return rows;
  }

  @Override
  public GrouponRecommendPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GrouponRecommendPojo grouponRecommend = grouponRecommenddao.getById(id);
    return grouponRecommend;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = grouponRecommenddao.countBy(params);
    return rows;
  }

  @Override
  public List<GrouponRecommendPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GrouponRecommendPojo> lists = grouponRecommenddao.listPage(params);
    return lists;
  }
}
