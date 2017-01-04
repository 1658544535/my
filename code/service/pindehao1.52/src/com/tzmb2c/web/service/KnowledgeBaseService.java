/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.KnowledgeBasePojo;

/**
 * @version 1.0
 * @author
 */
public interface KnowledgeBaseService {

  public int add(KnowledgeBasePojo knowledgeBase);

  public int update(KnowledgeBasePojo knowledgeBase);

  public int delete(Long id);

  public KnowledgeBasePojo getById(Long id);

  public Integer countBy(Map<String, Object> params);

  public List<KnowledgeBasePojo> listPage(Map<String, Object> params);

  public void checkKnowledgeBase(Long id);

  public void uncheckKnowledgeBase(Long id);
  
  /**
   * 查询一条数据
   * @param params
   */
  public KnowledgeBasePojo findKnowledgeByParams(Map<String, Object> params);

}
