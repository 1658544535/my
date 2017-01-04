package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCirclePostPojo;

public interface UserCirclePostDao {

  public int addUserCirclePost(UserCirclePostPojo userCirclePost);

  public int updateUserCirclePost(UserCirclePostPojo userCirclePost);

  int increaseUserCirclePostNumById(UserCirclePostPojo userCirclePost);

  int decreaseUserCirclePostNumById(UserCirclePostPojo userCirclePost);

  public int deleteUserCirclePostById(Long id);

  public UserCirclePostPojo getUserCirclePostById(Long id);

  public UserCirclePostPojo getPostBaseInfoById(Long id);

  public Integer userCirclePostCount(Map<String, Object> params);

  public List<UserCirclePostPojo> userCirclePostList(Map<String, Object> params);

  public void checkUserCirclePost(Long id);

  public void uncheckUserCirclePost(Long id);

  public UserCirclePostPojo getUserCirclePostByIdUserId(Long id);

  public List<UserCirclePostPojo> findBycircleIds(Map<String, Object> params);

  public UserCirclePostPojo findBycircleParams(Map<String, Object> params);

  public List<UserCirclePostPojo> selectPhotos(Map<String, Object> params);
}
