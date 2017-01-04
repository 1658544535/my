package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HelpPojo;

public interface HelpService {

  public int helpAllListCount(Map<String, Object> map);

  public List<HelpPojo> helpAllList(Map<String, Object> map);

  public void helpAdd(HelpPojo helpPojo);

  public HelpPojo goHelpUpdate(HelpPojo helpPojo);

  public void helpUpdate(HelpPojo helpPojo);

  public void helpDelete(HelpPojo helpPojo);

  public HelpPojo findHelpInfoById(Long id) throws SQLException;

  public void checkHelpInfo(HelpPojo helpPojo) throws SQLException;

  public void checkAllHelpInfoById(String[] tids);

  // ------前端方法------//
  // public List<InfoPojo> infoPageList(InfoPojo infoPojo, Pager page);
  public List<HelpPojo> helpPageList(Map<String, Object> map) throws SQLException;

  public List<HelpPojo> getListByTypeid(Long typeid) throws SQLException;

  public List<HelpPojo> getUsualList() throws SQLException;

  public List<HelpPojo> getHotList() throws SQLException;

  public List<HelpPojo> helpSearch(String searchkey) throws SQLException;
}
