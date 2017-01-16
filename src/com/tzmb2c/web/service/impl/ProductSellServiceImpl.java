/*
 * 文 件 名: ProductSellServiceImpl.java 创 建 人: admin 创建时间: 2016-11-19
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ProductSellDao;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.service.ProductSellService;

/**
 * ProductSell Service层
 */
public class ProductSellServiceImpl implements ProductSellService {

  @Autowired
  private ProductSellDao productSelldao;

  @Override
  public int add(ProductSellPojo productSell) throws SQLException {
    if (null == productSell) {
      return 0;
    }
    int rows = productSelldao.add(productSell);
    return rows;
  }

  @Override
  public int update(ProductSellPojo productSell) throws SQLException {
    if (null == productSell) {
      return 0;
    }
    int rows = productSelldao.update(productSell);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = productSelldao.delete(id);
    return rows;
  }

  @Override
  public ProductSellPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    ProductSellPojo productSell = productSelldao.getById(id);
    return productSell;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = productSelldao.countBy(params);
    return rows;
  }

  @Override
  public List<ProductSellPojo> listPage(Map<String, Object> params) throws SQLException {
    List<ProductSellPojo> lists = productSelldao.listPage(params);
    return lists;
  }

  @Override
  public int updateDaySell(ProductSellPojo productSell) throws SQLException {
    if (null == productSell) {
      return 0;
    }
    int rows = productSelldao.updateDaySell(productSell);
    return rows;
  }

  @Override
  public int addSeller(ProductSellPojo productSell) throws SQLException {
    if (null == productSell) {
      return 0;
    }
    int rows = productSelldao.addSeller(productSell);
    return rows;
  }

  @Override
  public int updateSeller(ProductSellPojo productSell) throws SQLException {
    if (null == productSell) {
      return 0;
    }
    int rows = productSelldao.updateSeller(productSell);
    return rows;
  }
}
