package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserMakerThemePojo;

public interface UserMakerThemeDao {

  public int addUserMakerTheme(UserMakerThemePojo userMakerTheme);

  public int updateUserMakerTheme(UserMakerThemePojo userMakerTheme);

  int increaseUserMakerThemeNumById(UserMakerThemePojo userMakerTheme);

  int decreaseUserMakerThemeNumById(UserMakerThemePojo userMakerTheme);

  public int deleteUserMakerThemeById(Long id);

  public UserMakerThemePojo getUserMakerThemeById(Long id);

  public Integer userMakerThemeCount(Map<String, Object> params);

  public List<UserMakerThemePojo> userMakerThemeList(Map<String, Object> params);

  /**
   * 
   * 根据专题ID更新审核状态
   * 
   * @param userMakerTheme 专题POJO(参数：id、status)R
   * @throws SQLException
   * @throw
   * @return void
   */
  public int updateUserMakerThemeById(UserMakerThemePojo userMakerTheme);
}
