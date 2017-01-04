package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PagePushPojo;

public interface PagePushDao {

  void addPushNotice(PagePushPojo pagePushPojo) throws SQLException;

  List<PagePushPojo> queryNotice(PagePushPojo notice) throws SQLException;

  PagePushPojo findNoticeById(Long id) throws SQLException;

  void updatePushNotice(PagePushPojo pagePushPojo) throws SQLException;

  void delNotice(PagePushPojo pagePushPojo) throws SQLException;

  void verifyNotice(PagePushPojo pagePushPojo) throws SQLException;

  void delAllNotice(String id) throws SQLException;

  void checkAllNotice(String id) throws SQLException;

  public List<PagePushPojo> pushNoticeAllList(Map<String, Object> map);

  public int pushNoticeAllCount(Map<String, Object> map);

  List<PagePushPojo> findNoticeItem(int item);

  public List<PagePushPojo> findAdByType(Integer type) throws SQLException;

  public List<PagePushPojo> findAdByTypeApi(Map<String, Object> map) throws SQLException;

  public List<PagePushPojo> findTopThreeDate(Integer type) throws SQLException;
}
