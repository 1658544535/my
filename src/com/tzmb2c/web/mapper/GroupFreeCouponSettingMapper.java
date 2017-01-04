/*
 * 文 件 名:  GroupFreeCouponSettingMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-23
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.GroupFreeCouponSettingPojo;

public interface GroupFreeCouponSettingMapper {

  GroupFreeCouponSettingPojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  List<GroupFreeCouponSettingPojo> listPage(Map<String, Object> params) throws SQLException;

  int insert(GroupFreeCouponSettingPojo groupFreeCouponSetting) throws SQLException;

  int update(GroupFreeCouponSettingPojo groupFreeCouponSetting) throws SQLException;

  int deleteById(Long id) throws SQLException;
}
