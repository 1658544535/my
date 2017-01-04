/*
 * 文 件 名: KnowledgeBaseServiceImpl.java 创 建 人: admin 创建时间: 2016-05-28
 */
package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.KnowledgeBaseDao;
import com.tzmb2c.web.pojo.KnowledgeBasePojo;
import com.tzmb2c.web.service.KnowledgeBaseService;

/**
 * KnowledgeBase Service层
 */
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

  @Autowired
  private KnowledgeBaseDao knowledgeBasedao;

  @Override
  public int add(KnowledgeBasePojo knowledgeBase) {
    if (null == knowledgeBase) {
      return 0;
    }
    int rows = knowledgeBasedao.add(knowledgeBase);
    return rows;
  }

  @Override
  public int update(KnowledgeBasePojo knowledgeBase) {
    if (null == knowledgeBase) {
      return 0;
    }
    int rows = knowledgeBasedao.update(knowledgeBase);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = knowledgeBasedao.delete(id);
    return rows;
  }

  @Override
  public KnowledgeBasePojo getById(Long id) {
    if (null == id) {
      return null;
    }
    KnowledgeBasePojo knowledgeBase = knowledgeBasedao.getById(id);
    return knowledgeBase;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = knowledgeBasedao.countBy(params);
    return rows;
  }

  @Override
  public List<KnowledgeBasePojo> listPage(Map<String, Object> params) {
    List<KnowledgeBasePojo> lists = knowledgeBasedao.listPage(params);
    return lists;
  }

  @Override
  public void checkKnowledgeBase(Long id) {
    knowledgeBasedao.checkKnowledgeBase(id);

  }

  @Override
  public void uncheckKnowledgeBase(Long id) {
    knowledgeBasedao.uncheckKnowledgeBase(id);

  }

  @Override
  public KnowledgeBasePojo findKnowledgeByParams(Map<String, Object> params) {
	  return knowledgeBasedao.findKnowledgeByParams(params);
  }
}
