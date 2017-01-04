/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.FocusSettingPojo;

/**
 * @version 1.0
 * @author
 */
public interface FocusSettingService {

  public int add(FocusSettingPojo focusSetting);

  public int update(FocusSettingPojo focusSetting);

  public int delete(Long id);

  public FocusSettingPojo getById(Long id);

  public Integer countBy(Map<String, Object> params);

  public List<FocusSettingPojo> listPage(Map<String, Object> params);

}
