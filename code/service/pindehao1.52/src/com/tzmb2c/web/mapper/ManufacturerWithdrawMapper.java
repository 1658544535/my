package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ManufacturerWithdrawPojo;

public interface ManufacturerWithdrawMapper {

  public int insertManufacturerWithdraw(ManufacturerWithdrawPojo manufacturerWithdrawPojo)
      throws SQLException;

  public void delWithdrawById(Long id) throws SQLException;

  public void delWithdrawByUserId(Long uid) throws SQLException;

  public int updateWithdrawInfo(ManufacturerWithdrawPojo manufacturerWithdrawPojo)
      throws SQLException;

  public List<ManufacturerWithdrawPojo> getManufacturerWithdrawList(Map<String, Object> map)
      throws SQLException;

  public int getManufacturerWithdrawCount(Map<String, Object> map) throws SQLException;

  public List<ManufacturerWithdrawPojo> getManufacturerWithdrawByUserId(Map<String, Object> map)
      throws SQLException;

  public ManufacturerWithdrawPojo getManufacturerWithdrawById(Long id) throws SQLException;

}
