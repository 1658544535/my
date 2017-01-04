package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserCircleFollowDao;
import com.tzmb2c.web.mapper.UserCircleFollowMapper;
import com.tzmb2c.web.pojo.UserCircleFollowPojo;

// @Service
public class UserCircleFollowDaoImpl implements UserCircleFollowDao {

  @Autowired
  private UserCircleFollowMapper userCircleFollowMapper;

  @Override
  @Transactional
  public int addUserCircleFollow(UserCircleFollowPojo userCircleFollow) {
    if (null == userCircleFollow) {
      return 0;
    }
    int rows = userCircleFollowMapper.addUserCircleFollow(userCircleFollow);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserCircleFollow(UserCircleFollowPojo userCircleFollow) {
    if (null == userCircleFollow) {
      return 0;
    }
    int rows = userCircleFollowMapper.updateUserCircleFollow(userCircleFollow);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserCircleFollowById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userCircleFollowMapper.deleteUserCircleFollowById(id);
    return rows;
  }


  @Override
  public UserCircleFollowPojo getUserCircleFollowById(Long id) {
    if (null == id) {
      return null;
    }
    UserCircleFollowPojo userCircleFollow = userCircleFollowMapper.getUserCircleFollowById(id);
    //
    return userCircleFollow;
  }

  @Override
  public Integer userCircleFollowCount(Map<String, Object> params) {
    Integer rows = userCircleFollowMapper.userCircleFollowCount(params);
    return rows;
  }

  @Override
  public List<UserCircleFollowPojo> userCircleFollowList(Map<String, Object> params) {
    List<UserCircleFollowPojo> lists = userCircleFollowMapper.userCircleFollowList(params);

    return lists;
  }

  @Override
  public List<UserCircleFollowPojo> findSocialCircleList(Map<String, Object> params) {

    return userCircleFollowMapper.findSocialCircleList(params);
  }

  @Override
  public List<UserCircleFollowPojo> findFocusUserList(Map<String, Object> params) {
    return userCircleFollowMapper.findFocusUserList(params);
  }

  @Override
  public UserCircleFollowPojo findUserCircleFollowByParams(Map<String, Object> params) {
    return userCircleFollowMapper.findUserCircleFollowByParams(params);
  }

  @Override
  public List<UserCircleFollowPojo> findUserConcernList(Map<String, Object> params) {
    return userCircleFollowMapper.findUserConcernList(params);
  }
}
