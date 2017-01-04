/*
 * 文 件 名: UserCouponFlagMapper.java 创 建 人: admin 创建时间: 2016-12-05
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCouponFlagPojo;

public interface UserCouponFlagMapper {

  UserCouponFlagPojo getById(Long id);

  int countBy(Map<String, Object> params);

  List<UserCouponFlagPojo> listPage(Map<String, Object> params);

  int insert(UserCouponFlagPojo userCouponFlag);

  int insert2(UserCouponFlagPojo userCouponFlag);

  int update(UserCouponFlagPojo userCouponFlag);

  int deleteById(Long id);
}
