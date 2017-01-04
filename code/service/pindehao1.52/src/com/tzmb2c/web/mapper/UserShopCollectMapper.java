package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserShopCollectPojo;

public interface UserShopCollectMapper {


  public List<UserShopCollectPojo> getUserShopCollectAll() throws SQLException;

  public void insertUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException;

  public void updateUserShopCollect(UserShopCollectPojo userShopCollectPojo) throws SQLException;

  public UserShopCollectPojo getfindByIdUserShopCollect(Long id) throws SQLException;

  public UserShopCollectPojo findUserShopCollectByShopId(UserShopCollectPojo userShopCollectPojo)
      throws SQLException;

  public List<UserShopCollectPojo> getfindByUserIdUserShopCollect(Long id) throws SQLException;

  public void deleteUserShopCollect(Map<String, Object> map) throws SQLException;

  public int userShopCollectAllCount(Map<String, Object> map);

  public List<UserShopCollectPojo> userShopCollectAllList(Map<String, Object> map);

  void delUserShopCollect(Long id) throws SQLException;

  public List<UserShopCollectPojo> userShopCollectByPage(Map<String, Object> map);
}
