package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HelpPojo;

public interface HelpDao {

  // ------后台方法------//
  public int helpAllListCount(Map<String, Object> map);

  public List<HelpPojo> helpAllList(Map<String, Object> map);

  public void helpAdd(HelpPojo helpPojo);

  public HelpPojo goHelpUpdate(HelpPojo helpPojo);

  public void helpUpdate(HelpPojo helpPojo);

  public void helpDelete(HelpPojo helpPojo);

  public HelpPojo findHelpById(Long id);

  public void checkHelpInfo(HelpPojo helpPojo) throws SQLException;

  public void checkAllHelpInfoById(String id) throws SQLException;

  // ------前端方法------//
  public List<HelpPojo> helpPageList(Map<String, Object> map) throws SQLException;

  public List<HelpPojo> getListByTypeid(Long typeid) throws SQLException;

  public List<HelpPojo> getUsualList() throws SQLException;

  public List<HelpPojo> getHotList() throws SQLException;

  public List<HelpPojo> helpSearch(String searchkey) throws SQLException;
}
