package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCirclePostPojo;

public interface UserCirclePostMapper {

  UserCirclePostPojo getUserCirclePostById(Long id);

  UserCirclePostPojo getPostBaseInfoById(Long id);

  int userCirclePostCount(Map<String, Object> params);

  List<UserCirclePostPojo> userCirclePostList(Map<String, Object> params);

  int addUserCirclePost(UserCirclePostPojo userCirclePost);

  int updateUserCirclePost(UserCirclePostPojo userCirclePost);

  int increaseUserCirclePostNumById(UserCirclePostPojo userCirclePost);

  int decreaseUserCirclePostNumById(UserCirclePostPojo userCirclePost);

  int deleteUserCirclePostById(Long id);

  void checkUserCirclePost(Long id);

  void uncheckUserCirclePost(Long id);

  public UserCirclePostPojo getUserCirclePostByIdUserId(Long id);

  public List<UserCirclePostPojo> findBycircleIds(Map<String, Object> params);

  public UserCirclePostPojo findBycircleParams(Map<String, Object> params);

  public List<UserCirclePostPojo> selectPhotos(Map<String, Object> params);
}
