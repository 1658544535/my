/*
 * 文 件 名: GrouponActivityServiceImpl.java 创 建 人: admin 创建时间: 2016-09-21
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GrouponActivityDao;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.service.GrouponActivityService;

/**
 * GrouponActivity Service层
 */
public class GrouponActivityServiceImpl implements GrouponActivityService {

  @Autowired
  private GrouponActivityDao grouponActivitydao;

  @Override
  public int add(GrouponActivityPojo grouponActivity) throws SQLException {
    if (null == grouponActivity) {
      return 0;
    }
    int rows = grouponActivitydao.add(grouponActivity);
    return rows;
  }

  @Override
  public int update(GrouponActivityPojo grouponActivity) throws SQLException {
    if (null == grouponActivity) {
      return 0;
    }
    int rows = grouponActivitydao.update(grouponActivity);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = grouponActivitydao.delete(id);
    return rows;
  }

  @Override
  public GrouponActivityPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GrouponActivityPojo grouponActivity = grouponActivitydao.getById(id);
    return grouponActivity;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    // List<GrouponActivityPojo> lists = grouponActivitydao.listPage(null);
    // if (lists.size() > 0) {
    // for (GrouponActivityPojo g : lists) {
    // Date date = new java.util.Date();
    // if (date.getTime() >= g.getBeginTime().getTime()
    // && date.getTime() < g.getEndTime().getTime() && g.getActivityStatus() != 1) {
    // GrouponActivityPojo grouponActivityPojo = new GrouponActivityPojo();
    // grouponActivityPojo.setActivityStatus(1);
    // grouponActivityPojo.setId(g.getId());
    // grouponActivitydao.update(grouponActivityPojo);
    // } else if (date.getTime() > g.getEndTime().getTime() && g.getActivityStatus() != 2) {
    // GrouponActivityPojo grouponActivityPojo = new GrouponActivityPojo();
    // grouponActivityPojo.setActivityStatus(2);
    // grouponActivityPojo.setId(g.getId());
    // grouponActivitydao.update(grouponActivityPojo);
    // }
    // }
    // }

    // GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
    // Date date = new java.util.Date();
    // grouponActivity.setCurrentTime(StringUtil.dateString(date));
    // grouponActivity.setActivityStatus(1);
    // grouponActivitydao.update(grouponActivity);
    // grouponActivity.setCurrentTime2(StringUtil.dateString(date));
    // grouponActivity.setActivityStatus(2);
    // grouponActivitydao.update(grouponActivity);

    Integer rows = grouponActivitydao.countBy(params);
    return rows;
  }

  @Override
  public List<GrouponActivityPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GrouponActivityPojo> lists = grouponActivitydao.listPage(params);
    return lists;
  }

  @Override
  public List<GrouponActivityPojo> listPage2(Map<String, Object> params) throws SQLException {
    List<GrouponActivityPojo> lists = grouponActivitydao.listPage(params);
    return lists;
  }
}
