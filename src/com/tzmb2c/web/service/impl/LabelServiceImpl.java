package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.LabelDao;
import com.tzmb2c.web.pojo.LabelPojo;
import com.tzmb2c.web.service.LabelService;

public class LabelServiceImpl implements LabelService {
  @Autowired
  private LabelDao labelDao;

  @Override
  public List<LabelPojo> findLabelList(Map<String, Object> map) throws SQLException {
    return labelDao.findLabelList(map);
  }

  @Override
  public int findLabelCount(Map<String, Object> map) throws SQLException {
    return labelDao.findLabelCount(map);
  }

  @Override
  public void insertLabel(LabelPojo labelPojo) throws SQLException {
    labelDao.insertLabel(labelPojo);
  }

  @Override
  public void delLabel(Long id) throws SQLException {
    labelDao.delLabel(id);
  }

  @Override
  public LabelPojo findLabelById(Long id) throws SQLException {
    return labelDao.findLabelById(id);
  }

  @Override
  public void updateLabel(LabelPojo labelPojo) throws SQLException {
    labelDao.updateLabel(labelPojo);
  }

  @Override
  public void checkLabel(Long id) throws SQLException {
    labelDao.checkLabel(id);
  }

  @Override
  public void uncheckLabel(Long id) throws SQLException {
    labelDao.uncheckLabel(id);
  }
}
