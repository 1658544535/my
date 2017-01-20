/*
 * 文 件 名: PindekeMonthSaleMapper.java 创 建 人: admin 创建时间: 2017-01-10
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PindekeMonthSalePojo;

public interface PindekeMonthSaleMapper {

  PindekeMonthSalePojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  int countBy2(Map<String, Object> params) throws SQLException;

  List<PindekeMonthSalePojo> listPage(Map<String, Object> params) throws SQLException;

  List<PindekeMonthSalePojo> listPage2(Map<String, Object> params) throws SQLException;

  int insert(PindekeMonthSalePojo pindekeMonthSale) throws SQLException;

  int update(PindekeMonthSalePojo pindekeMonthSale) throws SQLException;

  int deleteById(Long id) throws SQLException;
}
