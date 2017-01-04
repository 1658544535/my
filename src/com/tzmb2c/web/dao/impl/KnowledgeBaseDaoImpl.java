/*
 * 文 件 名: KnowledgeBaseDaoImpl.java 创 建 人: admin 创建时间: 2016-05-28
 */
package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.KnowledgeBaseDao;
import com.tzmb2c.web.mapper.KnowledgeBaseMapper;
import com.tzmb2c.web.pojo.KnowledgeBasePojo;

/**
 * KnowledgeBase Dao层
 */
public class KnowledgeBaseDaoImpl implements KnowledgeBaseDao {

  @Autowired
  private KnowledgeBaseMapper knowledgeBaseMapper;

  @Override
  public int add(KnowledgeBasePojo knowledgeBase) {
    if (null == knowledgeBase) {
      return 0;
    }
    int rows = knowledgeBaseMapper.insert(knowledgeBase);
    return rows;
  }

  @Override
  public int update(KnowledgeBasePojo knowledgeBase) {
    if (null == knowledgeBase) {
      return 0;
    }
    int rows = knowledgeBaseMapper.update(knowledgeBase);
    return rows;
  }

  @Override
  public int delete(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = knowledgeBaseMapper.deleteById(id);
    return rows;
  }

  @Override
  public KnowledgeBasePojo getById(Long id) {
    if (null == id) {
      return null;
    }
    KnowledgeBasePojo knowledgeBase = knowledgeBaseMapper.getById(id);
    //
    return knowledgeBase;
  }

  @Override
  public Integer countBy(Map<String, Object> params) {
    Integer rows = knowledgeBaseMapper.countBy(params);
    return rows;
  }

  @Override
  public List<KnowledgeBasePojo> listPage(Map<String, Object> params) {
    List<KnowledgeBasePojo> lists = knowledgeBaseMapper.listPage(params);
    return lists;
  }

  @Override
  public void checkKnowledgeBase(Long id) {
    knowledgeBaseMapper.checkKnowledgeBase(id);

  }

  @Override
  public void uncheckKnowledgeBase(Long id) {
    knowledgeBaseMapper.uncheckKnowledgeBase(id);

  }

  @Override
  public KnowledgeBasePojo findKnowledgeByParams(Map<String, Object> params) {
	return knowledgeBaseMapper.findKnowledgeByParams(params);
  }
}
