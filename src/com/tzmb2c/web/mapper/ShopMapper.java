package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ShopPojo;

public interface ShopMapper {

  public Long insertShop(ShopPojo shopPojo) throws SQLException;// 新添店铺 by wen

  public List<ShopPojo> getShopNew() throws SQLException;

  public List<ShopPojo> getShopAll(Map<String, Object> map) throws SQLException;

  public int updateShop(ShopPojo shopPojo) throws SQLException;

  public ShopPojo getfindByIdShop(Long id) throws SQLException;

  public ShopPojo getfindByIdShop2(Long id) throws SQLException;

  public List<ShopPojo> findShopIndex(ShopPojo shop) throws SQLException;

  ShopPojo findShop(ShopPojo shop);

  public int shopAllCount(Map<String, Object> map);

  public List<ShopPojo> shopAllList(Map<String, Object> map);

  void checkShop(Long id) throws SQLException;

  void uncheckShop(Long id) throws SQLException;

  void delShop(Long id) throws SQLException;

  // 前端调用
  public ShopPojo getfindByIdShopWeb(Long id) throws SQLException;

  public void updateShopWeb(ShopPojo shopPojo) throws SQLException;

  public List<ShopPojo> shopAllListByName(Map<String, Object> map);

  public int shopAllCountStatus(Map<String, Object> map);

  public void updateShopStatus(ShopPojo shopPojo) throws SQLException;

  public void updateShopInfo(Map<String, Object> map) throws SQLException;

  public ShopPojo selectShopInfo(Map<String, Object> map) throws SQLException;

  public List<ShopPojo> FindshopAll() throws SQLException;


}
