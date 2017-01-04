package com.tzmb2c.web.service;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPostCollectPojo;

public interface UserPostCollectService {

  public int addUserPostCollect(UserPostCollectPojo userPostCollect);

  public int updateUserPostCollect(UserPostCollectPojo userPostCollect);

  public int deleteUserPostCollectById(Long id);

  public UserPostCollectPojo getUserPostCollectById(Long id);

  public Integer userPostCollectCount(Map<String, Object> params);

  public List<UserPostCollectPojo> userPostCollectList(Map<String, Object> params);

  public List<UserPostCollectPojo> userTopicCollectList(Map<String, Object> params);

  /**
   * 获取笔记：是否收藏（0-未收藏；1-已收藏）
   * 
   * @param tid typeId
   * @param aid 作者id
   * @param type 1-主题;2-帖子;3-平台专题
   * @param uid 用户id
   * @return
   * @throw
   * @return int
   */
  public int isCollect(Long tid, Long aid, int type, Long uid);

}
