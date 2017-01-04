package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.HistoryDao;
import com.tzmb2c.web.mapper.HistoryMapper;
import com.tzmb2c.web.pojo.HistoryPojo;

public class HistoryDaoImpl implements HistoryDao {

  @Autowired
  private HistoryMapper historyMapper;

  @Override
  public void insert(HistoryPojo historyPojo) throws SQLException {
    // TODO Auto-generated method stub
    historyMapper.insert(historyPojo);
  }

  @Override
  public void delHistory(HistoryPojo historyPojo) throws SQLException {
    // TODO Auto-generated method stub
    historyMapper.delHistory(historyPojo);
  }

  @Override
  public void delAllHistoryById(String id) throws SQLException {
    // TODO Auto-generated method stub
    historyMapper.delAllHistoryById(id);
  }

  @Override
  public List<HistoryPojo> historyAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return historyMapper.historyAllList(map);
  }

  @Override
  public List<HistoryPojo> historyUserList(Long id) {
    // TODO Auto-generated method stub
    return historyMapper.historyUserList(id);
  }

  @Override
  public int historyAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return historyMapper.historyAllCount(map);
  }

  @Override
  public HistoryPojo findHistoryById(Long id) {
    // TODO Auto-generated method stub
    return historyMapper.findHistoryById(id);
  }

  @Override
  public void checkAllHistoryById(String id) throws SQLException {
    // TODO Auto-generated method stub
    historyMapper.checkAllHistoryById(id);
  }

  @Override
  public void checkHistory(HistoryPojo historyPojo) throws SQLException {
    // TODO Auto-generated method stub
    historyMapper.checkHistory(historyPojo);
  }

  @Override
  public List<HistoryPojo> historyUserList2(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return historyMapper.historyUserList2(map);
  }

  @Override
  public int checkhistory(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return historyMapper.checkhistory(map);
  }

  @Override
  public void inserthistory(Map<String, Object> map) {
    // TODO Auto-generated method stub
    historyMapper.inserthistory(map);
  }

  @Override
  public void updatehistory(Map<String, Object> map) {
    // TODO Auto-generated method stub
    historyMapper.updatehistory(map);
  }

}
