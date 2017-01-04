package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserConsumerCollectPojo;

public interface UserConsumerCollectMapper {

  void delUserConsumerCollect(Long id) throws SQLException;

  void deleteUserConsumerCollect(Map<String, Object> map) throws SQLException;

  public List<UserConsumerCollectPojo> getAllList(UserConsumerCollectPojo userConsumerCollectPojo);

  public UserConsumerCollectPojo findCollect(UserConsumerCollectPojo userConsumerCollectPojo);

  public List<UserConsumerCollectPojo> findCollect2(Map<String, Object> map);

  public void insertUserConsumerCollect(UserConsumerCollectPojo userConsumerCollectPojo)
      throws SQLException;

  public int findCountByUserId(Long userId) throws SQLException;
}
