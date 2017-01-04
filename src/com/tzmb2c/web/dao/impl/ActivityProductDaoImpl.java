package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityProductDao;
import com.tzmb2c.web.mapper.ActivityProductMapper;
import com.tzmb2c.web.pojo.ActivityProductPojo;

/**
 * @author EricChen
 */
public class ActivityProductDaoImpl implements ActivityProductDao {

  @Autowired
  private ActivityProductMapper activityProductMapper;

  @Override
  public int getActivityProductCount(Map<String, Object> map) throws SQLException {
    return activityProductMapper.getActivityProductCount(map);
  }

  @Override
  public List<ActivityProductPojo> getActivityProductList(Map<String, Object> map)
      throws SQLException {
    return activityProductMapper.getActivityProductList(map);
  }

  @Override
  public List<ActivityProductPojo> getActivityProductListWeb(Map<String, Object> map)
      throws SQLException {
    return activityProductMapper.getActivityProductListWeb(map);
  }

  @Override
  public void checkActivityProduct(Long id) throws SQLException {
    activityProductMapper.checkActivityProduct(id);
  }

  @Override
  public void uncheckActivityProduct(Long id) throws SQLException {
    activityProductMapper.uncheckActivityProduct(id);
  }

  @Override
  public void insertActivityProduct(ActivityProductPojo activityProductPojo) throws SQLException {
    activityProductMapper.insertActivityProduct(activityProductPojo);

  }

  @Override
  public void delActivityProduct(Long id) throws SQLException {
    activityProductMapper.delActivityProduct(id);

  }

  @Override
  public ActivityProductPojo getActivityProductById(Map<String, Object> map) throws SQLException {
    return activityProductMapper.getActivityProductById(map);
  }

  @Override
  public void updateActivityProduct(ActivityProductPojo activityProductPojo) throws SQLException {
    activityProductMapper.updateActivityProduct(activityProductPojo);

  }

  @Override
  public void delProductByTitleId(Map<String, Object> map) throws SQLException {
    activityProductMapper.delProductByTitleId(map);
  }

  @Override
  public int getActivityPlaceCount(Map<String, Object> map) throws SQLException {
    return activityProductMapper.getActivityPlaceCount(map);
  }

  @Override
  public List<ActivityProductPojo> getActivityPlaceList(Map<String, Object> map)
      throws SQLException {
    return activityProductMapper.getActivityPlaceList(map);
  }

  @Override
  public List<ActivityProductPojo> findActivityGoods(Map<String, Object> map) throws SQLException {
    return activityProductMapper.findActivityGoods(map);
  }

  @Override
  public int getActivitySetCount(Map<String, Object> map) throws SQLException {
    return activityProductMapper.getActivitySetCount(map);
  }

  @Override
  public List<ActivityProductPojo> getActivitySetList(Map<String, Object> map) throws SQLException {
    return activityProductMapper.getActivitySetList(map);
  }


}
