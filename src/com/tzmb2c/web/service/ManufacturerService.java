package com.tzmb2c.web.service;

import java.sql.SQLException;
import java.util.List;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ManufacturerPojo;

public interface ManufacturerService {

  public List<ManufacturerPojo> manufacturerAllService() throws SQLException;

  public void updateManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException;

  public ManufacturerPojo getfindByIdManufacturer(Long id) throws SQLException;

  public int manufacturerAllCount(ManufacturerPojo manufacturerDaoPojo);

  List<ManufacturerPojo> manufacturerAllList(ManufacturerPojo ticketRulePojo, Pager page);

  void manufacturerChecks(String[] tids);

  void checkManufacturer(Long id) throws SQLException;

  void uncheckManufacturer(Long id) throws SQLException;

  ManufacturerPojo findManufacturerById(Long merId) throws SQLException;

  void delManufacturer(Long id) throws SQLException;

  void deleteManufacturer(Long id) throws SQLException;

  void insertManufacturer(ManufacturerPojo manufacturerPojo) throws SQLException;

  ManufacturerPojo findManufacturerByUserId(Long id) throws SQLException;

  // 前台
  public void updateManufacturerWeb(ManufacturerPojo manufacturerPojo) throws SQLException;

}
