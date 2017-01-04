package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCircleFollowPojo;

public interface UserCircleFollowService {

  public int addUserCircleFollow(UserCircleFollowPojo userCircleFollow);

  public int updateUserCircleFollow(UserCircleFollowPojo userCircleFollow);

  public int deleteUserCircleFollowById(Long id);

  public UserCircleFollowPojo getUserCircleFollowById(Long id);

  public Integer userCircleFollowCount(Map<String, Object> params);

  public List<UserCircleFollowPojo> userCircleFollowList(Map<String, Object> params);

  /**
   *查询社圈列表 
   */
  public List<UserCircleFollowPojo> findSocialCircleList(Map<String, Object> params);

  /**
   *关注的人和我关注的人
   */
  public List<UserCircleFollowPojo> findFocusUserList(Map<String, Object> params);

  /**
   *更具UserCircleFollowPojo查询一条数据
   */
  public UserCircleFollowPojo findUserCircleFollowByParams(Map<String, Object> params);

  /** 
   * 查询用户关注数或粉丝数.
   * @param uid 用户ID
   * @param typeId 被关注用户ID
   * @param type 关注类型 1达人 2圈子 3创客
   * @throw 
   * @return int
   */
  public int getFollowNum(Long uid, Long typeId, int type);

  public List<UserCircleFollowPojo> findUserConcernList(Map<String, Object> params);

}
