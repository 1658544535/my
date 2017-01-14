package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductImagesPojo;

/**
 * @author EricChen
 */
public interface ProductImagesDao {

  int getCount(Map<String, Object> map);

  List<ProductImagesPojo> getProductImagesAll(Map<String, Object> map);

  ProductImagesPojo findProductImages(ProductImagesPojo productImages);

  void addProductImages(ProductImagesPojo productImages);

  void addProductImagesSeller(ProductImagesPojo productImages);

  void productImagesUpdate(ProductImagesPojo productImages);

  public void deleProductImages(Long id) throws SQLException;

  void checkProductImages(Long id) throws SQLException;

  public void unCheckProductImages(Long id) throws SQLException;

  List<ProductImagesPojo> productForId(Long id);
}
