package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.HistoryPojo;

public interface HistoryService {

  public void delHistory(HistoryPojo historyPojo) throws SQLException;

  public List<HistoryPojo> historyAllList(HistoryPojo historyPojo, Pager page);

  public int historyAllCount(HistoryPojo historyPojo);

  public void delAllHistoryById(String[] tids);

  public HistoryPojo findHistoryPojoById(Long id) throws SQLException;

  public void checkHistoryPojo(HistoryPojo historyPojo) throws SQLException;

  public void checkAllHistoryPojoById(String[] tids);

  List<HistoryPojo> historyUserList(Long id) throws SQLException;

  void insert(HistoryPojo historyPojo) throws SQLException;

  List<HistoryPojo> historyUserList2(HistoryPojo historyPojo, Pager page) throws SQLException;

  public int checkhistory(Map<String, Object> map);

  public int myFootprint(HistoryPojo historyPojo);

  public void inserthistory(Map<String, Object> map);

  public void updatehistory(Map<String, Object> map);

}
