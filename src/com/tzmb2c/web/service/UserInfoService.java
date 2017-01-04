package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserInfoPojo;

public interface UserInfoService {

  public List<UserInfoPojo> userInfoAllService() throws SQLException;

  public void updateUserInfo(UserInfoPojo userInfoPojo) throws SQLException;

  public UserInfoPojo getfindByIdUserInfo(Long id) throws SQLException;

  public int userInfoAllCount(UserInfoPojo userInfoDaoPojo);

  List<UserInfoPojo> userInfoAllList(UserInfoPojo ticketRulePojo, Pager page);

  void userInfoChecks(String[] tids);

  void checkUserInfo(Long id) throws SQLException;

  UserInfoPojo findUserInfoById(Long merId) throws SQLException;

  void delUserInfo(Long id) throws SQLException;

  void insertUserInfo(UserInfoPojo userInfoPojo) throws SQLException;

  UserInfoPojo findUserInfoByUserId(Long id) throws SQLException;

  void delUserInfobyUserId(Long id) throws SQLException;

  void uncheckUserInfo(Long id) throws SQLException;

  int userInfoAllCount2(Map<String, Object> map) throws SQLException;

  List<UserInfoPojo> userInfoAllList2(Map<String, Object> map);

  void updateUserInfo2(UserInfoPojo userInfoPojo) throws SQLException;

  UserInfoPojo getUserInfoById(Long id) throws SQLException;

  public int jinwanhaoAllCount(UserInfoPojo userInfoDaoPojo);

  List<UserInfoPojo> jinwanhaoAllList(UserInfoPojo ticketRulePojo, Pager page);
}
