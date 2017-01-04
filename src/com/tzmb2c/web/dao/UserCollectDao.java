package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserCollectPojo;

public interface UserCollectDao {

  List<UserCollectPojo> getUserCollectAll() throws SQLException;

  void insertUserCollect(UserCollectPojo userCollectPojo) throws SQLException;

  void updateUserCollect(UserCollectPojo userCollectPojo) throws SQLException;

  UserCollectPojo getfindByIdUserCollect(Long id) throws SQLException;

  void deleteUserCollect(Map<String, Object> map) throws SQLException;

  int userCollectAllCount(Map<String, Object> map);

  int productCollectAllCount(UserCollectPojo userCollectPojo);


  List<UserCollectPojo> userCollectAllList(Map<String, Object> map);

  void delUserCollect(Long id) throws SQLException;

  UserCollectPojo findUserCollectById(Long id) throws SQLException;

  UserCollectPojo findUserCollectByProductId(UserCollectPojo userCollectPojo) throws SQLException;

  List<UserCollectPojo> findUserCollectByUserId(Long id) throws SQLException;

  List<UserCollectPojo> UserCollectByUserId(Map<String, Object> map) throws SQLException;

  public List<UserCollectPojo> findUserCollectByUserIdPage(Map<String, Object> map)
      throws SQLException;
}
