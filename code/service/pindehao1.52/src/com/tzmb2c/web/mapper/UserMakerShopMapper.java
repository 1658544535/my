package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserMakerShopPojo;

public interface UserMakerShopMapper {
  public List<UserMakerShopPojo> findUserMakerShopList(Map<String, Object> map) throws SQLException;

  public List<UserMakerShopPojo> findMakerShopList(Map<String, Object> map) throws SQLException;

  public int findUserMakerShopCount(Map<String, Object> map) throws SQLException;

  public UserMakerShopPojo findUserMakerShopById(Long id) throws SQLException;

  public void checkUserMakerShop(Long id) throws SQLException;

  public void uncheckUserMakerShop(Long id) throws SQLException;

  public void insertUserMakerShop(UserMakerShopPojo userMakerShopPojo) throws SQLException;

  public void delUserMakerShop(Long id) throws SQLException;

  public UserMakerShopPojo findUserMakerShopByUid(Long id) throws SQLException;

  public void updateUserMakerShop(UserMakerShopPojo userMakerShopPojo) throws SQLException;
}
