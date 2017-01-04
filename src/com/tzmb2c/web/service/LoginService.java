package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.web.pojo.MenuPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;

public interface LoginService {

  public boolean loginCheckService(SysLoginPojo loginPojo) throws Exception;

  public boolean loginCheckWeb(SysLoginPojo loginPojo) throws Exception;

  public int insertLoginPojo(SysLoginPojo loginPojo) throws SQLException;

  public SysLoginPojo getUserPojoService(Long userId) throws Exception;

  public List<MenuPojo> getMenuService(Long userId) throws Exception;

  public void updatePassword(SysLoginPojo loginPojo) throws Exception;

  public void updateLoginPojo(SysLoginPojo loginPojo) throws Exception;

  public SysLoginPojo getLoginPojoByLoginname(String loginname) throws Exception;

  public SysLoginPojo getLoginPojoByLoginnameAndPassword(SysLoginPojo loginPojo) throws Exception;

  public void updateOauthBrand(SysLoginPojo loginPojo) throws Exception;

  /**
   * 更新百度id
   * 
   * @param loginPojo
   * @throws SQLException
   */
  public void updateBaiduUid(SysLoginPojo loginPojo) throws SQLException;
}
