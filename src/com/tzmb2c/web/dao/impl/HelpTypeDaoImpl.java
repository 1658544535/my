package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HelpTypeDao;
import com.tzmb2c.web.mapper.HelpTypeMapper;
import com.tzmb2c.web.pojo.HelpTypePojo;

public class HelpTypeDaoImpl implements HelpTypeDao {

  @Autowired
  private HelpTypeMapper helpTypeMapper;

  @Override
  public int getHelpTypeCount(Long pid) throws SQLException {
    // TODO Auto-generated method stub
    return helpTypeMapper.getHelpTypeCount(pid);
  }

  @Override
  public List<HelpTypePojo> getHelpTypeByPid(Long pid) throws SQLException {
    // TODO Auto-generated method stub
    return helpTypeMapper.getHelpTypeByPid(pid);
  }

  @Override
  public HelpTypePojo findHelpType(HelpTypePojo helpTypePojo) throws SQLException {
    // TODO Auto-generated method stub
    return helpTypeMapper.findHelpType(helpTypePojo);
  }

  @Override
  public void addHelpType(HelpTypePojo helpType) throws SQLException {
    // TODO Auto-generated method stub
    helpTypeMapper.addHelpType(helpType);
  }

  @Override
  public void deleHelpType(Long id) throws SQLException {
    // TODO Auto-generated method stub
    helpTypeMapper.deleHelpType(id);
  }

  @Override
  public void helpTypeUpdate(HelpTypePojo helpType) throws SQLException {
    // TODO Auto-generated method stub
    helpTypeMapper.helpTypeUpdate(helpType);
  }

  @Override
  public void checkHelpType(Long id) throws SQLException {
    // TODO Auto-generated method stub
    helpTypeMapper.checkHelpType(id);
  }

  @Override
  public void checkAll(String id) throws SQLException {
    helpTypeMapper.checkAllById(id);

  }

  @Override
  public List<HelpTypePojo> getHelpTypeByPidAndStatus(HelpTypePojo helpTypePojo)
      throws SQLException {
    // TODO Auto-generated method stub
    return helpTypeMapper.getHelpTypeByPidAndStatus(helpTypePojo);
  }
}
