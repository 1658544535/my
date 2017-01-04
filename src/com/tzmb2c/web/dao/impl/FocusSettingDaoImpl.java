/*
 * 文 件 名: FocusSettingDaoImpl.java 创 建 人: admin 创建时间: 2016-09-21
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.FocusSettingDao;
import com.tzmb2c.web.mapper.FocusSettingMapper;
import com.tzmb2c.web.pojo.FocusSettingPojo;

/**
 * FocusSetting Dao层
 */
public class FocusSettingDaoImpl implements FocusSettingDao {

  @Autowired
  private FocusSettingMapper focusSettingMapper;

  @Override
  public int add(FocusSettingPojo focusSetting) {
    if (null == focusSetting) {
      return 0;
    }
    int rows = focusSettingMapper.insert(focusSetting);
    return rows;
  }

  @Override
  public int update(FocusSettingPojo focusSetting) {
    if (null == focusSetting) {
      return 0;
    }
    int rows = focusSettingMapper.update(focusSetting);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = focusSettingMapper.deleteById(id);
    return rows;
  }

  @Override
  public FocusSettingPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    FocusSettingPojo focusSetting = focusSettingMapper.getById(id);
    return focusSetting;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = focusSettingMapper.countBy(params);
    return rows;
  }

  @Override
  public List<FocusSettingPojo> listPage(Map<String, Object> params) {
    List<FocusSettingPojo> lists = focusSettingMapper.listPage(params);
    return lists;
  }
}
