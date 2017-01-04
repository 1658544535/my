package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SysSecurityQuestionDao;
import com.tzmb2c.web.mapper.SysSecurityQuestionMapper;
import com.tzmb2c.web.pojo.SysSecurityQuestionPojo;

public class SysSecurityQuestionDaoImpl implements SysSecurityQuestionDao {

  @Autowired
  private SysSecurityQuestionMapper sysSecurityQuestionMapper;

  @Override
  public List<SysSecurityQuestionPojo> sysSecurityQuestionAllList(Map<String, Object> map)
      throws SQLException {
    // TODO Auto-generated method stub
    return sysSecurityQuestionMapper.sysSecurityQuestionAllList(map);
  }

  @Override
  public int sysSecurityQuestionAllCount(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return sysSecurityQuestionMapper.sysSecurityQuestionAllCount(map);
  }

  @Override
  public void delSysSecurityQuestion(SysSecurityQuestionPojo sysSecurityQuestionPojo)
      throws SQLException {
    // TODO Auto-generated method stub
    sysSecurityQuestionMapper.delSysSecurityQuestion(sysSecurityQuestionPojo);
  }

  @Override
  public void delAllSysSecurityQuestionById(String id) throws SQLException {
    // TODO Auto-generated method stub
    sysSecurityQuestionMapper.delAllSysSecurityQuestionById(id);
  }

  @Override
  public List<SysSecurityQuestionPojo> sysSecurityQuestionListWeb(Map<String, Object> map)
      throws SQLException {
    // TODO Auto-generated method stub
    return sysSecurityQuestionMapper.sysSecurityQuestionListWeb(map);
  }

}
