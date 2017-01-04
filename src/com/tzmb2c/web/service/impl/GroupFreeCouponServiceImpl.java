/*
 * 文 件 名:  GroupFreeCouponServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-23
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GroupFreeCouponDao;
import com.tzmb2c.web.pojo.GroupFreeCouponPojo;
import com.tzmb2c.web.service.GroupFreeCouponService;

/**
 * GroupFreeCoupon Service层
 */
public class GroupFreeCouponServiceImpl implements GroupFreeCouponService {

  @Autowired
  private GroupFreeCouponDao groupFreeCoupondao;

  @Override
  public int add(GroupFreeCouponPojo groupFreeCoupon) throws SQLException {
    if (null == groupFreeCoupon) {
      return 0;
    }
    int rows = groupFreeCoupondao.add(groupFreeCoupon);
    return rows;
  }

  @Override
  public int update(GroupFreeCouponPojo groupFreeCoupon) throws SQLException {
    if (null == groupFreeCoupon) {
      return 0;
    }
    int rows = groupFreeCoupondao.update(groupFreeCoupon);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = groupFreeCoupondao.delete(id);
    return rows;
  }

  @Override
  public GroupFreeCouponPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GroupFreeCouponPojo groupFreeCoupon = groupFreeCoupondao.getById(id);
    return groupFreeCoupon;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = groupFreeCoupondao.countBy(params);
    return rows;
  }

  @Override
  public List<GroupFreeCouponPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GroupFreeCouponPojo> lists = groupFreeCoupondao.listPage(params);
    return lists;
  }
}
