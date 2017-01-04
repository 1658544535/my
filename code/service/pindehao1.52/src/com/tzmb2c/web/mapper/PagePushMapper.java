package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.PagePushPojo;

public interface PagePushMapper {


  void addPushNotice(PagePushPojo pagePushPojo) throws SQLException;

  List<PagePushPojo> queryNotice(PagePushPojo notice);

  PagePushPojo findNoticeById(Long id);

  // void updateShopMoney(ShopMoneyPojo shopMoney);

  void updatePushNotice(PagePushPojo pagePushPojo);

  // List<ShopPojo> getShopByShopType(String shopType);

  void delAllNoticeById(String id) throws SQLException;

  void checkAllNoticeById(String id) throws SQLException;

  void delNotice(PagePushPojo pagePushPojo) throws SQLException;

  void verifyNotice(PagePushPojo pagePushPojo) throws SQLException;

  public List<PagePushPojo> pushNoticeAllList(Map<String, Object> map);

  public int pushNoticeAllCount(Map<String, Object> map);

  List<PagePushPojo> findNoticeItem(int item);

  public List<PagePushPojo> findAdByType(Integer type) throws SQLException;

  public List<PagePushPojo> findAdByTypeApi(Map<String, Object> map) throws SQLException;

  public List<PagePushPojo> findTopThreeDate(Integer type) throws SQLException;
}
