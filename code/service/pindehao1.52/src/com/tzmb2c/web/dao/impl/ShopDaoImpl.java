package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ShopDao;
import com.tzmb2c.web.mapper.ShopMapper;
import com.tzmb2c.web.pojo.ShopPojo;

public class ShopDaoImpl implements ShopDao {

  @Autowired
  private ShopMapper shopMapper;

  // 查询所有新品推荐的店铺 by wen
  @Override
  public List<ShopPojo> getShopNew() throws SQLException {
    return shopMapper.getShopNew();
  }

  @Override
  public List<ShopPojo> findShopIndex(ShopPojo shopPojo) throws SQLException {
    return shopMapper.findShopIndex(shopPojo);
  }

  @Override
  public List<ShopPojo> getShopAll(Map<String, Object> map) throws SQLException {
    return shopMapper.getShopAll(map);
  }

  @Override
  public List<ShopPojo> FindshopAll() throws SQLException {
    return shopMapper.FindshopAll();
  }

  @Override
  public Long insertShop(ShopPojo shopPojo) throws SQLException {

    return shopMapper.insertShop(shopPojo);
  }

  @Override
  public int updateShop(ShopPojo shopPojo) throws SQLException {
    return shopMapper.updateShop(shopPojo);
  }

  @Override
  public void updateShopStatus(ShopPojo shopPojo) throws SQLException {

    shopMapper.updateShopStatus(shopPojo);
  }

  @Override
  public ShopPojo getfindByIdShop(Long id) throws SQLException {
    return shopMapper.getfindByIdShop(id);

  }

  @Override
  public int shopAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return shopMapper.shopAllCount(map);
  }

  @Override
  public List<ShopPojo> shopAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return shopMapper.shopAllList(map);
  }

  @Override
  public List<ShopPojo> shopAllListByName(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return shopMapper.shopAllListByName(map);
  }

  @Override
  public void checkShop(Long id) throws SQLException {

    shopMapper.checkShop(id);
  }

  @Override
  public ShopPojo findShopById(Long id) throws SQLException {
    return shopMapper.getfindByIdShop(id);
  }

  @Override
  public void delShop(Long id) throws SQLException {
    shopMapper.delShop(id);

  }

  @Override
  public ShopPojo findShop(ShopPojo shop) {
    return shopMapper.findShop(shop);
  }

  @Override
  public ShopPojo getfindByIdShopWeb(Long id) throws SQLException {
    return shopMapper.getfindByIdShopWeb(id);
  }

  @Override
  public void updateShopWeb(ShopPojo shopPojo) throws SQLException {
    shopMapper.updateShopWeb(shopPojo);
  }

  @Override
  public int shopAllCountStatus(Map<String, Object> map) {
    return shopMapper.shopAllCountStatus(map);
  }

  @Override
  public void updateShopInfo(Map<String, Object> map) throws SQLException {
    shopMapper.updateShopInfo(map);
  }

  @Override
  public ShopPojo selectShopInfo(Map<String, Object> map) throws SQLException {
    return shopMapper.selectShopInfo(map);
  }

  @Override
  public ShopPojo getfindByIdShop2(Long id) throws SQLException {
    return shopMapper.getfindByIdShop2(id);
  }

  @Override
  public void uncheckShop(Long id) throws SQLException {
    shopMapper.uncheckShop(id);
  }
}
