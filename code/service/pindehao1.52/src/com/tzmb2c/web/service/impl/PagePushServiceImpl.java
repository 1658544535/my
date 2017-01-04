package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.PagePushDao;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.service.PagePushService;

public class PagePushServiceImpl implements PagePushService {

  @Autowired
  private PagePushDao pagePushDao;

  @Override
  public void addPushNotice(PagePushPojo pagePushPojo) throws SQLException {
    // TODO Auto-generated method stub
    this.pagePushDao.addPushNotice(pagePushPojo);
  }

  @Override
  public List<PagePushPojo> queryNotice(PagePushPojo notice) throws SQLException {
    // TODO Auto-generated method stub
    List<PagePushPojo> list = pagePushDao.queryNotice(notice);
    return list;
  }

  @Override
  public PagePushPojo findNoticeById(Long id) throws SQLException {
    // TODO Auto-generated method stub
    return pagePushDao.findNoticeById(id);
  }

  @Override
  public void updatePushNotice(PagePushPojo pagePushPojo) throws SQLException {
    // TODO Auto-generated method stub
    pagePushDao.updatePushNotice(pagePushPojo);
  }

  @Override
  public void delNotice(PagePushPojo pagePushPojo) throws SQLException {
    // TODO Auto-generated method stub
    pagePushDao.delNotice(pagePushPojo);
  }

  @Override
  public List<PagePushPojo> pushNoticeAllList(PagePushPojo pagePushPojo, Pager page, String type) {
    // TODO Auto-generated method stub

    Map<String, Object> map = new HashMap<String, Object>();
    if (pagePushPojo != null) {
      map.put("noticeTitle", pagePushPojo.getTitle());// 查询的标题
      map.put("noticeType", pagePushPojo.getType());// 查询的类型:(1、首页;2、内页)
    }
    map.put("type", type);
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<PagePushPojo> list = pagePushDao.pushNoticeAllList(map);
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
  public List<PagePushPojo> findNoticeItem(int item) {
    // TODO Auto-generated method stub
    return pagePushDao.findNoticeItem(item);
  }

  @Override
  public int pushNoticeAllCount(PagePushPojo pagePushPojo, String type) {
    // TODO Auto-generated method stub
    Map<String, Object> map = new HashMap<String, Object>();
    if (pagePushPojo != null) {
      map.put("noticeTitle", pagePushPojo.getTitle());// 查询的标题
      map.put("noticeType", pagePushPojo.getType());// 查询的类型:(1、首页;2、内页)
    }
    map.put("type", type);
    return pagePushDao.pushNoticeAllCount(map);
  }

  @Override
  public void delAllNoticeById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        pagePushDao.delAllNotice(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void verifyNotice(PagePushPojo pagePushPojo) throws SQLException {
    // TODO Auto-generated method stub
    pagePushDao.verifyNotice(pagePushPojo);
  }

  @Override
  public void checkAllNoticeById(String[] tids) {
    // TODO Auto-generated method stub
    for (String tid : tids) {
      try {
        pagePushDao.checkAllNotice(tid);
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }

  }

  @Override
  public List<PagePushPojo> findAdByType(Integer type) throws SQLException {
    return pagePushDao.findAdByType(type);
  }

  @Override
  public List<PagePushPojo> findTopThreeDate(Integer type) throws SQLException {
    return pagePushDao.findTopThreeDate(type);
  }

  @Override
  public List<PagePushPojo> findAdByTypeApi(Map<String, Object> map) throws SQLException {
    // TODO Auto-generated method stub
    return pagePushDao.findAdByTypeApi(map);
  }

}
