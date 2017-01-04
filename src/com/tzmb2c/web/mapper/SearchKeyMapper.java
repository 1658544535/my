package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.SearchKeyPojo;

public interface SearchKeyMapper {

  public void insertKeyword(Map<String, Object> map);

  public void updateKeyword(Map<String, Object> map);

  public List<SearchKeyPojo> selectKeyword(Map<String, Object> map);

  public int searchKeyAllCount(Map<String, Object> map);

  public List<SearchKeyPojo> searchKeyAllList(Map<String, Object> map);

  void delSearchKey(Long id) throws SQLException;

  public void updateSearchKey(SearchKeyPojo searchKeyPojo) throws SQLException;

  public SearchKeyPojo getfindByIdSearchKey(Long id) throws SQLException;

  public int searchKeyAllCountHot(Map<String, Object> map);

  public List<SearchKeyPojo> searchKeyAllListHot(Map<String, Object> map);

}
