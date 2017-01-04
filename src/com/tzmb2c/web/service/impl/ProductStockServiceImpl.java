package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductStockDao;
import com.tzmb2c.web.pojo.ProductStockPojo;
import com.tzmb2c.web.service.ProductStockService;

/**
 * @author EricChen
 */
public class ProductStockServiceImpl implements ProductStockService {

  @Autowired
  private ProductStockDao productStockDao;

  @Override
  public int getCount(ProductStockPojo productStock) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productStock.getProductId() != null && !productStock.getProductId().equals("")) {
      map.put("productId", productStock.getProductId());
    }
    return productStockDao.getCount(map);
  }

  @Override
  public List<ProductStockPojo> getProductStockAll(ProductStockPojo productStock, Pager page)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (productStock.getProductId() != null && !productStock.getProductId().equals("")) {
      map.put("productId", productStock.getProductId());
    }
    if (page != null) {
      map.put("pageSize", page.getPageNo() * page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return productStockDao.getProductStockAll(map);
  }

  @Override
  public ProductStockPojo findProductStock(ProductStockPojo productStock) {
    return productStockDao.findProductStock(productStock);
  }

  @Override
  public ProductStockPojo findByProductStock(ProductStockPojo productStock) {
    return productStockDao.findByProductStock(productStock);
  }

  @Override
  public void addProductStock(ProductStockPojo productStock) {
    productStockDao.addProductStock(productStock);
  }

  @Override
  public void productStockUpdate(ProductStockPojo productStock) {
    productStockDao.productStockUpdate(productStock);
  }

  @Override
  public void deleProductStock(Long id) throws SQLException {
    productStockDao.deleProductStock(id);
  }

  @Override
  public void productStockDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        productStockDao.deleProductStock(Long.parseLong(tid));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkProductStock(Long id) throws SQLException {
    productStockDao.checkProductStock(id);
  }
}
