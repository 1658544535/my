package com.tzmb2c.web.dao;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPostCollectPojo;

public interface UserPostCollectDao {

  public int addUserPostCollect(UserPostCollectPojo userPostCollect);

  public int updateUserPostCollect(UserPostCollectPojo userPostCollect);

  public int deleteUserPostCollectById(Long id);

  public UserPostCollectPojo getUserPostCollectById(Long id);

  public Integer userPostCollectCount(Map<String, Object> params);

  public List<UserPostCollectPojo> userPostCollectList(Map<String, Object> params);

  public List<UserPostCollectPojo> userTopicCollectList(Map<String, Object> params);

}
