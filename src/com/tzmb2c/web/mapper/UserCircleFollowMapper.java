package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCircleFollowPojo;

public interface UserCircleFollowMapper {

  UserCircleFollowPojo getUserCircleFollowById(Long id);

  int userCircleFollowCount(Map<String, Object> params);

  List<UserCircleFollowPojo> userCircleFollowList(Map<String, Object> params);

  int addUserCircleFollow(UserCircleFollowPojo userCircleFollow);

  int updateUserCircleFollow(UserCircleFollowPojo userCircleFollow);

  int deleteUserCircleFollowById(Long id);

  public List<UserCircleFollowPojo> findSocialCircleList(Map<String, Object> params);

  public List<UserCircleFollowPojo> findFocusUserList(Map<String, Object> params);

  public List<UserCircleFollowPojo> findUserConcernList(Map<String, Object> params);

  public UserCircleFollowPojo findUserCircleFollowByParams(Map<String, Object> params);
}
