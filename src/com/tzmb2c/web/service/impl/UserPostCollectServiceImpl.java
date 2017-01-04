package com.tzmb2c.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tzmb2c.web.dao.UserPostCollectDao;
import com.tzmb2c.web.pojo.UserPostCollectPojo;
import com.tzmb2c.web.service.UserPostCollectService;

//@Service
public class UserPostCollectServiceImpl implements UserPostCollectService {

  @Autowired
  private UserPostCollectDao userPostCollectDao;

  @Override
  @Transactional
  public int addUserPostCollect(UserPostCollectPojo userPostCollect) {
    if (null == userPostCollect) {
      return 0;
    }
    int rows = userPostCollectDao.addUserPostCollect(userPostCollect);
    return rows;
  }

  @Override
  @Transactional
  public int updateUserPostCollect(UserPostCollectPojo userPostCollect) {
    if (null == userPostCollect) {
      return 0;
    }
    int rows = userPostCollectDao.updateUserPostCollect(userPostCollect);
    return rows;
  }

  @Override
  @Transactional
  public int deleteUserPostCollectById(Long id) {
    if (null == id) {
      return 0;
    }
    int rows = userPostCollectDao.deleteUserPostCollectById(id);
    return rows;
  }


  @Override
  public UserPostCollectPojo getUserPostCollectById(Long id) {
    if (null == id) {
      return null;
    }
    UserPostCollectPojo userPostCollect = userPostCollectDao.getUserPostCollectById(id);
    //
    return userPostCollect;
  }

  @Override
  public Integer userPostCollectCount(Map<String, Object> params) {
    Integer rows = userPostCollectDao.userPostCollectCount(params);
    return rows;
  }

  @Override
  public List<UserPostCollectPojo> userPostCollectList(Map<String, Object> params) {
    List<UserPostCollectPojo> lists = userPostCollectDao.userPostCollectList(params);

    return lists;
  }

  @Override
  public List<UserPostCollectPojo> userTopicCollectList(Map<String, Object> params) {
    return userPostCollectDao.userTopicCollectList(params);
  }

  @Override
  public int isCollect(Long tid, Long aid, int type, Long uid) {
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("typeId", tid);
    option.put("authorId", aid);
    option.put("type", type);
    option.put("userId", uid);
    option.put("isCollect", 1);
    return userPostCollectCount(option);
  }


}
