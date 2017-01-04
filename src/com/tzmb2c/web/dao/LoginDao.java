package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.MenuPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;

public interface LoginDao {
  public SysLoginPojo getLoginPojoByLoginname(String loginname) throws SQLException;

  public SysLoginPojo getLoginPojoByLoginnameAndPassword(SysLoginPojo loginPojo)
      throws SQLException;

  public SysLoginPojo getLoginPojoByLoginnameAndPasswordAndType(SysLoginPojo loginPojo)
      throws SQLException;

  public int insertLoginPojo(SysLoginPojo loginPojo) throws SQLException;

  public void updateLoginPojo(SysLoginPojo loginPojo) throws SQLException;

  public void deleteLoginPojo(SysLoginPojo loginPojo) throws SQLException;

  public List<MenuPojo> getMenuAllByUserId(Long userId);

  public List<MenuPojo> getMenuAllBySonLevel(Map<String, String> map);

  public void updatePassword(SysLoginPojo loginPojo);

  public void updateOauthBrand(SysLoginPojo loginPojo) throws SQLException;

  /**
   * 更新百度id
   * 
   * @param loginPojo
   * @throws SQLException
   */
  public void updateBaiduUid(SysLoginPojo loginPojo) throws SQLException;
}
