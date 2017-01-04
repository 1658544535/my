package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ActivityGoodsDao;
import com.tzmb2c.web.mapper.ActivityGoodsMapper;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;

public class ActivityGoodsDaoImpl implements ActivityGoodsDao {
  @Autowired
  private ActivityGoodsMapper activityGoodsMapper;

  @Override
  public List<ActivityGoodsPojo> findActivityGoodsList(Map<String, Object> map) throws SQLException {
    return activityGoodsMapper.findActivityGoodsList(map);
  }

  @Override
  public int findActivityGoodsCount(Map<String, Object> map) throws SQLException {
    return activityGoodsMapper.findActivityGoodsCount(map);
  }

  @Override
  public ActivityGoodsPojo findActivityGoodsById(Long id) throws SQLException {
    return activityGoodsMapper.findActivityGoodsById(id);
  }

  @Override
  public Long insertActivityGoods(ActivityGoodsPojo activityGoods) throws SQLException {
    return activityGoodsMapper.insertActivityGoods(activityGoods);
  }

  @Override
  public void delActivityGoods(Long id) throws SQLException {
    activityGoodsMapper.delActivityGoods(id);
  }

  @Override
  public void updateActivityGoods(ActivityGoodsPojo activityGoods) throws SQLException {
    activityGoodsMapper.updateActivityGoods(activityGoods);
  }

  @Override
  public void checkActivityGoods(Long id) throws SQLException {
    activityGoodsMapper.checkActivityGoods(id);
  }

  @Override
  public void uncheckActivityGoods(Long id) throws SQLException {
    activityGoodsMapper.uncheckActivityGoods(id);
  }

  @Override
  public ActivityGoodsPojo findActivityGoodsByPid(Map<String, Object> map) throws SQLException {
    return activityGoodsMapper.findActivityGoodsByPid(map);
  }

  @Override
  public List<ActivityGoodsPojo> findActivityGoodsByProductId(Map<String, Object> map)
      throws SQLException {
    return activityGoodsMapper.findActivityGoodsByProductId(map);
  }

  @Override
  public List<ActivityGoodsPojo> findWalletGoodsByProductId(Map<String, Object> map)
      throws SQLException {
    return activityGoodsMapper.findWalletGoodsByProductId(map);
  }

  @Override
  public void delActivityGoodsByTimeId(Long id) throws SQLException {
    activityGoodsMapper.delActivityGoodsByTimeId(id);
  }

  @Override
  public ActivityGoodsPojo getActivityGoodsTimeByPid(Long pid) throws SQLException {
    return activityGoodsMapper.getActivityGoodsTimeByPid(pid);
  }

  @Override
  public ActivityGoodsPojo findActivityGoodsByPidTmp(Map<String, Object> map) throws SQLException {
    return activityGoodsMapper.findActivityGoodsByPidTmp(map);
  }

  @Override
  public List<ActivityGoodsPojo> checkActivityGoodsByPid(Map<String, Object> map)
      throws SQLException {
    return activityGoodsMapper.checkActivityGoodsByPid(map);
  }

  @Override
  public int checkActivityGoodsByPidCount(Map<String, Object> map) throws SQLException {
    return activityGoodsMapper.checkActivityGoodsByPidCount(map);
  }

  @Override
  public int updateActivityGoodsStock(ActivityGoodsPojo activityGoods) throws SQLException {
    return activityGoodsMapper.updateActivityGoodsStock(activityGoods);
  }
}
