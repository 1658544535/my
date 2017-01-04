package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserPostCollectPojo;

public interface UserPostCollectMapper {

  UserPostCollectPojo getUserPostCollectById(Long id);

  int userPostCollectCount(Map<String, Object> params);

  List<UserPostCollectPojo> userPostCollectList(Map<String, Object> params);

  List<UserPostCollectPojo> userTopicCollectList(Map<String, Object> params);

  int addUserPostCollect(UserPostCollectPojo userPostCollect);

  int updateUserPostCollect(UserPostCollectPojo userPostCollect);

  int deleteUserPostCollectById(Long id);
}
