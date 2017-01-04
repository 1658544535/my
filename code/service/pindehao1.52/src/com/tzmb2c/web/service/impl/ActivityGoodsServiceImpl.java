package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityGoodsDao;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.service.ActivityGoodsService;

public class ActivityGoodsServiceImpl implements ActivityGoodsService {
  @Autowired
  private ActivityGoodsDao activityGoodsDao;

  @Override
  public List<ActivityGoodsPojo> findActivityGoodsList(Map<String, Object> map) throws SQLException {
    return activityGoodsDao.findActivityGoodsList(map);
  }

  @Override
  public int findActivityGoodsCount(Map<String, Object> map) throws SQLException {
    return activityGoodsDao.findActivityGoodsCount(map);
  }

  @Override
  public ActivityGoodsPojo findActivityGoodsById(Long id) throws SQLException {
    return activityGoodsDao.findActivityGoodsById(id);
  }

  @Override
  public Long insertActivityGoods(ActivityGoodsPojo activityGoods) throws SQLException {
    return activityGoodsDao.insertActivityGoods(activityGoods);
  }

  @Override
  public void delActivityGoods(Long id) throws SQLException {
    activityGoodsDao.delActivityGoods(id);
  }

  @Override
  public void updateActivityGoods(ActivityGoodsPojo activityGoods) throws SQLException {
    activityGoodsDao.updateActivityGoods(activityGoods);
  }

  @Override
  public void checkActivityGoods(Long id) throws SQLException {
    activityGoodsDao.checkActivityGoods(id);
  }

  @Override
  public void uncheckActivityGoods(Long id) throws SQLException {
    activityGoodsDao.uncheckActivityGoods(id);
  }

  @Override
  public ActivityGoodsPojo findActivityGoodsByPid(Long id, Long activityId, Integer stockStatus,
      Integer checkActive) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productId", id);
    map.put("activityId", activityId);
    if (stockStatus != null) {
      map.put("stockStatus", 1);
    }
    if (checkActive != null) {
      map.put("checkActive", 1);
    }
    return activityGoodsDao.findActivityGoodsByPid(map);
  }

  @Override
  public List<ActivityGoodsPojo> findActivityGoodsByProductId(Map<String, Object> map)
      throws SQLException {
    return activityGoodsDao.findActivityGoodsByProductId(map);
  }

  @Override
  public List<ActivityGoodsPojo> findWalletGoodsByProductId(Map<String, Object> map)
      throws SQLException {
    return activityGoodsDao.findWalletGoodsByProductId(map);
  }

  @Override
  public void delActivityGoodsByTimeId(Long id) throws SQLException {
    activityGoodsDao.delActivityGoodsByTimeId(id);
  }

  @Override
  public ActivityGoodsPojo getActivityGoodsTimeByPid(Long pid) throws SQLException {
    return activityGoodsDao.getActivityGoodsTimeByPid(pid);
  }

  @Override
  public ActivityGoodsPojo findActivityGoodsByPidTmp(Map<String, Object> map) throws SQLException {
    return activityGoodsDao.findActivityGoodsByPidTmp(map);
  }

  @Override
  public List<ActivityGoodsPojo> checkActivityGoodsByPid(Map<String, Object> map)
      throws SQLException {
    return activityGoodsDao.checkActivityGoodsByPid(map);
  }

  @Override
  public int checkActivityGoodsByPidCount(Map<String, Object> map) throws SQLException {
    return activityGoodsDao.checkActivityGoodsByPidCount(map);
  }

  @Override
  public int updateActivityGoodsStock(ActivityGoodsPojo activityGoods) throws SQLException {
    return activityGoodsDao.updateActivityGoodsStock(activityGoods);
  }

}
