/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCouponFlagPojo;

/**
 * @version 1.0
 * @author
 */
public interface UserCouponFlagDao {

  public int add(UserCouponFlagPojo userCouponFlag);

  public int insert(UserCouponFlagPojo userCouponFlag);

  public int update(UserCouponFlagPojo userCouponFlag);

  public int delete(Long id);

  public UserCouponFlagPojo getById(Long id);

  public Integer countBy(Map<String, Object> params);

  public List<UserCouponFlagPojo> listPage(Map<String, Object> params);

}
