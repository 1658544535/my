/*
 * 文 件 名: ProductSellMapper.java 创 建 人: admin 创建时间: 2016-11-19
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ProductSellPojo;

public interface ProductSellMapper {

  ProductSellPojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  List<ProductSellPojo> listPage(Map<String, Object> params) throws SQLException;

  int insert(ProductSellPojo productSell) throws SQLException;

  int insertSeller(ProductSellPojo productSell) throws SQLException;

  int update(ProductSellPojo productSell) throws SQLException;

  int updateSeller(ProductSellPojo productSell) throws SQLException;

  int deleteById(Long id) throws SQLException;

  int updateDaySell(ProductSellPojo productSell) throws SQLException;
}
