/*
 * 文 件 名:  GroupFreeCouponSettingServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-23
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GroupFreeCouponSettingDao;
import com.tzmb2c.web.pojo.GroupFreeCouponSettingPojo;
import com.tzmb2c.web.service.GroupFreeCouponSettingService;

/**
 * GroupFreeCouponSetting Service层
 */
public class GroupFreeCouponSettingServiceImpl implements GroupFreeCouponSettingService {

  @Autowired
  private GroupFreeCouponSettingDao groupFreeCouponSettingdao;

  @Override
  public int add(GroupFreeCouponSettingPojo groupFreeCouponSetting) throws SQLException {
    if (null == groupFreeCouponSetting) {
      return 0;
    }
    int rows = groupFreeCouponSettingdao.add(groupFreeCouponSetting);
    return rows;
  }

  @Override
  public int update(GroupFreeCouponSettingPojo groupFreeCouponSetting) throws SQLException {
    if (null == groupFreeCouponSetting) {
      return 0;
    }
    int rows = groupFreeCouponSettingdao.update(groupFreeCouponSetting);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = groupFreeCouponSettingdao.delete(id);
    return rows;
  }

  @Override
  public GroupFreeCouponSettingPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GroupFreeCouponSettingPojo groupFreeCouponSetting = groupFreeCouponSettingdao.getById(id);
    return groupFreeCouponSetting;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = groupFreeCouponSettingdao.countBy(params);
    return rows;
  }

  @Override
  public List<GroupFreeCouponSettingPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GroupFreeCouponSettingPojo> lists = groupFreeCouponSettingdao.listPage(params);
    return lists;
  }
}
