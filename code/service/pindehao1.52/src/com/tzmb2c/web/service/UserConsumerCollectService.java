package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;

/**
 * @author EricChen
 */
public interface UserConsumerCollectService {
  public List<UserConsumerCollectPojo> getAllList(UserConsumerCollectPojo userConsumerCollectPojo);

  public UserConsumerCollectPojo findCollect(UserConsumerCollectPojo userConsumerCollectPojo);

  public void insertUserConsumerCollect(UserConsumerCollectPojo userConsumerCollectPojo)
      throws SQLException;

  void delUserConsumerCollect(Long id) throws SQLException;

  void deleteUserConsumerCollect(Map<String, Object> map) throws SQLException;

  public List<UserConsumerCollectPojo> findCollect2(
      UserConsumerCollectPojo userConsumerCollectPojo, Pager page);

  public int findCountByUserId(Long userId) throws SQLException;
}
