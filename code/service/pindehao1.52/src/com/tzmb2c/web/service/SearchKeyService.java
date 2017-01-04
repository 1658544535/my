package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SearchKeyPojo;

public interface SearchKeyService {

  void insertKeyword(Map<String, Object> map) throws SQLException;

  void updateKeyword(Map<String, Object> map) throws SQLException;

  public List<SearchKeyPojo> selectKeyword(Map<String, Object> map) throws SQLException;

  public int searchKeyAllCount(SearchKeyPojo searchKeyDaoPojo);

  List<SearchKeyPojo> searchKeyAllList(SearchKeyPojo ticketRulePojo, Pager page);

  void delSearchKey(Long id) throws SQLException;

  void searchKeyDeleteId(String[] tids);

  void updateSearchKey(SearchKeyPojo searchKeyPojo) throws SQLException;

  SearchKeyPojo getfindByIdSearchKey(Long id) throws SQLException;

  // public int searchKeyAllCountHot(SearchKeyPojo searchKey, String os);

  public Object searchKeyAllListHot(SearchKeyPojo searchKey, Pager page, String os);


}
