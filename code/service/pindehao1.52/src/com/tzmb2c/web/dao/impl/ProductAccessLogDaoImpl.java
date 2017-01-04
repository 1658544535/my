package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductAccessLogDao;
import com.tzmb2c.web.mapper.ProductAccessLogMapper;
import com.tzmb2c.web.pojo.ProductAccessLogPojo;

public class ProductAccessLogDaoImpl implements ProductAccessLogDao {

  @Autowired
  private ProductAccessLogMapper productAccessLogMapper;

  @Override
  public int productAccessLogAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productAccessLogMapper.productAccessLogAllCount(map);
  }

  @Override
  public List<ProductAccessLogPojo> productAccessLogAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productAccessLogMapper.productAccessLogAllList(map);
  }

  @Override
  public List<ProductAccessLogPojo> productAccessLogAllListHot(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productAccessLogMapper.productAccessLogAllListHot(map);
  }
}
