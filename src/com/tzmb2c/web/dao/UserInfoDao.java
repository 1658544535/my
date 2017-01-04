package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserInfoPojo;

public interface UserInfoDao {

  List<UserInfoPojo> getUserInfoAll() throws SQLException;

  void updateUserInfo(UserInfoPojo userInfoPojo) throws SQLException;

  UserInfoPojo getfindByIdUserInfo(Long id) throws SQLException;

  int userInfoAllCount(Map<String, Object> map);

  List<UserInfoPojo> userInfoAllList(Map<String, Object> map);

  void checkUserInfo(Long id) throws SQLException;

  UserInfoPojo findUserInfoById(Long id) throws SQLException;

  void delUserInfo(Long id) throws SQLException;

  void insertUserInfo(UserInfoPojo userInfoPojo) throws SQLException;

  UserInfoPojo findUserInfoByUserId(Long id) throws SQLException;

  void delUserInfobyUserId(Long id) throws SQLException;

  void uncheckUserInfo(Long id) throws SQLException;

  int userInfoAllCount2(Map<String, Object> map) throws SQLException;

  List<UserInfoPojo> userInfoAllList2(Map<String, Object> map);

  void updateUserInfo2(UserInfoPojo userInfoPojo) throws SQLException;

  UserInfoPojo getUserInfoById(Long id) throws SQLException;

  int jinwanhaoAllCount(Map<String, Object> map);

  List<UserInfoPojo> jinwanhaoAllList(Map<String, Object> map);
}
