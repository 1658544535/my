/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HonorRulePojo;

/**
 * @version 1.0
 * @author
 */
public interface HonorRuleService {

  public int add(HonorRulePojo honorRule);

  public int update(HonorRulePojo honorRule);

  public int delete(Long id);

  public HonorRulePojo getById(Long id);

  public HonorRulePojo selectHonorBy(Map<String, Object> params);

  public Integer countBy(Map<String, Object> params);

  public List<HonorRulePojo> listPage(Map<String, Object> params);

}
