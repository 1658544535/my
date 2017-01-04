package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SkuAttributeDao;
import com.tzmb2c.web.mapper.SkuAttributeMapper;
import com.tzmb2c.web.pojo.SkuAttributePojo;

public class SkuAttributeDaoImpl implements SkuAttributeDao {
  @Autowired
  private SkuAttributeMapper skuAttributeMapper;

  @Override
  public SkuAttributePojo getSkuAttributeById(Long id) throws Exception {
    return skuAttributeMapper.getSkuAttributeById(id);
  }

  @Override
  public List<SkuAttributePojo> getSkuAttributeByAttribute(Map<String, Object> map)
      throws Exception {
    return skuAttributeMapper.getSkuAttributeByAttribute(map);
  }

  @Override
  public Long insertSkuAttribute(SkuAttributePojo skuAttributePojo) throws Exception {
    return skuAttributeMapper.insertSkuAttribute(skuAttributePojo);
  }

  @Override
  public void deleSkuAttribute(Long id) throws SQLException {
    skuAttributeMapper.deleSkuAttribute(id);
  }

  @Override
  public void skuAttributeUpdateById(SkuAttributePojo skuAttributePojo) throws Exception {
    skuAttributeMapper.skuAttributeUpdateById(skuAttributePojo);
  }

  @Override
  public List<SkuAttributePojo> getSkuAttribute(Map<String, Object> map) throws Exception {
    return skuAttributeMapper.getSkuAttribute(map);
  }
}
