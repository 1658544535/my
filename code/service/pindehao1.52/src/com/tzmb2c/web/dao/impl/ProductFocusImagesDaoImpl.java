package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductFocusImagesDao;
import com.tzmb2c.web.mapper.ProductFocusImagesMapper;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;

public class ProductFocusImagesDaoImpl implements ProductFocusImagesDao {

  @Autowired
  private ProductFocusImagesMapper productFocusImagesMapper;

  @Override
  public List<ProductFocusImagesPojo> getProductFocusImagesAll() throws SQLException {
    return productFocusImagesMapper.getProductFocusImagesAll();
  }

  @Override
  public void insertProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException {
    try {
      productFocusImagesMapper.insertProductFocusImages(productFocusImagesPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException {

    productFocusImagesMapper.updateProductFocusImages(productFocusImagesPojo);
  }

  @Override
  public ProductFocusImagesPojo getfindByIdProductFocusImages(Long id) throws SQLException {
    return productFocusImagesMapper.getfindByIdProductFocusImages(id);

  }

  @Override
  public void deleteProductFocusImages(Long id) throws SQLException {
    productFocusImagesMapper.deleteProductFocusImages(id);
  }

  @Override
  public int productFocusImagesAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productFocusImagesMapper.productFocusImagesAllCount(map);
  }

  @Override
  public List<ProductFocusImagesPojo> productFocusImagesAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productFocusImagesMapper.productFocusImagesAllList(map);
  }

  @Override
  public void delProductFocusImages(Long id) throws SQLException {

    productFocusImagesMapper.delProductFocusImages(id);
  }

  @Override
  public ProductFocusImagesPojo findProductFocusImagesById(Long id) throws SQLException {
    return productFocusImagesMapper.getfindByIdProductFocusImages(id);
  }

  @Override
  public List<ProductFocusImagesPojo> findProductFocusImagesByPid(Long id) throws SQLException {
    return productFocusImagesMapper.getfindByPidProductFocusImages(id);
  }

  @Override
  public List<ProductFocusImagesPojo> getProductFocusImagesByPid(Long id) {
    return productFocusImagesMapper.getProductFocusImagesByPid(id);
  }
}
