/*
 * 文 件 名:  GroupFreeCouponSettingDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-23
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.GroupFreeCouponSettingDao;
import com.tzmb2c.web.mapper.GroupFreeCouponSettingMapper;
import com.tzmb2c.web.pojo.GroupFreeCouponSettingPojo;

/**
 * GroupFreeCouponSetting Dao层
 */
public class GroupFreeCouponSettingDaoImpl implements GroupFreeCouponSettingDao {

  @Autowired
  private GroupFreeCouponSettingMapper groupFreeCouponSettingMapper;

  @Override
  public int add(GroupFreeCouponSettingPojo groupFreeCouponSetting) throws SQLException {
    if (null == groupFreeCouponSetting) {
      return 0;
    }
    int rows = groupFreeCouponSettingMapper.insert(groupFreeCouponSetting);
    return rows;
  }

  @Override
  public int update(GroupFreeCouponSettingPojo groupFreeCouponSetting) throws SQLException {
    if (null == groupFreeCouponSetting) {
      return 0;
    }
    int rows = groupFreeCouponSettingMapper.update(groupFreeCouponSetting);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = groupFreeCouponSettingMapper.deleteById(id);
    return rows;
  }

  @Override
  public GroupFreeCouponSettingPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    GroupFreeCouponSettingPojo groupFreeCouponSetting = groupFreeCouponSettingMapper.getById(id);
    return groupFreeCouponSetting;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = groupFreeCouponSettingMapper.countBy(params);
    return rows;
  }

  @Override
  public List<GroupFreeCouponSettingPojo> listPage(Map<String, Object> params) throws SQLException {
    List<GroupFreeCouponSettingPojo> lists = groupFreeCouponSettingMapper.listPage(params);
    return lists;
  }
}
