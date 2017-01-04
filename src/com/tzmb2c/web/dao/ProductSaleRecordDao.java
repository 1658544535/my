package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductSaleRecordPojo;

public interface ProductSaleRecordDao {

  public int productSaleRecordAllCount(Map<String, Object> map);

  public List<ProductSaleRecordPojo> productSaleRecordAllList(Map<String, Object> map);

  public void addProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException;

  public void delProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException;

  public void delAllProductSaleRecordById(String id) throws SQLException;

  public ProductSaleRecordPojo findProductSaleRecordById(Long id) throws SQLException;

  public void updateProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException;

  public void checkProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException;

  public void checkAllProductSaleRecordById(String id) throws SQLException;

}
