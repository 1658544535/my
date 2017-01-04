package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SpecialProductPojo;

public interface SpecialProductService {
  List<SpecialProductPojo> findSpecialProductList(Map<String, Object> map) throws SQLException;

  int findSpecialProductCount(Map<String, Object> map) throws SQLException;

  SpecialProductPojo findSpecialProductById(Long id) throws SQLException;

  void insertSpecialProduct(SpecialProductPojo specialProduct) throws SQLException;

  void delSpecialProduct(Long id) throws SQLException;

  void delSpecialProductBySpecialId(Long specialId) throws SQLException;

  void updateSpecialProduct(SpecialProductPojo specialProduct) throws SQLException;

  void checkSpecialProductByActivityId(Long activityId) throws SQLException;

  void uncheckSpecialProductByActivityId(Long activityId) throws SQLException;
}
