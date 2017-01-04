package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.AccountSecurityPojo;

public interface AccountSecurityService {

  // -----后台方法-----//
  public List<AccountSecurityPojo> securityQuesionAllList(AccountSecurityPojo accountSecurityPojo,
      Pager page, String type);

  public int securityQuesionAllCount(AccountSecurityPojo accountSecurityPojo, String type);

  public void delQuesion(AccountSecurityPojo accountSecurityPojo) throws SQLException;

  public void delAllQuesionById(String[] tids);

  public void verifySecurityQuesion(AccountSecurityPojo accountSecurityPojo) throws SQLException;

  public void checkAllQuesionById(String[] tids);

  public List<AccountSecurityPojo> securityQuesionbyUserId(String id) throws SQLException;

  // -----前端页面调用-----//
  public List<AccountSecurityPojo> securityQuesionListWeb(AccountSecurityPojo accountSecurityPojo,
      String type);

  public void insertUserQuestion(List<AccountSecurityPojo> userQuestionList);

}
