package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ProductSaleRecordPojo;

public interface ProductSaleRecordService {

  public int productSaleRecordAllCount(ProductSaleRecordPojo productSaleRecordPojo);

  public List<ProductSaleRecordPojo> productSaleRecordAllList(
      ProductSaleRecordPojo productSaleRecordPojo, Pager page);

  public void addProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException;

  public void delProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException;

  public void delAllProductSaleRecordById(String[] tids);

  public ProductSaleRecordPojo findProductSaleRecordById(Long id) throws SQLException;

  public void updateProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException;

  public void checkProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException;

  public void checkAllProductSaleRecordById(String[] tids);

}
