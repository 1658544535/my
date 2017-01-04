package com.tzmb2c.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserCirclePostDao;
import com.tzmb2c.web.mapper.UserCirclePostMapper;
import com.tzmb2c.web.pojo.UserCirclePostPojo;

// @Service
public class UserCirclePostDaoImpl implements UserCirclePostDao {

  @Autowired
  private UserCirclePostMapper userCirclePostMapper;

  @Override
  @Transactional
  public int addUserCirclePost(UserCirclePostPojo userCirclePost) {
    if (null == userCirclePost) {
      return 0;
    }
    int rows = userCirclePostMapper.addUserCirclePost(userCirclePost);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserCirclePost(UserCirclePostPojo userCirclePost) {
    if (null == userCirclePost) {
      return 0;
    }
    int rows = userCirclePostMapper.updateUserCirclePost(userCirclePost);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserCirclePostById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userCirclePostMapper.deleteUserCirclePostById(id);
    return rows;
  }


  @Override
  public UserCirclePostPojo getUserCirclePostById(Long id) {
    if (null == id) {
      return null;
    }
    UserCirclePostPojo userCirclePost = userCirclePostMapper.getUserCirclePostById(id);
    //
    return userCirclePost;
  }

  @Override
  public Integer userCirclePostCount(Map<String, Object> params) {
    Integer rows = userCirclePostMapper.userCirclePostCount(params);
    return rows;
  }

  @Override
  public List<UserCirclePostPojo> userCirclePostList(Map<String, Object> params) {
    List<UserCirclePostPojo> lists = userCirclePostMapper.userCirclePostList(params);

    return lists;
  }

  @Override
  public void checkUserCirclePost(Long id) {
    userCirclePostMapper.checkUserCirclePost(id);

  }

  @Override
  public void uncheckUserCirclePost(Long id) {
    userCirclePostMapper.uncheckUserCirclePost(id);

  }

  @Override
  public UserCirclePostPojo getUserCirclePostByIdUserId(Long id) {
    return userCirclePostMapper.getUserCirclePostByIdUserId(id);
  }

  @Override
  public List<UserCirclePostPojo> findBycircleIds(Map<String, Object> params) {
    return userCirclePostMapper.findBycircleIds(params);
  }

  @Override
  public int increaseUserCirclePostNumById(UserCirclePostPojo userCirclePost) {
    // TODO Auto-generated method stub
    return userCirclePostMapper.increaseUserCirclePostNumById(userCirclePost);
  }

  @Override
  public int decreaseUserCirclePostNumById(UserCirclePostPojo userCirclePost) {
    // TODO Auto-generated method stub
    return userCirclePostMapper.decreaseUserCirclePostNumById(userCirclePost);
  }

  @Override
  public UserCirclePostPojo findBycircleParams(Map<String, Object> params) {
    return userCirclePostMapper.findBycircleParams(params);
  }

  @Override
  public List<UserCirclePostPojo> selectPhotos(Map<String, Object> params) {
    return userCirclePostMapper.selectPhotos(params);
  }

  @Override
  public UserCirclePostPojo getPostBaseInfoById(Long id) {
    return userCirclePostMapper.getPostBaseInfoById(id);
  }
}
