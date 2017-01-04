package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SkuAttributePojo;

public interface SkuAttributeMapper {

  public List<SkuAttributePojo> getSkuAttributeByAttribute(Map<String, Object> map)
      throws Exception;

  public Long insertSkuAttribute(SkuAttributePojo skuAttributePojo) throws Exception;

  public void deleSkuAttribute(Long id) throws SQLException;

  public SkuAttributePojo getSkuAttributeById(Long id) throws Exception;

  public void skuAttributeUpdateById(SkuAttributePojo skuAttributePojo) throws Exception;

  public List<SkuAttributePojo> getSkuAttribute(Map<String, Object> map) throws Exception;


}
