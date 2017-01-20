/*
 * 文 件 名: PindekeMonthSaleDaoImpl.java 创 建 人: admin 创建时间: 2017-01-10
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PindekeMonthSaleDao;
import com.tzmb2c.web.mapper.PindekeMonthSaleMapper;
import com.tzmb2c.web.pojo.PindekeMonthSalePojo;

/**
 * PindekeMonthSale Dao层
 */
public class PindekeMonthSaleDaoImpl implements PindekeMonthSaleDao {

  @Autowired
  private PindekeMonthSaleMapper pindekeMonthSaleMapper;

  @Override
  public int add(PindekeMonthSalePojo pindekeMonthSale) throws SQLException {
    if (null == pindekeMonthSale) {
      return 0;
    }
    int rows = pindekeMonthSaleMapper.insert(pindekeMonthSale);
    return rows;
  }

  @Override
  public int update(PindekeMonthSalePojo pindekeMonthSale) throws SQLException {
    if (null == pindekeMonthSale) {
      return 0;
    }
    int rows = pindekeMonthSaleMapper.update(pindekeMonthSale);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = pindekeMonthSaleMapper.deleteById(id);
    return rows;
  }

  @Override
  public PindekeMonthSalePojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    PindekeMonthSalePojo pindekeMonthSale = pindekeMonthSaleMapper.getById(id);
    return pindekeMonthSale;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = pindekeMonthSaleMapper.countBy(params);
    return rows;
  }

  @Override
  public List<PindekeMonthSalePojo> listPage(Map<String, Object> params) throws SQLException {
    List<PindekeMonthSalePojo> lists = pindekeMonthSaleMapper.listPage(params);
    return lists;
  }

  @Override
  public List<PindekeMonthSalePojo> listPage2(Map<String, Object> params) throws SQLException {
    List<PindekeMonthSalePojo> lists = pindekeMonthSaleMapper.listPage2(params);
    return lists;
  }

  @Override
  public Integer countBy2(Map<String, Object> params) throws SQLException {
    Integer rows = pindekeMonthSaleMapper.countBy2(params);
    return rows;
  }
}
