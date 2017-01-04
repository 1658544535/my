package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductStockPojo;

/**
 * @author EricChen
 */
public interface ProductStockDao {

  int getCount(Map<String, Object> map);

  List<ProductStockPojo> getProductStockAll(Map<String, Object> map);

  ProductStockPojo findProductStock(ProductStockPojo productStock);

  ProductStockPojo findByProductStock(ProductStockPojo productStock);

  void addProductStock(ProductStockPojo productStock);

  void productStockUpdate(ProductStockPojo productStock);

  public void deleProductStock(Long id) throws SQLException;

  void checkProductStock(Long id) throws SQLException;
}
