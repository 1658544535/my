package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SkuAttributePojo;

public interface SkuAttributeService {

  List<SkuAttributePojo> getSkuAttributeByAttribute(Map<String, Object> map) throws Exception;

  public Long insertSkuAttribute(SkuAttributePojo skuAttributePojo) throws Exception;

  public void deleSkuAttribute(Long id) throws SQLException;

  SkuAttributePojo getSkuAttributeById(Long id) throws Exception;

  public void skuAttributeUpdateById(SkuAttributePojo skuAttributePojo) throws Exception;

  List<SkuAttributePojo> getSkuAttribute(Map<String, Object> map) throws Exception;


}
