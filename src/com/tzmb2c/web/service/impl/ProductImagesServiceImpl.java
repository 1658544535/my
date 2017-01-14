package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductImagesDao;
import com.tzmb2c.web.pojo.ProductImagesPojo;
import com.tzmb2c.web.service.ProductImagesService;

/**
 * @author EricChen
 */
public class ProductImagesServiceImpl implements ProductImagesService {

  @Autowired
  private ProductImagesDao productImagesDao;

  @Override
  public int getCount(ProductImagesPojo productImages) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productImages != null && productImages.getProductId() != null
        && !productImages.getProductId().equals("")) {
      map.put("productId", productImages.getProductId());
    }
    if (productImages != null && productImages.getProductName() != null
        && !productImages.getProductName().equals("")) {
      map.put("productName", productImages.getProductName());
    }
    if (productImages != null && productImages.getProductNum() != null
        && !productImages.getProductNum().equals("")) {
      map.put("productNum", productImages.getProductNum());
    }
    if (productImages != null && productImages.getImages() != null
        && !productImages.getImages().equals("")) {
      map.put("images", productImages.getImages());
    }
    return productImagesDao.getCount(map);
  }

  @Override
  public List<ProductImagesPojo> getProductImagesAll(ProductImagesPojo productImages, Pager page)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productImages != null && productImages.getProductId() != null
        && !productImages.getProductId().equals("")) {
      map.put("productId", productImages.getProductId());
    }
    if (productImages != null && productImages.getCids() != null
        && productImages.getCids().length > 0) {
      map.put("cids", productImages.getCids());
    }
    if (productImages != null && productImages.getProductName() != null
        && !productImages.getProductName().equals("")) {
      map.put("productName", productImages.getProductName());
    }
    if (productImages != null && productImages.getProductNum() != null
        && !productImages.getProductNum().equals("")) {
      map.put("productNum", productImages.getProductNum());
    }
    if (productImages != null && productImages.getImages() != null
        && !productImages.getImages().equals("")) {
      map.put("images", productImages.getImages());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productImagesDao.getProductImagesAll(map);
  }

  @Override
  public ProductImagesPojo findProductImages(ProductImagesPojo productImages) {
    return productImagesDao.findProductImages(productImages);
  }

  @Override
  public void addProductImages(ProductImagesPojo productImages) {
    productImagesDao.addProductImages(productImages);
  }

  @Override
  public void productImagesUpdate(ProductImagesPojo productImages) {
    productImagesDao.productImagesUpdate(productImages);
  }

  @Override
  public void deleProductImages(Long id) throws SQLException {
    productImagesDao.deleProductImages(id);
  }

  @Override
  public void productImagesDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        productImagesDao.deleProductImages(Long.parseLong(tid));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkProductImages(Long id) throws SQLException {
    productImagesDao.checkProductImages(id);
  }

  @Override
  public void unCheckProductImages(Long id) throws SQLException {
    productImagesDao.unCheckProductImages(id);
  }

  @Override
  public List<ProductImagesPojo> productForId(Long id) {
    return productImagesDao.productForId(id);
  }

  @Override
  public void addProductImagesSeller(ProductImagesPojo productImages) {
    productImagesDao.addProductImagesSeller(productImages);
  }
}
