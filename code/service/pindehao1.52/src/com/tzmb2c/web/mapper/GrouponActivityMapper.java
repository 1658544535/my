/*
 * 文 件 名: GrouponActivityMapper.java 创 建 人: admin 创建时间: 2016-09-21
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GrouponActivityPojo;

public interface GrouponActivityMapper {

  GrouponActivityPojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  List<GrouponActivityPojo> listPage(Map<String, Object> params) throws SQLException;

  int insert(GrouponActivityPojo grouponActivity) throws SQLException;

  int update(GrouponActivityPojo grouponActivity) throws SQLException;

  int deleteById(Long id) throws SQLException;

  List<GrouponActivityPojo> listPage2(Map<String, Object> params) throws SQLException;
}
