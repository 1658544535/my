package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.LabelPojo;

public interface LabelService {
  List<LabelPojo> findLabelList(Map<String, Object> map) throws SQLException;

  int findLabelCount(Map<String, Object> map) throws SQLException;

  void insertLabel(LabelPojo labelPojo) throws SQLException;

  void delLabel(Long id) throws SQLException;

  LabelPojo findLabelById(Long id) throws SQLException;

  void updateLabel(LabelPojo labelPojo) throws SQLException;

  void checkLabel(Long id) throws SQLException;

  void uncheckLabel(Long id) throws SQLException;
}
