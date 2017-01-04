package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ShopDao;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.service.ShopService;

public class ShopServiceImpl implements ShopService {
  @Autowired
  private ShopDao shopDao;

  public void setShopDao(ShopDao shopDao) {
    this.shopDao = shopDao;
  }

  @Override
  public List<ShopPojo> getShopNew() throws SQLException {
    return shopDao.getShopNew();
  }

  @Override
  public List<ShopPojo> findShopIndex(ShopPojo shopPojo) throws SQLException {
    return shopDao.findShopIndex(shopPojo);
  }

  @Override
  public List<ShopPojo> shopAllService(ShopPojo shopDaoPojo, Pager page) throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (shopDaoPojo != null) {
      map.put("mainCategory", shopDaoPojo.getMainCategory());
      map.put("name", shopDaoPojo.getName());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return shopDao.getShopAll(map);
  }

  @Override
  public List<ShopPojo> FindshopAll() throws SQLException {

    return shopDao.FindshopAll();
  }

  @Override
  public Long insertShop(ShopPojo shopPojo) throws SQLException {
    return shopDao.insertShop(shopPojo);

  }

  @Override
  public int updateShop(ShopPojo shopPojo) throws SQLException {
    return shopDao.updateShop(shopPojo);
  }

  @Override
  public void updateShopStatus(ShopPojo shopPojo) throws SQLException {
    shopDao.updateShopStatus(shopPojo);

  }

  @Override
  public ShopPojo getfindByIdShop(Long id) throws SQLException {
    return shopDao.getfindByIdShop(id);

  }

  @Override
  public int shopAllCountStatus(ShopPojo shopDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (shopDaoPojo != null) {
      map.put("userName", shopDaoPojo.getUserName());
      map.put("address", shopDaoPojo.getAddress());
      map.put("isNew", shopDaoPojo.getIsNew());
      map.put("mainCategory", shopDaoPojo.getMainCategory());
      map.put("salesArea", shopDaoPojo.getSalesArea());
      map.put("name", shopDaoPojo.getName());
    }
    return shopDao.shopAllCountStatus(map);
  }

  @Override
  public int shopAllCount(ShopPojo shopDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (shopDaoPojo != null) {
      map.put("userName", shopDaoPojo.getUserName());
      map.put("address", shopDaoPojo.getAddress());
      map.put("isNew", shopDaoPojo.getIsNew());
      map.put("mainCategory", shopDaoPojo.getMainCategory());
      map.put("salesArea", shopDaoPojo.getSalesArea());
      map.put("name", shopDaoPojo.getName());
    }
    return shopDao.shopAllCount(map);
  }

  @Override
  public List<ShopPojo> shopAllList(ShopPojo shopDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (shopDaoPojo != null) {
      map.put("userName", shopDaoPojo.getUserName());
      map.put("address", shopDaoPojo.getAddress());
      map.put("isNew", shopDaoPojo.getIsNew());
      map.put("mainCategory", shopDaoPojo.getMainCategory());
      map.put("salesArea", shopDaoPojo.getSalesArea());
      map.put("name", shopDaoPojo.getName());
      map.put("status", shopDaoPojo.getStatus());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ShopPojo> list = shopDao.shopAllList(map);

    return list;
  }

  // 前端“店铺”搜索
  @Override
  public List<ShopPojo> shopAllListByName(ShopPojo shopDaoPojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (shopDaoPojo != null) {
      map.put("userName", shopDaoPojo.getUserName());
      map.put("address", shopDaoPojo.getAddress());
      map.put("isNew", shopDaoPojo.getIsNew());
      map.put("mainCategory", shopDaoPojo.getMainCategory());
      map.put("salesArea", shopDaoPojo.getSalesArea());
      map.put("name", shopDaoPojo.getName());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<ShopPojo> list = shopDao.shopAllListByName(map);

    return list;
  }

  @Override
  public void shopChecks(String[] tids) {
    for (String tid : tids) {
      try {
        shopDao.checkShop(Long.parseLong(tid));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void checkShop(Long id) throws SQLException {
    shopDao.checkShop(id);
  }

  @Override
  public void delShop(Long id) throws SQLException {
    shopDao.delShop(id);
  }

  @Override
  public ShopPojo findShopById(Long id) throws SQLException {

    return shopDao.findShopById(id);

  }

  @Override
  public ShopPojo findShop(ShopPojo shop) {
    return shopDao.findShop(shop);
  }

  @Override
  public ShopPojo getfindByIdShopWeb(Long id) throws SQLException {
    return shopDao.getfindByIdShopWeb(id);
  }

  @Override
  public void updateShopWeb(ShopPojo shopPojo) throws SQLException {
    shopDao.updateShopWeb(shopPojo);
  }

  @Override
  public void updateShopInfo(Map<String, Object> map) throws SQLException {
    shopDao.updateShopInfo(map);
  }

  @Override
  public ShopPojo selectShopInfo(Map<String, Object> map) throws SQLException {
    return shopDao.selectShopInfo(map);
  }

  @Override
  public ShopPojo getfindByIdShop2(Long id) throws SQLException {
    return shopDao.getfindByIdShop2(id);
  }

  @Override
  public void uncheckShop(Long id) throws SQLException {
    shopDao.uncheckShop(id);
  }

  @Override
  public int updateShopSelfSupport(Long shopId, boolean isSelf) throws SQLException {
    if (shopId != null && shopId == 0) {
      ShopPojo shopPojo = new ShopPojo();
      shopPojo.setId(shopId);
      if (isSelf) {
        shopPojo.setSelfSupport(1);
      } else {
        shopPojo.setSelfSupport(0);
      }
      return shopDao.updateShop(shopPojo);
    }
    return 0;
  }
}
