package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ManufacturerSettleDao;
import com.tzmb2c.web.pojo.ManufacturerSettlePojo;
import com.tzmb2c.web.service.ManufacturerSettleService;

public class ManufacturerSettleServiceImpl implements ManufacturerSettleService {
  @Autowired
  private ManufacturerSettleDao manufacturerSettleDao;

  @Override
  public Long insertManufacturerSettle(ManufacturerSettlePojo manufacturerSettlePojo)
      throws SQLException {

    return manufacturerSettleDao.insertManufacturerSettle(manufacturerSettlePojo);
  }

  @Override
  public void delSettleById(Long id) throws SQLException {

    manufacturerSettleDao.delSettleById(id);
  }

  @Override
  public void delSettleByUserId(Long uid) throws SQLException {

    manufacturerSettleDao.delSettleByUserId(uid);
  }

  @Override
  public int updateSettleInfo(ManufacturerSettlePojo manufacturerSettlePojo) throws SQLException {

    return manufacturerSettleDao.updateSettleInfo(manufacturerSettlePojo);
  }

  @Override
  public List<ManufacturerSettlePojo> getManufacturerSettleList(Map<String, Object> map)
      throws SQLException {

    return manufacturerSettleDao.getManufacturerSettleList(map);
  }

  @Override
  public int getManufacturerSettleCount(Map<String, Object> map) throws SQLException {

    return manufacturerSettleDao.getManufacturerSettleCount(map);
  }

  @Override
  public List<ManufacturerSettlePojo> getManufacturerSettleByUserId(Map<String, Object> map)
      throws SQLException {

    return manufacturerSettleDao.getManufacturerSettleByUserId(map);
  }

  @Override
  public ManufacturerSettlePojo getManufacturerSettleById(Long id) throws SQLException {

    return manufacturerSettleDao.getManufacturerSettleById(id);
  }

}
