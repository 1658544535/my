package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserInfoDao;
import com.tzmb2c.web.mapper.UserInfoMapper;
import com.tzmb2c.web.pojo.UserInfoPojo;

public class UserInfoDaoImpl implements UserInfoDao {

  @Autowired
  private UserInfoMapper userInfoMapper;

  @Override
  public void insertUserInfo(UserInfoPojo userInfoPojo) throws SQLException {
    try {
      userInfoMapper.insertUserInfo(userInfoPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public List<UserInfoPojo> getUserInfoAll() throws SQLException {
    return userInfoMapper.getUserInfoAll();
  }

  @Override
  public void updateUserInfo(UserInfoPojo userInfoPojo) throws SQLException {

    userInfoMapper.updateUserInfo(userInfoPojo);
  }

  @Override
  public UserInfoPojo getfindByIdUserInfo(Long id) throws SQLException {
    return userInfoMapper.getfindByIdUserInfo(id);

  }

  @Override
  public int userInfoAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userInfoMapper.userInfoAllCount(map);
  }

  @Override
  public List<UserInfoPojo> userInfoAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userInfoMapper.userInfoAllList(map);
  }

  @Override
  public void checkUserInfo(Long id) throws SQLException {

    userInfoMapper.checkUserInfo(id);
  }

  @Override
  public UserInfoPojo findUserInfoById(Long id) throws SQLException {
    return userInfoMapper.getfindByIdUserInfo(id);
  }

  @Override
  public UserInfoPojo findUserInfoByUserId(Long id) throws SQLException {
    return userInfoMapper.getfindByUserIdUserInfo(id);
  }

  @Override
  public void delUserInfo(Long id) throws SQLException {
    userInfoMapper.delUserInfo(id);

  }

  @Override
  public void delUserInfobyUserId(Long id) throws SQLException {
    userInfoMapper.delUserInfobyUserId(id);
  }

  @Override
  public int userInfoAllCount2(Map<String, Object> map) throws SQLException {
    return userInfoMapper.userInfoAllCount2(map);
  }

  @Override
  public List<UserInfoPojo> userInfoAllList2(Map<String, Object> map) {
    return userInfoMapper.userInfoAllList2(map);
  }

  @Override
  public void updateUserInfo2(UserInfoPojo userInfoPojo) throws SQLException {
    userInfoMapper.updateUserInfo2(userInfoPojo);
  }

  @Override
  public void uncheckUserInfo(Long id) throws SQLException {
    userInfoMapper.uncheckUserInfo(id);
  }

  @Override
  public UserInfoPojo getUserInfoById(Long id) throws SQLException {
    return userInfoMapper.findUserInfoById(id);
  }

  @Override
  public int jinwanhaoAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userInfoMapper.jinwanhaoAllCount(map);
  }

  @Override
  public List<UserInfoPojo> jinwanhaoAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return userInfoMapper.jinwanhaoAllList(map);
  }
}
