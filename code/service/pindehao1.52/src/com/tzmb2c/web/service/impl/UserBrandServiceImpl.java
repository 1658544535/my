package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserBrandDao;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.service.UserBrandService;

public class UserBrandServiceImpl implements UserBrandService {

  @Autowired
  private UserBrandDao userBrandDao;

  @Override
  public List<UserBrandPojo> findUserBrandList(Map<String, Object> map) throws SQLException {

    return userBrandDao.findUserBrandList(map);
  }

  @Override
  public int findUserBrandCount(Map<String, Object> map) throws SQLException {

    return userBrandDao.findUserBrandCount(map);
  }

  @Override
  public void checkUserBrand(Long id) throws SQLException {

    userBrandDao.checkUserBrand(id);
  }

  @Override
  public void uncheckUserBrand(Long id) throws SQLException {

    userBrandDao.uncheckUserBrand(id);
  }

  @Override
  public void delUserBrand(Long id) throws SQLException {

    userBrandDao.delUserBrand(id);
  }

  @Override
  public void insertUserBrand(UserBrandPojo userBrandPojo) throws SQLException {

    userBrandDao.insertUserBrand(userBrandPojo);
  }

  @Override
  public void updateUserBrand(UserBrandPojo userBrandPojo) throws SQLException {

    userBrandDao.updateUserBrand(userBrandPojo);
  }

  @Override
  public List<UserBrandPojo> findUserBrandByUserId(Map<String, Object> map) throws SQLException {

    return userBrandDao.findUserBrandByUserId(map);
  }

  @Override
  public UserBrandPojo findUserBrandById(Long id) throws SQLException {

    return userBrandDao.findUserBrandById(id);
  }

  @Override
  public List<UserBrandPojo> findUserBrandNameByUserId(Map<String, Object> map) throws SQLException {
    return userBrandDao.findUserBrandNameByUserId(map);
  }

  @Override
  public List<UserBrandPojo> findActiveBrandList(Map<String, Object> map) throws SQLException {
    return userBrandDao.findActiveBrandList(map);
  }
}
