package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductStockPojo;

/**
 * @author EricChen
 */
public interface ProductStockService {

  public int getCount(ProductStockPojo productStock);

  public List<ProductStockPojo> getProductStockAll(ProductStockPojo productStock, Pager page)
      throws SQLException;

  public ProductStockPojo findProductStock(ProductStockPojo productStock);

  public ProductStockPojo findByProductStock(ProductStockPojo productStock);

  public void addProductStock(ProductStockPojo productStock);

  public void productStockUpdate(ProductStockPojo productStock);

  public void deleProductStock(Long id) throws SQLException;

  public void productStockDeleteId(String[] tids);

  public void checkProductStock(Long id) throws SQLException;
}
