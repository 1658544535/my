/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductSellPojo;

/**
 * @version 1.0
 * @author
 */
public interface ProductSellService {

  public int add(ProductSellPojo productSell) throws SQLException;

  public int addSeller(ProductSellPojo productSell) throws SQLException;

  public int update(ProductSellPojo productSell) throws SQLException;

  public int delete(Long id) throws SQLException;

  public ProductSellPojo getById(Long id) throws SQLException;

  public Integer countBy(Map<String, Object> params) throws SQLException;

  public List<ProductSellPojo> listPage(Map<String, Object> params) throws SQLException;

  public int updateDaySell(ProductSellPojo productSell) throws SQLException;

}
