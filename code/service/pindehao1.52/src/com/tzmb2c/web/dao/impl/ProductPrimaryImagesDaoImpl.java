package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductPrimaryImagesDao;
import com.tzmb2c.web.mapper.ProductPrimaryImagesMapper;
import com.tzmb2c.web.pojo.ProductPrimaryImagesPojo;

public class ProductPrimaryImagesDaoImpl implements ProductPrimaryImagesDao {

  @Autowired
  private ProductPrimaryImagesMapper productPrimaryImagesMapper;

  @Override
  public List<ProductPrimaryImagesPojo> getProductPrimaryImagesAll() throws SQLException {
    return productPrimaryImagesMapper.getProductPrimaryImagesAll();
  }

  @Override
  public void insertProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException {
    try {
      productPrimaryImagesMapper.insertProductPrimaryImages(productPrimaryImagesPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException {

    productPrimaryImagesMapper.updateProductPrimaryImages(productPrimaryImagesPojo);
  }

  @Override
  public ProductPrimaryImagesPojo getfindByIdProductPrimaryImages(Long id) throws SQLException {
    return productPrimaryImagesMapper.getfindByIdProductPrimaryImages(id);

  }

  @Override
  public void deleteProductPrimaryImages(Long id) throws SQLException {
    productPrimaryImagesMapper.deleteProductPrimaryImages(id);
  }

  @Override
  public int productPrimaryImagesAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productPrimaryImagesMapper.productPrimaryImagesAllCount(map);
  }

  @Override
  public List<ProductPrimaryImagesPojo> productPrimaryImagesAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productPrimaryImagesMapper.productPrimaryImagesAllList(map);
  }

  @Override
  public void delProductPrimaryImages(Long id) throws SQLException {

    productPrimaryImagesMapper.delProductPrimaryImages(id);
  }

  @Override
  public ProductPrimaryImagesPojo findProductPrimaryImagesById(Long id) throws SQLException {
    return productPrimaryImagesMapper.getfindByIdProductPrimaryImages(id);
  }

  @Override
  public List<ProductPrimaryImagesPojo> getPrimaryImagesByProduct(Long productId) {
    return productPrimaryImagesMapper.getPrimaryImagesByProduct(productId);
  }
}
