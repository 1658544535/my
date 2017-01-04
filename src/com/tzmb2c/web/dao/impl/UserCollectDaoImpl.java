package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserCollectDao;
import com.tzmb2c.web.mapper.UserCollectMapper;
import com.tzmb2c.web.pojo.UserCollectPojo;

public class UserCollectDaoImpl implements UserCollectDao {

  @Autowired
  private UserCollectMapper userCollectMapper;

  @Override
  public List<UserCollectPojo> getUserCollectAll() throws SQLException {
    return userCollectMapper.getUserCollectAll();
  }

  @Override
  public void insertUserCollect(UserCollectPojo userCollectPojo) throws SQLException {
    try {
      userCollectMapper.insertUserCollect(userCollectPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateUserCollect(UserCollectPojo userCollectPojo) throws SQLException {

    userCollectMapper.updateUserCollect(userCollectPojo);
  }

  @Override
  public UserCollectPojo getfindByIdUserCollect(Long id) throws SQLException {
    return userCollectMapper.getfindByIdUserCollect(id);

  }

  @Override
  public void deleteUserCollect(Map<String, Object> map) throws SQLException {
    userCollectMapper.deleteUserCollect(map);
  }

  @Override
  public int userCollectAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userCollectMapper.userCollectAllCount(map);
  }

  @Override
  public int productCollectAllCount(UserCollectPojo userCollectPojo) {
    // TODO Auto-generated method stub
    return userCollectMapper.productCollectAllCount(userCollectPojo);
  }

  @Override
  public List<UserCollectPojo> userCollectAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userCollectMapper.userCollectAllList(map);
  }

  @Override
  public void delUserCollect(Long id) throws SQLException {

    userCollectMapper.delUserCollect(id);
  }

  @Override
  public UserCollectPojo findUserCollectById(Long id) throws SQLException {
    return userCollectMapper.getfindByIdUserCollect(id);
  }

  @Override
  public UserCollectPojo findUserCollectByProductId(UserCollectPojo userCollectPojo)
      throws SQLException {
    return userCollectMapper.findUserCollectByProductId(userCollectPojo);
  }

  @Override
  public List<UserCollectPojo> findUserCollectByUserId(Long id) throws SQLException {
    return userCollectMapper.getfindByUserIdUserCollect(id);
  }

  @Override
  public List<UserCollectPojo> UserCollectByUserId(Map<String, Object> map) throws SQLException {
    return userCollectMapper.UserCollectByUserId(map);
  }

  @Override
  public List<UserCollectPojo> findUserCollectByUserIdPage(Map<String, Object> map)
      throws SQLException {

    return userCollectMapper.findUserCollectByUserIdPage(map);
  }
}
