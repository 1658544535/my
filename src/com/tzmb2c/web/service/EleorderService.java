package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.EleorderPojo;

public interface EleorderService {
  List<EleorderPojo> findEleorderAll(Map<String, Object> map) throws SQLException;

  int EleorderAllCount(Map<String, Object> map) throws SQLException;

  void insertEleorder(Map<String, Object> map) throws SQLException;

  List<EleorderPojo> findEleorderByorderNo(Map<String, Object> map) throws SQLException;

  EleorderPojo findEleorderByorderNos(Map<String, Object> map) throws SQLException;

  public int sellSumNum(Long productId) throws SQLException;
}
