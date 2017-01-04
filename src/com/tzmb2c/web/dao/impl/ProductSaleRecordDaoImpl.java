package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductSaleRecordDao;
import com.tzmb2c.web.mapper.ProductSaleRecordMapper;
import com.tzmb2c.web.pojo.ProductSaleRecordPojo;

public class ProductSaleRecordDaoImpl implements ProductSaleRecordDao {

  @Autowired
  private ProductSaleRecordMapper productSaleRecordMapper;

  @Override
  public int productSaleRecordAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productSaleRecordMapper.productSaleRecordAllCount(map);
  }

  @Override
  public List<ProductSaleRecordPojo> productSaleRecordAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return productSaleRecordMapper.productSaleRecordAllList(map);
  }

  @Override
  public void addProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordMapper.addProductSaleRecord(productSaleRecordPojo);
  }

  @Override
  public void delProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo) throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordMapper.delProductSaleRecord(productSaleRecordPojo);
  }

  @Override
  public void delAllProductSaleRecordById(String id) throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordMapper.delAllProductSaleRecordById(id);
  }

  @Override
  public ProductSaleRecordPojo findProductSaleRecordById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return productSaleRecordMapper.findProductSaleRecordById(id);
  }

  @Override
  public void updateProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordMapper.updateProductSaleRecord(productSaleRecordPojo);
  }

  @Override
  public void checkProductSaleRecord(ProductSaleRecordPojo productSaleRecordPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordMapper.checkProductSaleRecord(productSaleRecordPojo);
  }

  @Override
  public void checkAllProductSaleRecordById(String id) throws SQLException {
    // TODO Auto-generated method stub
    productSaleRecordMapper.checkAllProductSaleRecordById(id);
  }

}
