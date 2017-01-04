package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.HistoryDao;
import com.tzmb2c.web.pojo.HistoryPojo;
import com.tzmb2c.web.service.HistoryService;

public class HistoryServiceImpl implements HistoryService {

  @Autowired
  private HistoryDao historyDao;

  @Override
  public void insert(HistoryPojo historyPojo) throws SQLException {
    // TODO Auto-generated method stub
    historyDao.insert(historyPojo);
  }

  @Override
  public void delHistory(HistoryPojo historyPojo) throws SQLException {
    // TODO Auto-generated method stub
    historyDao.delHistory(historyPojo);
  }

  @Override
  public List<HistoryPojo> historyAllList(HistoryPojo historyPojo, Pager page) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (historyPojo != null) {
      map.put("userName", historyPojo.getUserName());// 用户昵称
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<HistoryPojo> list = historyDao.historyAllList(map);
    if (list != null) {
      // 判断信息是否被审核
      // for(PagePushPojo no:list){
      // BusinessDictPojo sysDictPojo = new BusinessDictPojo();
      // sysDictPojo.setBusinessDictId(m.getNoticeType());
      // BusinessDictPojo
      // syddsffDictPojo=businessDictDao.sysDictById(sysDictPojo);
      // m.setNoticeType(syddsffDictPojo.getBusinessDictName());
      // if(m.getNoticeState().equals("0")){
      // no.setNoticeState("未审核");
      // }else{
      // no.setNoticeState("已审核");
      // }
      // }
    }
    return list;
  }

  @Override
  public int historyAllCount(HistoryPojo historyPojo) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (historyPojo != null) {
      map.put("userName", historyPojo.getUserName());// 用户昵称
    }
    return historyDao.historyAllCount(map);
  }

  @Override
  public void delAllHistoryById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        historyDao.delAllHistoryById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public List<HistoryPojo> historyUserList(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return historyDao.historyUserList(id);
  }

  @Override
  public HistoryPojo findHistoryPojoById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return historyDao.findHistoryById(id);
  }

  @Override
  public void checkHistoryPojo(HistoryPojo historyPojo) throws SQLException {
    // TODO Auto-generated method stub
    historyDao.checkHistory(historyPojo);
  }

  @Override
  public void checkAllHistoryPojoById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        historyDao.checkAllHistoryById(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public List<HistoryPojo> historyUserList2(HistoryPojo historyPojo, Pager page)
      throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (historyPojo != null) {
      map.put("userId", historyPojo.getUserId());// 用户昵称
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    return historyDao.historyUserList2(map);
  }

  @Override
  public int checkhistory(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return historyDao.checkhistory(map);
  }

  @Override
  public int myFootprint(HistoryPojo historyPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (historyPojo != null) {
      map.put("userId", historyPojo.getUserId());// 用户昵称
    }
    // TODO Auto-generated method stub
    return historyDao.checkhistory(map);
  }

  @Override
  public void inserthistory(Map<String, Object> map) {
    // TODO Auto-generated method stub
    historyDao.inserthistory(map);
  }

  @Override
  public void updatehistory(Map<String, Object> map) {
    // TODO Auto-generated method stub
    historyDao.updatehistory(map);
  }
}
