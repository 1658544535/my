package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.SearchKeyDao;
import com.tzmb2c.web.mapper.SearchKeyMapper;
import com.tzmb2c.web.pojo.SearchKeyPojo;

public class SearchKeyDaoImpl implements SearchKeyDao {

  @Autowired
  private SearchKeyMapper searchKeyMapper;

  @Override
  public void insertKeyword(Map<String, Object> map) {
    // TODO Auto-generated method stub
    searchKeyMapper.insertKeyword(map);
  }

  @Override
  public List<SearchKeyPojo> selectKeyword(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return searchKeyMapper.selectKeyword(map);
  }

  @Override
  public void updateKeyword(Map<String, Object> map) {
    // TODO Auto-generated method stub
    searchKeyMapper.updateKeyword(map);
  }

  @Override
  public int searchKeyAllCount(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return searchKeyMapper.searchKeyAllCount(map);
  }

  @Override
  public List<SearchKeyPojo> searchKeyAllList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return searchKeyMapper.searchKeyAllList(map);
  }

  @Override
  public void delSearchKey(Long id) throws SQLException {

    searchKeyMapper.delSearchKey(id);
  }

  @Override
  public void updateSearchKey(SearchKeyPojo searchKeyPojo) throws SQLException {

    searchKeyMapper.updateSearchKey(searchKeyPojo);
  }

  @Override
  public SearchKeyPojo getfindByIdSearchKey(Long id) throws SQLException {
    return searchKeyMapper.getfindByIdSearchKey(id);

  }

  @Override
  public int searchKeyAllCountHot(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return searchKeyMapper.searchKeyAllCountHot(map);
  }

  @Override
  public List<SearchKeyPojo> searchKeyAllListHot(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return searchKeyMapper.searchKeyAllListHot(map);
  }
}
