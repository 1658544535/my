package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserSpecialCollectDao;
import com.tzmb2c.web.mapper.UserSpecialCollectMapper;
import com.tzmb2c.web.pojo.UserSpecialCollectPojo;

public class UserSpecialCollectDaoImpl implements UserSpecialCollectDao {
  @Autowired
  UserSpecialCollectMapper userSpecialCollectMapper;

  @Override
  public List<UserSpecialCollectPojo> getUserSpecialCollectList(Map<String, Object> map)
      throws SQLException {

    return userSpecialCollectMapper.getUserSpecialCollectList(map);
  }

  @Override
  public int getUserSpecialCollectListCount(Map<String, Object> map) throws SQLException {

    return userSpecialCollectMapper.getUserSpecialCollectListCount(map);
  }

  @Override
  public UserSpecialCollectPojo getUserSpecialCollectById(Long id) throws SQLException {

    return userSpecialCollectMapper.getUserSpecialCollectById(id);
  }

  @Override
  public List<UserSpecialCollectPojo> getUserSpecialCollectByUserId(Map<String, Object> map)
      throws SQLException {

    return userSpecialCollectMapper.getUserSpecialCollectByUserId(map);
  }

  @Override
  public void insertUserSpecialCollect(UserSpecialCollectPojo userSpecialCollect)
      throws SQLException {
    userSpecialCollectMapper.insertUserSpecialCollect(userSpecialCollect);
  }

  @Override
  public void delUserSpecialCollectById(Long id) throws SQLException {
    userSpecialCollectMapper.delUserSpecialCollectById(id);
  }

  @Override
  public void delUserSpecialCollectBySpecialId(Map<String, Object> map) throws SQLException {
    userSpecialCollectMapper.delUserSpecialCollectBySpecialId(map);
  }

  @Override
  public UserSpecialCollectPojo getUserSpecialCollectBySpecialId(
      UserSpecialCollectPojo userSpecialCollect) throws SQLException {
    return userSpecialCollectMapper.getUserSpecialCollectBySpecialId(userSpecialCollect);
  }

}
