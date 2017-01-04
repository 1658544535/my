package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.LabelDao;
import com.tzmb2c.web.mapper.LabelMapper;
import com.tzmb2c.web.pojo.LabelPojo;

public class LabelDaoImpl implements LabelDao {
  @Autowired
  private LabelMapper labelMapper;


  @Override
  public List<LabelPojo> findLabelList(Map<String, Object> map) throws SQLException {
    return labelMapper.findLabelList(map);
  }

  @Override
  public int findLabelCount(Map<String, Object> map) throws SQLException {
    return labelMapper.findLabelCount(map);
  }

  @Override
  public void insertLabel(LabelPojo labelPojo) throws SQLException {
    labelMapper.insertLabel(labelPojo);
  }

  @Override
  public void delLabel(Long id) throws SQLException {
    labelMapper.delLabel(id);
  }

  @Override
  public LabelPojo findLabelById(Long id) throws SQLException {
    return labelMapper.findLabelById(id);
  }

  @Override
  public void updateLabel(LabelPojo labelPojo) throws SQLException {
    labelMapper.updateLabel(labelPojo);
  }

  @Override
  public void checkLabel(Long id) throws SQLException {
    labelMapper.checkLabel(id);
  }

  @Override
  public void uncheckLabel(Long id) throws SQLException {
    labelMapper.uncheckLabel(id);
  }
}
