package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.AccountSecurityDao;
import com.tzmb2c.web.pojo.AccountSecurityPojo;
import com.tzmb2c.web.service.AccountSecurityService;

public class AccountSecurityServiceImpl implements AccountSecurityService {

  @Autowired
  private AccountSecurityDao accountSecurityDao;

  @Override
  public void delQuesion(AccountSecurityPojo accountSecurityPojo) throws SQLException {
    accountSecurityDao.delQuesion(accountSecurityPojo);
  }

  @Override
  public List<AccountSecurityPojo> securityQuesionAllList(AccountSecurityPojo accountSecurityPojo,
      Pager page, String type) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (accountSecurityPojo != null) {
      map.put("userId", accountSecurityPojo.getUserId());// 用户ID
      map.put("userName", accountSecurityPojo.getUserName());// 用户昵称
      map.put("quesionKeyWord", accountSecurityPojo.getQuesion());// 问题的关键字
      map.put("quesionStatus", accountSecurityPojo.getStatus());// 状态:1、审核;2、未审核
    }
    map.put("type", type);// 用户类型
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<AccountSecurityPojo> list = accountSecurityDao.securityQuesionAllList(map);
    if (list != null) {
      // 判断信息是否被审核
    }
    return list;
  }

  @Override
  public int securityQuesionAllCount(AccountSecurityPojo accountSecurityPojo, String type) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (accountSecurityPojo != null) {
      map.put("userId", accountSecurityPojo.getUserId());// 用户ID
      map.put("userName", accountSecurityPojo.getUserName());// 用户昵称
      map.put("quesionKeyWord", accountSecurityPojo.getQuesion());// 问题的关键字
      map.put("quesionStatus", accountSecurityPojo.getStatus());// 状态:1、审核;2、未审核
    }
    map.put("type", type);// 用户类型
    return accountSecurityDao.securityQuesionAllCount(map);
  }

  @Override
  public void delAllQuesionById(String[] tids) {
    for (String tid : tids) {
      try {
        accountSecurityDao.delAllQuesionById(tid);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void verifySecurityQuesion(AccountSecurityPojo accountSecurityPojo) throws SQLException {
    accountSecurityDao.verifySecurityQuesion(accountSecurityPojo);
  }

  @Override
  public void checkAllQuesionById(String[] tids) {
    for (String tid : tids) {
      try {
        accountSecurityDao.checkAllQuesion(tid);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public List<AccountSecurityPojo> securityQuesionListWeb(AccountSecurityPojo accountSecurityPojo,
      String type) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", type);// 用户类型
    List<AccountSecurityPojo> list = accountSecurityDao.securityQuesionListWeb(map);
    return list;
  }

  @Override
  public void insertUserQuestion(List<AccountSecurityPojo> userQuestionList) {
    // TODO Auto-generated method stub
    accountSecurityDao.insertUserQuestion(userQuestionList);
  }

  @Override
  public List<AccountSecurityPojo> securityQuesionbyUserId(String id) throws SQLException {
    return accountSecurityDao.securityQuesionbyUserId(id);
  }

}
