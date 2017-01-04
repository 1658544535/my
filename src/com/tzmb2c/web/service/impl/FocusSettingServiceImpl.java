/*
 * 文 件 名: FocusSettingServiceImpl.java 创 建 人: admin 创建时间: 2016-09-21
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.FocusSettingDao;
import com.tzmb2c.web.pojo.FocusSettingPojo;
import com.tzmb2c.web.service.FocusSettingService;

/**
 * FocusSetting Service层
 */
public class FocusSettingServiceImpl implements FocusSettingService {

  @Autowired
  private FocusSettingDao focusSettingdao;

  @Override
  public int add(FocusSettingPojo focusSetting) {
    if (null == focusSetting) {
      return 0;
    }
    int rows = focusSettingdao.add(focusSetting);
    return rows;
  }

  @Override
  public int update(FocusSettingPojo focusSetting) {
    if (null == focusSetting) {
      return 0;
    }
    int rows = focusSettingdao.update(focusSetting);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = focusSettingdao.delete(id);
    return rows;
  }

  @Override
  public FocusSettingPojo getById(Long id) {
    if (null == id) {
      return null;
    }
    FocusSettingPojo focusSetting = focusSettingdao.getById(id);
    return focusSetting;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = focusSettingdao.countBy(params);
    return rows;
  }

  @Override
  public List<FocusSettingPojo> listPage(Map<String, Object> params) {
    List<FocusSettingPojo> lists = focusSettingdao.listPage(params);
    return lists;
  }
}
