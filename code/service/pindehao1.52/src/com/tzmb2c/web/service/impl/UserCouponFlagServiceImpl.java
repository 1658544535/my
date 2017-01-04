/*
 * 文 件 名: UserCouponFlagServiceImpl.java 创 建 人: admin 创建时间: 2016-12-05
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserCouponFlagDao;
import com.tzmb2c.web.pojo.UserCouponFlagPojo;
import com.tzmb2c.web.service.UserCouponFlagService;

/**
 * UserCouponFlag Service层
 */
public class UserCouponFlagServiceImpl implements UserCouponFlagService {

  @Autowired
  private UserCouponFlagDao userCouponFlagdao;

  @Override
  public int add(UserCouponFlagPojo userCouponFlag) {
    if (null == userCouponFlag) {
      return 0;
    }
    int rows = userCouponFlagdao.add(userCouponFlag);
    return rows;
  }

  @Override
  public int insert(UserCouponFlagPojo userCouponFlag) {
    if (null == userCouponFlag) {
      return 0;
    }
    int rows = userCouponFlagdao.insert(userCouponFlag);
    return rows;
  }

  @Override
  public int update(UserCouponFlagPojo userCouponFlag) {
    if (null == userCouponFlag) {
      return 0;
    }
    int rows = userCouponFlagdao.update(userCouponFlag);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userCouponFlagdao.delete(id);
    return rows;
  }

  @Override
  public UserCouponFlagPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    UserCouponFlagPojo userCouponFlag = userCouponFlagdao.getById(id);
    return userCouponFlag;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = userCouponFlagdao.countBy(params);
    return rows;
  }

  @Override
  public List<UserCouponFlagPojo> listPage(Map<String, Object> params) {
    List<UserCouponFlagPojo> lists = userCouponFlagdao.listPage(params);
    return lists;
  }
}
