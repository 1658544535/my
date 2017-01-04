package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.PagePushDao;
import com.tzmb2c.web.mapper.PagePushMapper;
import com.tzmb2c.web.pojo.PagePushPojo;

public class PagePushDaoImpl implements PagePushDao {

  @Autowired
  private PagePushMapper pagePushMapper;

  @Override
  public void addPushNotice(PagePushPojo pagePushPojo) throws SQLException {
    pagePushMapper.addPushNotice(pagePushPojo);
  }

  @Override
  public List<PagePushPojo> queryNotice(PagePushPojo notice) {
    return pagePushMapper.queryNotice(notice);
  }

  @Override
  public PagePushPojo findNoticeById(Long id) {
    return pagePushMapper.findNoticeById(id);
  }

  @Override
  public void updatePushNotice(PagePushPojo pagePushPojo) throws SQLException {
    pagePushMapper.updatePushNotice(pagePushPojo);
  }

  @Override
  public void delNotice(PagePushPojo pagePushPojo) throws SQLException {
    pagePushMapper.delNotice(pagePushPojo);
  }

  @Override
  public List<PagePushPojo> pushNoticeAllList(Map<String, Object> map) {
    return pagePushMapper.pushNoticeAllList(map);
  }

  @Override
  public int pushNoticeAllCount(Map<String, Object> map) {
    return pagePushMapper.pushNoticeAllCount(map);
  }

  @Override
  public List<PagePushPojo> findNoticeItem(int item) {
    return pagePushMapper.findNoticeItem(item);
  }

  @Override
  public void delAllNotice(String id) throws SQLException {
    // TODO Auto-generated method stub
    pagePushMapper.delAllNoticeById(id);
  }

  @Override
  public void verifyNotice(PagePushPojo pagePushPojo) throws SQLException {
    // TODO Auto-generated method stub
    pagePushMapper.verifyNotice(pagePushPojo);
  }

  @Override
  public void checkAllNotice(String id) throws SQLException {
    // TODO Auto-generated method stub
    pagePushMapper.checkAllNoticeById(id);
  }

  @Override
  public List<PagePushPojo> findAdByType(Integer type) throws SQLException {
    return pagePushMapper.findAdByType(type);
  }

  @Override
  public List<PagePushPojo> findTopThreeDate(Integer type) throws SQLException {
    return pagePushMapper.findTopThreeDate(type);
  }

  @Override
  public List<PagePushPojo> findAdByTypeApi(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return pagePushMapper.findAdByTypeApi(map);
  }


}
