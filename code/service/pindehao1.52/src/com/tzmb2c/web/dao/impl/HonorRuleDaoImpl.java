/*
 * 文 件 名: HonorRuleDaoImpl.java 创 建 人: admin 创建时间: 2016-06-05
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HonorRuleDao;
import com.tzmb2c.web.mapper.HonorRuleMapper;
import com.tzmb2c.web.pojo.HonorRulePojo;

/**
 * HonorRule Dao层
 */
public class HonorRuleDaoImpl implements HonorRuleDao {

  @Autowired
  private HonorRuleMapper honorRuleMapper;

  @Override
  public int add(HonorRulePojo honorRule) {
    if (null == honorRule) {
      return 0;
    }
    int rows = honorRuleMapper.insert(honorRule);
    return rows;
  }

  @Override
  public int update(HonorRulePojo honorRule) {
    if (null == honorRule) {
      return 0;
    }
    int rows = honorRuleMapper.update(honorRule);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = honorRuleMapper.deleteById(id);
    return rows;
  }

  @Override
  public HonorRulePojo getById(Long id) {
    if (null == id) {
      return null;
    }
    HonorRulePojo honorRule = honorRuleMapper.getById(id);
    return honorRule;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = honorRuleMapper.countBy(params);
    return rows;
  }

  @Override
  public List<HonorRulePojo> listPage(Map<String, Object> params) {
    List<HonorRulePojo> lists = honorRuleMapper.listPage(params);
    return lists;
  }

  @Override
  public HonorRulePojo selectHonorBy(Map<String, Object> params) {
    return honorRuleMapper.selectHonorBy(params);
  }
}
