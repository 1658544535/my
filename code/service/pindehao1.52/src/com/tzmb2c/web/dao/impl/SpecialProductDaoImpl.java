package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialProductDao;
import com.tzmb2c.web.mapper.SpecialProductMapper;
import com.tzmb2c.web.pojo.SpecialProductPojo;

public class SpecialProductDaoImpl implements SpecialProductDao {
  @Autowired
  private SpecialProductMapper specialProductMapper;

  @Override
  public List<SpecialProductPojo> findSpecialProductList(Map<String, Object> map)
      throws SQLException {
    return specialProductMapper.findSpecialProductList(map);
  }

  @Override
  public int findSpecialProductCount(Map<String, Object> map) throws SQLException {
    return specialProductMapper.findSpecialProductCount(map);
  }

  @Override
  public SpecialProductPojo findSpecialProductById(Long id) throws SQLException {
    return specialProductMapper.findSpecialProductById(id);
  }

  @Override
  public void insertSpecialProduct(SpecialProductPojo specialProduct) throws SQLException {
    specialProductMapper.insertSpecialProduct(specialProduct);
  }

  @Override
  public void delSpecialProduct(Long id) throws SQLException {
    specialProductMapper.delSpecialProduct(id);
  }

  @Override
  public void delSpecialProductBySpecialId(Long specialId) throws SQLException {
    specialProductMapper.delSpecialProductBySpecialId(specialId);
  }

  @Override
  public void updateSpecialProduct(SpecialProductPojo specialProduct) throws SQLException {
    specialProductMapper.updateSpecialProduct(specialProduct);
  }

  @Override
  public void checkSpecialProductByActivityId(Long activityId) throws SQLException {
    specialProductMapper.checkSpecialProductByActivityId(activityId);
  }

  @Override
  public void uncheckSpecialProductByActivityId(Long activityId) throws SQLException {
    specialProductMapper.uncheckSpecialProductByActivityId(activityId);
  }
}
