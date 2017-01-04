package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductFocusImagesPojo;

public interface ProductFocusImagesDao {

  List<ProductFocusImagesPojo> getProductFocusImagesAll() throws SQLException;

  void insertProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo) throws SQLException;

  void updateProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo) throws SQLException;

  ProductFocusImagesPojo getfindByIdProductFocusImages(Long id) throws SQLException;

  List<ProductFocusImagesPojo> findProductFocusImagesByPid(Long id) throws SQLException;

  void deleteProductFocusImages(Long id) throws SQLException;

  int productFocusImagesAllCount(Map<String, Object> map);

  List<ProductFocusImagesPojo> productFocusImagesAllList(Map<String, Object> map);

  void delProductFocusImages(Long id) throws SQLException;

  ProductFocusImagesPojo findProductFocusImagesById(Long id) throws SQLException;

  public List<ProductFocusImagesPojo> getProductFocusImagesByPid(Long id);

}
