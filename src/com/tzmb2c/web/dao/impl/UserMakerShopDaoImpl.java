package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserMakerShopDao;
import com.tzmb2c.web.mapper.UserMakerShopMapper;
import com.tzmb2c.web.pojo.UserMakerShopPojo;

public class UserMakerShopDaoImpl implements UserMakerShopDao {
  @Autowired
  private UserMakerShopMapper userMakerShopMapper;

  @Override
  public List<UserMakerShopPojo> findUserMakerShopList(Map<String, Object> map) throws SQLException {
    return userMakerShopMapper.findUserMakerShopList(map);
  }

  @Override
  public int findUserMakerShopCount(Map<String, Object> map) throws SQLException {
    return userMakerShopMapper.findUserMakerShopCount(map);
  }

  @Override
  public UserMakerShopPojo findUserMakerShopById(Long id) throws SQLException {
    return userMakerShopMapper.findUserMakerShopById(id);
  }

  @Override
  public void checkUserMakerShop(Long id) throws SQLException {
    userMakerShopMapper.checkUserMakerShop(id);
  }

  @Override
  public void uncheckUserMakerShop(Long id) throws SQLException {
    userMakerShopMapper.uncheckUserMakerShop(id);
  }

  @Override
  public void insertUserMakerShop(UserMakerShopPojo userMakerShopPojo) throws SQLException {
    userMakerShopMapper.insertUserMakerShop(userMakerShopPojo);
  }

  @Override
  public void delUserMakerShop(Long id) throws SQLException {
    userMakerShopMapper.delUserMakerShop(id);
  }

  @Override
  public List<UserMakerShopPojo> findMakerShopList(Map<String, Object> map) throws SQLException {
    return userMakerShopMapper.findMakerShopList(map);
  }

  @Override
  public UserMakerShopPojo findUserMakerShopByUid(Long id) throws SQLException {
    return userMakerShopMapper.findUserMakerShopByUid(id);
  }

  @Override
  public void updateUserMakerShop(UserMakerShopPojo userMakerShopPojo) throws SQLException {
    userMakerShopMapper.updateUserMakerShop(userMakerShopPojo);
    
  }
}
