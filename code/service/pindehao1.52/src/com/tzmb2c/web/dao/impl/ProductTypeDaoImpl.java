package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductTypeDao;
import com.tzmb2c.web.mapper.ProductTypeMapper;
import com.tzmb2c.web.pojo.ProductTypePojo;

/**
 * @author EricChen
 */
public class ProductTypeDaoImpl implements ProductTypeDao {

  @Autowired
  private ProductTypeMapper productTypeMapper;

  @Override
  public int getCount(ProductTypePojo productTypePojo) {
    return productTypeMapper.getCount(productTypePojo);
  }

  @Override
  public List<ProductTypePojo> getProductTypeByPid8(Long pid) {
    return productTypeMapper.getProductTypeByPid8(pid);
  }

  @Override
  public List<ProductTypePojo> getProductTypeByPid(ProductTypePojo productTypePojo) {
    return productTypeMapper.getProductTypeByPid(productTypePojo);
  }

  @Override
  public List<ProductTypePojo> getProductTypeByPids(ProductTypePojo productTypePojo)
      throws SQLException {
    return productTypeMapper.getProductTypeByPids(productTypePojo);
  }

  @Override
  public ProductTypePojo findProductType(ProductTypePojo productTypePojo) {
    return productTypeMapper.findProductType(productTypePojo);
  }

  @Override
  public void addProductType(ProductTypePojo productType) {
    productTypeMapper.addProductType(productType);
  }

  @Override
  public void productTypeUpdate(ProductTypePojo productType) {
    productTypeMapper.productTypeUpdate(productType);
  }

  @Override
  public void deleProductType(Long id) throws SQLException {
    productTypeMapper.deleProductType(id);
  }

  @Override
  public void checkProductType(Long id) throws SQLException {
    productTypeMapper.checkProductType(id);
  }

  @Override
  public List<ProductTypePojo> getProductTypeSecondLevel() throws SQLException {
    return productTypeMapper.getProductTypeSecondLevel();
  }

  @Override
  public List<ProductTypePojo> getProductTypeByPidName(ProductTypePojo productTypePojo) {
    return productTypeMapper.getProductTypeByPidName(productTypePojo);
  }

  @Override
  public void visableProductType(Long id) throws SQLException {
    productTypeMapper.visableProductType(id);
  }

  @Override
  public void invisableProductType(Long id) throws SQLException {
    productTypeMapper.invisableProductType(id);
  }

  @Override
  public List<ProductTypePojo> getProductTypeThree(Map<String, Object> map) {
    return productTypeMapper.getProductTypeThree(map);
  }

  @Override
  public int getCountProductTypeThree(Map<String, Object> map) {
    return productTypeMapper.getCountProductTypeThree(map);
  }

  @Override
  public List<ProductTypePojo> findThridProductType() throws SQLException {

    return productTypeMapper.findThridProductType();
  }

  @Override
  public void checkAllProductTypeById(String id) throws SQLException {
    productTypeMapper.checkAllProductTypeById(id);
  }
}
