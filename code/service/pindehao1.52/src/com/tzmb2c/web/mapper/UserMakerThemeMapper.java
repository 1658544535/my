package com.tzmb2c.web.mapper;

import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.UserMakerThemePojo;

public interface UserMakerThemeMapper {

  UserMakerThemePojo getUserMakerThemeById(Long id);

  int userMakerThemeCount(Map<String, Object> params);

  List<UserMakerThemePojo> userMakerThemeList(Map<String, Object> params);

  int addUserMakerTheme(UserMakerThemePojo userMakerTheme);

  int updateUserMakerTheme(UserMakerThemePojo userMakerTheme);

  int increaseUserMakerThemeNumById(UserMakerThemePojo userMakerTheme);

  int decreaseUserMakerThemeNumById(UserMakerThemePojo userMakerTheme);

  int deleteUserMakerThemeById(Long id);

  /**
   * 
   * 根据专题ID更新审核状态
   * 
   * @param userMakerTheme 专题POJO(参数：id、status)
   * @return
   * @throw
   * @return int
   */
  int updateUserMakerThemeById(UserMakerThemePojo userMakerTheme);
}
