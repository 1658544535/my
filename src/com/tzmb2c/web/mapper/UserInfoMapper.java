package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserInfoPojo;

public interface UserInfoMapper {


  public List<UserInfoPojo> getUserInfoAll() throws SQLException;

  public void updateUserInfo(UserInfoPojo userInfoPojo) throws SQLException;

  public UserInfoPojo getfindByIdUserInfo(Long id) throws SQLException;

  public UserInfoPojo getfindByUserIdUserInfo(Long id) throws SQLException;

  public int userInfoAllCount(Map<String, Object> map);

  public List<UserInfoPojo> userInfoAllList(Map<String, Object> map);

  void checkUserInfo(Long id) throws SQLException;

  void uncheckUserInfo(Long id) throws SQLException;

  void delUserInfo(Long id) throws SQLException;

  void delUserInfobyUserId(Long id) throws SQLException;

  public void insertUserInfo(UserInfoPojo userInfoPojo) throws SQLException;

  public int userInfoAllCount2(Map<String, Object> map) throws SQLException;

  public List<UserInfoPojo> userInfoAllList2(Map<String, Object> map);

  public void updateUserInfo2(UserInfoPojo userInfoPojo) throws SQLException;

  public UserInfoPojo findUserInfoById(Long id) throws SQLException;

  public int jinwanhaoAllCount(Map<String, Object> map);

  public List<UserInfoPojo> jinwanhaoAllList(Map<String, Object> map);

}
