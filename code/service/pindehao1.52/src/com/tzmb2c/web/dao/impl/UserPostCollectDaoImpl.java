package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserPostCollectDao;
import com.tzmb2c.web.mapper.UserPostCollectMapper;
import com.tzmb2c.web.pojo.UserPostCollectPojo;

//@Service
public class UserPostCollectDaoImpl implements UserPostCollectDao {

  @Autowired
  private UserPostCollectMapper userPostCollectMapper;

  @Override
  @Transactional
  public int addUserPostCollect(UserPostCollectPojo userPostCollect) {
    if (null == userPostCollect) {
      return 0;
    }
    int rows = userPostCollectMapper.addUserPostCollect(userPostCollect);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserPostCollect(UserPostCollectPojo userPostCollect) {
    if (null == userPostCollect) {
      return 0;
    }
    int rows = userPostCollectMapper.updateUserPostCollect(userPostCollect);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserPostCollectById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userPostCollectMapper.deleteUserPostCollectById(id);
    return rows;
  }


  @Override
  public UserPostCollectPojo getUserPostCollectById(Long id) {
    if (null == id) {
      return null;
    }
    UserPostCollectPojo userPostCollect = userPostCollectMapper.getUserPostCollectById(id);
    //
    return userPostCollect;
  }

  @Override
  public Integer userPostCollectCount(Map<String, Object> params) {
    Integer rows = userPostCollectMapper.userPostCollectCount(params);
    return rows;
  }

  @Override
  public List<UserPostCollectPojo> userPostCollectList(Map<String, Object> params) {
    List<UserPostCollectPojo> lists = userPostCollectMapper.userPostCollectList(params);

    return lists;
  }

  @Override
  public List<UserPostCollectPojo> userTopicCollectList(Map<String, Object> params) {
    return userPostCollectMapper.userTopicCollectList(params);
  }
}
