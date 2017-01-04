package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductTypeDao;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.service.ProductTypeService;

/**
 * @author EricChen
 */
public class ProductTypeServiceImpl implements ProductTypeService {

  @Autowired
  private ProductTypeDao productTypeDao;

  @Override
  public int getCount(ProductTypePojo productTypePojo) {
    return productTypeDao.getCount(productTypePojo);
  }

  @Override
  public List<ProductTypePojo> getProductTypeByPid8(Long pid) throws SQLException {
    return productTypeDao.getProductTypeByPid8(pid);
  }

  @Override
  public List<ProductTypePojo> getProductTypeByPid(ProductTypePojo productTypePojo)
      throws SQLException {
    return productTypeDao.getProductTypeByPid(productTypePojo);
  }

  @Override
  public List<ProductTypePojo> getProductTypeByPids(ProductTypePojo productTypePojo)
      throws SQLException {
    return productTypeDao.getProductTypeByPids(productTypePojo);
  }

  @Override
  public ProductTypePojo findProductType(ProductTypePojo productTypePojo) {
    return productTypeDao.findProductType(productTypePojo);
  }

  @Override
  public void addProductType(ProductTypePojo productType) {
    productTypeDao.addProductType(productType);
  }

  @Override
  public void productTypeUpdate(ProductTypePojo productType) {
    productTypeDao.productTypeUpdate(productType);
  }

  @Override
  public void deleProductType(Long id) throws SQLException {
    productTypeDao.deleProductType(id);
  }

  @Override
  public void checkProductType(Long id) throws SQLException {
    productTypeDao.checkProductType(id);
  }

  @Override
  public List<ProductTypePojo> getProductTypeSecondLevel() throws SQLException {
    return productTypeDao.getProductTypeSecondLevel();
  }

  @Override
  public List<ProductTypePojo> getProductTypeByPidName(ProductTypePojo productTypePojo) {
    return productTypeDao.getProductTypeByPidName(productTypePojo);
  }

  @Override
  public void visableProductType(Long id) throws SQLException {
    productTypeDao.visableProductType(id);
  }

  @Override
  public void invisableProductType(Long id) throws SQLException {
    productTypeDao.invisableProductType(id);
  }

  @Override
  public List<ProductTypePojo> getProductTypeThree(Map<String, Object> map) throws SQLException {
    return productTypeDao.getProductTypeThree(map);
  }

  @Override
  public int getCountProductTypeThree(Map<String, Object> map) {
    return productTypeDao.getCountProductTypeThree(map);
  }

  @Override
  public List<ProductTypePojo> findThridProductType() throws SQLException {
    return productTypeDao.findThridProductType();
  }

  @Override
  public void checkAllProductTypeById(String id) throws SQLException {
    productTypeDao.checkAllProductTypeById(id);
  }
}
