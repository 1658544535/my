package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductPrimaryImagesPojo;

public interface ProductPrimaryImagesMapper {


  public List<ProductPrimaryImagesPojo> getProductPrimaryImagesAll() throws SQLException;

  public void insertProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException;

  public void updateProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException;

  public ProductPrimaryImagesPojo getfindByIdProductPrimaryImages(Long id) throws SQLException;

  public void deleteProductPrimaryImages(Long id) throws SQLException;

  public int productPrimaryImagesAllCount(Map<String, Object> map);

  public List<ProductPrimaryImagesPojo> productPrimaryImagesAllList(Map<String, Object> map);

  void delProductPrimaryImages(Long id) throws SQLException;

  public List<ProductPrimaryImagesPojo> getPrimaryImagesByProduct(Long id);

}
