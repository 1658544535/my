package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserShopCollectPojo;

public interface UserShopCollectService {

  public List<UserShopCollectPojo> userShopCollectAllService() throws SQLException;

  public void addUserShopCollectService(UserShopCollectPojo userShopCollectPojo)
      throws SQLException;

  public void insertUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException;

  public void updateUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException;

  public UserShopCollectPojo getfindByIdUserShopCollect(Long id) throws SQLException;

  public void deleteUserShopCollect(Map<String, Object> map) throws SQLException;

  public int userShopCollectAllCount(UserShopCollectPojo userShopCollectDaoPojo);

  public int myShopCollectAllCount(long uid);

  List<UserShopCollectPojo> userShopCollectAllList(UserShopCollectPojo ticketRulePojo, Pager page);

  void userShopCollectDeleteId(String[] tids);

  void delUserShopCollect(Long id) throws SQLException;

  UserShopCollectPojo findUserShopCollectById(Long merId) throws SQLException;

  List<UserShopCollectPojo> findUserShopCollectByUserId(Long id) throws SQLException;

  UserShopCollectPojo findUserShopCollectByShopId(UserShopCollectPojo userShopCollectPojo)
      throws SQLException;

  List<UserShopCollectPojo> userShopCollectByPage(UserShopCollectPojo userShopCollectPojo,
      Pager page);
}
