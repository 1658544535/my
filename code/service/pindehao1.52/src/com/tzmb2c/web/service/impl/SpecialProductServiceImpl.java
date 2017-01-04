package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SpecialProductDao;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.service.SpecialProductService;

public class SpecialProductServiceImpl implements SpecialProductService {
  @Autowired
  private SpecialProductDao specialProductDao;

  @Override
  public List<SpecialProductPojo> findSpecialProductList(Map<String, Object> map)
      throws SQLException {
    return specialProductDao.findSpecialProductList(map);
  }

  @Override
  public int findSpecialProductCount(Map<String, Object> map) throws SQLException {
    return specialProductDao.findSpecialProductCount(map);
  }

  @Override
  public SpecialProductPojo findSpecialProductById(Long id) throws SQLException {
    return specialProductDao.findSpecialProductById(id);
  }

  @Override
  public void insertSpecialProduct(SpecialProductPojo specialProduct) throws SQLException {
    specialProductDao.insertSpecialProduct(specialProduct);
  }

  @Override
  public void delSpecialProduct(Long id) throws SQLException {
    specialProductDao.delSpecialProduct(id);
  }

  @Override
  public void delSpecialProductBySpecialId(Long specialId) throws SQLException {
    specialProductDao.delSpecialProductBySpecialId(specialId);
  }

  @Override
  public void updateSpecialProduct(SpecialProductPojo specialProduct) throws SQLException {
    specialProductDao.updateSpecialProduct(specialProduct);
  }

  @Override
  public void checkSpecialProductByActivityId(Long activityId) throws SQLException {
    specialProductDao.checkSpecialProductByActivityId(activityId);
  }

  @Override
  public void uncheckSpecialProductByActivityId(Long activityId) throws SQLException {
    specialProductDao.uncheckSpecialProductByActivityId(activityId);
  }
}
