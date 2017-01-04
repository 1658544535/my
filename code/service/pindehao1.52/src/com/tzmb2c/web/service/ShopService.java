package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ShopPojo;

public interface ShopService {

  Long insertShop(ShopPojo shopPojo) throws SQLException;

  List<ShopPojo> getShopNew() throws SQLException;// 查询所有新品推荐的店铺 By wen

  List<ShopPojo> findShopIndex(ShopPojo shopPojo) throws SQLException;// 前端首页 循环店铺图片

  public int updateShop(ShopPojo shopPojo) throws SQLException;

  public ShopPojo getfindByIdShop(Long id) throws SQLException;

  public ShopPojo getfindByIdShop2(Long id) throws SQLException;

  public ShopPojo findShop(ShopPojo shopDaoPojo);

  public int shopAllCount(ShopPojo shopDaoPojo);

  List<ShopPojo> shopAllList(ShopPojo ticketRulePojo, Pager page);

  void shopChecks(String[] tids);

  void checkShop(Long id) throws SQLException;

  void uncheckShop(Long id) throws SQLException;

  ShopPojo findShopById(Long merId) throws SQLException;

  void delShop(Long id) throws SQLException;

  List<ShopPojo> shopAllService(ShopPojo shopDaoPojo, Pager page) throws SQLException;

  // 前端调用
  public ShopPojo getfindByIdShopWeb(Long id) throws SQLException;

  public void updateShopWeb(ShopPojo shopPojo) throws SQLException;

  List<ShopPojo> shopAllListByName(ShopPojo shopDaoPojo, Pager page);

  public int shopAllCountStatus(ShopPojo shopDaoPojo);

  void updateShopStatus(ShopPojo shopPojo) throws SQLException;

  public void updateShopInfo(Map<String, Object> map) throws SQLException;

  public ShopPojo selectShopInfo(Map<String, Object> map) throws SQLException;

  List<ShopPojo> FindshopAll() throws SQLException;

  public int updateShopSelfSupport(Long shopId, boolean isSelf) throws SQLException;

}
