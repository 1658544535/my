/*
 * 文 件 名: UserCalendarMapper.java 创 建 人: admin 创建时间: 2016-06-03
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCalendarPojo;

public interface UserCalendarMapper {

  UserCalendarPojo getById(Long id);

  List<UserCalendarPojo> getByUid(Long uid);

  int countBy(Map<String, Object> params);

  List<UserCalendarPojo> listPage(Map<String, Object> params);

  int insert(UserCalendarPojo userCalendar);

  int update(UserCalendarPojo userCalendar);

  int deleteById(Long id);
}
