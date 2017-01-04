package com.tzmb2c.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserCirclePostDao;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.service.UserCirclePostService;

// @Service
public class UserCirclePostServiceImpl implements UserCirclePostService {

  @Autowired
  private UserCirclePostDao userCirclePostDao;

  @Override
  @Transactional
  public int addUserCirclePost(UserCirclePostPojo userCirclePost) {
    if (null == userCirclePost) {
      return 0;
    }
    int rows = userCirclePostDao.addUserCirclePost(userCirclePost);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserCirclePost(UserCirclePostPojo userCirclePost) {
    if (null == userCirclePost) {
      return 0;
    }
    int rows = userCirclePostDao.updateUserCirclePost(userCirclePost);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserCirclePostById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userCirclePostDao.deleteUserCirclePostById(id);
    return rows;
  }


  @Override
  public UserCirclePostPojo getUserCirclePostById(Long id) {
    if (null == id) {
      return null;
    }
    UserCirclePostPojo userCirclePost = userCirclePostDao.getUserCirclePostById(id);
    //
    return userCirclePost;
  }

  @Override
  public Integer userCirclePostCount(Map<String, Object> params) {
    Integer rows = userCirclePostDao.userCirclePostCount(params);
    return rows;
  }

  @Override
  public List<UserCirclePostPojo> userCirclePostList(Map<String, Object> params) {
    List<UserCirclePostPojo> lists = userCirclePostDao.userCirclePostList(params);

    return lists;
  }

  @Override
  public void checkUserCirclePost(Long id) {
    userCirclePostDao.checkUserCirclePost(id);

  }

  @Override
  public void uncheckUserCirclePost(Long id) {
    userCirclePostDao.uncheckUserCirclePost(id);

  }

  @Override
  public UserCirclePostPojo getUserCirclePostByIdUserId(Long id) {
    return userCirclePostDao.getUserCirclePostByIdUserId(id);
  }

  @Override
  public List<UserCirclePostPojo> findBycircleIds(Map<String, Object> params) {
    return userCirclePostDao.findBycircleIds(params);
  }

  @Override
  public int increaseUserCirclePostNumById(UserCirclePostPojo userCirclePost) {
    return userCirclePostDao.increaseUserCirclePostNumById(userCirclePost);
  }

  @Override
  public int decreaseUserCirclePostNumById(UserCirclePostPojo userCirclePost) {
    return userCirclePostDao.decreaseUserCirclePostNumById(userCirclePost);
  }

  @Override
  public UserCirclePostPojo findBycircleParams(Map<String, Object> params) {

    return userCirclePostDao.findBycircleParams(params);
  }

  @Override
  public List<UserCirclePostPojo> selectPhotos(Map<String, Object> params) {
    return userCirclePostDao.selectPhotos(params);
  }

  @Override
  public UserCirclePostPojo getPostBaseInfoById(Long id) {
    return userCirclePostDao.getPostBaseInfoById(id);
  }
}
