package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ActivityProductPojo;

/**
 * @author EricChen
 */
public interface ActivityProductMapper {
  int getActivityProductCount(Map<String, Object> map) throws SQLException;

  List<ActivityProductPojo> getActivityProductList(Map<String, Object> map) throws SQLException;

  ActivityProductPojo getActivityProductById(Map<String, Object> map) throws SQLException;

  void checkActivityProduct(Long id) throws SQLException;

  void uncheckActivityProduct(Long id) throws SQLException;

  void updateActivityProduct(ActivityProductPojo activityProductPojo) throws SQLException;

  void insertActivityProduct(ActivityProductPojo activityProductPojo) throws SQLException;

  void delActivityProduct(Long id) throws SQLException;

  void delProductByTitleId(Map<String, Object> map) throws SQLException;

  List<ActivityProductPojo> getActivityProductListWeb(Map<String, Object> map);

  int getActivityPlaceCount(Map<String, Object> map) throws SQLException;

  List<ActivityProductPojo> getActivityPlaceList(Map<String, Object> map) throws SQLException;

  List<ActivityProductPojo> findActivityGoods(Map<String, Object> map) throws SQLException;

  int getActivitySetCount(Map<String, Object> map) throws SQLException;

  List<ActivityProductPojo> getActivitySetList(Map<String, Object> map) throws SQLException;

}
