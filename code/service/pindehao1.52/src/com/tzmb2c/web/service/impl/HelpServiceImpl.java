package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HelpDao;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.service.HelpService;

public class HelpServiceImpl implements HelpService {

  @Autowired
  private HelpDao helpDao;

  @Override
  public void helpAdd(HelpPojo helpPojo) {
    // TODO Auto-generated method stub
    helpDao.helpAdd(helpPojo);
  }

  @Override
  public HelpPojo goHelpUpdate(HelpPojo helpPojo) {
    // TODO Auto-generated method stub
    return helpDao.goHelpUpdate(helpPojo);

  }

  @Override
  public void helpUpdate(HelpPojo helpPojo) {
    // TODO Auto-generated method stub
    helpDao.helpUpdate(helpPojo);
  }

  @Override
  public void helpDelete(HelpPojo helpPojo) {
    // TODO Auto-generated method stub
    helpDao.helpDelete(helpPojo);
  }

  @Override
  public int helpAllListCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return helpDao.helpAllListCount(map);
  }

  @Override
  public List<HelpPojo> helpAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return helpDao.helpAllList(map);
  }

  @Override
  public HelpPojo findHelpInfoById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return helpDao.findHelpById(id);
  }

  @Override
  public void checkHelpInfo(HelpPojo helpPojo) throws SQLException {
    // TODO Auto-generated method stub
    helpDao.checkHelpInfo(helpPojo);
  }

  @Override
  public void checkAllHelpInfoById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        helpDao.checkAllHelpInfoById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public List<HelpPojo> helpPageList(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    List<HelpPojo> list = helpDao.helpPageList(map);
    if (list != null) {
      // 判断信息是否被审核
    }
    return list;
  }

  @Override
  public List<HelpPojo> getListByTypeid(Long typeid) throws SQLException {
    return helpDao.getListByTypeid(typeid);
  };

  @Override
  public List<HelpPojo> getUsualList() throws SQLException {
    return helpDao.getUsualList();
  };

  @Override
  public List<HelpPojo> getHotList() throws SQLException {
    return helpDao.getHotList();
  }

  @Override
  public List<HelpPojo> helpSearch(String searchkey) throws SQLException {
    return helpDao.helpSearch(searchkey);
  };
}
