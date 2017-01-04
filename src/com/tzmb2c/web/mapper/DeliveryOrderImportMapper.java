/*
 * 文 件 名:  DeliveryOrderImportMapper.java
 * 创 建 人:  admin
 * 创建时间:  2016-09-22
 */
package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.DeliveryOrderImportPojo;

public interface DeliveryOrderImportMapper {

  DeliveryOrderImportPojo getById(Long id) throws SQLException;

  int countBy(Map<String, Object> params) throws SQLException;

  List<DeliveryOrderImportPojo> listPage(Map<String, Object> params) throws SQLException;

  int insert(DeliveryOrderImportPojo deliveryOrderImport) throws SQLException;

  int update(DeliveryOrderImportPojo deliveryOrderImport) throws SQLException;

  int deleteById(Long id) throws SQLException;
}
