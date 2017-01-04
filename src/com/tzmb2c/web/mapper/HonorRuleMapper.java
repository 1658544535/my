/*
 * 文 件 名: HonorRuleMapper.java 创 建 人: admin 创建时间: 2016-06-05
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HonorRulePojo;

public interface HonorRuleMapper {

  HonorRulePojo getById(Long id);

  HonorRulePojo selectHonorBy(Map<String, Object> params);

  int countBy(Map<String, Object> params);

  List<HonorRulePojo> listPage(Map<String, Object> params);

  int insert(HonorRulePojo honorRule);

  int update(HonorRulePojo honorRule);

  int deleteById(Long id);
}
