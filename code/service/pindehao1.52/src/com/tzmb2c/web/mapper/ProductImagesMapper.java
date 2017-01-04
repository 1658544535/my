package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductImagesPojo;

/**
 * @author EricChen
 */
public interface ProductImagesMapper {

  int getCount(Map<String, Object> map);

  List<ProductImagesPojo> getProductImagesAll(Map<String, Object> map);

  ProductImagesPojo findProductImages(ProductImagesPojo productImages);

  public void addProductImages(ProductImagesPojo productImages);

  public void productImagesUpdate(ProductImagesPojo productImages);

  public void deleProductImages(Long id) throws SQLException;

  public void checkProductImages(Long id) throws SQLException;

  public void unCheckProductImages(Long id) throws SQLException;

  List<ProductImagesPojo> productForId(Long id);
}
