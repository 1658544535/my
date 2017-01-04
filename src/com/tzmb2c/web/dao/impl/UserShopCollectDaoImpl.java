package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserShopCollectDao;
import com.tzmb2c.web.mapper.UserShopCollectMapper;
import com.tzmb2c.web.pojo.UserShopCollectPojo;

public class UserShopCollectDaoImpl implements UserShopCollectDao {

  @Autowired
  private UserShopCollectMapper userShopCollectMapper;

  @Override
  public List<UserShopCollectPojo> getUserShopCollectAll() throws SQLException {
    return userShopCollectMapper.getUserShopCollectAll();
  }

  @Override
  public void insertUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException {
    try {
      userShopCollectMapper.insertUserShopCollect(userShopCollectPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public void updateUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException {

    userShopCollectMapper.updateUserShopCollect(userShopCollectPojo);
  }

  @Override
  public UserShopCollectPojo getfindByIdUserShopCollect(Long id) throws SQLException {
    return userShopCollectMapper.getfindByIdUserShopCollect(id);

  }

  @Override
  public void deleteUserShopCollect(Map<String, Object> map) throws SQLException {
    userShopCollectMapper.deleteUserShopCollect(map);
  }

  @Override
  public int userShopCollectAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userShopCollectMapper.userShopCollectAllCount(map);
  }

  @Override
  public List<UserShopCollectPojo> userShopCollectAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userShopCollectMapper.userShopCollectAllList(map);
  }

  @Override
  public void delUserShopCollect(Long id) throws SQLException {

    userShopCollectMapper.delUserShopCollect(id);
  }

  @Override
  public UserShopCollectPojo findUserShopCollectById(Long id) throws SQLException {
    return userShopCollectMapper.getfindByIdUserShopCollect(id);
  }

  @Override
  public UserShopCollectPojo findUserShopCollectByShopId(UserShopCollectPojo userShopCollectPojo)
      throws SQLException {
    return userShopCollectMapper.findUserShopCollectByShopId(userShopCollectPojo);
  }

  @Override
  public List<UserShopCollectPojo> findUserShopCollectByUserId(Long id) throws SQLException {
    return userShopCollectMapper.getfindByUserIdUserShopCollect(id);
  }

  @Override
  public List<UserShopCollectPojo> userShopCollectByPage(Map<String, Object> map) {

    return userShopCollectMapper.userShopCollectByPage(map);
  }
}
