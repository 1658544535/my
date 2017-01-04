package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PushHomePageDao;
import com.tzmb2c.web.mapper.PushHomePageMapper;
import com.tzmb2c.web.pojo.PushHomePagePojo;


public class PushHomePageDaoImpl implements PushHomePageDao {

  @Autowired
  private PushHomePageMapper pushHomePageMapper;

  @Override
  public List<PushHomePagePojo> findAll(Map<String, Object> map) throws SQLException {
    return pushHomePageMapper.findAll(map);
  }

  @Override
  public int findAllCount(Map<String, Object> map) throws SQLException {
    return pushHomePageMapper.findAllCount(map);
  }

  @Override
  public void insertPushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException {
    pushHomePageMapper.insertPushHomePage(pushHomePagePojo);
  }

  @Override
  public void delPushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException {
    pushHomePageMapper.delPushHomePage(pushHomePagePojo);
  }

  @Override
  public PushHomePagePojo findPushHomePageById(Long id) throws SQLException {
    return pushHomePageMapper.findPushHomePageById(id);
  }

  @Override
  public void updatePushHomePage(PushHomePagePojo pushHomePagePojo) throws SQLException {
    pushHomePageMapper.updatePushHomePage(pushHomePagePojo);
  }

  @Override
  public void checkPushHomePage(Long id) throws SQLException {
    pushHomePageMapper.checkPushHomePage(id);

  }

  @Override
  public void uncheckPushHomePage(Long id) throws SQLException {
    pushHomePageMapper.uncheckPushHomePage(id);

  }

  @Override
  public void pushHomePageDeleteId(Long id) throws SQLException {
    // TODO Auto-generated method stub
    pushHomePageMapper.pushHomePageDeleteId(id);
  }
}
