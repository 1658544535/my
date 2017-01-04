package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SearchKeyDao;
import com.tzmb2c.web.pojo.SearchKeyPojo;
import com.tzmb2c.web.service.SearchKeyService;

public class SearchKeyServiceImpl implements SearchKeyService {
  @Autowired
  private SearchKeyDao searchKeyDao;

  public void setSearchKeyDao(SearchKeyDao searchKeyDao) {
    this.searchKeyDao = searchKeyDao;
  }

  @Override
  public int searchKeyAllCount(SearchKeyPojo searchKeyDaoPojo) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (searchKeyDaoPojo != null) {
      map.put("type", searchKeyDaoPojo.getType());
      map.put("keyword", searchKeyDaoPojo.getKeyword());
    }
    return searchKeyDao.searchKeyAllCount(map);
  }

  @Override
  public List<SearchKeyPojo> searchKeyAllList(SearchKeyPojo ticketRulePojo, Pager page) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("type", ticketRulePojo.getType());
      map.put("keyword", ticketRulePojo.getKeyword());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }

    List<SearchKeyPojo> list = searchKeyDao.searchKeyAllList(map);

    return list;
  }

  @Override
  public void searchKeyDeleteId(String[] tids) {
    for (String tid : tids) {
      try {
        searchKeyDao.delSearchKey(Long.parseLong(tid));
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  @Override
  public void delSearchKey(Long id) throws SQLException {
    searchKeyDao.delSearchKey(id);
  }

  @Override
  public void updateSearchKey(SearchKeyPojo searchKeyPojo) throws SQLException {
    searchKeyDao.updateSearchKey(searchKeyPojo);

  }

  @Override
  public SearchKeyPojo getfindByIdSearchKey(Long id) throws SQLException {
    return searchKeyDao.getfindByIdSearchKey(id);

  }

  // /////////////////////////////////////分割线/////////////////////////////////////////
  // 热门排行记录
  // public int searchKeyAllCountHot(SearchKeyPojo searchKeyDaoPojo,String os) {
  // Map<String, Object> map = new HashMap<String, Object>();
  // if(searchKeyDaoPojo!=null){
  // map.put("keyword", searchKeyDaoPojo.getKeyword());
  // }
  // if(os!=null&!os.equals(""))
  // map.put("os", Integer.parseInt(os));
  // return searchKeyDao.searchKeyAllCountHot(map);
  // }

  @Override
  public List<SearchKeyPojo> searchKeyAllListHot(SearchKeyPojo ticketRulePojo, Pager page, String os) {

    Map<String, Object> map = new HashMap<String, Object>();
    if (ticketRulePojo != null) {
      map.put("keyword", ticketRulePojo.getKeyword());
    }
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    if (os != null & !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }

    List<SearchKeyPojo> list = searchKeyDao.searchKeyAllListHot(map);

    return list;
  }

  @Override
  public void insertKeyword(Map<String, Object> map) throws SQLException {
    searchKeyDao.insertKeyword(map);

  }

  @Override
  public void updateKeyword(Map<String, Object> map) throws SQLException {
    searchKeyDao.updateKeyword(map);

  }

  @Override
  public List<SearchKeyPojo> selectKeyword(Map<String, Object> map) throws SQLException {
    return searchKeyDao.selectKeyword(map);

  }
}
