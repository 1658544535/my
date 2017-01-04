package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserSpecialCollectPojo;

public interface UserSpecialCollectService {
  List<UserSpecialCollectPojo> getUserSpecialCollectList(Map<String, Object> map)
      throws SQLException;

  int getUserSpecialCollectListCount(Map<String, Object> map) throws SQLException;

  UserSpecialCollectPojo getUserSpecialCollectById(Long id) throws SQLException;

  UserSpecialCollectPojo getUserSpecialCollectBySpecialId(UserSpecialCollectPojo userSpecialCollect)
      throws SQLException;

  List<UserSpecialCollectPojo> getUserSpecialCollectByUserId(Map<String, Object> map)
      throws SQLException;

  void insertUserSpecialCollect(UserSpecialCollectPojo userSpecialCollect) throws SQLException;

  void delUserSpecialCollectById(Long id) throws SQLException;

  void delUserSpecialCollectBySpecialId(Map<String, Object> map) throws SQLException;
}
