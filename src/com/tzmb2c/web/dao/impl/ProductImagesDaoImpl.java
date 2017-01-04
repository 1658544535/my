package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductImagesDao;
import com.tzmb2c.web.mapper.ProductImagesMapper;
import com.tzmb2c.web.pojo.ProductImagesPojo;

/**
 * @author EricChen
 */
public class ProductImagesDaoImpl implements ProductImagesDao {

  @Autowired
  private ProductImagesMapper productImagesMapper;

  @Override
  public int getCount(Map<String, Object> map) {
    return productImagesMapper.getCount(map);
  }

  @Override
  public List<ProductImagesPojo> getProductImagesAll(Map<String, Object> map) {
    return productImagesMapper.getProductImagesAll(map);
  }

  @Override
  public ProductImagesPojo findProductImages(ProductImagesPojo productImages) {
    return productImagesMapper.findProductImages(productImages);
  }

  @Override
  public void addProductImages(ProductImagesPojo productImages) {
    productImagesMapper.addProductImages(productImages);
  }

  @Override
  public void productImagesUpdate(ProductImagesPojo productImages) {
    productImagesMapper.productImagesUpdate(productImages);
  }

  @Override
  public void deleProductImages(Long id) throws SQLException {
    productImagesMapper.deleProductImages(id);
  }

  @Override
  public void checkProductImages(Long id) throws SQLException {
    productImagesMapper.checkProductImages(id);
  }

  @Override
  public void unCheckProductImages(Long id) throws SQLException {
    productImagesMapper.unCheckProductImages(id);
  }

  @Override
  public List<ProductImagesPojo> productForId(Long id) {
    return productImagesMapper.productForId(id);
  }
}
