package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserSpecialCollectDao;
import com.tzmb2c.web.pojo.UserSpecialCollectPojo;
import com.tzmb2c.web.service.UserSpecialCollectService;

public class UserSpecialCollectServiceImpl implements UserSpecialCollectService {
  @Autowired
  UserSpecialCollectDao userSpecialCollectDao;

  @Override
  public List<UserSpecialCollectPojo> getUserSpecialCollectList(Map<String, Object> map)
      throws SQLException {

    return userSpecialCollectDao.getUserSpecialCollectList(map);
  }

  @Override
  public int getUserSpecialCollectListCount(Map<String, Object> map) throws SQLException {

    return userSpecialCollectDao.getUserSpecialCollectListCount(map);
  }

  @Override
  public UserSpecialCollectPojo getUserSpecialCollectById(Long id) throws SQLException {

    return userSpecialCollectDao.getUserSpecialCollectById(id);
  }

  @Override
  public List<UserSpecialCollectPojo> getUserSpecialCollectByUserId(Map<String, Object> map)
      throws SQLException {

    return userSpecialCollectDao.getUserSpecialCollectByUserId(map);
  }

  @Override
  public void insertUserSpecialCollect(UserSpecialCollectPojo userSpecialCollect)
      throws SQLException {
    userSpecialCollectDao.insertUserSpecialCollect(userSpecialCollect);

  }

  @Override
  public void delUserSpecialCollectById(Long id) throws SQLException {
    userSpecialCollectDao.delUserSpecialCollectById(id);

  }

  @Override
  public void delUserSpecialCollectBySpecialId(Map<String, Object> map) throws SQLException {
    userSpecialCollectDao.delUserSpecialCollectBySpecialId(map);

  }

  @Override
  public UserSpecialCollectPojo getUserSpecialCollectBySpecialId(
      UserSpecialCollectPojo userSpecialCollect) throws SQLException {
    return userSpecialCollectDao.getUserSpecialCollectBySpecialId(userSpecialCollect);
  }

}
