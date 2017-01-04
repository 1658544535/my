/*
 * 文 件 名: UserCouponFlagDaoImpl.java 创 建 人: admin 创建时间: 2016-12-05
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserCouponFlagDao;
import com.tzmb2c.web.mapper.UserCouponFlagMapper;
import com.tzmb2c.web.pojo.UserCouponFlagPojo;

/**
 * UserCouponFlag Dao层
 */
public class UserCouponFlagDaoImpl implements UserCouponFlagDao {

  @Autowired
  private UserCouponFlagMapper userCouponFlagMapper;

  @Override
  public int add(UserCouponFlagPojo userCouponFlag) {
    if (null == userCouponFlag) {
      return 0;
    }
    int rows = userCouponFlagMapper.insert(userCouponFlag);
    return rows;
  }

  @Override
  public int insert(UserCouponFlagPojo userCouponFlag) {
    if (null == userCouponFlag) {
      return 0;
    }
    int rows = userCouponFlagMapper.insert2(userCouponFlag);
    return rows;
  }

  @Override
  public int update(UserCouponFlagPojo userCouponFlag) {
    if (null == userCouponFlag) {
      return 0;
    }
    int rows = userCouponFlagMapper.update(userCouponFlag);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userCouponFlagMapper.deleteById(id);
    return rows;
  }

  @Override
  public UserCouponFlagPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    UserCouponFlagPojo userCouponFlag = userCouponFlagMapper.getById(id);
    return userCouponFlag;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = userCouponFlagMapper.countBy(params);
    return rows;
  }

  @Override
  public List<UserCouponFlagPojo> listPage(Map<String, Object> params) {
    List<UserCouponFlagPojo> lists = userCouponFlagMapper.listPage(params);
    return lists;
  }
}
