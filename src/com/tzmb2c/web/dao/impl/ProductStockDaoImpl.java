package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductStockDao;
import com.tzmb2c.web.mapper.ProductStockMapper;
import com.tzmb2c.web.pojo.ProductStockPojo;

/**
 * @author EricChen
 */
public class ProductStockDaoImpl implements ProductStockDao {

  @Autowired
  private ProductStockMapper productStockMapper;

  @Override
  public int getCount(Map<String, Object> map) {
    return productStockMapper.getCount(map);
  }

  @Override
  public List<ProductStockPojo> getProductStockAll(Map<String, Object> map) {
    return productStockMapper.getProductStockAll(map);
  }

  @Override
  public ProductStockPojo findProductStock(ProductStockPojo productStock) {
    return productStockMapper.findProductStock(productStock);
  }

  @Override
  public ProductStockPojo findByProductStock(ProductStockPojo productStock) {
    return productStockMapper.findByProductStock(productStock);
  }

  @Override
  public void addProductStock(ProductStockPojo productStock) {
    productStockMapper.addProductStock(productStock);
  }

  @Override
  public void productStockUpdate(ProductStockPojo productStock) {
    productStockMapper.productStockUpdate(productStock);
  }

  @Override
  public void deleProductStock(Long id) throws SQLException {
    productStockMapper.deleProductStock(id);
  }

  @Override
  public void checkProductStock(Long id) throws SQLException {
    productStockMapper.checkProductStock(id);
  }
}
