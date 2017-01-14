package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductFocusImagesPojo;

public interface ProductFocusImagesMapper {


  public List<ProductFocusImagesPojo> getProductFocusImagesAll() throws SQLException;

  public void insertProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException;

  public void insertProductFocusImagesSeller(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException;

  public void updateProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException;

  public ProductFocusImagesPojo getfindByIdProductFocusImages(Long id) throws SQLException;

  public List<ProductFocusImagesPojo> getfindByPidProductFocusImages(Long id) throws SQLException;// 前台产品详情页的焦点图

  public void deleteProductFocusImages(Long id) throws SQLException;

  public int productFocusImagesAllCount(Map<String, Object> map);

  public List<ProductFocusImagesPojo> productFocusImagesAllList(Map<String, Object> map);

  void delProductFocusImages(Long id) throws SQLException;

  public List<ProductFocusImagesPojo> getProductFocusImagesByPid(Long id);

}
