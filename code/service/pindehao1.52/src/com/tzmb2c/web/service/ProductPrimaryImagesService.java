package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductPrimaryImagesPojo;

public interface ProductPrimaryImagesService {

  public List<ProductPrimaryImagesPojo> productPrimaryImagesAllService() throws SQLException;

  public void addProductPrimaryImagesService(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException;

  public void insertProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException;

  public void updateProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException;

  public ProductPrimaryImagesPojo getfindByIdProductPrimaryImages(Long id) throws SQLException;

  public void deleteProductPrimaryImages(Long productPrimaryImagesId) throws SQLException;

  public int productPrimaryImagesAllCount(ProductPrimaryImagesPojo productPrimaryImagesDaoPojo);

  List<ProductPrimaryImagesPojo> productPrimaryImagesAllList(
      ProductPrimaryImagesPojo ticketRulePojo, Pager page);

  void productPrimaryImagesDeleteId(String[] tids);

  void delProductPrimaryImages(Long id) throws SQLException;

  ProductPrimaryImagesPojo findProductPrimaryImagesById(Long merId) throws SQLException;

  public List<ProductPrimaryImagesPojo> getPrimaryImagesByProduct(Long productId);
}
