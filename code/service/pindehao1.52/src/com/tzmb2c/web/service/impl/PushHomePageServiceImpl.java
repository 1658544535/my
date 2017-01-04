package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PushHomePageDao;
import com.tzmb2c.web.pojo.PushHomePagePojo;
import com.tzmb2c.web.service.PushHomePageService;


public class PushHomePageServiceImpl implements PushHomePageService {
  @Autowired
  private PushHomePageDao pushHomePageDao;

  @Override
  public List<PushHomePagePojo> findAll(Map<String, Object> map) throws SQLException {
    return pushHomePageDao.findAll(map);
  }

  @Override
  public int findAllCount(Map<String, Object> map) throws SQLException {
    return pushHomePageDao.findAllCount(map);
  }

  @Override
  public void insertPushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException {
    pushHomePageDao.insertPushHomePage(pushHomePagePojo);
  }

  @Override
  public void delPushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException {

    pushHomePageDao.delPushHomePage(pushHomePagePojo);
  }

  @Override
  public PushHomePagePojo findPushHomePageById(Long id) throws SQLException {

    return pushHomePageDao.findPushHomePageById(id);
  }

  @Override
  public void updatePushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException {

    pushHomePageDao.updatePushHomePage(pushHomePagePojo);
  }

  @Override
  public void checkPushHomePage(Long id) throws SQLException {
    pushHomePageDao.checkPushHomePage(id);

  }

  @Override
  public void uncheckPushHomePage(Long id) throws SQLException {
    pushHomePageDao.uncheckPushHomePage(id);

  }

  @Override
  public void pushHomePageDeleteId(String[] tids) throws SQLException {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        pushHomePageDao.pushHomePageDeleteId(Long.parseLong(tid));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }



}
