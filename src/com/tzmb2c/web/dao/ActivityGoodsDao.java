package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ActivityGoodsPojo;

public interface ActivityGoodsDao {
  List<ActivityGoodsPojo> findActivityGoodsList(Map<String, Object> map) throws SQLException;

  int findActivityGoodsCount(Map<String, Object> map) throws SQLException;

  ActivityGoodsPojo findActivityGoodsById(Long id) throws SQLException;

  ActivityGoodsPojo findActivityGoodsByPid(Map<String, Object> map) throws SQLException;

  ActivityGoodsPojo getActivityGoodsTimeByPid(Long pid) throws SQLException;

  ActivityGoodsPojo findActivityGoodsByPidTmp(Map<String, Object> map) throws SQLException;

  List<ActivityGoodsPojo> checkActivityGoodsByPid(Map<String, Object> map) throws SQLException;

  int checkActivityGoodsByPidCount(Map<String, Object> map) throws SQLException;

  Long insertActivityGoods(ActivityGoodsPojo activityGoods) throws SQLException;

  void delActivityGoods(Long id) throws SQLException;

  void updateActivityGoods(ActivityGoodsPojo activityGoods) throws SQLException;

  void checkActivityGoods(Long id) throws SQLException;

  void uncheckActivityGoods(Long id) throws SQLException;

  List<ActivityGoodsPojo> findActivityGoodsByProductId(Map<String, Object> map) throws SQLException;

  void delActivityGoodsByTimeId(Long id) throws SQLException;

  List<ActivityGoodsPojo> findWalletGoodsByProductId(Map<String, Object> map) throws SQLException;

  int updateActivityGoodsStock(ActivityGoodsPojo activityGoods) throws SQLException;
}
