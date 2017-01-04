package com.tzmb2c.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserCircleFollowDao;
import com.tzmb2c.web.pojo.UserCircleFollowPojo;
import com.tzmb2c.web.service.UserCircleFollowService;

// @Service
public class UserCircleFollowServiceImpl implements UserCircleFollowService {

  @Autowired
  private UserCircleFollowDao userCircleFollowDao;

  @Override
  @Transactional
  public int addUserCircleFollow(UserCircleFollowPojo userCircleFollow) {
    if (null == userCircleFollow) {
      return 0;
    }
    int rows = userCircleFollowDao.addUserCircleFollow(userCircleFollow);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserCircleFollow(UserCircleFollowPojo userCircleFollow) {
    if (null == userCircleFollow) {
      return 0;
    }
    int rows = userCircleFollowDao.updateUserCircleFollow(userCircleFollow);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserCircleFollowById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userCircleFollowDao.deleteUserCircleFollowById(id);
    return rows;
  }


  @Override
  public UserCircleFollowPojo getUserCircleFollowById(Long id) {
    if (null == id) {
      return null;
    }
    UserCircleFollowPojo userCircleFollow = userCircleFollowDao.getUserCircleFollowById(id);
    //
    return userCircleFollow;
  }

  @Override
  public Integer userCircleFollowCount(Map<String, Object> params) {
    Integer rows = userCircleFollowDao.userCircleFollowCount(params);
    return rows;
  }

  @Override
  public List<UserCircleFollowPojo> userCircleFollowList(Map<String, Object> params) {
    List<UserCircleFollowPojo> lists = userCircleFollowDao.userCircleFollowList(params);

    return lists;
  }

  @Override
  public List<UserCircleFollowPojo> findSocialCircleList(Map<String, Object> params) {

    return userCircleFollowDao.findSocialCircleList(params);
  }

  @Override
  public List<UserCircleFollowPojo> findFocusUserList(Map<String, Object> params) {
    return userCircleFollowDao.findFocusUserList(params);
  }

  @Override
  public UserCircleFollowPojo findUserCircleFollowByParams(Map<String, Object> params) {
    return userCircleFollowDao.findUserCircleFollowByParams(params);
  }

  @Override
  public int getFollowNum(Long uid, Long typeId, int type) {
    Map<String, Object> option = new HashMap<String, Object>();
    if (uid != null && uid != 0) {
      option.put("userId", uid);
    }
    if (typeId != null && typeId != 0) {
      option.put("typeId", typeId);
    }
    if (type != 0) {
      option.put("type", type);
    } else {
      option.put("topicType", 1);
    }
    option.put("isFollow", 1);
    return userCircleFollowCount(option);
  }

  @Override
  public List<UserCircleFollowPojo> findUserConcernList(Map<String, Object> params) {
    return userCircleFollowDao.findUserConcernList(params);
  }
}
