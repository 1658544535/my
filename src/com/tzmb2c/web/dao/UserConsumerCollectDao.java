package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserConsumerCollectPojo;

public interface UserConsumerCollectDao {

  void delUserConsumerCollect(Long id) throws SQLException;

  void deleteUserConsumerCollect(Map<String, Object> map) throws SQLException;

  List<UserConsumerCollectPojo> getAllList(UserConsumerCollectPojo userConsumerCollectPojo);

  UserConsumerCollectPojo findCollect(UserConsumerCollectPojo userConsumerCollectPojo);

  void insertUserConsumerCollect(UserConsumerCollectPojo userConsumerCollectPojo)
      throws SQLException;

  List<UserConsumerCollectPojo> findCollect2(Map<String, Object> map);

  public int findCountByUserId(Long userId) throws SQLException;
}
