/*
 * 文 件 名: UserCalendarDaoImpl.java 创 建 人: admin 创建时间: 2016-06-03
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserCalendarDao;
import com.tzmb2c.web.mapper.UserCalendarMapper;
import com.tzmb2c.web.pojo.UserCalendarPojo;

/**
 * UserCalendar Dao层
 */
public class UserCalendarDaoImpl implements UserCalendarDao {

  @Autowired
  private UserCalendarMapper userCalendarMapper;

  @Override
  public int add(UserCalendarPojo userCalendar) {
    if (null == userCalendar) {
      return 0;
    }
    int rows = userCalendarMapper.insert(userCalendar);
    return rows;
  }

  @Override
  public int update(UserCalendarPojo userCalendar) {
    if (null == userCalendar) {
      return 0;
    }
    int rows = userCalendarMapper.update(userCalendar);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userCalendarMapper.deleteById(id);
    return rows;
  }

  @Override
  public UserCalendarPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    UserCalendarPojo userCalendar = userCalendarMapper.getById(id);
    return userCalendar;
  }

  @Override
  public List<UserCalendarPojo> getByUid(Long uid) {
    if (null == uid) {
      return null;
    }
    List<UserCalendarPojo> userCalendars = userCalendarMapper.getByUid(uid);
    return userCalendars;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = userCalendarMapper.countBy(params);
    return rows;
  }

  @Override
  public List<UserCalendarPojo> listPage(Map<String, Object> params) {
    List<UserCalendarPojo> lists = userCalendarMapper.listPage(params);
    return lists;
  }
}
