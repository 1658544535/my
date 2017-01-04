package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.HistoryPojo;

public interface HistoryDao {

  public void delHistory(HistoryPojo historyPojo) throws SQLException;

  public void delAllHistoryById(String id) throws SQLException;

  public List<HistoryPojo> historyAllList(Map<String, Object> map);

  public int historyAllCount(Map<String, Object> map);

  public HistoryPojo findHistoryById(Long id);

  public void checkAllHistoryById(String id) throws SQLException;

  public void checkHistory(HistoryPojo historyPojo) throws SQLException;

  List<HistoryPojo> historyUserList(Long id);

  void insert(HistoryPojo historyPojo) throws SQLException;

  List<HistoryPojo> historyUserList2(Map<String, Object> map);

  public int checkhistory(Map<String, Object> map);

  public void inserthistory(Map<String, Object> map);

  public void updatehistory(Map<String, Object> map);
}
