package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductPrimaryImagesDao;
import com.tzmb2c.web.pojo.ProductPrimaryImagesPojo;
import com.tzmb2c.web.service.ProductPrimaryImagesService;

public class ProductPrimaryImagesServiceImpl implements ProductPrimaryImagesService {
  @Autowired
  private ProductPrimaryImagesDao productPrimaryImagesDao;

  public void setProductPrimaryImagesDao(ProductPrimaryImagesDao productPrimaryImagesDao) {
    this.productPrimaryImagesDao = productPrimaryImagesDao;
  }


  @Override
  public List<ProductPrimaryImagesPojo> productPrimaryImagesAllService() throws SQLException {
    return productPrimaryImagesDao.getProductPrimaryImagesAll();
  }



  @Override
  public void addProductPrimaryImagesService(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException {

    /*
     * if("0".equals(productPrimaryImagesPojo.getProductPrimaryImagesLevel())){
     * ProductPrimaryImagesPojo rsProductPrimaryImagesPojo =
     * productPrimaryImagesDao.getProductPrimaryImagesCountByProductPrimaryImagesLevel
     * (productPrimaryImagesPojo.getLevel()); int productPrimaryImagesId =0;
     * if(rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() > -1 &&
     * rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() <= 9){ productPrimaryImagesId =
     * rsProductPrimaryImagesPojo.getProductPrimaryImagesCount()+1;
     * productPrimaryImagesPojo.setProductPrimaryImagesId("A0" + productPrimaryImagesId); }else
     * if(rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() > 9 &&
     * rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() <= 99){ productPrimaryImagesId =
     * rsProductPrimaryImagesPojo.getProductPrimaryImagesCount()+1;
     * productPrimaryImagesPojo.setProductPrimaryImagesId("A" +productPrimaryImagesId ); }
     * //productPrimaryImagesPojo.setProductPrimaryImagesPath("#"); }else{ ProductPrimaryImagesPojo
     * rsProductPrimaryImagesPojo =
     * productPrimaryImagesDao.getProductPrimaryImagesCountByProductPrimaryImagesLevel
     * (productPrimaryImagesPojo.getLevel());
     * if(rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() > -1 &&
     * rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() <= 9){
     * productPrimaryImagesPojo.setProductPrimaryImagesId(productPrimaryImagesPojo.getLevel()+ "B0"
     * + (rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() + 1)); }else
     * if(rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() > 9 &&
     * rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() <= 99){
     * productPrimaryImagesPojo.setProductPrimaryImagesId(productPrimaryImagesPojo.getLevel()+ "B" +
     * (rsProductPrimaryImagesPojo.getProductPrimaryImagesCount() + 1)); } }
     */
    productPrimaryImagesDao.insertProductPrimaryImages(productPrimaryImagesPojo);
  }


  @Override
  public void insertProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException {

    productPrimaryImagesDao.insertProductPrimaryImages(productPrimaryImagesPojo);
  }

  @Override
  public void updateProductPrimaryImages(ProductPrimaryImagesPojo productPrimaryImagesPojo)
      throws SQLException {
    productPrimaryImagesDao.updateProductPrimaryImages(productPrimaryImagesPojo);

  }

  @Override
  public ProductPrimaryImagesPojo getfindByIdProductPrimaryImages(Long id) throws SQLException {
    return productPrimaryImagesDao.getfindByIdProductPrimaryImages(id);

  }

  @Override
  public void deleteProductPrimaryImages(Long id) throws SQLException {

    productPrimaryImagesDao.deleteProductPrimaryImages(id);
  }

  @Override
  public int productPrimaryImagesAllCount(ProductPrimaryImagesPojo productPrimaryImagesDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productPrimaryImagesDaoPojo != null) {
      map.put("productId", productPrimaryImagesDaoPojo.getProductId());
    }
    return productPrimaryImagesDao.productPrimaryImagesAllCount(map);
  }

  @Override
  public List<ProductPrimaryImagesPojo> productPrimaryImagesAllList(
      ProductPrimaryImagesPojo productPrimaryImagesDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (productPrimaryImagesDaoPojo != null) {
      map.put("productId", productPrimaryImagesDaoPojo.getProductId());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ProductPrimaryImagesPojo> list = productPrimaryImagesDao.productPrimaryImagesAllList(map);

    return list;
  }

  @Override
  public void productPrimaryImagesDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        productPrimaryImagesDao.delProductPrimaryImages(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delProductPrimaryImages(Long id) throws SQLException {
    productPrimaryImagesDao.delProductPrimaryImages(id);
  }

  @Override
  public ProductPrimaryImagesPojo findProductPrimaryImagesById(Long id) throws SQLException {

    return productPrimaryImagesDao.findProductPrimaryImagesById(id);

  }

  @Override
  public List<ProductPrimaryImagesPojo> getPrimaryImagesByProduct(Long productId) {
    return productPrimaryImagesDao.getPrimaryImagesByProduct(productId);
  }
}
