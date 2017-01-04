package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCollectPojo;

public interface UserCollectMapper {


  public List<UserCollectPojo> getUserCollectAll() throws SQLException;

  public void insertUserCollect(UserCollectPojo userCollectPojo) throws SQLException;

  public void updateUserCollect(UserCollectPojo userCollectPojo) throws SQLException;

  public UserCollectPojo getfindByIdUserCollect(Long id) throws SQLException;

  public UserCollectPojo findUserCollectByProductId(UserCollectPojo userCollectPojo)
      throws SQLException;

  public List<UserCollectPojo> getfindByUserIdUserCollect(Long id) throws SQLException;

  public void deleteUserCollect(Map<String, Object> map) throws SQLException;

  public int userCollectAllCount(Map<String, Object> map);

  public int productCollectAllCount(UserCollectPojo userCollectPojo);

  public List<UserCollectPojo> userCollectAllList(Map<String, Object> map);

  void delUserCollect(Long id) throws SQLException;

  public List<UserCollectPojo> UserCollectByUserId(Map<String, Object> map) throws SQLException;

  public List<UserCollectPojo> findUserCollectByUserIdPage(Map<String, Object> map)
      throws SQLException;
}
