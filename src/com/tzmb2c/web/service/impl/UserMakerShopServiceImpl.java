package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserMakerShopDao;
import com.tzmb2c.web.pojo.UserMakerShopPojo;
import com.tzmb2c.web.service.UserMakerShopService;

public class UserMakerShopServiceImpl implements UserMakerShopService {
  @Autowired
  private UserMakerShopDao userMakerShopDao;

  @Override
  public List<UserMakerShopPojo> findUserMakerShopList(Map<String, Object> map) throws SQLException {
    return userMakerShopDao.findUserMakerShopList(map);
  }

  @Override
  public int findUserMakerShopCount(Map<String, Object> map) throws SQLException {
    return userMakerShopDao.findUserMakerShopCount(map);
  }

  @Override
  public UserMakerShopPojo findUserMakerShopById(Long id) throws SQLException {
    return userMakerShopDao.findUserMakerShopById(id);
  }

  @Override
  public void checkUserMakerShop(Long id) throws SQLException {
    userMakerShopDao.checkUserMakerShop(id);
  }

  @Override
  public void uncheckUserMakerShop(Long id) throws SQLException {
    userMakerShopDao.uncheckUserMakerShop(id);
  }

  @Override
  public void insertUserMakerShop(UserMakerShopPojo userMakerShopPojo) throws SQLException {
    userMakerShopDao.insertUserMakerShop(userMakerShopPojo);
  }

  @Override
  public void delUserMakerShop(Long id) throws SQLException {
    userMakerShopDao.delUserMakerShop(id);
  }

  @Override
  public List<UserMakerShopPojo> findMakerShopList(Map<String, Object> map) throws SQLException {
    return userMakerShopDao.findMakerShopList(map);
  }

  @Override
  public UserMakerShopPojo findUserMakerShopByUid(Long id) throws SQLException {
    return userMakerShopDao.findUserMakerShopByUid(id);
  }

  @Override
  public void updateUserMakerShop(UserMakerShopPojo userMakerShopPojo) throws SQLException {
    userMakerShopDao.updateUserMakerShop(userMakerShopPojo);
    
  }
}
