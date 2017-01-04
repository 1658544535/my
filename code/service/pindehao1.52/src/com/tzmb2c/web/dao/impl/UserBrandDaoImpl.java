package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserBrandDao;
import com.tzmb2c.web.mapper.UserBrandMapper;
import com.tzmb2c.web.pojo.UserBrandPojo;

public class UserBrandDaoImpl implements UserBrandDao {

  @Autowired
  private UserBrandMapper userBrandMapper;

  @Override
  public List<UserBrandPojo> findUserBrandList(Map<String, Object> map) throws SQLException {

    return userBrandMapper.findUserBrandList(map);
  }

  @Override
  public int findUserBrandCount(Map<String, Object> map) throws SQLException {

    return userBrandMapper.findUserBrandCount(map);
  }

  @Override
  public void checkUserBrand(Long id) throws SQLException {

    userBrandMapper.checkUserBrand(id);
  }

  @Override
  public void uncheckUserBrand(Long id) throws SQLException {

    userBrandMapper.uncheckUserBrand(id);
  }

  @Override
  public void delUserBrand(Long id) throws SQLException {

    userBrandMapper.delUserBrand(id);
  }

  @Override
  public void insertUserBrand(UserBrandPojo userBrandPojo) throws SQLException {

    userBrandMapper.insertUserBrand(userBrandPojo);
  }

  @Override
  public void updateUserBrand(UserBrandPojo userBrandPojo) throws SQLException {

    userBrandMapper.updateUserBrand(userBrandPojo);
  }

  @Override
  public List<UserBrandPojo> findUserBrandByUserId(Map<String, Object> map) throws SQLException {
    return userBrandMapper.findUserBrandByUserId(map);
  }

  @Override
  public UserBrandPojo findUserBrandById(Long id) throws SQLException {
    return userBrandMapper.findUserBrandById(id);
  }

  @Override
  public List<UserBrandPojo> findUserBrandNameByUserId(Map<String, Object> map) throws SQLException {
    return userBrandMapper.findUserBrandNameByUserId(map);
  }

  @Override
  public List<UserBrandPojo> findActiveBrandList(Map<String, Object> map) throws SQLException {
    return userBrandMapper.findActiveBrandList(map);
  }


}
