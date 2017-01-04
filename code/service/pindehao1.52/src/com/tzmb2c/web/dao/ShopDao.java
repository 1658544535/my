package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ShopPojo;

public interface ShopDao {

  Long insertShop(ShopPojo shopPojo) throws SQLException;

  List<ShopPojo> getShopNew() throws SQLException;// 查询所有新品推荐的店铺 by wen

  int updateShop(ShopPojo shopPojo) throws SQLException;

  ShopPojo getfindByIdShop(Long id) throws SQLException;

  ShopPojo getfindByIdShop2(Long id) throws SQLException;

  ShopPojo findShop(ShopPojo shop);

  List<ShopPojo> findShopIndex(ShopPojo shopPojo) throws SQLException;

  int shopAllCount(Map<String, Object> map);

  List<ShopPojo> shopAllList(Map<String, Object> map);

  void checkShop(Long id) throws SQLException;

  void uncheckShop(Long id) throws SQLException;

  ShopPojo findShopById(Long id) throws SQLException;

  void delShop(Long id) throws SQLException;

  List<ShopPojo> getShopAll(Map<String, Object> map) throws SQLException;

  // 前端调用

  public ShopPojo getfindByIdShopWeb(Long id) throws SQLException;

  public void updateShopWeb(ShopPojo shopPojo) throws SQLException;

  // 前端商品搜索方法
  List<ShopPojo> shopAllListByName(Map<String, Object> map);

  public int shopAllCountStatus(Map<String, Object> map);

  void updateShopStatus(ShopPojo shopPojo) throws SQLException;

  public void updateShopInfo(Map<String, Object> map) throws SQLException;

  public ShopPojo selectShopInfo(Map<String, Object> map) throws SQLException;

  List<ShopPojo> FindshopAll() throws SQLException;

}
