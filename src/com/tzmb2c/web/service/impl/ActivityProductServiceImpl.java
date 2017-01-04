package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityProductDao;
import com.tzmb2c.web.pojo.ActivityProductPojo;
import com.tzmb2c.web.service.ActivityProductService;

public class ActivityProductServiceImpl implements ActivityProductService {
  @Autowired
  private ActivityProductDao activityProductDao;

  @Override
  public int getActivityProductCount(Map<String, Object> map) throws SQLException {
    return activityProductDao.getActivityProductCount(map);
  }

  @Override
  public List<ActivityProductPojo> getActivityProductList(Map<String, Object> map)
      throws SQLException {
    return activityProductDao.getActivityProductList(map);
  }

  @Override
  public List<ActivityProductPojo> getActivityProductListWeb(Map<String, Object> map)
      throws SQLException {
    return activityProductDao.getActivityProductListWeb(map);
  }

  @Override
  public void checkActivityProduct(Long id) throws SQLException {
    activityProductDao.checkActivityProduct(id);

  }

  @Override
  public void uncheckActivityProduct(Long id) throws SQLException {
    activityProductDao.uncheckActivityProduct(id);

  }

  @Override
  public void insertActivityProduct(ActivityProductPojo activityProductPojo) throws SQLException {
    activityProductDao.insertActivityProduct(activityProductPojo);

  }

  @Override
  public void delActivityProduct(Long id) throws SQLException {
    activityProductDao.delActivityProduct(id);

  }

  @Override
  public ActivityProductPojo getActivityProductById(Map<String, Object> map) throws SQLException {
    return activityProductDao.getActivityProductById(map);
  }

  @Override
  public void updateActivityProduct(ActivityProductPojo activityProductPojo) throws SQLException {
    activityProductDao.updateActivityProduct(activityProductPojo);

  }

  @Override
  public void delProductByTitleId(Map<String, Object> map) throws SQLException {
    activityProductDao.delProductByTitleId(map);
  }

  @Override
  public int getActivityPlaceCount(Map<String, Object> map) throws SQLException {
    return activityProductDao.getActivityPlaceCount(map);
  }

  @Override
  public List<ActivityProductPojo> getActivityPlaceList(Map<String, Object> map)
      throws SQLException {
    return activityProductDao.getActivityPlaceList(map);
  }

  @Override
  public List<ActivityProductPojo> findActivityGoods(Map<String, Object> map) throws SQLException {
    return activityProductDao.findActivityGoods(map);
  }

  @Override
  public int getActivitySetCount(Map<String, Object> map) throws SQLException {
    return activityProductDao.getActivitySetCount(map);
  }

  @Override
  public List<ActivityProductPojo> getActivitySetList(Map<String, Object> map) throws SQLException {
    return activityProductDao.getActivitySetList(map);
  }

}
