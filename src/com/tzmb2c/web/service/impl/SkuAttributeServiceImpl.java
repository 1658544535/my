package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SkuAttributeDao;
import com.tzmb2c.web.pojo.SkuAttributePojo;
import com.tzmb2c.web.service.SkuAttributeService;

public class SkuAttributeServiceImpl implements SkuAttributeService {

  @Autowired
  private SkuAttributeDao skuAttributeDao;

  @Override
  public List<SkuAttributePojo> getSkuAttributeByAttribute(Map<String, Object> map)
      throws Exception {

    return skuAttributeDao.getSkuAttributeByAttribute(map);
  }

  @Override
  public Long insertSkuAttribute(SkuAttributePojo skuAttributePojo) throws Exception {
    return skuAttributeDao.insertSkuAttribute(skuAttributePojo);
  }

  @Override
  public void deleSkuAttribute(Long id) throws SQLException {
    skuAttributeDao.deleSkuAttribute(id);
  }

  @Override
  public SkuAttributePojo getSkuAttributeById(Long id) throws Exception {
    return skuAttributeDao.getSkuAttributeById(id);
  }

  @Override
  public void skuAttributeUpdateById(SkuAttributePojo skuAttributePojo) throws Exception {
    skuAttributeDao.skuAttributeUpdateById(skuAttributePojo);
  }

  @Override
  public List<SkuAttributePojo> getSkuAttribute(Map<String, Object> map) throws Exception {
    return skuAttributeDao.getSkuAttribute(map);
  }
}
