package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HelpTypeDao;
import com.tzmb2c.web.pojo.HelpTypePojo;
import com.tzmb2c.web.service.HelpTypeService;

public class HelpTypeServiceImpl implements HelpTypeService {

  @Autowired
  private HelpTypeDao helpTypeDao;

  @Override
  public int getHelpTypeCount(Long pid) throws SQLException {
    return helpTypeDao.getHelpTypeCount(pid);
  }

  @Override
  public List<HelpTypePojo> getHelpTypeByPid(Long pid) throws SQLException {
    return helpTypeDao.getHelpTypeByPid(pid);
  }

  @Override
  public HelpTypePojo findHelpType(HelpTypePojo helpTypePojo) throws SQLException {
    return helpTypeDao.findHelpType(helpTypePojo);
  }

  @Override
  public void addHelpType(HelpTypePojo helpType) throws SQLException {
    helpTypeDao.addHelpType(helpType);
  }

  @Override
  public void helpTypeUpdate(HelpTypePojo helpType) throws SQLException {
    helpTypeDao.helpTypeUpdate(helpType);
  }

  @Override
  public void deleHelpType(Long id) throws SQLException {
    helpTypeDao.deleHelpType(id);
  }

  @Override
  public void checkHelpType(Long id) throws SQLException {
    helpTypeDao.checkHelpType(id);
  }

  @Override
  public void checkAllById(String[] tids) {
    for (String tid : tids) {
      try {
        helpTypeDao.checkAll(tid);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public List<HelpTypePojo> getHelpTypeByPidAndStatus(HelpTypePojo helpTypePojo)
      throws SQLException {
    // TODO Auto-generated method stub
    return helpTypeDao.getHelpTypeByPidAndStatus(helpTypePojo);
  }
}
