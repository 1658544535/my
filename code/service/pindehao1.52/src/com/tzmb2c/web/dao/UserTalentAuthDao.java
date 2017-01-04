package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserTalentAuthPojo;

public interface UserTalentAuthDao {
  List<UserTalentAuthPojo> searchUserTalentAuth(Map<String, Object> map) throws SQLException;

  List<UserTalentAuthPojo> findUserTalentAuthList(Map<String, Object> map) throws SQLException;

  int findUserTalentAuthCount(Map<String, Object> map) throws SQLException;

  void insertUserTalentAuth(UserTalentAuthPojo userTalentAuthPojo) throws SQLException;

  int updateUserTalentAuth(UserTalentAuthPojo userTalentAuthPojo) throws SQLException;

  UserTalentAuthPojo findUserTalentAuthById(Long id) throws SQLException;

  UserTalentAuthPojo findUserTalentAuthByUid(Long uid) throws SQLException;

  void checkUserTalentAuth(Long id) throws SQLException;

  void uncheckUserTalentAuth(Long id) throws SQLException;
}
