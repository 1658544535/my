package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SysSecurityQuestionPojo;

public interface SysSecurityQuestionMapper {

  // -----后台方法-----//
  public List<SysSecurityQuestionPojo> sysSecurityQuestionAllList(Map<String, Object> map)
      throws SQLException;

  public int sysSecurityQuestionAllCount(Map<String, Object> map) throws SQLException;

  public void delSysSecurityQuestion(SysSecurityQuestionPojo sysSecurityQuestionPojo)
      throws SQLException;

  public void delAllSysSecurityQuestionById(String id) throws SQLException;

  // -----前端页面调用-----//
  public List<SysSecurityQuestionPojo> sysSecurityQuestionListWeb(Map<String, Object> map)
      throws SQLException;

}
