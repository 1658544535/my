package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductImagesPojo;

/**
 * @author EricChen
 */
public interface ProductImagesService {

  public int getCount(ProductImagesPojo productImages);

  public List<ProductImagesPojo> getProductImagesAll(ProductImagesPojo productImages, Pager page)
      throws SQLException;

  public ProductImagesPojo findProductImages(ProductImagesPojo productImages);

  public void addProductImages(ProductImagesPojo productImages);

  public void productImagesUpdate(ProductImagesPojo productImages);

  public void deleProductImages(Long id) throws SQLException;

  public void productImagesDeleteId(String[] tids);

  public void checkProductImages(Long id) throws SQLException;

  public void unCheckProductImages(Long id) throws SQLException;

  public List<ProductImagesPojo> productForId(Long id);
}
