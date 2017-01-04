package com.tzmb2c.web.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.dao.ManufacturerWithdrawDao;
import com.tzmb2c.web.pojo.ManufacturerWithdrawPojo;
import com.tzmb2c.web.service.ManufacturerWithdrawService;

public class ManufacturerWithdrawServiceImpl implements ManufacturerWithdrawService {
  @Autowired
  private ManufacturerWithdrawDao manufacturerWithdrawDao;

  @Override
  public int insertManufacturerWithdraw(ManufacturerWithdrawPojo manufacturerWithdrawPojo)
      throws SQLException {

    return manufacturerWithdrawDao.insertManufacturerWithdraw(manufacturerWithdrawPojo);
  }

  @Override
  public void delWithdrawById(Long id) throws SQLException {

    manufacturerWithdrawDao.delWithdrawById(id);
  }

  @Override
  public void delWithdrawByUserId(Long uid) throws SQLException {

    manufacturerWithdrawDao.delWithdrawByUserId(uid);
  }

  @Override
  public int updateWithdrawInfo(ManufacturerWithdrawPojo manufacturerWithdrawPojo)
      throws SQLException {

    return manufacturerWithdrawDao.updateWithdrawInfo(manufacturerWithdrawPojo);
  }

  @Override
  public List<ManufacturerWithdrawPojo> getManufacturerWithdrawList(Map<String, Object> map)
      throws SQLException {

    return manufacturerWithdrawDao.getManufacturerWithdrawList(map);
  }

  @Override
  public int getManufacturerWithdrawCount(Map<String, Object> map) throws SQLException {

    return manufacturerWithdrawDao.getManufacturerWithdrawCount(map);
  }

  @Override
  public List<ManufacturerWithdrawPojo> getManufacturerWithdrawByUserId(Map<String, Object> map)
      throws SQLException {

    return manufacturerWithdrawDao.getManufacturerWithdrawByUserId(map);
  }

  @Override
  public ManufacturerWithdrawPojo getManufacturerWithdrawById(Long id) throws SQLException {

    return manufacturerWithdrawDao.getManufacturerWithdrawById(id);
  }

}
