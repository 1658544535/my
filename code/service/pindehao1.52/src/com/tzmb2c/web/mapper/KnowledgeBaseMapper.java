/*
 * 文 件 名: KnowledgeBaseMapper.java 创 建 人: admin 创建时间: 2016-05-28
 */
package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.KnowledgeBasePojo;

public interface KnowledgeBaseMapper {

  KnowledgeBasePojo getById(Long id);

  int countBy(Map<String, Object> params);

  List<KnowledgeBasePojo> listPage(Map<String, Object> params);

  int insert(KnowledgeBasePojo knowledgeBase);

  int update(KnowledgeBasePojo knowledgeBase);

  int deleteById(Long id);

  void checkKnowledgeBase(Long id);

  void uncheckKnowledgeBase(Long id);
  
  public KnowledgeBasePojo findKnowledgeByParams(Map<String, Object> params);
}
