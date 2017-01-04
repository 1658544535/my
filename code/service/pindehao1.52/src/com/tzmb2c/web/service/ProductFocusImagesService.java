package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;

public interface ProductFocusImagesService {

  public List<ProductFocusImagesPojo> productFocusImagesAllService() throws SQLException;

  public void addProductFocusImagesService(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException;

  public void insertProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException;

  public void updateProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException;

  public ProductFocusImagesPojo getfindByIdProductFocusImages(Long id) throws SQLException;

  List<ProductFocusImagesPojo> findProductFocusImagesByPid(Long id) throws SQLException;

  public void deleteProductFocusImages(Long productFocusImagesId) throws SQLException;

  public int productFocusImagesAllCount(ProductFocusImagesPojo productFocusImagesDaoPojo);

  List<ProductFocusImagesPojo> productFocusImagesAllList(ProductFocusImagesPojo ticketRulePojo,
      Pager page);

  void productFocusImagesDeleteId(String[] tids);

  void delProductFocusImages(Long id) throws SQLException;

  ProductFocusImagesPojo findProductFocusImagesById(Long merId) throws SQLException;

  public List<ProductFocusImagesPojo> getProductFocusImagesByPid(Long id);
}
