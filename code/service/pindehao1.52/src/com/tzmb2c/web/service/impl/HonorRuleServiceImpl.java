/*
 * 文 件 名: HonorRuleServiceImpl.java 创 建 人: admin 创建时间: 2016-06-05
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HonorRuleDao;
import com.tzmb2c.web.pojo.HonorRulePojo;
import com.tzmb2c.web.service.HonorRuleService;

/**
 * HonorRule Service层
 */
public class HonorRuleServiceImpl implements HonorRuleService {

  @Autowired
  private HonorRuleDao honorRuledao;

  @Override
  public int add(HonorRulePojo honorRule) {
    if (null == honorRule) {
      return 0;
    }
    int rows = honorRuledao.add(honorRule);
    return rows;
  }

  @Override
  public int update(HonorRulePojo honorRule) {
    if (null == honorRule) {
      return 0;
    }
    int rows = honorRuledao.update(honorRule);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = honorRuledao.delete(id);
    return rows;
  }

  @Override
  public HonorRulePojo getById(Long id) {
    if (null == id) {
      return null;
    }
    HonorRulePojo honorRule = honorRuledao.getById(id);
    return honorRule;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = honorRuledao.countBy(params);
    return rows;
  }

  @Override
  public List<HonorRulePojo> listPage(Map<String, Object> params) {
    List<HonorRulePojo> lists = honorRuledao.listPage(params);
    return lists;
  }

  @Override
  public HonorRulePojo selectHonorBy(Map<String, Object> params) {
    return honorRuledao.selectHonorBy(params);
  }
}
