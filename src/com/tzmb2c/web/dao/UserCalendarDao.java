/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCalendarPojo;

/**
 * @version 1.0
 * @author
 */
public interface UserCalendarDao {

  public int add(UserCalendarPojo userCalendar);

  public int update(UserCalendarPojo userCalendar);

  public int delete(Long id);

  public UserCalendarPojo getById(Long id);

  public List<UserCalendarPojo> getByUid(Long uid);

  public Integer countBy(Map<String, Object> params);

  public List<UserCalendarPojo> listPage(Map<String, Object> params);

}
