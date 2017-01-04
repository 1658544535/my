package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCirclePostPojo;

public interface UserCirclePostService {

  public int addUserCirclePost(UserCirclePostPojo userCirclePost);

  public int updateUserCirclePost(UserCirclePostPojo userCirclePost);

  int increaseUserCirclePostNumById(UserCirclePostPojo userCirclePost);

  int decreaseUserCirclePostNumById(UserCirclePostPojo userCirclePost);

  public int deleteUserCirclePostById(Long id);

  public UserCirclePostPojo getUserCirclePostById(Long id);

  public Integer userCirclePostCount(Map<String, Object> params);

  public List<UserCirclePostPojo> userCirclePostList(Map<String, Object> params);

  public void checkUserCirclePost(Long id);

  public void uncheckUserCirclePost(Long id);

  /**
   * 根据userId查最新的一张帖子
   * 
   * @param id 用户id
   * @return
   */
  public UserCirclePostPojo getUserCirclePostByIdUserId(Long id);

  /**
   * 根据userId查最新的一张帖子基本信息，不包括内容
   * 
   * @param id 用户id
   * @return
   */
  public UserCirclePostPojo getPostBaseInfoById(Long id);

  /**
   * 根据关注的圈子集查询帖子
   * 
   * @param typeIds
   * @return
   */
  public List<UserCirclePostPojo> findBycircleIds(Map<String, Object> params);

  /**
   * 多条件查询一条数据 查询笔迹标签
   * 
   * @param typeIds
   * @return
   */
  public UserCirclePostPojo findBycircleParams(Map<String, Object> params);

  public List<UserCirclePostPojo> selectPhotos(Map<String, Object> params);

}
