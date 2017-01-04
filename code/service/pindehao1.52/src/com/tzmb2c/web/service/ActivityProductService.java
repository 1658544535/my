package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ActivityProductPojo;

/**
 * @author EricChen
 */
public interface ActivityProductService {
  public int getActivityProductCount(Map<String, Object> map) throws SQLException;

  public List<ActivityProductPojo> getActivityProductList(Map<String, Object> map)
      throws SQLException;

  public ActivityProductPojo getActivityProductById(Map<String, Object> map) throws SQLException;

  public void updateActivityProduct(ActivityProductPojo activityProductPojo) throws SQLException;

  public void checkActivityProduct(Long id) throws SQLException;

  public void uncheckActivityProduct(Long id) throws SQLException;

  public void insertActivityProduct(ActivityProductPojo activityProductPojo) throws SQLException;

  public void delActivityProduct(Long id) throws SQLException;

  public void delProductByTitleId(Map<String, Object> map) throws SQLException;

  List<ActivityProductPojo> getActivityProductListWeb(Map<String, Object> map) throws SQLException;

  public List<ActivityProductPojo> getActivityPlaceList(Map<String, Object> map)
      throws SQLException;

  public int getActivityPlaceCount(Map<String, Object> map) throws SQLException;

  public List<ActivityProductPojo> findActivityGoods(Map<String, Object> map) throws SQLException;

  int getActivitySetCount(Map<String, Object> map) throws SQLException;

  List<ActivityProductPojo> getActivitySetList(Map<String, Object> map) throws SQLException;

}
