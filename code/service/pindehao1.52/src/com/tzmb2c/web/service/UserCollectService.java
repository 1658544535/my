package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserCollectPojo;

public interface UserCollectService {

  public List<UserCollectPojo> userCollectAllService() throws SQLException;

  public void addUserCollectService(UserCollectPojo userCollectPojo) throws SQLException;

  public void insertUserCollect(UserCollectPojo userCollectPojo) throws SQLException;

  public void updateUserCollect(UserCollectPojo userCollectPojo) throws SQLException;

  public UserCollectPojo getfindByIdUserCollect(Long id) throws SQLException;

  public void deleteUserCollect(Map<String, Object> map) throws SQLException;

  public int userCollectAllCount(UserCollectPojo userCollectDaoPojo);

  public int productCollectAllCount(UserCollectPojo userCollectDaoPojo);

  public int myProductCollectAllCount(long uid);

  List<UserCollectPojo> userCollectAllList(UserCollectPojo ticketRulePojo, Pager page);

  void userCollectDeleteId(String[] tids);

  void delUserCollect(Long id) throws SQLException;

  UserCollectPojo findUserCollectById(Long merId) throws SQLException;

  UserCollectPojo findUserCollectByProductId(UserCollectPojo userCollectPojo) throws SQLException;

  List<UserCollectPojo> findUserCollectByUserId(Long id) throws SQLException;

  List<UserCollectPojo> UserCollectByUserId(UserCollectPojo userCollectPojo, Pager page)
      throws SQLException;

  public List<UserCollectPojo> findUserCollectByUserIdPage(UserCollectPojo userCollectPojo,
      Pager page) throws SQLException;
}
