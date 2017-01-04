/*
 * 文 件 名:  GroupFreeCouponMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-23
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GroupFreeCouponPojo;

public interface GroupFreeCouponMapper {

  GroupFreeCouponPojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  List<GroupFreeCouponPojo> listPage(Map<String, Object> params) throws SQLException;

  int insert(GroupFreeCouponPojo groupFreeCoupon) throws SQLException;

  int update(GroupFreeCouponPojo groupFreeCoupon) throws SQLException;

  int deleteById(Long id) throws SQLException;
}
