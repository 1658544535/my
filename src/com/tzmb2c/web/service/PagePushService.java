package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.PagePushPojo;

public interface PagePushService {

  void addPushNotice(PagePushPojo pagePushPojo) throws SQLException;

  List<PagePushPojo> queryNotice(PagePushPojo notice) throws SQLException;

  PagePushPojo findNoticeById(Long id) throws SQLException;

  void updatePushNotice(PagePushPojo pagePushPojo) throws SQLException;

  // void delNotice(String id) throws SQLException;
  void delNotice(PagePushPojo pagePushPojo) throws SQLException;

  void verifyNotice(PagePushPojo pagePushPojo) throws SQLException;

  public List<PagePushPojo> pushNoticeAllList(PagePushPojo pagePushPojo, Pager page, String type);

  public int pushNoticeAllCount(PagePushPojo pagePushPojo, String type);

  public void delAllNoticeById(String[] tids);

  public void checkAllNoticeById(String[] tids);

  List<PagePushPojo> findNoticeItem(int item);

  public List<PagePushPojo> findAdByType(Integer type) throws SQLException;

  public List<PagePushPojo> findAdByTypeApi(Map<String, Object> map) throws SQLException;

  public List<PagePushPojo> findTopThreeDate(Integer type) throws SQLException;
}
