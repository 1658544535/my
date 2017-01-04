package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HelpDao;
import com.tzmb2c.web.mapper.HelpMapper;
import com.tzmb2c.web.pojo.HelpPojo;

public class HelpDaoImpl implements HelpDao {

  @Autowired
  private HelpMapper helpMapper;

  @Override
  public List<HelpPojo> helpAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return helpMapper.helpAllList(map);
  }

  @Override
  public int helpAllListCount(Map<String, Object> map) {
    return helpMapper.helpAllListCount(map);
  }

  @Override
  public void helpAdd(HelpPojo helpPojo) {
    // TODO Auto-generated method stub
    helpMapper.helpAdd(helpPojo);
  }

  @Override
  public HelpPojo goHelpUpdate(HelpPojo helpPojo) {
    // TODO Auto-generated method stub
    return helpMapper.goHelpUpdate(helpPojo);
  }

  @Override
  public void helpUpdate(HelpPojo helpPojo) {
    // TODO Auto-generated method stub
    helpMapper.helpUpdate(helpPojo);
  }

  @Override
  public void helpDelete(HelpPojo helpPojo) {
    // TODO Auto-generated method stub
    helpMapper.helpDelete(helpPojo);
  }

  @Override
  public HelpPojo findHelpById(Long id) {
    // TODO Auto-generated method stub
    return helpMapper.findHelpById(id);
  }

  @Override
  public void checkHelpInfo(HelpPojo helpPojo) throws SQLException {
    // TODO Auto-generated method stub
    helpMapper.checkHelpInfo(helpPojo);
  }

  @Override
  public void checkAllHelpInfoById(String id) throws SQLException {
    // TODO Auto-generated method stub
    helpMapper.checkAllHelpInfoById(id);
  }

  @Override
  public List<HelpPojo> helpPageList(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return helpMapper.helpPageList(map);
  }

  @Override
  public List<HelpPojo> getListByTypeid(Long typeid) throws SQLException {
    return helpMapper.getListByTypeid(typeid);
  };

  @Override
  public List<HelpPojo> getUsualList() throws SQLException {
    return helpMapper.getUsualList();
  };

  @Override
  public List<HelpPojo> getHotList() throws SQLException {
    return helpMapper.getHotList();
  };

  @Override
  public List<HelpPojo> helpSearch(String searchkey) throws SQLException {
    return helpMapper.helpSearch(searchkey);
  }
}
