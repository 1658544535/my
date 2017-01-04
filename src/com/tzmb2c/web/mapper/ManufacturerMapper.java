package com.tzmb2c.web.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ManufacturerPojo;

public interface ManufacturerMapper {


  public List<ManufacturerPojo> getManufacturerAll() throws SQLException;

  public void updateManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException;

  public ManufacturerPojo getfindByIdManufacturer(Long id) throws SQLException;

  public ManufacturerPojo getfindByUserIdManufacturer(Long id) throws SQLException;

  public int manufacturerAllCount(Map<String, Object> map);

  public List<ManufacturerPojo> manufacturerAllList(Map<String, Object> map);

  void checkManufacturer(Long id) throws SQLException;

  void uncheckManufacturer(Long id) throws SQLException;

  void delManufacturer(Long id) throws SQLException;

  void deleteManufacturer(Long id) throws SQLException;

  public void insertManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException;

  // 前台
  public void updateManufacturerWeb(ManufacturerPojo manufacturerPojo) throws SQLException;

}
