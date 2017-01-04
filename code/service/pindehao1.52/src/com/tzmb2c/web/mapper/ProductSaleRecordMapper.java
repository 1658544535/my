package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductSaleRecordPojo;

public interface ProductSaleRecordMapper {

  public int productSaleRecordAllCount(Map<String, Object> map);

  public List<ProductSaleRecordPojo> productSaleRecordAllList(Map<String, Object> map);

  public void addProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException;

  public void delAllProductSaleRecordById(String id) throws SQLException;

  public void delProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException;

  public ProductSaleRecordPojo findProductSaleRecordById(Long id);

  public void updateProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo);

  public void checkAllProductSaleRecordById(String id) throws SQLException;

  public void checkProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException;

}
