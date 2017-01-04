package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserShopCollectPojo;

public interface UserShopCollectDao {

  List<UserShopCollectPojo> getUserShopCollectAll() throws SQLException;

  void insertUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException;

  void updateUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException;

  UserShopCollectPojo getfindByIdUserShopCollect(Long id) throws SQLException;

  void deleteUserShopCollect(Map<String, Object> map) throws SQLException;

  int userShopCollectAllCount(Map<String, Object> map);

  List<UserShopCollectPojo> userShopCollectAllList(Map<String, Object> map);

  void delUserShopCollect(Long id) throws SQLException;

  UserShopCollectPojo findUserShopCollectById(Long id) throws SQLException;

  List<UserShopCollectPojo> findUserShopCollectByUserId(Long id) throws SQLException;

  UserShopCollectPojo findUserShopCollectByShopId(UserShopCollectPojo userShopCollectPojo)
      throws SQLException;

  List<UserShopCollectPojo> userShopCollectByPage(Map<String, Object> map);
}
