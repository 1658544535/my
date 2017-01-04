package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.AccountSecurityDao;
import com.tzmb2c.web.mapper.AccountSecurityMapper;
import com.tzmb2c.web.pojo.AccountSecurityPojo;

public class AccountSecurityDaoImpl implements AccountSecurityDao {

  @Autowired
  private AccountSecurityMapper accountSecurityMapper;

  @Override
  public void delQuesion(AccountSecurityPojo accountSecurityPojo) throws SQLException {
    // TODO Auto-generated method stub
    accountSecurityMapper.delQuesion(accountSecurityPojo);
  }

  @Override
  public void delAllQuesionById(String id) throws SQLException {
    // TODO Auto-generated method stub
    accountSecurityMapper.delAllQuesionById(id);
  }

  @Override
  public List<AccountSecurityPojo> securityQuesionAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return accountSecurityMapper.securityQuesionAllList(map);
  }

  @Override
  public int securityQuesionAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return accountSecurityMapper.securityQuesionAllCount(map);
  }

  @Override
  public void verifySecurityQuesion(AccountSecurityPojo accountSecurityPojo) throws SQLException {
    // TODO Auto-generated method stub
    accountSecurityMapper.verifySecurityQuesion(accountSecurityPojo);
  }

  @Override
  public void checkAllQuesion(String id) throws SQLException {
    // TODO Auto-generated method stub
    accountSecurityMapper.checkAllQuesionById(id);
  }

  @Override
  public List<AccountSecurityPojo> securityQuesionListWeb(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return accountSecurityMapper.securityQuesionListWeb(map);
  }

  @Override
  public void insertUserQuestion(List<AccountSecurityPojo> userQuestionList) {
    // TODO Auto-generated method stub
    accountSecurityMapper.insertUserQuestion(userQuestionList);
  }

  @Override
  public List<AccountSecurityPojo> securityQuesionbyUserId(String id) throws SQLException {
    // TODO Auto-generated method stub
    return accountSecurityMapper.securityQuesionbyUserId(id);
  }

}
