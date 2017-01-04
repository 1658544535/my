package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserBrandPojo;

public interface UserBrandService {
  public List<UserBrandPojo> findUserBrandList(Map<String, Object> map) throws SQLException;

  public List<UserBrandPojo> findActiveBrandList(Map<String, Object> map) throws SQLException;

  public List<UserBrandPojo> findUserBrandNameByUserId(Map<String, Object> map) throws SQLException;

  public List<UserBrandPojo> findUserBrandByUserId(Map<String, Object> map) throws SQLException;

  public UserBrandPojo findUserBrandById(Long id) throws SQLException;

  public int findUserBrandCount(Map<String, Object> map) throws SQLException;

  public void checkUserBrand(Long id) throws SQLException;

  public void uncheckUserBrand(Long id) throws SQLException;

  public void delUserBrand(Long id) throws SQLException;

  public void insertUserBrand(UserBrandPojo userBrandPojo) throws SQLException;

  public void updateUserBrand(UserBrandPojo userBrandPojo) throws SQLException;
}
