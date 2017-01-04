package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.AccountSecurityPojo;

public interface AccountSecurityDao {

  // -----后台方法-----//
  public List<AccountSecurityPojo> securityQuesionAllList(Map<String, Object> map);

  public int securityQuesionAllCount(Map<String, Object> map);

  public void delQuesion(AccountSecurityPojo accountSecurityPojo) throws SQLException;

  public void delAllQuesionById(String id) throws SQLException;

  public void verifySecurityQuesion(AccountSecurityPojo accountSecurityPojo) throws SQLException;

  public void checkAllQuesion(String id) throws SQLException;

  public List<AccountSecurityPojo> securityQuesionbyUserId(String id) throws SQLException;

  // -----前端页面调用-----//
  public List<AccountSecurityPojo> securityQuesionListWeb(Map<String, Object> map);

  public void insertUserQuestion(List<AccountSecurityPojo> userQuestionList);
}
