package com.tzmb2c.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tzmb2c.web.pojo.ManufacturerPojo;

public interface ManufacturerDao {

  List<ManufacturerPojo> getManufacturerAll() throws SQLException;

  void updateManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException;

  ManufacturerPojo getfindByIdManufacturer(Long id) throws SQLException;

  int manufacturerAllCount(Map<String, Object> map);

  List<ManufacturerPojo> manufacturerAllList(Map<String, Object> map);

  void checkManufacturer(Long id) throws SQLException;

  void uncheckManufacturer(Long id) throws SQLException;

  ManufacturerPojo findManufacturerById(Long id) throws SQLException;

  void delManufacturer(Long id) throws SQLException;

  void deleteManufacturer(Long id) throws SQLException;

  void insertManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException;

  ManufacturerPojo findManufacturerByUserId(Long id) throws SQLException;

  // 前台
  public void updateManufacturerWeb(ManufacturerPojo manufacturerPojo) throws SQLException;

}
