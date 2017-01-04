/*
 * 文 件 名: UserCalendarServiceImpl.java 创 建 人: admin 创建时间: 2016-06-03
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserCalendarDao;
import com.tzmb2c.web.pojo.UserCalendarPojo;
import com.tzmb2c.web.service.UserCalendarService;

/**
 * UserCalendar Service层
 */
public class UserCalendarServiceImpl implements UserCalendarService {

  @Autowired
  private UserCalendarDao userCalendardao;

  @Override
  public int add(UserCalendarPojo userCalendar) {
    if (null == userCalendar) {
      return 0;
    }
    int rows = userCalendardao.add(userCalendar);
    return rows;
  }

  @Override
  public int update(UserCalendarPojo userCalendar) {
    if (null == userCalendar) {
      return 0;
    }
    int rows = userCalendardao.update(userCalendar);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userCalendardao.delete(id);
    return rows;
  }

  @Override
  public UserCalendarPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    UserCalendarPojo userCalendar = userCalendardao.getById(id);
    return userCalendar;
  }

  @Override
  public List<UserCalendarPojo> getByUid(Long uid) {
    if (null == uid) {
      return null;
    }
    List<UserCalendarPojo> userCalendars = userCalendardao.getByUid(uid);
    return userCalendars;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = userCalendardao.countBy(params);
    return rows;
  }

  @Override
  public List<UserCalendarPojo> listPage(Map<String, Object> params) {
    List<UserCalendarPojo> lists = userCalendardao.listPage(params);
    return lists;
  }
}
