package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductPrimaryImagesPojo;

public interface ProductPrimaryImagesDao {

  List<ProductPrimaryImagesPojo> getProductPrimaryImagesAll() throws SQLException;

  void insertProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException;

  void updateProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException;

  ProductPrimaryImagesPojo getfindByIdProductPrimaryImages(Long id) throws SQLException;

  void deleteProductPrimaryImages(Long id) throws SQLException;

  int productPrimaryImagesAllCount(Map<String, Object> map);

  List<ProductPrimaryImagesPojo> productPrimaryImagesAllList(Map<String, Object> map);

  void delProductPrimaryImages(Long id) throws SQLException;

  ProductPrimaryImagesPojo findProductPrimaryImagesById(Long id) throws SQLException;

  List<ProductPrimaryImagesPojo> getPrimaryImagesByProduct(Long productId);

}
