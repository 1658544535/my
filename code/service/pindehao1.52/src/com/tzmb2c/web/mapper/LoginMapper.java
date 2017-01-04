package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.MenuPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;

/**
 * 
 * 用户登录接口 --相关操作方法自行添加 增删查改
 * 
 */
public interface LoginMapper {

  SysLoginPojo getLoginPojoByLoginname(String loginname) throws SQLException;

  SysLoginPojo getLoginPojoByLoginnameAndPassword(SysLoginPojo loginPojo) throws SQLException;

  SysLoginPojo getLoginPojoByLoginnameAndPasswordAndType(SysLoginPojo loginPojo)
      throws SQLException;

  int insertLoginPojo(SysLoginPojo loginPojo) throws SQLException;

  void updateLoginPojo(SysLoginPojo loginPojo) throws SQLException;

  void deleteLoginPojo(SysLoginPojo loginPojo) throws SQLException;

  List<MenuPojo> getMenuAllByUserId(Long userId);

  List<MenuPojo> getMenuAllBySonLevel(Map<String, String> map);

  void updatePassword(SysLoginPojo loginPojo);

  void updateOauthBrand(SysLoginPojo loginPojo) throws SQLException;

  /**
   * 更新百度id
   * 
   * @param sysLoginPojo
   * @throws SQLException
   */
  public void updateBaiduUid(SysLoginPojo sysLoginPojo) throws SQLException;
}
