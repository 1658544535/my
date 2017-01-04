/*
 * 文 件 名:  DeliveryOrderImportServiceImpl.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-22
 */
package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.DeliveryOrderImportDao;
import com.tzmb2c.web.pojo.DeliveryOrderImportPojo;
import com.tzmb2c.web.service.DeliveryOrderImportService;

/**
 * DeliveryOrderImport Service层
 */
public class DeliveryOrderImportServiceImpl implements DeliveryOrderImportService {

  @Autowired
  private DeliveryOrderImportDao deliveryOrderImportdao;

  @Override
  public int add(DeliveryOrderImportPojo deliveryOrderImport) throws SQLException {
    if (null == deliveryOrderImport) {
      return 0;
    }
    int rows = deliveryOrderImportdao.add(deliveryOrderImport);
    return rows;
  }

  @Override
  public int update(DeliveryOrderImportPojo deliveryOrderImport) throws SQLException {
    if (null == deliveryOrderImport) {
      return 0;
    }
    int rows = deliveryOrderImportdao.update(deliveryOrderImport);
    return rows;
  }

  @Override
  public int delete(Long id) throws SQLException {
    if (null == id) {
      return 0;
    }
    int rows = deliveryOrderImportdao.delete(id);
    return rows;
  }

  @Override
  public DeliveryOrderImportPojo getById(Long id) throws SQLException {
    if (null == id) {
      return null;
    }
    DeliveryOrderImportPojo deliveryOrderImport = deliveryOrderImportdao.getById(id);
    return deliveryOrderImport;
  }

  @Override
  public Integer countBy(Map<String, Object> params) throws SQLException {
    Integer rows = deliveryOrderImportdao.countBy(params);
    return rows;
  }

  @Override
  public List<DeliveryOrderImportPojo> listPage(Map<String, Object> params) throws SQLException {
    List<DeliveryOrderImportPojo> lists = deliveryOrderImportdao.listPage(params);
    return lists;
  }
}
