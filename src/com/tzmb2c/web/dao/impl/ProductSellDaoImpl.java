/*
 * 文 件 名: ProductSellDaoImpl.java 创 建 人: admin 创建时间: 2016-11-19
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductSellDao;
import com.tzmb2c.web.mapper.ProductSellMapper;
import com.tzmb2c.web.pojo.ProductSellPojo;

/**
 * ProductSell Dao层
 */
public class ProductSellDaoImpl implements ProductSellDao {

  @Autowired
  private ProductSellMapper productSellMapper;

  @Override
  public int add(ProductSellPojo productSell) throws SQLException {
    if (null == productSell) {
      return 0;
    }
    int rows = productSellMapper.insert(productSell);
    return rows;
  }

  @Override
  public int update(ProductSellPojo productSell) throws SQLException {
    if (null == productSell) {
      return 0;
    }
    int rows = productSellMapper.update(productSell);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = productSellMapper.deleteById(id);
    return rows;
  }

  @Override
  public ProductSellPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    ProductSellPojo productSell = productSellMapper.getById(id);
    return productSell;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = productSellMapper.countBy(params);
    return rows;
  }

  @Override
  public List<ProductSellPojo> listPage(Map<String, Object> params) throws SQLException {
    List<ProductSellPojo> lists = productSellMapper.listPage(params);
    return lists;
  }

  @Override
  public int updateDaySell(ProductSellPojo productSell) throws SQLException {
    if (null == productSell) {
      return 0;
    }
    int rows = productSellMapper.updateDaySell(productSell);
    return rows;
  }
}
