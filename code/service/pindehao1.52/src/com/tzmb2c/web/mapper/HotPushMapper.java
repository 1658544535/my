/*
 * 文 件 名:  HotPushMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-08-27
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HotPushPojo;

public interface HotPushMapper {

  HotPushPojo getById(Long id);

  int countBy(Map<String, Object> params);

  List<HotPushPojo> listPage(Map<String, Object> params);

  List<HotPushPojo> topicListPage(Map<String, Object> params);

  int insert(HotPushPojo hotPush);

  int update(HotPushPojo hotPush);

  int deleteById(Long id);

  public HotPushPojo findByParams(Map<String, Object> params);
}
