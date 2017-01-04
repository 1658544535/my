/*
 * 文 件 名:  GroupFreeCouponDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-23
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GroupFreeCouponDao;
import com.tzmb2c.web.mapper.GroupFreeCouponMapper;
import com.tzmb2c.web.pojo.GroupFreeCouponPojo;

/**
 * GroupFreeCoupon Dao层
 */
public class GroupFreeCouponDaoImpl implements GroupFreeCouponDao {

  @Autowired
  private GroupFreeCouponMapper groupFreeCouponMapper;

  @Override
  public int add(GroupFreeCouponPojo groupFreeCoupon) throws SQLException {
    if (null == groupFreeCoupon) {
      return 0;
    }
    int rows = groupFreeCouponMapper.insert(groupFreeCoupon);
    return rows;
  }

  @Override
  public int update(GroupFreeCouponPojo groupFreeCoupon) throws SQLException {
    if (null == groupFreeCoupon) {
      return 0;
    }
    int rows = groupFreeCouponMapper.update(groupFreeCoupon);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = groupFreeCouponMapper.deleteById(id);
    return rows;
  }

  @Override
  public GroupFreeCouponPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GroupFreeCouponPojo groupFreeCoupon = groupFreeCouponMapper.getById(id);
    return groupFreeCoupon;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = groupFreeCouponMapper.countBy(params);
    return rows;
  }

  @Override
  public List<GroupFreeCouponPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GroupFreeCouponPojo> lists = groupFreeCouponMapper.listPage(params);
    return lists;
  }
}
