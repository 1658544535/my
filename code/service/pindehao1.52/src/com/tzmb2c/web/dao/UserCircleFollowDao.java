package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCircleFollowPojo;

public interface UserCircleFollowDao {

  public int addUserCircleFollow(UserCircleFollowPojo userCircleFollow);

  public int updateUserCircleFollow(UserCircleFollowPojo userCircleFollow);

  public int deleteUserCircleFollowById(Long id);

  public UserCircleFollowPojo getUserCircleFollowById(Long id);

  public Integer userCircleFollowCount(Map<String, Object> params);

  public List<UserCircleFollowPojo> userCircleFollowList(Map<String, Object> params);

  public List<UserCircleFollowPojo> findSocialCircleList(Map<String, Object> params);

  public List<UserCircleFollowPojo> findFocusUserList(Map<String, Object> params);

  public List<UserCircleFollowPojo> findUserConcernList(Map<String, Object> params);

  public UserCircleFollowPojo findUserCircleFollowByParams(Map<String, Object> params);
}
