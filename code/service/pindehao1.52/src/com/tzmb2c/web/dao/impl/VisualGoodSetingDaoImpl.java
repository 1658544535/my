package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.VisualGoodSetingDao;
import com.tzmb2c.web.mapper.VisualGoodSetingMapper;
import com.tzmb2c.web.pojo.SpecialProductPojo;

public class VisualGoodSetingDaoImpl implements VisualGoodSetingDao {
  @Autowired
  private VisualGoodSetingMapper visualGoodSetingMapper;

  @Override
  public List<SpecialProductPojo> findVisualGoodSetingList(Map<String, Object> map)
      throws SQLException {
    return visualGoodSetingMapper.findVisualGoodSetingList(map);
  }

  @Override
  public int findVisualGoodSetingCount(Map<String, Object> map) throws SQLException {
    return visualGoodSetingMapper.findVisualGoodSetingCount(map);
  }

  @Override
  public void setSpecialProduct(Long id) throws SQLException {
    visualGoodSetingMapper.setSpecialProduct(id);
  }

  @Override
  public void unsetSpecialProduct(Long id) throws SQLException {
    visualGoodSetingMapper.unsetSpecialProduct(id);
  }
}
