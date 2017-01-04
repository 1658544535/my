package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SysSecurityQuestionPojo;

public interface SysSecurityQuestionService {

  // -----后台方法-----//
  public List<SysSecurityQuestionPojo> sysSecurityQuestionAllList(
      SysSecurityQuestionPojo sysSecurityQuestionPojo, Pager page, String type) throws SQLException;

  public int sysSecurityQuestionAllCount(SysSecurityQuestionPojo sysSecurityQuestionPojo,
      String type) throws SQLException;

  public void delSysSecurityQuestion(SysSecurityQuestionPojo sysSecurityQuestionPojo)
      throws SQLException;

  public void delAllSysSecurityQuestionById(String[] tids) throws SQLException;

  // -----前端页面调用-----//
  public List<SysSecurityQuestionPojo> sysSecurityQuestionListWeb(
      SysSecurityQuestionPojo sysSecurityQuestionPojo) throws SQLException;

}
