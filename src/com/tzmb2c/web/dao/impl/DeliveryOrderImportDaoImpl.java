/*
 * 文 件 名:  DeliveryOrderImportDaoImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-22
 */
package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.DeliveryOrderImportDao;
import com.tzmb2c.web.mapper.DeliveryOrderImportMapper;
import com.tzmb2c.web.pojo.DeliveryOrderImportPojo;

/**
 * DeliveryOrderImport Dao层
 */
public class DeliveryOrderImportDaoImpl implements DeliveryOrderImportDao {

  @Autowired
  private DeliveryOrderImportMapper deliveryOrderImportMapper;

  @Override
  public int add(DeliveryOrderImportPojo deliveryOrderImport) throws SQLException {
    if (null == deliveryOrderImport) {
      return 0;
    }
    int rows = deliveryOrderImportMapper.insert(deliveryOrderImport);
    return rows;
  }

  @Override
  public int update(DeliveryOrderImportPojo deliveryOrderImport) throws SQLException {
    if (null == deliveryOrderImport) {
      return 0;
    }
    int rows = deliveryOrderImportMapper.update(deliveryOrderImport);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = deliveryOrderImportMapper.deleteById(id);
    return rows;
  }

  @Override
  public DeliveryOrderImportPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    DeliveryOrderImportPojo deliveryOrderImport = deliveryOrderImportMapper.getById(id);
    return deliveryOrderImport;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = deliveryOrderImportMapper.countBy(params);
    return rows;
  }

  @Override
  public List<DeliveryOrderImportPojo> listPage(Map<String, Object> params) throws SQLException {
    List<DeliveryOrderImportPojo> lists = deliveryOrderImportMapper.listPage(params);
    return lists;
  }
}
