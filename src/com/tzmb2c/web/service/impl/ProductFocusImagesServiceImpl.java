package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductFocusImagesDao;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.service.ProductFocusImagesService;

public class ProductFocusImagesServiceImpl implements ProductFocusImagesService {
  @Autowired
  private ProductFocusImagesDao productFocusImagesDao;

  public void setProductFocusImagesDao(ProductFocusImagesDao productFocusImagesDao) {
    this.productFocusImagesDao = productFocusImagesDao;
  }


  @Override
  public List<ProductFocusImagesPojo> productFocusImagesAllService() throws SQLException {
    return productFocusImagesDao.getProductFocusImagesAll();
  }



  @Override
  public void addProductFocusImagesService(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException {

    /*
     * if("0".equals(productFocusImagesPojo.getProductFocusImagesLevel())){ ProductFocusImagesPojo
     * rsProductFocusImagesPojo =
     * productFocusImagesDao.getProductFocusImagesCountByProductFocusImagesLevel
     * (productFocusImagesPojo.getLevel()); int productFocusImagesId =0;
     * if(rsProductFocusImagesPojo.getProductFocusImagesCount() > -1 &&
     * rsProductFocusImagesPojo.getProductFocusImagesCount() <= 9){ productFocusImagesId =
     * rsProductFocusImagesPojo.getProductFocusImagesCount()+1;
     * productFocusImagesPojo.setProductFocusImagesId("A0" + productFocusImagesId); }else
     * if(rsProductFocusImagesPojo.getProductFocusImagesCount() > 9 &&
     * rsProductFocusImagesPojo.getProductFocusImagesCount() <= 99){ productFocusImagesId =
     * rsProductFocusImagesPojo.getProductFocusImagesCount()+1;
     * productFocusImagesPojo.setProductFocusImagesId("A" +productFocusImagesId ); }
     * //productFocusImagesPojo.setProductFocusImagesPath("#"); }else{ ProductFocusImagesPojo
     * rsProductFocusImagesPojo =
     * productFocusImagesDao.getProductFocusImagesCountByProductFocusImagesLevel
     * (productFocusImagesPojo.getLevel()); if(rsProductFocusImagesPojo.getProductFocusImagesCount()
     * > -1 && rsProductFocusImagesPojo.getProductFocusImagesCount() <= 9){
     * productFocusImagesPojo.setProductFocusImagesId(productFocusImagesPojo.getLevel()+ "B0" +
     * (rsProductFocusImagesPojo.getProductFocusImagesCount() + 1)); }else
     * if(rsProductFocusImagesPojo.getProductFocusImagesCount() > 9 &&
     * rsProductFocusImagesPojo.getProductFocusImagesCount() <= 99){
     * productFocusImagesPojo.setProductFocusImagesId(productFocusImagesPojo.getLevel()+ "B" +
     * (rsProductFocusImagesPojo.getProductFocusImagesCount() + 1)); } }
     */
    productFocusImagesDao.insertProductFocusImages(productFocusImagesPojo);
  }


  @Override
  public void insertProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException {
    productFocusImagesDao.insertProductFocusImages(productFocusImagesPojo);
  }

  @Override
  public void updateProductFocusImages(ProductFocusImagesPojo productFocusImagesPojo)
      throws SQLException {
    productFocusImagesDao.updateProductFocusImages(productFocusImagesPojo);

  }

  @Override
  public ProductFocusImagesPojo getfindByIdProductFocusImages(Long id) throws SQLException {
    return productFocusImagesDao.getfindByIdProductFocusImages(id);

  }

  @Override
  public void deleteProductFocusImages(Long id) throws SQLException {

    productFocusImagesDao.deleteProductFocusImages(id);
  }

  @Override
  public int productFocusImagesAllCount(ProductFocusImagesPojo productFocusImagesDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productFocusImagesDaoPojo != null) {
      map.put("productId", productFocusImagesDaoPojo.getProductId());
      map.put("images", productFocusImagesDaoPojo.getImages());
    }
    return productFocusImagesDao.productFocusImagesAllCount(map);
  }

  @Override
  public List<ProductFocusImagesPojo> productFocusImagesAllList(
      ProductFocusImagesPojo productFocusImagesDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (productFocusImagesDaoPojo != null) {
      map.put("productId", productFocusImagesDaoPojo.getProductId());
      map.put("images", productFocusImagesDaoPojo.getImages());
      map.put("cids", productFocusImagesDaoPojo.getCids());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ProductFocusImagesPojo> list = productFocusImagesDao.productFocusImagesAllList(map);

    return list;
  }

  @Override
  public void productFocusImagesDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        productFocusImagesDao.delProductFocusImages(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delProductFocusImages(Long id) throws SQLException {
    productFocusImagesDao.delProductFocusImages(id);
  }

  @Override
  public ProductFocusImagesPojo findProductFocusImagesById(Long id) throws SQLException {

    return productFocusImagesDao.findProductFocusImagesById(id);

  }

  @Override
  public List<ProductFocusImagesPojo> findProductFocusImagesByPid(Long id) throws SQLException {

    return productFocusImagesDao.findProductFocusImagesByPid(id);

  }


  @Override
  public List<ProductFocusImagesPojo> getProductFocusImagesByPid(Long id) {
    return productFocusImagesDao.getProductFocusImagesByPid(id);
  }
}
