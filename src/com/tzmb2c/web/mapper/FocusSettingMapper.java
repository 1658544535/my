/*
 * 文 件 名: FocusSettingMapper.java 创 建 人: admin 创建时间: 2016-09-21
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.FocusSettingPojo;

public interface FocusSettingMapper {

  FocusSettingPojo getById(Long id);

  int countBy(Map<String, Object> params);

  List<FocusSettingPojo> listPage(Map<String, Object> params);

  int insert(FocusSettingPojo focusSetting);

  int update(FocusSettingPojo focusSetting);

  int deleteById(Long id);
}
