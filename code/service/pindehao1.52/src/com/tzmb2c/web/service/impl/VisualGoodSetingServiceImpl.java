package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.VisualGoodSetingDao;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.service.VisualGoodSetingService;

public class VisualGoodSetingServiceImpl implements VisualGoodSetingService {
  @Autowired
  private VisualGoodSetingDao visualGoodSetingDao;

  @Override
  public List<SpecialProductPojo> findVisualGoodSetingList(Map<String, Object> map)
      throws SQLException {
    return visualGoodSetingDao.findVisualGoodSetingList(map);
  }

  @Override
  public int findVisualGoodSetingCount(Map<String, Object> map) throws SQLException {
    return visualGoodSetingDao.findVisualGoodSetingCount(map);
  }

  @Override
  public void setSpecialProduct(Long id) throws SQLException {
    visualGoodSetingDao.setSpecialProduct(id);
  }

  @Override
  public void unsetSpecialProduct(Long id) throws SQLException {
    visualGoodSetingDao.unsetSpecialProduct(id);
  }
}
