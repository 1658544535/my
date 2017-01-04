package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.UserConsumerCollectDao;
import com.tzmb2c.web.mapper.UserConsumerCollectMapper;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;

public class UserConsumerCollectDaoImpl implements UserConsumerCollectDao {

  @Autowired
  private UserConsumerCollectMapper userConsumerCollectMapper;

  @Override
  public void delUserConsumerCollect(Long id) throws SQLException {
    userConsumerCollectMapper.delUserConsumerCollect(id);
  }

  @Override
  public List<UserConsumerCollectPojo> getAllList(UserConsumerCollectPojo userConsumerCollectPojo) {
    return userConsumerCollectMapper.getAllList(userConsumerCollectPojo);
  }

  @Override
  public UserConsumerCollectPojo findCollect(UserConsumerCollectPojo userConsumerCollectPojo) {
    return userConsumerCollectMapper.findCollect(userConsumerCollectPojo);
  }

  @Override
  public List<UserConsumerCollectPojo> findCollect2(Map<String, Object> map) {
    return userConsumerCollectMapper.findCollect2(map);
  }

  @Override
  public void insertUserConsumerCollect(UserConsumerCollectPojo userConsumerCollectPojo)
      throws SQLException {
    try {
      userConsumerCollectMapper.insertUserConsumerCollect(userConsumerCollectPojo);
    } catch (Exception e) {
      e.printStackTrace();

    }

  }

  @Override
  public int findCountByUserId(Long userId) throws SQLException {
    return userConsumerCollectMapper.findCountByUserId(userId);
  }

  @Override
  public void deleteUserConsumerCollect(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    userConsumerCollectMapper.deleteUserConsumerCollect(map);
  }
}
