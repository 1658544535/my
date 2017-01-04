package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.LoginDao;
import com.tzmb2c.web.mapper.LoginMapper;
import com.tzmb2c.web.pojo.MenuPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;

public class LoginDaoImpl implements LoginDao {

  @Autowired
  private LoginMapper loginMapper;

  @Override
  public SysLoginPojo getLoginPojoByLoginname(String loginname) throws SQLException {
    // TODO Auto-generated method stub
    return loginMapper.getLoginPojoByLoginname(loginname);
  }

  @Override
  public SysLoginPojo getLoginPojoByLoginnameAndPassword(SysLoginPojo loginPojo)
      throws SQLException {
    return loginMapper.getLoginPojoByLoginnameAndPassword(loginPojo);
  }


  @Override
  public int insertLoginPojo(SysLoginPojo loginPojo) throws SQLException {
    return loginMapper.insertLoginPojo(loginPojo);

  }

  @Override
  public void updateLoginPojo(SysLoginPojo loginPojo) throws SQLException {
    loginMapper.updateLoginPojo(loginPojo);
  }

  @Override
  public void deleteLoginPojo(SysLoginPojo loginPojo) throws SQLException {

  }

  @Override
  public List<MenuPojo> getMenuAllByUserId(Long userId) {
    return loginMapper.getMenuAllByUserId(userId);
  }

  @Override
  public List<MenuPojo> getMenuAllBySonLevel(Map<String, String> map) {
    return loginMapper.getMenuAllBySonLevel(map);
  }

  @Override
  public void updatePassword(SysLoginPojo loginPojo) {
    loginMapper.updatePassword(loginPojo);
  }

  @Override
  public SysLoginPojo getLoginPojoByLoginnameAndPasswordAndType(SysLoginPojo loginPojo)
      throws SQLException {
    return loginMapper.getLoginPojoByLoginnameAndPasswordAndType(loginPojo);
  }

  @Override
  public void updateOauthBrand(SysLoginPojo loginPojo) throws SQLException {
    loginMapper.updateOauthBrand(loginPojo);
  }

  /**
   * 更新百度id
   */
  @Override
  public void updateBaiduUid(SysLoginPojo loginPojo) throws SQLException {
    loginMapper.updateBaiduUid(loginPojo);

  }

}
