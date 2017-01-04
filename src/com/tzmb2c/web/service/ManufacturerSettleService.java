package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ManufacturerSettlePojo;

public interface ManufacturerSettleService {

  public Long insertManufacturerSettle(ManufacturerSettlePojo manufacturerSettlePojo)
      throws SQLException;

  public void delSettleById(Long id) throws SQLException;

  public void delSettleByUserId(Long uid) throws SQLException;

  public int updateSettleInfo(ManufacturerSettlePojo manufacturerSettlePojo) throws SQLException;

  public List<ManufacturerSettlePojo> getManufacturerSettleList(Map<String, Object> map)
      throws SQLException;

  public int getManufacturerSettleCount(Map<String, Object> map) throws SQLException;

  public List<ManufacturerSettlePojo> getManufacturerSettleByUserId(Map<String, Object> map)
      throws SQLException;

  public ManufacturerSettlePojo getManufacturerSettleById(Long id) throws SQLException;

}
