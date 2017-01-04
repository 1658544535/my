package com.tzmb2c.web.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ManufacturerWithdrawDao;
import com.tzmb2c.web.mapper.ManufacturerWithdrawMapper;
import com.tzmb2c.web.pojo.ManufacturerWithdrawPojo;

public class ManufacturerWithdrawDaoImpl implements ManufacturerWithdrawDao {

  @Autowired
  private ManufacturerWithdrawMapper manufacturerWithdrawMapper;

  @Override
  public int insertManufacturerWithdraw(ManufacturerWithdrawPojo manufacturerWithdrawPojo)
      throws SQLException {

    return manufacturerWithdrawMapper.insertManufacturerWithdraw(manufacturerWithdrawPojo);
  }

  @Override
  public void delWithdrawById(Long id) throws SQLException {

    manufacturerWithdrawMapper.delWithdrawById(id);
  }

  @Override
  public void delWithdrawByUserId(Long uid) throws SQLException {

    manufacturerWithdrawMapper.delWithdrawByUserId(uid);
  }

  @Override
  public int updateWithdrawInfo(ManufacturerWithdrawPojo manufacturerWithdrawPojo)
      throws SQLException {

    return manufacturerWithdrawMapper.updateWithdrawInfo(manufacturerWithdrawPojo);
  }

  @Override
  public List<ManufacturerWithdrawPojo> getManufacturerWithdrawList(Map<String, Object> map)
      throws SQLException {

    return manufacturerWithdrawMapper.getManufacturerWithdrawList(map);
  }

  @Override
  public int getManufacturerWithdrawCount(Map<String, Object> map) throws SQLException {

    return manufacturerWithdrawMapper.getManufacturerWithdrawCount(map);
  }

  @Override
  public List<ManufacturerWithdrawPojo> getManufacturerWithdrawByUserId(Map<String, Object> map)
      throws SQLException {

    return manufacturerWithdrawMapper.getManufacturerWithdrawByUserId(map);
  }

  @Override
  public ManufacturerWithdrawPojo getManufacturerWithdrawById(Long id) throws SQLException {

    return manufacturerWithdrawMapper.getManufacturerWithdrawById(id);
  }

}
