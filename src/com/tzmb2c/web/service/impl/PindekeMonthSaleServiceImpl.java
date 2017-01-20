/*
 * 文 件 名: PindekeMonthSaleServiceImpl.java 创 建 人: admin 创建时间: 2017-01-10
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PindekeMonthSaleDao;
import com.tzmb2c.web.pojo.PindekeMonthSalePojo;
import com.tzmb2c.web.service.PindekeMonthSaleService;

/**
 * PindekeMonthSale Service层
 */
public class PindekeMonthSaleServiceImpl implements PindekeMonthSaleService {

  @Autowired
  private PindekeMonthSaleDao pindekeMonthSaledao;

  @Override
  public int add(PindekeMonthSalePojo pindekeMonthSale) throws SQLException {
    if (null == pindekeMonthSale) {
      return 0;
    }
    int rows = pindekeMonthSaledao.add(pindekeMonthSale);
    return rows;
  }

  @Override
  public synchronized int update(PindekeMonthSalePojo pindekeMonthSale) throws SQLException {
    if (null == pindekeMonthSale) {
      return 0;
    }
    int rows = pindekeMonthSaledao.update(pindekeMonthSale);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = pindekeMonthSaledao.delete(id);
    return rows;
  }

  @Override
  public PindekeMonthSalePojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    PindekeMonthSalePojo pindekeMonthSale = pindekeMonthSaledao.getById(id);
    return pindekeMonthSale;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = pindekeMonthSaledao.countBy(params);
    return rows;
  }

  @Override
  public List<PindekeMonthSalePojo> listPage(Map<String, Object> params) throws SQLException {
    List<PindekeMonthSalePojo> lists = pindekeMonthSaledao.listPage(params);
    return lists;
  }

  @Override
  public List<PindekeMonthSalePojo> listPage2(Map<String, Object> params) throws SQLException {
    List<PindekeMonthSalePojo> lists = pindekeMonthSaledao.listPage2(params);
    return lists;
  }

  @Override
  public Integer countBy2(Map<String, Object> params) throws SQLException {
    Integer rows = pindekeMonthSaledao.countBy2(params);
    return rows;
  }
}
