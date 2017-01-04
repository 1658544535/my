package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SearchKeyPojo;

public interface SearchKeyDao {

  void insertKeyword(Map<String, Object> map);

  void updateKeyword(Map<String, Object> map);

  List<SearchKeyPojo> selectKeyword(Map<String, Object> map);

  int searchKeyAllCount(Map<String, Object> map);

  List<SearchKeyPojo> searchKeyAllList(Map<String, Object> map);

  void delSearchKey(Long id) throws SQLException;

  void updateSearchKey(SearchKeyPojo searchKeyPojo) throws SQLException;

  SearchKeyPojo getfindByIdSearchKey(Long id) throws SQLException;

  int searchKeyAllCountHot(Map<String, Object> map);

  List<SearchKeyPojo> searchKeyAllListHot(Map<String, Object> map);



}
