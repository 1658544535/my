package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ManufacturerSettleDao;
import com.tzmb2c.web.mapper.ManufacturerSettleMapper;
import com.tzmb2c.web.pojo.ManufacturerSettlePojo;

public class ManufacturerSettleDaoImpl implements ManufacturerSettleDao {

  @Autowired
  private ManufacturerSettleMapper manufacturerSettleMapper;

  @Override
  public Long insertManufacturerSettle(ManufacturerSettlePojo manufacturerSettlePojo)
      throws SQLException {

    return manufacturerSettleMapper.insertManufacturerSettle(manufacturerSettlePojo);
  }

  @Override
  public void delSettleById(Long id) throws SQLException {

    manufacturerSettleMapper.delSettleById(id);
  }

  @Override
  public void delSettleByUserId(Long uid) throws SQLException {

    manufacturerSettleMapper.delSettleByUserId(uid);
  }

  @Override
  public int updateSettleInfo(ManufacturerSettlePojo manufacturerSettlePojo) throws SQLException {

    return manufacturerSettleMapper.updateSettleInfo(manufacturerSettlePojo);
  }

  @Override
  public List<ManufacturerSettlePojo> getManufacturerSettleList(Map<String, Object> map)
      throws SQLException {

    return manufacturerSettleMapper.getManufacturerSettleList(map);
  }

  @Override
  public int getManufacturerSettleCount(Map<String, Object> map) throws SQLException {

    return manufacturerSettleMapper.getManufacturerSettleCount(map);
  }

  @Override
  public List<ManufacturerSettlePojo> getManufacturerSettleByUserId(Map<String, Object> map)
      throws SQLException {

    return manufacturerSettleMapper.getManufacturerSettleByUserId(map);
  }

  @Override
  public ManufacturerSettlePojo getManufacturerSettleById(Long id) throws SQLException {

    return manufacturerSettleMapper.getManufacturerSettleById(id);
  }

}
